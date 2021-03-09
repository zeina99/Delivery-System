package delivery.UI.swing;

import delivery.domain.SystemController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;

public class ViewReport extends JFrame {
    private JTextArea fileTextArea;
    private JPanel ReportView;
    private JLabel title;
    private JButton backButton;


    public ViewReport(String title, String filename, SystemController systemController) {
        super(title);

        this.title.setText(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(ReportView);
        this.pack();

        readFileIntoView(filename);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                Login login = new Login("Login", systemController );
                login.setSize(400, 700);
                login.setVisible(true);
            }
        });
    }

    void readFileIntoView(String filename) {
        try (FileReader fileReader = new FileReader(filename)) {

            fileTextArea.read(fileReader, filename);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
