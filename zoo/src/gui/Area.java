package gui;

import commandLine.CommandManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.stream.Stream;
import javax.swing.JTextArea;
import launch.Play;

/**
 *
 * @author doyenm
 */
public class Area extends JTextArea {

    CommandManager manager;

    public Area(Play play, int columns, int line) {
        super(line, columns);
        this.setLineWrap(true);
        this.setWrapStyleWord(false); 
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        JTextAreaOutputStream outStream = new JTextAreaOutputStream(this);
        JTextAreaOutputStream errStream = new JTextAreaOutputStream(this);
        System.setOut(new PrintStream(outStream));
        System.setErr(new PrintStream(errStream));
        manager = new CommandManager(play);
        this.keyEventListener();
    }

    private void keyEventListener() {
        KeyListener l;
        l = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                //  throw new UnsupportedOperationException("Not supported yet."); 
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        enterPressed();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        enterReleased();
                        break;
                    default:
                        break;
                }
            }
        };
        addKeyListener(l);
    }

    private void enterReleased() {
        this.setCaretPosition(this.getCaretPosition() + 2);
    }

    private Object[] recoverCmdLinesArray() {
        BufferedReader in = new BufferedReader(new StringReader(this.getText()));
        Stream<String> stream = in.lines();
        return stream.toArray();
    }

    private void enterPressed() {
        Object[] cmdLinesArray = recoverCmdLinesArray();
        System.out.println(manager.run(cmdLinesArray[cmdLinesArray.length - 1].toString()));
    }

}
