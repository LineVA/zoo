package zoo;

import exception.IncorrectDimensionsException;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class PaddockCoordinates {

    @Getter
    private int x;
    @Getter
    private int y;
    @Getter
    private int width;
    @Getter
    private int height;

    public PaddockCoordinates(int x, int y, int width, int height) throws IncorrectDimensionsException {
        if(x < 0 || y<0 || width<=0 || height<=0){
            throw new IncorrectDimensionsException("The coordinates must be "
                    + "greater or equals than 0, the dimensions greater than 0.");
        }
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isCompeting(PaddockCoordinates coor) {
        boolean competing = ((coor.getX() <= this.x) && (this.x <= (coor.x + coor.width)));
        competing = competing || ((coor.y <= this.y) && (this.y <= (coor.y + coor.height)));
        competing = competing || ((this.x <= coor.x) && (coor.x <= (this.x + this.width)));
        competing = competing || ((this.y <= coor.y) && (coor.y <= (this.y + this.height)));
        return competing;
    }

}
