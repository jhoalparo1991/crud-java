import models.Product;
import repository.ProductRepositoryImpl;

import java.util.List;

public class TestProducts {

    public static void main(String[] args) {

        ProductRepositoryImpl pr = new ProductRepositoryImpl();

        /*
        System.out.println("*************** Create product ***************");
        Product p1 = new Product(0L,"Camiseta Rust",55.00);
        pr.save(p1);
        // update
        System.out.println("*************** Update product ***************");
        Product p2 = new Product(2L,"Camiseta NodeJs",100.00);
        pr.save(p2);
        */
        //Delete product
        System.out.println("************* Delete by id *************");
        pr.delete(60L);
        //List of products
        System.out.println("************* list products *************");
        pr.findAll().forEach(System.out::println);
        System.out.println("************* get one by id *************");
        Product product = pr.getOne(25L);
        System.out.println(product);




    }
}
