package delivery.UI.swing;

import delivery.domain.Employee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This class should Show the table holding the emplyee db info, and the add button should
// add a row, remove button should remove chosen row, etc.


public class ManagerDb extends JFrame {
    private JPanel MngrDbview;
    private JScrollPane Scrollpane;
    private JTable EmployeeTbl;
    private JButton Addbtn;
    private JButton Removebtn;
    private JButton deleteBtn;
    private JButton UpdateFields;
    private JComboBox employeeType;
    //private TableColumn Employee;

    public ManagerDb(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MngrDbview);
        this.pack();


        // Data to be displayed in the JTable
        String[][] data = {
                {"Kundan Kumar Jha", "4031", "CSE"},
                {"Anand Jha", "6014", "IT"}
        };

        // Column Names
        String[] columnNames = {"Name", "Roll Number", "Department"};

        // i dont get the issue here
        // Initializing the JTable
        EmployeeTbl = new JTable(data, columnNames); // why is this not working


        EmployeeTbl.setBounds(30, 40, 200, 300);

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
