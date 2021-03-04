package delivery.UI.swing;

import javax.swing.*;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ViewReport extends JFrame{
    private JTextArea FileText;
    private JPanel ReportView;
    private JLabel reportTitle;
    private String content;
    private String employeeType;


    public ViewReport(String title, String employeeType /*String reportName*/) {
        super(title);
        this.employeeType = employeeType;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(ReportView);
        this.pack();
        this.reportTitle = new JLabel(title);

        FileText.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent inputMethodEvent) {
                // if driver read this file
                //else if read this file etc
                //readFile(); // read file (Add some file to read)
            }

            @Override
            public void caretPositionChanged(InputMethodEvent inputMethodEvent) {

            }
        });
    }
    public String readFile(String filename)
    {
        String content = null;
        File file = new File(filename); // For example, foo.txt
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }
}
