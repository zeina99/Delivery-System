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

     void delete(T object);

     T getById(int pk);

     List<T> getAll();

}

//     public static List<Driver> ViewAll(DriverDAO dr, String sql) {
//
//
//          try (Connection ALL = Connect.cnct();
//               Statement stmt  = ALL.createStatement();
//               ResultSet rs    = stmt.executeQuery(sql)){
//               // loop through the result set
//               while (rs.next()) {
//                    System.out.println(rs.getInt("id") +  "\t" +
//                            rs.getString("name") + "\t");
//               }
//
//          } catch (SQLException e) {
//               System.out.println(e.getMessage());
//          }
//
//          return null;
//     }


//            Driver d = new Driver(ID, Name, PIN);
//            d.getId();
//            d.getName();
//            d.getPin();

///List<Driver> testing = new ArrayList<>();