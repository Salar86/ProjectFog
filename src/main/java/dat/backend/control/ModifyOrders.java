package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ModifyOrders", value = "/modifyorders")
public class ModifyOrders extends HttpServlet {
    private ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
    private ArrayList<Order> allOrders = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        double price = Double.parseDouble(request.getParameter("priceupdate"));
        allOrders = (ArrayList<Order>) session.getAttribute("orders");
        try {
            OrderFacade.modifyPrice(orderId,price,connectionPool);

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String statusUpdate = request.getParameter("statusupdate");
        allOrders = (ArrayList<Order>) session.getAttribute("orders");
        try {
            OrderFacade.modifyStatus(orderId,statusUpdate,connectionPool);

        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        for (Order o: allOrders) {
            if (o.getOrderId() == orderId) {
                o.setStatus(statusUpdate);
            }
        }
        request.getRequestDispatcher("WEB-INF/showAllOrdersForAdmin.jsp").forward(request, response);

    }
}
