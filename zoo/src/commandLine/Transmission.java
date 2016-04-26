package commandLine;

import exception.IncorrectDimensionsException;
import exception.name.NameException;
import gui.Editor;
import gui.EditorColors;
import gui.FormattingDisplay;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;
import zoo.Zoo;

/**
 *
 * @author doyenm
 */
public class Transmission {

    @Getter
    @Setter
    Editor editor;

    @Getter
    @Setter
    Zoo zoo;

    //TODO : CHECK IF ZOO IS NULL !!!!
    // TODO : ZOO, PAddock : forbidden, empty or already used names
    private void reportedException(Exception ex) {
        Logger.getLogger(Transmission.class.getName()).log(Level.SEVERE, null, ex);
        ex.printStackTrace();
        this.editor.printOnEditor(ex.getMessage(), true, false,
                EditorColors.EXCEPTION.getColor());
    }

    private void zooIsNull() {
        this.editor.printOnEditor("First, you must create a zoo.",
                true, false, EditorColors.EXCEPTION.getColor());
    }

    public void paddockCreate(String name, String x, String y, String width,
            String height) {
        if (zoo == null) {
            zooIsNull();
        } else {
            try {
                this.zoo.addPaddock(name, Integer.parseInt(x), Integer.parseInt(y),
                        Integer.parseInt(width), Integer.parseInt(height));
                this.editor.printOnEditor("Your paddock has been sucessfully created.",
                        true, false, EditorColors.INFO.getColor());
            } catch (NameException | IncorrectDimensionsException ex) {
                reportedException(ex);
            }
        }
    }

    public void paddockDetailed(String name) {
        if (zoo == null) {
            zooIsNull();
        } else {
            try {
                throw new Exception("Not yet implemented ; comming soon...");
            } catch (Exception ex) {
                reportedException(ex);
            }
        }
    }

    public void paddockLs() {
        if (zoo == null) {
            zooIsNull();
        } else {
            this.editor.printOnEditor(
                    FormattingDisplay.formattingArrayList(this.zoo.listPaddock()),
                    true, false, EditorColors.INFO.getColor());
        }
    }
    
    void unknownCmd(String cpt) {
         this.editor.printOnEditor("Unknown command line : see 'man " + cpt 
                 +"' for more information.",
                    true, false, EditorColors.INFO.getColor());
    }
    
    // TODO : If a dimension is a letter

    public void zooCreate(String name, String width, String height) {
        try {
            this.zoo = new Zoo(name, Integer.parseInt(width), Integer.parseInt(height));
            this.editor.printOnEditor("Your zoo has been sucessfully created.",
                    true, false, EditorColors.INFO.getColor());
        } catch (IncorrectDimensionsException ex) {
            reportedException(ex);
        }
    }

    public void zooMap() {
        if (zoo == null) {
            zooIsNull();
        } else {
            this.editor.printOnEditor(FormattingDisplay.zooMap(zoo.getWidth(),
                    zoo.getHeight()), true, false, EditorColors.INFO.getColor());
        }
    }

    

}
