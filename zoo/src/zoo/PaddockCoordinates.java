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
    
    public boolean isNotCompeting(PaddockCoordinates coor){
        boolean notCompeting = ((this.y + this.height - 1)<coor.y);
        notCompeting = notCompeting || (this.y > (coor.y + coor.height - 1));
        notCompeting = notCompeting || ((this.x + this.width - 1) < coor.x);
        notCompeting = notCompeting || (this.x > (coor.x + coor.width - 1));
        return notCompeting;
    }

}
