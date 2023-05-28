package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.ItemList;
import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemListFacade;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GenerateItemListForUser", value = "/generateitemlistforuser")
public class GenerateItemListForUser extends HttpServlet {
    private ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
    private ArrayList<Order> orderHistory;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        ArrayList<ItemList> entireItemList = new ArrayList<>();
        try {
            entireItemList = ItemListFacade.getItemList(orderId,connectionPool);
            session.setAttribute("itemList", entireItemList);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("WEB-INF/itemlist.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String statusUpdate = request.getParameter("offeraccepted");
        orderHistory = (ArrayList<Order>) session.getAttribute("history");
        try {
            OrderFacade.modifyStatus(orderId,statusUpdate,connectionPool);


        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        for (Order o: orderHistory) {
            if (o.getOrderId() == orderId) {
                o.setStatus(statusUpdate);
            }
        }
        request.getRequestDispatcher("WEB-INF/ordersForCustomer.jsp").forward(request,response);

    }
}
