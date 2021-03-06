package delivery.technicalServices.persistence;

import delivery.domain.Driver;
import delivery.domain.Employee;
import delivery.domain.Loader;
import delivery.domain.Picker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class EmployeeDAO extends ConnectionFactory {

    void insertEmp(Employee employee, String sql) {

        try (Connection New = this.connect(); PreparedStatement Pstmt = New.prepareStatement(sql)) {

            Pstmt.setString(1, employee.getName());
            Pstmt.setInt(2, employee.getPin());
            Pstmt.executeUpdate();

            closeConnection(New);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

     void updateEmp(Employee employee, String sql) {


        try (Connection up = this.connect();
             PreparedStatement Pstmt = up.prepareStatement(sql)) {

            // set the corresponding param

            Pstmt.setString(1, employee.getName());
            Pstmt.setInt(2, employee.getPin());
            Pstmt.setInt(3, employee.getId());
            // update
            Pstmt.executeUpdate();

            closeConnection(up);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void deleteEmp(int dID, String sql) {


        try (Connection del = this.connect();
             PreparedStatement Pstmt = del.prepareStatement(sql)) {

            // set the corresponding param
            Pstmt.setInt(1, dID);
            // execute the delete statement
            Pstmt.executeUpdate();

            closeConnection(del);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    Employee getByEmpId(int pk, String sqlStatement) {

        Employee employee = null;
        try (Connection One = this.connect();
             PreparedStatement pstmt = One.prepareStatement(sqlStatement)) {

            // set the value
            pstmt.setInt(1, pk);
            ResultSet rs = pstmt.executeQuery();

            employee = new Employee(
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getInt("PIN")
            );

            closeConnection(One);

            return employee;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employee;
    }

    List<Employee> getAllEmp(String sql) {

        List<Employee> employeeList = new ArrayList<>();

        try (Connection ALL = this.connect();
             Statement stmt = ALL.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                employeeList.add(new Employee(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getInt("PIN"))
                );

            }
            closeConnection(ALL);
            return employeeList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employeeList;
    }

    boolean validateEmployee(int pinEntered, String sqlStatement) {

        boolean doesExist = false;
        try (Connection One = this.connect();
             PreparedStatement pstmt = One.prepareStatement(sqlStatement)) {

            // set the value
            pstmt.setInt(1, pinEntered);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                doesExist = true;


            closeConnection(One);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return doesExist;
    }
}
