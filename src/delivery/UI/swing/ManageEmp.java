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
    private JButton Removebtn;
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


        // Data to be displayed in the JTable
//        String[][] data = {
//                {"Kundan Kumar Jha", "4031", "CSE"},
//                {"Anand Jha", "6014", "IT"}
//        };

        // Column Names
        //String[] columnNames = {"Name", "Roll Number", "Department"};

        // i dont get the issue here
        // Initializing the JTable
        //String[] columnNames = {"ID", "Name", "PIN"};

        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("PIN");

        //EmployeeTbl = new JTable(); // why is this not working
        EmployeeTbl.setModel(tableModel);

        EmployeeTbl.setBounds(30, 40, 200, 300);

        // IMPORTANT: Table has to have Driver as first option
        // fill table with data
        List<Driver> driverList = systemController.getAllDrivers();
        fillTableWithData(driverList);

        employeeType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (employeeType.getSelectedItem().equals("Driver")) {

                    // clear table data
                    resetTable();

                    // fill table with data
                    List<Driver> driverList = systemController.getAllDrivers();
                    fillTableWithData(driverList);
                } else if (employeeType.getSelectedItem().equals("Loader")) {

                    resetTable();

                    List<Loader> loaderList = systemController.getAllLoaders();
                    fillTableWithData(loaderList);
                } else if (employeeType.getSelectedItem().equals("Picker")) {

                    resetTable();

                    List<Picker> pickerList = systemController.getAllPickers();
                    fillTableWithData(pickerList);
                } else if (employeeType.getSelectedItem().equals("Manager")) {

                    resetTable();

                    List<Manager> managerList = systemController.getAllManagers();
                    fillTableWithData(managerList);
                }
            }
        });


        // gotta fix above issue to add shit to buttons
        Addbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                insertEmployee();

            }
        });

        Removebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {


            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        UpdateFields.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateEmployee();
            }
        });


    }

    private void resetTable() {
        tableModel.setRowCount(0);
    }

    private void fillTableWithData(List<? extends Employee> employeeList) {

        for (int i = 0; i < employeeList.size(); i++) {
            tableModel.insertRow(i, new Object[]{employeeList.get(i).getId(), employeeList.get(i).getName(), employeeList.get(i).getPin()});
        }

    }

    private void insertEmployee() {

        JTextField name = new JTextField();
        JTextField pin = new JTextField();

        Object[] fields = {
                "Enter a Name: ", name,
                "Enter a PIN: ", pin
        };

        int input = JOptionPane.showConfirmDialog(null, fields, "Enter details: ", JOptionPane.OK_CANCEL_OPTION);
        System.out.println("Name: " + name.getText() + "PIN: " + pin.getText());

        if (input == JOptionPane.OK_OPTION) {
            //TODO: add methods here
            switch (employeeType.getSelectedItem().toString()) {

                case "Driver":
                    System.out.println("Insert Driver to db");
                    break;

                case "Loader":
                    System.out.println("Insert Loader to db");
                    break;

                case "Picker":
                    System.out.println("Insert Picker to db");
                    break;

                case "Manager":
                    System.out.println("Insert Manager to db");
                    break;
            }
        }

    }

    private void updateEmployee() {

        JTextField name = new JTextField();
        JTextField pin = new JTextField();

        Object[] fields = {
                "Enter a Name: ", name,
                "Enter a PIN: ", pin
        };

        int input = JOptionPane.showConfirmDialog(null, fields, "Enter details: ", JOptionPane.OK_CANCEL_OPTION);
        System.out.println("Name: " + name.getText() + "PIN: " + pin.getText());

        if (input == JOptionPane.OK_OPTION) {

            switch (employeeType.getSelectedItem().toString()) {
                //TODO: add methods here
                case "Driver":
                    System.out.println("Update Driver to db");
                    break;

                case "Loader":
                    System.out.println("Update Loader to db");
                    break;

                case "Picker":
                    System.out.println("Update Picker to db");
                    break;

                case "Manager":
                    System.out.println("Update Manager to db");
                    break;
            }
        }

    }


}
