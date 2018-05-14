package service;

import dao.AdminProductDao;
import dmain.Category;
import dmain.Condition;
import dmain.PageBean;
import dmain.Product;

import java.sql.SQLException;
import java.util.List;

public class AdminProductService {

    public List<Product> findAllProduct() {
        List<Product> productList = null;
        try {
            productList = AdminProductDao.findAllProductDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Category> findAllCategory() {

        List<Category> AllCategoryDao = null;
        try {
            AllCategoryDao = AdminProductDao.findAllCategoryDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return AllCategoryDao;

    }

    public void addAdminProduct(Product product) {

        AdminProductDao dao = new AdminProductDao();
        try {
            dao.addAdminProductDao(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void deleteAdminProduct(String pid) {
        AdminProductDao dao = new AdminProductDao();
        try {
            dao.deleteAdminProductDao(pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Product findAdminProductById(String pid) {
        AdminProductDao dao = new AdminProductDao();
        Product product = null;
        try {
            product = dao.findAdminProductByIdDao(pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;

    }

    public boolean updateAdminProduct(Product product) {
        AdminProductDao dao = new AdminProductDao();
        boolean b=false;
        try {
            b = dao.updateAdminProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;

    }


    public List<Product> selectAdminProduct(Condition condition) {
        AdminProductDao dao=new AdminProductDao();
        List<Product> productList= null;
        try {
            productList = dao.selectAdminProductDao(condition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public PageBean<Product> findPageBean(int currentPage, int currentCount) throws SQLException {
        PageBean pageBean = new PageBean();


        pageBean.setCurrentPage(currentPage);

        pageBean.setCurrentCount(currentCount);

        int totalCount=AdminProductDao.findAllProductCountDao();
        pageBean.setTotalCount(totalCount);

        int totalPage= (int)Math.ceil(1.0*totalCount/currentCount);

        pageBean.setTotalPage(totalPage);

        int index=(currentPage-1)*(currentCount);
        List<Product> productList=AdminProductDao.findAllProductByPageBeanDao(index,currentCount);
        pageBean.setProductList(productList);

        return pageBean;


    }
}
