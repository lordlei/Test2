package web;

import dmain.PageBean;
import dmain.Product;
import service.AdminProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ProductList")
public class ProductListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminProductService service = new AdminProductService();
//       模拟当前页数
        String currentPageStr = request.getParameter("currentPage");
            int currentPage;
        if (currentPageStr == null) {
            currentPage=1;
        } else {
            currentPage = Integer.parseInt(currentPageStr);
        }
//       模拟当前页数的个数
        int currentCount = 6;

        PageBean<Product> pageBean = null;
        try {
            pageBean = service.findPageBean(currentPage, currentCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("pageBean", pageBean);

        request.getRequestDispatcher("product_list.jsp").forward(request, response);

    }
}
