package delivery.UI.swing;

import delivery.domain.SystemController;

import javax.swing.*;

public class Main {
    // initialize the controller from domain layer
    // then in the main Jframe (start page) initialization pass in the controller object


    public static void main(String[] args) {
        SystemController controller = new SystemController();
       //show login window
        JFrame login = new Login("Login", controller);
        login.setSize(400, 700);
        login.setVisible(true);

    }

}
