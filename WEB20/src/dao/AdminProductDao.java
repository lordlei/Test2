package dao;

import dmain.Category;
import dmain.Condition;
import dmain.Product;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminProductDao {
    public static List<Product> findAllProductDao() throws SQLException {
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from product";
        List<Product> query = qr.query(sql, new BeanListHandler<Product>(Product.class));
        return query;
    }

    public static List<Category> findAllCategoryDao() throws SQLException {
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from category";
        List<Category> query = qr.query(sql, new BeanListHandler<Category>(Category.class));
        return query;
    }

    public void addAdminProductDao(Product product) throws SQLException {
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="insert product values(?,?,?,?,?,?,?,?,?,?)";
        qr.update(sql,product.getPid(),product.getPname(),product.getMarket_price(),
                product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),
                product.getPdesc(),product.getPflag(),product.getCid());
    }

    public void deleteAdminProductDao(String pid) throws SQLException {
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="delete from product where pid=?";
        qr.update(sql,pid);

    }

    public Product findAdminProductByIdDao(String pid) throws SQLException {
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from product where pid=?";
        Product query = qr.query(sql, new BeanHandler<Product>(Product.class), pid);
        return query;
    }

    public boolean updateAdminProduct(Product product) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update product set pname=?,market_price=?,shop_price=?,pimage=?,pdate=?,is_hot=?,pdesc=?,pflag=?,cid=? where pid=?";
        int update = runner.update(sql, product.getPname(), product.getMarket_price(),
                product.getShop_price(), product.getPimage(), product.getPdate(), product.getIs_hot(),
                product.getPdesc(), product.getPflag(), product.getCid(), product.getPid());

        boolean istrue=false;
        if(update>0){
            istrue=true;
        }
        return istrue;
    }

    public List<Product> selectAdminProductDao(Condition condition) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from product where 1=1";
        List<Object> list =new ArrayList<Object>();
        if (condition.getPname()!=null&&!condition.getPname().trim().equals(""))
        {
            sql+=" and pname like ?";
            list.add("%"+condition.getPname().trim()+"%");
        }
        if (condition.getIs_hot()!=2)
        {
            sql+=" and is_hot=?";
            list.add(condition.getIs_hot());
        }
        if (condition.getCid()!=null&&!condition.getCid().trim().equals(""))
        {
            sql+=" and cid = ?";
            list.add(condition.getCid().trim());
        }

        List<Product> query = qr.query(sql, new BeanListHandler<Product>(Product.class),list.toArray());
        return query;


    }


    public static int findAllProductCountDao() throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select Count(*) from product";
        Long query =(Long)qr.query(sql, new ScalarHandler());
        return query.intValue();
    }

    public static List<Product> findAllProductByPageBeanDao(int index, int currentCount) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from product limit ?,?";
        List<Product> query = qr.query(sql, new BeanListHandler<Product>(Product.class), index, currentCount);
        return query;
    }
}
