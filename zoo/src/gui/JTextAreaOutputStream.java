package gui;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

/**
 * Un OutputStream vers un JTextArea. Utile pour redéfinir System.out
 * et consorts vers un JTextArea.
 * @see javax.swing.JTextArea
 * @see java.io.OutputStream
 * @author Glob
 * @version 0.2
 */
class JTextAreaOutputStream extends OutputStream {
   private JTextArea m_textArea = null;

   /**
    * Method JTextAreaOutputStream.
    * @param aTextArea le JTextArea qui recevra les caractères.
    */
   public JTextAreaOutputStream(JTextArea aTextArea) {
      m_textArea = aTextArea;
   }

   /**
    * Écrit un caractère dans le JTextArea.
    * Si le caractère est un retour chariot, scrolling.
    * @see java.io.OutputStream#write(int)
    */
   public void write(int b) throws IOException {
      byte[] bytes = new byte[1];
      bytes[0] = (byte)b;
      String newText = new String(bytes);
      m_textArea.append(newText);
      if (newText.indexOf('\n') > -1) {
         try {
            m_textArea.scrollRectToVisible(m_textArea.modelToView(
                        m_textArea.getDocument().getLength()));
         } catch (javax.swing.text.BadLocationException err) {
            err.printStackTrace();
         }
      }
   }

   /**
    * Écrit un tableau de bytes dans le JTextArea.
    * Scrolling du JTextArea à la fin du texte ajouté.
    * @see java.io.OutputStream#write(byte[])
    */
   public final void write(byte[] arg0) throws IOException {
      String txt = new String(arg0);
      m_textArea.append(txt);
      try {
         m_textArea.scrollRectToVisible(m_textArea.modelToView(m_textArea.getDocument().getLength()));
      } catch (javax.swing.text.BadLocationException err) {
         err.printStackTrace();
      }
   }
}