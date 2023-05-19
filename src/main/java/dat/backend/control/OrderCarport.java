package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OrderCarport", value = "/ordercarport")
public class OrderCarport extends HttpServlet {
    private ConnectionPool connectionPool = ApplicationStart.getConnectionPool();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double width = Double.parseDouble(request.getParameter("width"));
        double length = Double.parseDouble(request.getParameter("length"));
        String material = "";
        double price = 0;
        String status = "PENDING";
        int userId = Integer.parseInt(request.getParameter("userId"));
        try {
            OrderFacade.createOrder(length,width,price,material,status,userId,connectionPool);
            request.setAttribute("success", "ordren er blevet sendt til en medarbejder");
            request.getRequestDispatcher("orderCarport.jsp").forward(request, response);
        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }


    }
}
