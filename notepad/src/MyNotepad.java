import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;

public class MyNotepad implements ActionListener {

    Button saveButton , loadButton, exitButton ;
    TextArea text;
    Frame myFrame;

    public MyNotepad() {

        myFrame = new Frame("My Notepad");
        myFrame.setSize(580,400);
        myFrame.setLayout(new BorderLayout());

        text = new TextArea(20,50);

        saveButton = new Button("Save");
        saveButton.addActionListener(this);

        loadButton = new Button("Load");
        loadButton.addActionListener(this);

        exitButton = new Button("Exit");
        exitButton.addActionListener(this);

        Panel centerPanle = new Panel();
        Panel southPanle = new Panel();

        centerPanle.add(text);

        southPanle.add(saveButton);
        southPanle.add(loadButton);
        southPanle.add(exitButton);

        myFrame.add("Center",centerPanle);
        myFrame.add("South",southPanle);

        myFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == saveButton) {

            try{
                String fileName = JOptionPane.showInputDialog(null,"Please Enter File Name to Save", "Input", JOptionPane.QUESTION_MESSAGE);

                if(fileName == null)
                    return;

                BufferedWriter write = new BufferedWriter(new FileWriter("C:\\Users\\436100729\\Desktop\\" + fileName));

                write.write(text.getText());
                write.flush();
                write.close();

                JOptionPane.showMessageDialog(null,"The file is saved", "Notification", JOptionPane.INFORMATION_MESSAGE);
            }

            catch(Exception e1) {

                JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource() == loadButton) {

            try{

                String fileName = JOptionPane.showInputDialog(null,"Please Enter File Name to Load", "Input", JOptionPane.QUESTION_MESSAGE);

                BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\436100729\\Desktop\\" + fileName));
                String temp;

                while((temp = reader.readLine()) != null) {

                    text.append(temp);
                    text.append("\n");
                }

                reader.close();
            }
            catch(Exception e1) {

                JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        else
            System.exit(0);
    }

    public static void main(String[] args) {

        new MyNotepad();
    }
}