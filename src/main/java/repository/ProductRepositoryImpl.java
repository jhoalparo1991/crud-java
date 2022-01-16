package repository;

import models.Product;
import util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements repository<Product> {


    private Connection getConnection() throws SQLException {
        return DbConnection.getInstance();
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (
                Statement st = getConnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM product");
                ){
                while(rs.next()){
                    Product p = new Product(rs.getLong("id"),rs.getString("name"),rs.getDouble("price"));
                    products.add(p);
                }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return products;
    }

    @Override
    public Product getOne(Long id) {
        Product product =null;
        try (
                PreparedStatement st = getConnection().prepareStatement("SELECT * FROM product WHERE id=?")
        ){
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                product = new Product(rs.getLong("id"),rs.getString("name"),rs.getDouble("price"));
            }
        }catch (SQLException e){
            System.out.println("e = " + e.getMessage());
        }
        return product;
    }

    @Override
    public void save(Product product) {
        String sql = "";
        try {
            if(product.getId() > 0 && product.getId() != null){
                sql = "UPDATE product SET name=?,price=? WHERE id=?";
            }else{
                sql = "INSERT INTO product(name,price) values(?,?)";
            }

            PreparedStatement ps = getConnection().prepareStatement(sql);
            if(product.getId() > 0){
                ps.setLong(3,product.getId());
            }
            ps.setString(1,product.getName());
            ps.setDouble(2,product.getPrice());
            int result = ps.executeUpdate();
            if(result != 0){
                System.out.println("Registro guardado con exito");
            }else{
                System.out.println("Hubo un problema al guardar");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (
                PreparedStatement st = getConnection().prepareStatement("DELETE FROM product WHERE id=?")
        ){
            st.setLong(1,id);
            int result = st.executeUpdate();
            if(result != 0){
                System.out.println("Registro eliminado con exito");
            }
        }catch (SQLException e){
            System.out.println("e = " + e.getMessage());
        }
    }
}
