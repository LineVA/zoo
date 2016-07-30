package gui;

import commandLine.CommandManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.stream.Stream;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import launch.Play;

/**
 *
 * @author doyenm
 */
public class TextPane extends JTextPane {

    CommandManager manager;

    public TextPane(Play play, int columns, int line) {
        super();
        this.setPreferredSize(new Dimension(line, columns));
        this.setBackground(Color.BLACK);
        this.setForeground(EditorColors.CMD.getColor());
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
//        this.setCaretPosition(this.getCaretPosition() + 2);
    }

    private Object[] recoverCmdLinesArray() {
        BufferedReader in = new BufferedReader(new StringReader(this.getText()));
        Stream<String> stream = in.lines();
        return stream.toArray();
    }

    private void enterPressed() {
        Object[] cmdLinesArray = recoverCmdLinesArray();
//        this.setText(this.getText() + "\n" + manager.run(cmdLinesArray[cmdLinesArray.length - 1].toString()));
        append(manager.run(cmdLinesArray[cmdLinesArray.length - 1].toString()), EditorColors.INFO.getColor());
    }

    public void append(String str, Color color) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
                StyleConstants.Foreground, color);

        setCaretPosition(getDocument().getLength());
        setCharacterAttributes(aset, false);
        replaceSelection("\n" + str);
        aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
                StyleConstants.Foreground, EditorColors.CMD.getColor());

        setCaretPosition(getDocument().getLength());
        setCharacterAttributes(aset, false);
    }

}
