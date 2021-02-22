package delivery.technicalServices.persistence;

import java.util.List;

public interface GenericDAO<T> {

     void insert(T object);
     void update(T object);
     void delete(T object);
     T getById(int pk);
     List<T> getAll();


}
