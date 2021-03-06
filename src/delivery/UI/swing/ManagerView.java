package delivery.UI.swing;

import delivery.domain.SystemController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// this class should let the manager choose between reading report or viewing db

public class ManagerView extends JFrame  {
    private JButton EmpDbBtn;
    private JPanel ManagerChoicePanel;
    private JButton viewUnaccomoatedReportButton;
    private JButton ViewRevbtn;

    public ManagerView(String title, SystemController systemController) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(ManagerChoicePanel);
        this.pack();

        EmpDbBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                new ManageEmp("Manage Employee Data", systemController).setVisible(true);
            }


        });
        viewUnaccomoatedReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                ViewReport viewReport = new ViewReport("View report", systemController.getUnaccomodatedOrdersReport());
                viewReport.setVisible(true);
                viewReport.setSize(400, 500);

            }
        });

        ViewRevbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                //ManageEmp lol = new ManageEmp();  // wtf does it want in here what argument in the brackets omg
                //lol.setVisible(true); // added this just to see if initializing was prob
                //new ManageEmp("Revenue Data").setVisible(true);
                ViewReport viewReport = new ViewReport("Revenue Data", systemController.getRevenueReport());
                viewReport.setVisible(true);
                viewReport.setSize(400, 500);

            }


        });
    }
}
