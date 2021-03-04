package delivery.UI.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// this class should let the manager choose between reading report or viewing db

public class ManagerChoice extends JFrame  {
    private JButton EmpDbBtn;
    private JPanel ManagerChoicePanel;
    private JButton viewUnaccomoatedReportButton;
    private JButton ViewRevbtn;

    public ManagerChoice(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(ManagerChoicePanel);
        this.pack();

        EmpDbBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                //ManagerDb lol = new ManagerDb();  // wtf does it want in here what argument in the brackets omg
                //lol.setVisible(true); // added this just to see if initializing was prob
                new ManagerDb("Manage Employee Data").setVisible(true);
            }


        });
        viewUnaccomoatedReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose(); // find why dispose isnt working
                ViewReport viewReport = new ViewReport("View report", "Manager");
                viewReport.setVisible(true);
                viewReport.setSize(400, 500);

            }
        });

        ViewRevbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                //ManagerDb lol = new ManagerDb();  // wtf does it want in here what argument in the brackets omg
                //lol.setVisible(true); // added this just to see if initializing was prob
                //new ManagerDb("Revenue Data").setVisible(true);
                ViewReport viewReport = new ViewReport("Revenue Data", "Manager");
                viewReport.setVisible(true);
                viewReport.setSize(400, 500);

            }


        });
    }
}
