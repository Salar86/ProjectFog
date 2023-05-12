package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AllUsers", value = "/AllUsers")
public class AllUsers extends HttpServlet {

    private ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
    private ArrayList<User> allUsers;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.allUsers = UserFacade.showUsers(connectionPool);HttpSession session = request.getSession();
            session.setAttribute("users", allUsers);
            request.getRequestDispatcher("WEB-INF/adminallusers.jsp").forward(request, response);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
