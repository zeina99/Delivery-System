package delivery.UI.swing;

import delivery.domain.SystemController;
import javax.swing.*;

public class Main {
    // initialize the controller from domain layer
    SystemController controller = new SystemController();

    public static void main(String[] args) {
       //show login window
        JFrame login = new Login("Login");
        login.setSize(400, 700);
        login.setVisible(true);
        // then in the main Jframe (start page) initialization pass in the controller object

        JFrame ManCh = new ManagerChoice("Manager Choice");
        ManCh.setSize(400,700);
        ManCh.setVisible(true);

        //show manager db view
        JFrame ManDb = new ManagerDb("Manager Database");
        ManDb.setSize(400,700);
        ManDb.setVisible(true);

        //show viewer
        JFrame ReportView = new ViewReport("View Report");
        ReportView.setSize(400,700);
        ReportView.setVisible(true);
    }

}
