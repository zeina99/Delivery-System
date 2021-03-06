package delivery.UI.swing;

import javax.swing.*;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.io.*;

public class ViewReport extends JFrame{
    private JTextArea FileText;
    private JPanel ReportView;
    private JLabel reportTitle;
    private String content;
    private String employeeType;


    public ViewReport(String title, String filename) {
        super(title);
        this.employeeType = employeeType;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(ReportView);
        this.pack();



        readFileIntoView(filename);

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
    void readFileIntoView(String filename)  {
        try (FileReader fileReader = new FileReader(filename)){

            FileText.read(fileReader, filename);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
            FileText.read(reader, file);
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
