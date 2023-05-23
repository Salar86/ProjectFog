package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ShowOrderForAdmin", value = "/showordersforadmin")
public class ShowOrdersForAdmin extends HttpServlet {
    private ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
    private ArrayList<Order> allOrders = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            allOrders = OrderFacade.showOrders(connectionPool);
            session.setAttribute("orders", allOrders);
            request.getRequestDispatcher("WEB-INF/showAllOrdersForAdmin.jsp").forward(request, response);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
