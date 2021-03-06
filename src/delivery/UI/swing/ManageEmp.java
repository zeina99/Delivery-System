package delivery.UI.swing;

import delivery.domain.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

// This class should Show the table holding the emplyee db info, and the add button should
// add a row, remove button should remove chosen row, etc.


public class ManageEmp extends JFrame {
    private JPanel MngrDbview;
    private JScrollPane Scrollpane;
    private JTable EmployeeTbl;
    private JButton Addbtn;

    private JButton deleteBtn;
    private JButton UpdateFields;
    private JComboBox employeeType;
    private SystemController systemController;
    private DefaultTableModel tableModel = new DefaultTableModel();
    //private TableColumn Employee;

    public ManageEmp(String title, SystemController systemController) {
        super(title);
        this.systemController = systemController;
        this.setSize(400, 500);
//        this.setBounds();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MngrDbview);
        this.pack();


        // columns
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("PIN");


        EmployeeTbl.setModel(tableModel);

        EmployeeTbl.setBounds(30, 40, 200, 300);

        // IMPORTANT: Table has to have Driver as first option in dropdown
        // fill table with data
        fillTableWithData(employeeType.getSelectedItem().toString());


        employeeType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {

                resetTable();

                fillTableWithData(employeeType.getSelectedItem().toString());

            }
        });



        Addbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                insertEmp();

                fillTableWithData(employeeType.getSelectedItem().toString());
            }
        });


        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

               deleteEmp();
            }
        });

        UpdateFields.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateEmp();
            }
        });


    }



    private void resetTable() {
        tableModel.setRowCount(0);
    }
    private List<? extends Employee> fillTableWithData(String employeeType) {
        List<? extends Employee> employeeList;
        switch (employeeType){
            case "Driver":
                 employeeList =  systemController.getAllDrivers();
                 break;
            case "Picker":
                employeeList = systemController.getAllPickers();
                break;
            case "Loader":
                employeeList = systemController.getAllLoaders();
                break;
            case "Manager":
                employeeList = systemController.getAllManagers();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + employeeType);
        }

        return employeeList;
    }
//    private void fillTableWithData(List<? extends Employee> employeeList) {
//
//        for (int i = 0; i < employeeList.size(); i++) {
//            tableModel.insertRow(i, new Object[]{employeeList.get(i).getId(), employeeList.get(i).getName(), employeeList.get(i).getPin()});
//        }
//
//    }

    private void insertEmp() {
        JTextField name = new JTextField();
        JTextField pin = new JTextField();

        Object[] insertFields = {

                "Enter a Name: ", name,
                "Enter a PIN: ", pin
        };

        int input = JOptionPane.showConfirmDialog(null, insertFields, "Enter details: ", JOptionPane.OK_CANCEL_OPTION);

        if (input == JOptionPane.OK_OPTION) {

            if (name.getText().equals("") || pin.getText().equals(""))
                JOptionPane.showMessageDialog(null, "You are missing fields", "Error", JOptionPane.ERROR_MESSAGE);


            switch (employeeType.getSelectedItem().toString()) {
                case "Driver":
                    systemController.manageEmp("insert", new Driver(name.getText(), Integer.parseInt(pin.getText())));
                    break;
                case "Loader":
                    systemController.manageEmp("insert", new Loader(name.getText(), Integer.parseInt(pin.getText())));
                    break;
                case "Manager":
                    systemController.manageEmp("insert", new Manager(name.getText(), Integer.parseInt(pin.getText())));
                    break;
                case "Picker":
                    systemController.manageEmp("insert", new Picker(name.getText(), Integer.parseInt(pin.getText())));
                    break;
            }

        }
    }

    private void updateEmp() {
        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField pin = new JTextField();

        Object[] updateFields = {
                "Enter Employee ID: ", id,
                "Enter a Name: ", name,
                "Enter a PIN: ", pin
        };

        int input = JOptionPane.showConfirmDialog(null, updateFields, "Enter details: ", JOptionPane.OK_CANCEL_OPTION);

        if (input == JOptionPane.OK_OPTION) {

            if (name.getText().equals("") || pin.getText().equals("") || id.getText().equals(""))
                JOptionPane.showMessageDialog(null, "You are missing fields", "Error", JOptionPane.ERROR_MESSAGE);

            switch (employeeType.getSelectedItem().toString()) {
                case "Driver":
                    systemController.manageEmp("update", new Driver(Integer.parseInt(id.getText()), name.getText(), Integer.parseInt(pin.getText())));
                    break;
                case "Loader":
                    systemController.manageEmp("update", new Loader(Integer.parseInt(id.getText()), name.getText(), Integer.parseInt(pin.getText())));
                    break;
                case "Manager":
                    systemController.manageEmp("update", new Manager(Integer.parseInt(id.getText()), name.getText(), Integer.parseInt(pin.getText())));
                    break;
                case "Picker":
                    systemController.manageEmp("update", new Picker(Integer.parseInt(id.getText()), name.getText(), Integer.parseInt(pin.getText())));
                    break;
            }

        }
    }

    private void deleteEmp() {
        JTextField id = new JTextField();

        Object[] deleteFields = {
                "Enter Employee ID: ", id,

        };

        int input = JOptionPane.showConfirmDialog(null, deleteFields, "Enter details: ", JOptionPane.OK_CANCEL_OPTION);

        if (input == JOptionPane.OK_OPTION) {

            if (id.getText().equals(""))
                JOptionPane.showMessageDialog(null, "You are missing fields", "Error", JOptionPane.ERROR_MESSAGE);


            systemController.deleteEmp(Integer.parseInt(id.getText()), employeeType.getSelectedItem().toString());

        }

    }



    // TODO: needs refactoring
//    private void manageEmployee(String actionToDo) {
//        JTextField id = new JTextField();
//        JTextField name = new JTextField();
//        JTextField pin = new JTextField();
//
//        Object[] insertFields = {
//
//                "Enter a Name: ", name,
//                "Enter a PIN: ", pin
//        };
//        Object[] updateFields = {
//                "Enter Employee ID: ", id,
//                "Enter a Name: ", name,
//                "Enter a PIN: ", pin
//        };
//
//        Object[] deleteFields = {
//                "Enter Employee ID: ", id,
//        };
//        int input;
//        switch (actionToDo) {
//
//            case "insert":
//                input = JOptionPane.showConfirmDialog(null, insertFields, "Enter details: ", JOptionPane.OK_CANCEL_OPTION);
//
//                if (name.getText().equals("") || pin.getText().equals(""))
//                    JOptionPane.showMessageDialog(null, "You are missing fields", "Error", JOptionPane.ERROR_MESSAGE);
//
//                break;
//
//            case "update":
//                input = JOptionPane.showConfirmDialog(null, updateFields, "Enter details: ", JOptionPane.OK_CANCEL_OPTION);
//
//                if (name.getText().equals("") || pin.getText().equals("") || id.getText().equals("")) {
//                    JOptionPane.showMessageDialog(null, "You are missing fields", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//
//                break;
//
//            case "delete":
//                input = JOptionPane.showConfirmDialog(null, deleteFields, "Enter details: ", JOptionPane.OK_CANCEL_OPTION);
//
//                if (id.getText().equals("")) {
//                    JOptionPane.showMessageDialog(null, "You are missing fields", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + actionToDo);
//        }
//
//
//        System.out.println("Name: " + name.getText() + "PIN: " + pin.getText());
//
//
//        if (input == JOptionPane.OK_OPTION) {
//            //TODO: add methods here
//
//
//            //systemController.manageEmp(actionToDo, employeeType.getSelectedItem().toString(), Integer.parseInt(id.getText()), name.getText(), Integer.parseInt(pin.getText()));
//
//
//        }

    }



