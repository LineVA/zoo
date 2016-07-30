package gui;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextPane;

/**
 * Un OutputStream vers un JTextPane. Utile pour redéfinir System.out
 * et consorts vers un JTextPane
 * @author Glob
 * @version 0.2
 */
class JTextPaneOutputStream extends OutputStream {
   private JTextPane textPane = null;

   public JTextPaneOutputStream(JTextPane textPane) {
      this.textPane = textPane;
   }

   @Override
   public void write(int b) throws IOException {
      byte[] bytes = new byte[1];
      bytes[0] = (byte)b;
      String newText = textPane.getText() + new String(bytes);
      textPane.setText(newText);
      if (newText.indexOf('\n') > -1) {
         try {
            textPane.scrollRectToVisible(textPane.modelToView(
                        textPane.getDocument().getLength()));
         } catch (javax.swing.text.BadLocationException err) {
            err.printStackTrace();
         }
      }
   }
}