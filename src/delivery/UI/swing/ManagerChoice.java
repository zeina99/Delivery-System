package delivery.UI.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// this class should let the manager choose between reading report or viewing db

public class ManagerChoice extends JFrame  {
    private JButton EmpDbBtn;
    private JPanel ManagerChoicePanel;
    private JButton ViewReportbtn;
    private JButton ViewRevbtn;

    public ManagerChoice(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(ManagerChoicePanel);
        this.pack();
    }

    public ManagerChoice() {
        EmpDbBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                //ManagerDb lol = new ManagerDb();  // wtf does it want in here what argument in the brackets omg
                //lol.setVisible(true); // added this just to see if initializing was prob
                new ManagerDb("Manage Employee Data").setVisible(true);
            }


        });
        ViewReportbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose(); // find why dispose isnt working
                new ViewReport("View report").setVisible(true);
            }
        });

        ViewRevbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                //ManagerDb lol = new ManagerDb();  // wtf does it want in here what argument in the brackets omg
                //lol.setVisible(true); // added this just to see if initializing was prob
                new ManagerDb("Revenue Data").setVisible(true);
            }


        });
    }
}
