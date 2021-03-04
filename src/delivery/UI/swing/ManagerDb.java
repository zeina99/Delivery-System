package delivery.UI.swing;

import javax.swing.*;
import javax.swing.table.*;
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
    private JButton SaveChangesbtn;
    private JButton UpdateFields;
    //private TableColumn Employee;

    public ManagerDb(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MngrDbview);
        this.pack();


        // Data to be displayed in the JTable
        String[][] data = {
            { "Kundan Kumar Jha", "4031", "CSE" },
            { "Anand Jha", "6014", "IT" }
        };

        // Column Names
        String[] columnNames = { "Name", "Roll Number", "Department" };

        // i dont get the issue here
        // Initializing the JTable
        EmployeeTbl(data, columnNames);// why is this not working
        EmployeeTbl.setBounds(30, 40, 200, 300);

// gotta fix above issue to add shit to buttons
        Addbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        Removebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        SaveChangesbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        UpdateFields.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }


}
