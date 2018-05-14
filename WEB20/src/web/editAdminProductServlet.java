package web;

import dmain.Category;
import dmain.Product;
import service.AdminProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/editAdminProduct")
public class editAdminProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        AdminProductService service = new AdminProductService();

        Product product = service.findAdminProductById(pid);

        List<Category> categoryList=service.findAllCategory();


        request.setAttribute("categoryList",categoryList);

        request.setAttribute("product",product);
        request.getRequestDispatcher("admin/product/edit.jsp").forward(request,response);

    }
}
