import models.Product;
import repository.ProductRepositoryImpl;

public class test_product {

    public static void main(String[] args) {

        test_product t = new test_product();
        /*
        t.insert(0L,"Guayos nike r99",99.00,1L);
        t.insert(0L,"Zapatos Dama Sofia",80.00,2L);
        t.insert(0L,"Vestido 15 años",180.00,3L);
        */

        t.show();
    }

    public void show(){
        ProductRepositoryImpl pr = new ProductRepositoryImpl();
        System.out.println("************* list products *************");
        pr.findAll().forEach(System.out::println);
    }

    public void insert(Long id,String name,Double price,Long categoryId){
        ProductRepositoryImpl repo = new ProductRepositoryImpl();
        Product p1 = new Product(id,name,price,categoryId);
        repo.save(p1);
    }

    public void delete(Long id){
        ProductRepositoryImpl repo = new ProductRepositoryImpl();
        repo.delete(id);
    }

    public void get_one(Long id){
        ProductRepositoryImpl repo = new ProductRepositoryImpl();
        System.out.println("************* get one by id *************");
        Product product = repo.getOne(id);
        System.out.println(product);
    }
}
