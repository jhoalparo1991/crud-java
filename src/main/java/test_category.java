import models.Category;
import models.Product;
import repository.CategoryRepositoryImpl;
import repository.ProductRepositoryImpl;

public class test_category {

    public static void main(String[] args) {

        test_category t = new test_category();
       /*
        t.insert(0,"Sport");
        t.insert(0,"Food");
        t.insert(0,"Clothing");
        */
       // t.delete(4L);
        //t.show_categories();
        t.get_one(1L);
    }


    public void show_categories(){
        System.out.println("*************** List categories *****************");
        CategoryRepositoryImpl repo = new CategoryRepositoryImpl();
        repo.findAll().forEach(System.out::println);
    }

    public void get_one(Long id){
        CategoryRepositoryImpl repo = new CategoryRepositoryImpl();
        System.out.println("************* get one by id *************");
        Category category = repo.getOne(id);
        System.out.println(category);
    }

    public void insert(Long id,String name){
        CategoryRepositoryImpl repo = new CategoryRepositoryImpl();
        Category category = new Category(id,name);
        repo.save(category);
    }

    public void delete(Long id){
        CategoryRepositoryImpl repo = new CategoryRepositoryImpl();
        repo.delete(id);
    }


}
