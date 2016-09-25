package backup.save;

import exception.name.EmptyNameException;
import zoo.IZoo;

/**
 * interface to save a zoo into a XML file
 * @author doyenm
 */

public interface Save {
    /**
     * The main method  to save a zoo
     * @param zoo the zoo to save
     * @param fileName the file where to save it
     * @throws EmptyNameException 
     */
    public void saveZoo(IZoo zoo, String fileName) throws EmptyNameException;
}

