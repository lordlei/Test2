package web;

import dmain.Category;
import dmain.Condition;
import dmain.Product;
import org.apache.commons.beanutils.BeanUtils;
import service.AdminProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/selectAdminProduct")
public class selectAdminProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Condition condition=new Condition();
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(condition,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        AdminProductService service=new AdminProductService();


        List<Category> categoryList=service.findAllCategory();
        request.setAttribute("categoryList",categoryList);

        request.setAttribute("condition",condition);


        List<Product> productList=service.selectAdminProduct(condition);
        request.setAttribute("productList",productList);
        request.getRequestDispatcher("admin/product/list.jsp").forward(request,response);

    }
}
