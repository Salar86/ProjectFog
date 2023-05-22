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

@WebServlet(name = "ShowUsers", value = "/showusers")
public class ShowUsers extends HttpServlet {
    private ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
    private ArrayList<User> allUsers = new ArrayList<>();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            allUsers = UserFacade.showUsers(connectionPool);
            session.setAttribute("users", allUsers);
            request.getRequestDispatcher("WEB-INF/showAllUsersForAdmin.jsp").forward(request, response);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        try {
            UserFacade.deleteUser(userId,connectionPool);

        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        allUsers.removeIf(u -> userId == u.getUserId());
        request.getRequestDispatcher("WEB-INF/showAllUsersForAdmin.jsp").forward(request, response);


    }
}
