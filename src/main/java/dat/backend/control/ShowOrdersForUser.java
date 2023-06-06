package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ShowOrdersForUser", value = "/showordersforuser")
public class ShowOrdersForUser extends HttpServlet {
    private ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
    private ArrayList<Order> orderHistory;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //User user = (User) session.getAttribute("user"); KAN LAVES  OM TIL NEDENSTÅENDE SÅ MAN IKKE SKAL BRUGE USER KLASSEN
        int userId = ((User) session.getAttribute("user")).getUserId();
        System.out.println("USER ID " +  userId);
        try {
            orderHistory = OrderFacade.showOrdersForUser(userId, connectionPool);
            session.setAttribute("history", orderHistory);
            request.getRequestDispatcher("WEB-INF/ordersForCustomer.jsp").forward(request, response);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
