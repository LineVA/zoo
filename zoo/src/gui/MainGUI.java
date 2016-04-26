package gui;

import commandLine.CommandLineParser;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author doyenm
 */
public class MainGUI extends JFrame {

    //private TextArea text;
  //  private CommandLineParser parser;

    public MainGUI(CommandLineParser parser) {
        super("Zoo");
        this.setLayout(new FlowLayout());
        // To set the JFrame in fullscreen 
        //  this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension dimension = new Dimension(470, 300);
        Editor editor = new Editor(dimension, parser);
        JScrollPane scroll = new JScrollPane(editor);
        this.getContentPane().add(scroll);
        this.setSize(dimension);
        this.closeWindows();
        
        this.pack();
        setVisible(true);
        parser.getTransmission().setEditor(editor);
    }

    private void closeWindows() {
        WindowListener l = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(l);
    }
}
