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
        int orderId = Integer.parseInt(request.getParameter("orderId")); // Gets the orderId from the jsp-site showAllOrdersForAdmin
        double width = Double.parseDouble(request.getParameter("width")), length = Double.parseDouble(request.getParameter("length")), price = 0; // Gets length and width from jsp-site.
        String status = "TILBUD AFGIVET";
        ArrayList<Order> allOrders = (ArrayList<Order>) session.getAttribute("orders"); // Gets the full orderlist, which is first set in the showordersforadmin-servlet.

        try {
            for (ItemList itemList: cal.calculateCarport(orderId, width,length)) { // Calls the Calculater-class and returns the needed products.
                ItemListFacade.createItemList(itemList, connectionPool); // Writes the itemlist to the database.
            }
            price = ItemListFacade.getPrice(orderId,connectionPool); // Gets the total price for the itemlist from the database.
            OrderFacade.modifyPrice(orderId, price, connectionPool); // Updates the price in the database
            OrderFacade.modifyStatus(orderId, status, connectionPool); // Updates the status in the database.
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        for (Order o: allOrders) { // Sets the price and status for the orderId in the arraylist to be displayed on the jsp-site.
            if (o.getOrderId() == orderId) {
                o.setPrice(price);
                o.setStatus(status);
            }
        }
        request.getRequestDispatcher("WEB-INF/showAllOrdersForAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*HttpSession session = request.getSession();
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
        request.getRequestDispatcher("WEB-INF/showAllOrdersForAdmin.jsp").forward(request, response);*/
    }
}
