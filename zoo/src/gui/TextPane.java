package gui;

import commandLine.CommandManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    AttributeSet aset;
    StyleContext sc;
    private final String cmdInvite = "> ";

    public TextPane(Play play, int columns, int line) {
        super();
        this.setPreferredSize(new Dimension(line, columns));
        this.setBackground(Color.BLACK);
        this.setForeground(EditorColors.CMD.getColor());
        manager = new CommandManager(play);
        this.keyEventListener();
        this.mouseEventListener();
        sc = StyleContext.getDefaultStyleContext();
    }

    private void keyEventListener() {
        KeyListener l;
        l = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        enterPressed();
                        e.consume();
                        break;
                    case KeyEvent.VK_BACK_SPACE:
                        if (!canUseBackSpace()) {
                            e.consume();
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
        addKeyListener(l);
    }

    public boolean canUseBackSpace() {
        Object[] lines = recoverCmdLinesArray();
        String currentLine = lines[lines.length - 1].toString();
        int lengthCurrentLine = currentLine.length();
        return (lengthCurrentLine > this.cmdInvite.length());
    }

    private Object[] recoverCmdLinesArray() {
        BufferedReader in = new BufferedReader(new StringReader(this.getText()));
        Stream<String> stream = in.lines();
        return stream.toArray();
    }

    private void enterPressed() {
        Object[] cmdLinesArray = recoverCmdLinesArray();
        append(manager.run(cmdLinesArray[cmdLinesArray.length - 1].toString()), EditorColors.INFO.getColor());
    }

    private void enterReleased() {
        this.setCaretPosition(this.getCaretPosition() + 2);
    }

    public void append(String str, Color color) {
        aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
                StyleConstants.Foreground, color);

        setCaretPosition(getDocument().getLength());
        setCharacterAttributes(aset, false);
        replaceSelection("\n" + str);
        aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
                StyleConstants.Foreground, EditorColors.CMD.getColor());

        setCaretPosition(getDocument().getLength());
        setCharacterAttributes(aset, false);
        replaceSelection("\n" + this.cmdInvite);
    }

    private void mouseEventListener() {
        MouseListener m = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                goEndOfTheText();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        addMouseListener(m);
    }

    private void goEndOfTheText() {
        this.setCaretPosition(this.getText().length());
        // Used to set the character color back to the one of CMD
        aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
                StyleConstants.Foreground, EditorColors.CMD.getColor());
        setCharacterAttributes(aset, true);
    }

}
