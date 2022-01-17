import models.Users;
import repository.UsersRepositoryImpl;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersTest {

    public static void main(String[] args) {

        String[] options = {"Get all","Find One","Insert","Edit","Delete","Salir"};
        UsersRepositoryImpl repository = new UsersRepositoryImpl();
        UsersTest ut = new UsersTest();
        while(true){

            int result = JOptionPane.showOptionDialog(null,"Seleccione una opcion","Registro de usuarios",
                    JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
            if(result == 0){
                List<Users> users = repository.findAll();
                JOptionPane.showMessageDialog(null,users);
            }else  if(result == 1){
                String data = JOptionPane.showInputDialog(null,"Ingrese el codigo");
                Users user = repository.getOne(Long.parseLong(data));
                JOptionPane.showMessageDialog(null,user);
            }else if(result == 2){
                ut.save(0L);
                JOptionPane.showMessageDialog(null,"Registro exitoso");
            }else if(result == 3){
                String id = JOptionPane.showInputDialog(null,"Ingrese el codigo");
                ut.save(Long.parseLong(id));
                JOptionPane.showMessageDialog(null,"Registro modificado con exito");
            }
            if(result == 5){
                System.exit(0);
            }
        }
    }


    public void show(){

    }
    public void getOne(Long id){
        UsersRepositoryImpl repository = new UsersRepositoryImpl();
        Users user = repository.getOne(id);
        System.out.println(user);
    }

    public void save(Long id){
        UsersRepositoryImpl repository = new UsersRepositoryImpl();


        String fullname = JOptionPane.showInputDialog(null,"Ingrese el nombre completo");
        String email = JOptionPane.showInputDialog(null,"Ingrese el email");
        String password = JOptionPane.showInputDialog(null,"Ingrese el password");

        Users user = new Users();
        user.setId(id);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(password);
        repository.save(user);
    }

    public void delete(Long id){
        UsersRepositoryImpl repository = new UsersRepositoryImpl();
        repository.delete(id);
    }

}
