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

@WebServlet(name = "ModifyOrdersByUser", value = "/modifyordersbyuser")
public class ModifyOrdersByUser extends HttpServlet {
    private ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
    private ArrayList<Order> orderHistory;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String statusUpdate = request.getParameter("offerdeclined");
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
        request.getRequestDispatcher("WEB-INF/ordersForCustomer.jsp").forward(request, response);

    }
}
