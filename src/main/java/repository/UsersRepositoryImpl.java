package repository;

import models.Users;
import util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryImpl implements repository<Users> {

    private Connection getConnection() throws SQLException {
        return DbConnection.getInstance();
    }

    @Override
    public List<Users> findAll() {
        List<Users> userList = new ArrayList<>();
        try (
                Statement st = getConnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM users");
        ){
            while(rs.next()){
                Users users = new Users(
                        rs.getLong("id"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("password"));
                userList.add(users);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return userList;
    }

    @Override
    public Users getOne(Long id) {
        Users user =null;
        try (
                PreparedStatement st =
                        getConnection().prepareStatement("SELECT * FROM users WHERE id=?")
        ){
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                user = new Users(
                        rs.getLong("id"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("password"));
            }
        }catch (SQLException e){
            System.out.println("e = " + e.getMessage());
        }
        return user;
    }

    @Override
    public void save(Users user) {
        String sql = "";
        try {
            if(user.getId() > 0 && user.getId() != null){
                sql = "UPDATE users SET fullname=?, email=?,password=? WHERE id=?";
            }else{
                sql = "INSERT INTO users(fullname,email,password) values(?,?,?)";
            }

            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1,user.getFullname());
            ps.setString(2,user.getEmail());
            ps.setString(3,user.getPassword());
            if(user.getId() > 0){
                ps.setLong(4,user.getId());
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
                PreparedStatement st = getConnection().prepareStatement("DELETE FROM users WHERE id=?")
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
