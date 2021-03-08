package delivery.UI.swing;

import delivery.domain.SystemController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Login extends JFrame {
    private SystemController systemController;
    private JPanel panel1;
    private JPasswordField password;
    private JButton loginbtn;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel loginLabel;
    private JComboBox userDropDown;

    public Login(String title, SystemController systemController) {
        super(title);
        this.systemController = systemController;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();


        userDropDown.addItem("Driver");
        userDropDown.addItem("Loader");
        userDropDown.addItem("Picker");
        userDropDown.addItem("Manager");

        // TODO: remove all this, on click of login, get the entered pin and selected employee type and check if pin exists


        loginbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean isValid;

                // user did not enter a password
                if (password.getText().equals("")) {
                    JOptionPane.showMessageDialog(panel1, "Enter a password");

                    isValid = false;

                }
                // user entered a password, validate user
                else {

                    int pswrd = Integer.parseInt(password.getText());

                    isValid = validateUser(userDropDown.getSelectedItem().toString(), pswrd);

                }

                // display  frames depending on employee type
                if (isValid) {

                    JOptionPane.showMessageDialog(panel1, "you have logged in", "Successful", 1);

                    switch (Objects.requireNonNull(userDropDown.getSelectedItem()).toString()) {
                        case "Manager":
                            dispose();
                            JFrame ManCh = new ManagerView("Manager Choice", systemController);
                            ManCh.setSize(400, 700);
                            ManCh.setVisible(true);
                            break;

                        case "Loader":
                            dispose();
                            JFrame ReportView = new ViewReport("Van loading report", systemController.getVanLoadingReport());
                            ReportView.setSize(400, 700);
                            ReportView.setVisible(true);
                            break;

                        case "Picker":
                            dispose();
                            ReportView = new ViewReport("Box Content report", systemController.getBoxContentReport());
                            ReportView.setSize(400, 700);
                            ReportView.setVisible(true);
                            break;

                        case "Driver":
                            dispose();
                            ReportView = new ViewReport("Van Schedule Report", systemController.getVanScheduleReport());
                            ReportView.setSize(400, 700);
                            ReportView.setVisible(true);
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(panel1, "Wrong password");
                }


            }




        });
    }

    ;


    private boolean validateUser(String employeeType, int pinEntered) {

        return systemController.validateUser(employeeType, pinEntered);
    }
}