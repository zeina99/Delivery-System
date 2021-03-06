package delivery.UI.swing;

import delivery.domain.SystemController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

public class Login extends JFrame {
    private SystemController systemController;
    private JPanel panel1;
    private JPasswordField password;
    private JButton loginbtn;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel loginLabel;
    private JComboBox UserDropDown;
    private int correctPIN;

    public Login(String title, SystemController systemController) {
        super(title);
        this.systemController = systemController;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();


        UserDropDown.addItem("Driver");
        UserDropDown.addItem("Loader");
        UserDropDown.addItem("Picker");
        UserDropDown.addItem("Manager");

        // TODO: remove all this, on click of login, get the entered pin and selected employee type and check if pin exists
        UserDropDown.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    if (UserDropDown.getSelectedItem().equals("Driver"))
                        // why is this thinking im trying to equate string to int
                        correctPIN = 123;

                    else if (UserDropDown.getSelectedItem().equals("Loader"))
                        correctPIN = 456;

                    else if (UserDropDown.getSelectedItem().equals("Picker"))
                        correctPIN = 789;

                    else if (UserDropDown.getSelectedItem().equals("Manager"))
                        correctPIN = 101;
                }
            }
        });

        loginbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean isValid;

                // user did not enter a password
                if (password.getText().equals("")) {
                    JOptionPane.showMessageDialog(panel1, "Enter a password");

                    isValid = false;

                }
                // user entered a password
                else {

                    int pswrd = Integer.parseInt(password.getText());

                    isValid = validateUser(UserDropDown.getSelectedItem().toString(), pswrd);

                }

                // display  frames depending on employee type
                if (isValid) {

                    JOptionPane.showMessageDialog(panel1, "you have logged in", "Successful", 1);

                    switch (Objects.requireNonNull(UserDropDown.getSelectedItem()).toString()) {
                        case "Manager":
                            dispose();
                            JFrame ManCh = new ManagerView("Manager Choice", systemController);
                            ManCh.setSize(400, 700);
                            ManCh.setVisible(true);
                            break;

                        case "Loader":
                            dispose();
                            JFrame ReportView = new ViewReport("View Report", systemController.getVanLoadingReport());
                            ReportView.setSize(400, 700);
                            ReportView.setVisible(true);
                            break;

                        case "Picker":
                            dispose();
                            ReportView = new ViewReport("View Report", systemController.getBoxContentReport());
                            ReportView.setSize(400, 700);
                            ReportView.setVisible(true);
                            break;

                        case "Driver":
                            dispose();
                            ReportView = new ViewReport("View Report", systemController.getVanScheduleReport());
                            ReportView.setSize(400, 700);
                            ReportView.setVisible(true);
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(panel1, "Wrong password");
                }


            }

            // why is it always false wth


        });
    }

    ;


    private boolean validateUser(String employeeType, int pinEntered) {

        return systemController.validateUser(employeeType, pinEntered);
    }
}