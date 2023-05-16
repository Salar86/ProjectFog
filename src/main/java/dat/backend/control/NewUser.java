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

@WebServlet(name = "NewUser", value = "/newuser")
public class NewUser extends HttpServlet {
    private ConnectionPool connectionPool = ApplicationStart.getConnectionPool();

    /*public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String role = request.getParameter("role");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phonenumber = request.getParameter("phonenumber");
        String confirmPassword = request.getParameter("confirmpassword");
        HttpSession session;
        if(!password.equals(confirmPassword)) {
            request.setAttribute("errormessage", "your passwords do not match");
            request.getRequestDispatcher("error.jsp").forward(request, response);

        } else {
            try {
                User user = UserFacade.createUser(fullname, email, password, phonenumber, connectionPool);
                session = request.getSession();
                session.setAttribute("user", user); // adding user object to session scope
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } catch (DatabaseException e) {
                request.setAttribute("errormessage", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }


    }

