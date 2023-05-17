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

@WebServlet(name = "ShowOrdersForUser", value = "/ShowOrdersForUser")
public class ShowOrdersForUser extends HttpServlet {
    private ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
    private ArrayList<Order> allOrders;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            int userId = (int) request.getSession().getAttribute("userId");
            this.allOrders = OrderFacade.showOrdersForUser(userId, connectionPool);
            session.setAttribute("orders", allOrders);
            request.getRequestDispatcher("WEB-INF/adminshowusers.jsp").forward(request, response);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
