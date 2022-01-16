package repository;

import models.Category;
import util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements repository<Category> {

    private Connection getConnection() throws SQLException {
        return DbConnection.getInstance();
    }


    @Override
    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        try(
                Statement st = getConnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM category")
        ) {
            while (rs.next()){
                Category category = new Category(rs.getLong("id"),rs.getString("name"));
                categoryList.add(category);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return categoryList;
    }

    @Override
    public Category getOne(Long id) {
        Category category =  null;
        try(
                PreparedStatement pst = getConnection().prepareStatement("SELECT * FROM category WHERE id=?")
        ) {
            pst.setLong(1,id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                category = new Category(rs.getLong("id"),rs.getString("name"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return category;
    }

    @Override
    public void save(Category category) {
        String sql = "";
        try {
            if(category.getId() > 0){
                sql = "UPDATE category SET name=? WHERE id=?";
            }else{
                sql = "INSERT INTO category(name) VALUES(?)";
            }

            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1,category.getName());

            if(category.getId() > 0){
                ps.setLong(2,category.getId());
            }

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
                PreparedStatement st = getConnection().prepareStatement("DELETE FROM category WHERE id=?")
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
