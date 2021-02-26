package delivery.technicalServices.persistence;

import delivery.domain.Driver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface GenericDAO<T> {



     void insert(T object);

     void update(T object);

     void delete(int dID);

     T getById(int pk);

     List<T> getAll();

}
