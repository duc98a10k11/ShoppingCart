/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import IO.ProductIO;
import business.Cart;
import business.LineItem;
import business.Product;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productCode = request.getParameter("productCode");
        String quantityString = request.getParameter("quantity");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }

        int quantity = 1;
        try {
            quantity = Integer.parseInt(quantityString);
            if (quantity < 0) {
                quantity = 1;
            }
        } catch (NumberFormatException nfe) {
            quantity = 1;
        }
        String path= getServletContext().getRealPath("WEB-INF/products.txt");
        Product product= ProductIO.getProduct(productCode, path);

        LineItem lineItem = new LineItem();
        lineItem.setProduct(product);
        lineItem.setQuantity(quantity);
        if (quantity > 0) {
            cart.addItem(lineItem);
        } else if (quantity == 0) {
            cart.removeItem(lineItem);
        }

        session.setAttribute("cart", cart);
        String url = "/cart.jsp";
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
