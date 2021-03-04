package delivery.UI.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerChoice extends JFrame  {
    private JButton EmpDbBtn;
    private JPanel ManagerChoicePanel;
    private JButton ViewReportbtn;

    public ManagerChoice(String title) {
        super(title);
        this.setContentPane(ManagerChoicePanel);
    }

    public ManagerChoice() {
        EmpDbBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                this.dispose;
                //ManagerDb lol = new ManagerDb();  // wtf does it want in here what argument in the brackets omg
                //lol.setVisible(true); // added this just to see if initializing was prob
                new ManagerDb().setVisible(true);
            }
        });
        ViewReportbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                this.dispose; // find why dispose isnt working
                new ViewReport().setVisible(true);
            }
        });
    }
}
