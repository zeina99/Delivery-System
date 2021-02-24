package delivery.technicalServices.persistence;

import delivery.domain.Picker;

import java.util.List;

public class PickerDAO implements GenericDAO<Picker> {
    @Override
    public void insert(Picker object) {

    }

    @Override
    public void update(Picker object) {

    }

    @Override
    public void delete(Picker object) {

    }

    @Override
    public Picker getById(int pk) {
        return null;
    }

    @Override
    public List<Picker> getAll() {
        return null;
    }

    public static void main(String[] args) {

//        DriverDAO tst = new DriverDAO();
//        String sql = "SELECT ID, Name FROM Picker";
//        GenericDAO.ViewAll(tst, sql);
    }
}
