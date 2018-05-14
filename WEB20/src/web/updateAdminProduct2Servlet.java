package web;

import dmain.Product;
import org.apache.commons.beanutils.BeanUtils;
import service.AdminProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@WebServlet("/updateAdminProduct2")
public class updateAdminProduct2Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        Product product=new Product();
        try {
            BeanUtils.populate(product,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        product.setPimage("products/1/c_0012.jpg");
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");

        product.setPdate( format.format(new Date()));
        product.setPflag(0);


        AdminProductService service = new AdminProductService();
        boolean b = service.updateAdminProduct(product);

        response.sendRedirect(request.getContextPath()+"/adminProductList");





    }
}
