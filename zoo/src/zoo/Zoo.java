package zoo;

import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import exception.name.UnknownNameException;
import gui.FormattingDisplay;
import java.util.ArrayList;
import java.util.HashMap;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class Zoo {

    /**
     * The name of the zoo
     */
    @Getter
    private String name;
    /**
     * Its width
     */
    @Getter
    private int width;
    /**
     * Its height
     */
    @Getter
    private int height;
    /**
     * The hashmap of the paddocks it contains
     */
    @Getter
    private HashMap<String, Paddock> paddocks;

    /**
     * Constructor of the object 'Zoo'
     *
     * @param name the name of the zoo
     * @param width the width of the zoo
     * @param height the height of the zoo
     * @throws IncorrectDimensionsException throws if the width and/or the
     * height is/are smaller than 1
     */
    public Zoo(String name, int width, int height) throws IncorrectDimensionsException {
        this.name = name;
        if (width < 1 || height < 1) {
            throw new IncorrectDimensionsException("A zoo must have a width and "
                    + "an height greater or equals to 1 each.");
        } else {
            this.width = width;
            this.height = height;
        }
        paddocks = new HashMap<>();
    }

    /**
     * Method used to add a paddock to the zoo
     *
     * @param paddockName the name of the new paddock
     * @param x the origin x of the new paddock
     * @param y the origin y of the new paddock
     * @param width the width of the new paddock
     * @param height the height of the new paddock
     * @throws AlreadyUsedNameException if there is already a paddock in this
     * zoo with the same name
     * @throws IncorrectDimensionsException if the paddock is to big for the zoo
     * or its width and/or are smaller than 1 or its origins are sammler than 0
     */
    public void addPaddock(String paddockName, int x, int y, int width, int height)
            throws AlreadyUsedNameException, IncorrectDimensionsException {
        if (paddocks.containsKey(paddockName)) {
            throw new AlreadyUsedNameException("A paddock with this name is"
                    + " already existing. Please choose another one.");
        } else {
            PaddockCoordinates coor = new PaddockCoordinates(x, y, width, height);
            if (this.tooSmallforThisPaddock(coor)) {
                throw new IncorrectDimensionsException("This paddock cannot be "
                        + "set here : the zoo is too small.");
            }
            for (HashMap.Entry<String, Paddock> entry : paddocks.entrySet()) {
                if (!coor.isNotCompeting(entry.getValue().getCoordinates())) {
                    throw new IncorrectDimensionsException("This paddock cannot"
                            + " be set here : there is already another one on this place.");
                }
            }
            Paddock paddock = new Paddock(paddockName, coor);
            this.paddocks.put(paddockName, paddock);
        }
    }

    /**
     * Method used to list the paddocks of the zoo
     *
     * @return an Arraylist<String> of their names
     */
    public ArrayList<String> listPaddock() {
        ArrayList<String> list = new ArrayList<>();
        for (HashMap.Entry<String, Paddock> entry : paddocks.entrySet()) {
            list.add(entry.getKey());
        }
        return list;
    }

    /**
     * Method used to find a paddock by its name
     * @param name the name to search
     * @return the paddock if it exists
     * @throws UnknownNameException if the paddock does not exist
     */
    public Paddock paddockByName(String name) throws UnknownNameException {
        for (HashMap.Entry<String, Paddock> entry : paddocks.entrySet()) {
            if (entry.getKey().equals(name)) {
                return entry.getValue();
            }
        }
        throw new UnknownNameException("This paddock does not exist.");
    }

    /**
     * method used to obtain detailed information about a paddock
     * @param name the name of the paddock we search
     * @return these information without mef
     * @throws UnknownNameException if the searched paddock does not exist
     */
    public String detailedPaddock(String name) throws UnknownNameException {
        Paddock pad = paddockByName(name);
        return FormattingDisplay.idDisplay(name);
    }

    /**
     * Method used to know if a paddock can be placed into the zoo without 
     * looking for the others paddocks
     * @param coor the coordinates of the new paddock
     * @return true if we can set it, false if it is too big for the zoo.
     */
    private boolean tooSmallforThisPaddock(PaddockCoordinates coor) {
        return ((coor.getX() + coor.getWidth() > this.width)
                || (coor.getY() + coor.getHeight() > this.height));
    }
}
