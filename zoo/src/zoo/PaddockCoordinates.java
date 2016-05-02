package zoo;

import exception.IncorrectDimensionsException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
@EqualsAndHashCode()
public class PaddockCoordinates {

    /**
     * The x coordinate of the left and top corner
     */
    @Getter
    private int x;
    /**
     * The y coordinate of the left and top corner
     */ 
    @Getter
    private int y;
    /**
     * The width of the paddock
     */
    @Getter
    private int width;
    /**
     * The height of the paddock
     */
    @Getter
    private int height;

    /**
     * Constructor
     * @param x The x coordinate of the left and top corner
     * @param y The y coordinate of the left and top corner
     * @param width The width of the paddock
     * @param height The height of the paddock
     * @throws IncorrectDimensionsException if x and/or y are lower than 0 or
     * if the width and/or the height are lower or equals than 0
     */
    public PaddockCoordinates(int x, int y, int width, int height) throws IncorrectDimensionsException {
        if (x < 0 || y < 0 || width <= 0 || height <= 0) {
            throw new IncorrectDimensionsException("The coordinates must be "
                    + "greater or equals than 0, the dimensions greater than 0.");
        }
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Calculates if two paddocks are competing
     * @param coor the coordinates of the paddock with which the competing 
     * is checking
     * @return true if there is no competing, false else.
     */
    public boolean isNotCompeting(PaddockCoordinates coor) {
        boolean notCompeting = ((this.y + this.height - 1) < coor.y);
        notCompeting = notCompeting || (this.y > (coor.y + coor.height - 1));
        notCompeting = notCompeting || ((this.x + this.width - 1) < coor.x);
        notCompeting = notCompeting || (this.x > (coor.x + coor.width - 1));
        return notCompeting;
    }

}