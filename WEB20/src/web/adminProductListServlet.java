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

@WebServlet("/adminProductList")
public class adminProductListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminProductService adminProductService = new AdminProductService();
        List<Product> productList=adminProductService.findAllProduct();


        List<Category> categoryList=adminProductService.findAllCategory();
        request.setAttribute("categoryList",categoryList);

        request.setAttribute("productList",productList);
        request.getRequestDispatcher("admin/product/list.jsp").forward(request,response);
    }
}
