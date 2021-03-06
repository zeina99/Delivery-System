package delivery.UI.swing;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ViewReport extends JFrame {
    private JTextArea fileTextArea;
    private JPanel ReportView;


    public ViewReport(String title, String filename) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(ReportView);
        this.pack();

        readFileIntoView(filename);

    }

    void readFileIntoView(String filename) {
        try (FileReader fileReader = new FileReader(filename)) {

            fileTextArea.read(fileReader, filename);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
