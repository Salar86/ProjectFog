package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Calculations;
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

@WebServlet(name = "GenerateItemList", value = "/generateitemlist")
public class GenerateItemList extends HttpServlet {
    private ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Calculations cal = new Calculations();
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        double width = Double.parseDouble(request.getParameter("width"));
        double length = Double.parseDouble(request.getParameter("length"));
        double price = 0;
        String status = "TILBUD AFGIVET";
        ArrayList<Order> allOrders = (ArrayList<Order>) session.getAttribute("orders");
        try {
            for (ItemList itemList: cal.calculateCarport(orderId, width,length)) {
                ItemListFacade.createItemList(itemList, connectionPool);
            }
            price = ItemListFacade.getPrice(orderId,connectionPool);
            OrderFacade.modifyPrice(orderId, price, connectionPool);
            OrderFacade.modifyStatus(orderId, status, connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        for (Order o: allOrders) {
            if (o.getOrderId() == orderId) {
                o.setPrice(price);
                o.setStatus(status);
            }
        }
        request.getRequestDispatcher("WEB-INF/showAllOrdersForAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        double price = 0;
        ArrayList<Order> allOrders = (ArrayList<Order>) session.getAttribute("orders");
        try {
           price = ItemListFacade.getPrice(orderId,connectionPool);
            OrderFacade.modifyPrice(orderId, price, connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        for (Order o: allOrders) {
            if (o.getOrderId() == orderId) {
                o.setPrice(price);
            }
        }
        request.getRequestDispatcher("WEB-INF/showAllOrdersForAdmin.jsp").forward(request, response);
    }
}
