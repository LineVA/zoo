package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.stream.Stream;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author doyenm
 */
public class Editor extends JTextPane {

    private final StyleContext sc;
    private AttributeSet aset;
    private int lastPressedKey;
    private final String cmdInvite = "> ";
    private int searchingLine = 0;

    public Editor(Dimension dimension) {
        super();
        this.setPreferredSize(dimension);
        this.setEditable(true);
        sc = StyleContext.getDefaultStyleContext();
        aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, EditorColors.CMD.getColor());
        this.setFont(new Font("Monospaced", this.getFont().getStyle(), this.getFont().getSize()));
        //  this.setForeground(EditorColors.CMD.getColor());
        this.setBackground(Color.BLACK);
        this.setSize(dimension.width, dimension.height);
//        lastPressedKey = -1;
//        this.keyEventListener();
//        // this.addActionListener(new ClickListener());
//        this.mouseEventListener();
//        printCmdInvite(false);
//        this.setCaretPosition(2);
        // Used to set the character color back to the one of CMD
        setCharacterAttributes(aset, true);
        this.setVisible(true);

    }

    /**
     * Method used to print something of the shell
     *
     * @param msg the text to print
     * @param beforeLine true if we need to go to the next line before printing
     * @param afterLine true if we need to go to the next line after printing
     * @param foreground the color of the message we print
     */
    public void printOnEditor(String msg, boolean beforeLine, boolean afterLine, Color foreground) {
        if (foreground != null) {
            aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, foreground);
        } else {
            aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, EditorColors.CMD.getColor());
        }

        int len = this.getDocument().getLength();
        this.setCaretPosition(len);
        this.setCharacterAttributes(aset, false);

        try {
            if (foreground != null) {
                this.setForeground(foreground);
            }
            if (beforeLine) {
                this.replaceSelection("\n");
            }
            this.replaceSelection(msg);

            if (afterLine) {
                this.replaceSelection("\n");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void printCmdInvite(boolean backPrevious) {
        printOnEditor(this.cmdInvite, false, false, null);
        if (backPrevious) {
            backPreviousLine();
        }

    }

    private void mouseEventListener() {
        MouseListener m = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                goEndOfTheText();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        addMouseListener(m);
    }

    private void goEndOfTheText() {
        this.setCaretPosition(this.getText().length());
        // Used to set the character color back to the one of CMD
        setCharacterAttributes(aset, true);
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
                        lastPressedKey = KeyEvent.VK_ENTER;
                        break;
                    case KeyEvent.VK_UP:
                        upPressed();
                        lastPressedKey = KeyEvent.VK_UP;
                        break;
                    case KeyEvent.VK_DOWN:
                        downPressed();
                        lastPressedKey = KeyEvent.VK_DOWN;
                        break;
                    default:
                        lastPressedKey = -1;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        enterReleased();
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_UP:
                        upAndDownReleased();
                        break;
                    default:
                        lastPressedKey = -1;
                        break;
                }
                //    throw new UnsupportedOperationException("Not supported yet."); 
            }
        };
        addKeyListener(l);
    }

    private void enterReleased() {
        this.setCaretPosition(this.getCaretPosition() + 2);
    }

    private void backPreviousLine() {
        this.setCaretPosition(this.getCaretPosition() - 2);
        // Used to set the character color back to the one of CMD
        setCharacterAttributes(aset, true);
    }

    private Object[] recoverCmdLinesArray() {
        BufferedReader in = new BufferedReader(new StringReader(this.getText()));
        Stream<String> stream = in.lines();
        return stream.toArray();
    }

    /**
     * The processing to execute if we press the enter key
     */
    private void enterPressed() {
        /*
         A solution without Stream<String> : easier to understand
         but we need to scroll through the entire BufferedReader
         for each new line. 
         The second solution seems to be cleaner.
         */
//                    private int linesNb;
//                      this.linesNb++;
//                    try {
//                        String lu ="";
//                        int cpt = 0;
//                        while (cpt < linesNb) {
//                            lu = in.readLine();
//                            cpt++;
//                        }
//                        System.out.println(lu);
//
//                    } catch (IOException ex) {
//                        Logger.getLogger(Editor.class.getName()).
//                          log(Level.SEVERE, null, ex);
//                    }
        this.searchingLine = 0;
        Object[] cmdLinesArray = recoverCmdLinesArray();
//        this.parser.parseAnalyzeAndAct(cmdLinesArray[cmdLinesArray.length - 1].toString().substring(2));
        this.printCmdInvite(true);
        // Used to set the character color back to the one of CMD
        setCharacterAttributes(aset, true);
        //i++;
    }

    /**
     * Function used to find the previous cmd
     *
     * @param bufferedArray a array of the previous lines, not including the
     * current one !
     * @return if it exists, the last cmd without the cmd invite, else an empty
     * string.
     */
    private String findFirstPreviousNotEmptyCommandLine(Object[] bufferedArray) {
        int i = bufferedArray.length - 1;
        while (i >= 0) {
            // If the considered line begins with a command invite 
            if (bufferedArray[i].toString().length() >= 2) {
                if (bufferedArray[i].toString().substring(0, 2).equals(this.cmdInvite)) {
                    this.searchingLine = i;
                    return bufferedArray[i].toString().substring(2);
                }
            }
            i--;
        }
        return "";
    }

    private String findFirstNextNotEmptyCommandLine(Object[] bufferedArray) {
        int i = 0;
        while (i < bufferedArray.length) {
            // If the considered line begins with a command invite 
            if (bufferedArray[i].toString().length() >= 2) {
                if (bufferedArray[i].toString().substring(0, 2).equals(this.cmdInvite)) {
                    this.searchingLine += i + 1;
                    System.out.println(bufferedArray[i].toString().substring(2));
                    return bufferedArray[i].toString().substring(2);
                }
            }
            i++;
        }
        return "";
    }

    private int indexOfTheBeginningOfTheLastLine(Object[] bufferedArray) {
        int sum = 0;
        for (Object bufferedArray1 : Arrays.copyOfRange(bufferedArray, 0, bufferedArray.length - 1)) {
            sum += bufferedArray1.toString().length();
        }
        return sum;
    }

    private void cleanCharacters(int start, int end) {
        StringBuilder builder = new StringBuilder(this.getText());
        builder.replace(start, end, "");
        this.setText(builder.toString());
    }

    private void upPressed() {
        Object[] cmdLinesArray = recoverCmdLinesArray();
        // If we are not on the first line of the editor
        if (cmdLinesArray.length != 0) {
            // If the last line is just a cmd invite
            if (cmdLinesArray[cmdLinesArray.length - 1].equals(this.cmdInvite)) {
                this.printOnEditor(this.findFirstPreviousNotEmptyCommandLine(Arrays.copyOfRange(cmdLinesArray,
                        0, cmdLinesArray.length - 1)), false, false, null);
            } else if (this.searchingLine != 0) {
                int end = this.getText().length();
                int start = this.indexOfTheBeginningOfTheLastLine(cmdLinesArray) + 2 + cmdLinesArray.length - 1;
                cleanCharacters(start, end);
                this.printOnEditor(this.findFirstPreviousNotEmptyCommandLine(Arrays.copyOfRange(cmdLinesArray,
                        0, this.searchingLine)), false, false, null);
            } else {
                int end = this.getText().length();
                int start = this.indexOfTheBeginningOfTheLastLine(cmdLinesArray) + 2 + cmdLinesArray.length - 1;
                cleanCharacters(start, end);
                this.printOnEditor(this.findFirstPreviousNotEmptyCommandLine(Arrays.copyOfRange(cmdLinesArray,
                        0, cmdLinesArray.length - 1)), false, false, null);
            }
        }
    }

    private void downPressed() {
        Object[] cmdLinesArray = recoverCmdLinesArray();
        // If we are not on the first line of the editor
        if (cmdLinesArray.length != 0) {
            if (this.lastPressedKey == KeyEvent.VK_UP || this.lastPressedKey == KeyEvent.VK_DOWN) {
                int end = this.getText().length();
                int start = this.indexOfTheBeginningOfTheLastLine(cmdLinesArray) + 2 + cmdLinesArray.length - 1;
                cleanCharacters(start, end);
                if (this.searchingLine + 1 <= cmdLinesArray.length) {
                    this.printOnEditor(this.findFirstNextNotEmptyCommandLine(Arrays.copyOfRange(cmdLinesArray,
                            this.searchingLine + 1, cmdLinesArray.length - 1)), false, false, null);
                }
            }
        }
    }

    private void upAndDownReleased() {
        this.setCaretPosition(this.getText().length());
        // Used to set the character color back to the one of CMD
        setCharacterAttributes(aset, true);
    }
}
