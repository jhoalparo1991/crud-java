package repository;

import java.util.List;

public interface repository<T> {

    //List all
    List<T> findAll();

    //Get one element
    T getOne(Long id);

    //Save data
    void save(T t);

    //Remove data
    void delete(Long id);
}
