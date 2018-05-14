package web;

import dmain.Category;
import service.AdminProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@WebServlet("/adminAddProductUI")
public class adminAddProductUIServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminProductService service = new AdminProductService();

        List<Category> categoryList=service.findAllCategory();
        request.setAttribute("categoryList",categoryList);

        request.getRequestDispatcher("admin/product/add.jsp").forward(request,response);

    }
}
