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
     * @throws EmptyNameException if the name of the file is empty
     */
    public void saveZoo(IZoo zoo, String fileName) throws EmptyNameException;
    
    /**
     * Check if a file with this name is already existing
     * @param fileName the name of file to check
     * @return true if it is already existing, else false
     * @throws EmptyNameException if the name of the file is empty
     */
    public boolean isFileAlreadyExisting(String fileName) throws EmptyNameException;
}

