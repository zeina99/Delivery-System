package delivery.UI.swing;

import javax.swing.*;
import javax.swing.table.*;


public class ManagerDb extends JFrame {
    private JPanel MngrDbview;
    private JTable EmployeeTbl;
    private JButton Addbtn;
    private JButton Removebtn;
    private JButton SaveChangesbtn;
    //private TableColumn Employee;

    public ManagerDb(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MngrDbview);
        this.pack();

    }


}
