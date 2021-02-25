package delivery.UI.swing;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPanel panel1;
    private JPasswordField password;
    private JTextField username;
    private JButton loginbtn;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel loginLabel;
    //private JOptionPane feedback;

    public Login(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();

        loginbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname = username.getText();
                String pswrd = password.getText();

                if (uname.equals("name") && pswrd.equals("password")) {
                    JOptionPane.showMessageDialog(panel1, "you have logged in");
                } else
                    JOptionPane.showMessageDialog(panel1, "you have not logged in");
            }
        });
    }
/*
    public static void main(String[] args) {
        JFrame login = new LogIn("Login");
        login.setSize(400, 700);
        login.setVisible(true);
    }
*/
}