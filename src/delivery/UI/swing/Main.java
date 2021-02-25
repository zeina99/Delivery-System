package delivery.UI.swing;

import delivery.domain.SystemController;
import javax.swing.*;

public class Main {
    // initialize the controller from domain layer
    SystemController controller = new SystemController();

    public static void main(String[] args) {
        JFrame login = new Login("Login");
        login.setSize(400, 700);
        login.setVisible(true);
        // then in the main Jframe (start page) initialization pass in the controller object
    }

}
