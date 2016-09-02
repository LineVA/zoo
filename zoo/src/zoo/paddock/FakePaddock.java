package zoo.paddock;

import exception.IncorrectDimensionsException;
import exception.name.EmptyNameException;
import java.util.ArrayList;
import launch.options.Option;

/**
 *
 * @author doyenm
 */
public class FakePaddock {
    String name;
    int x;
    int y;
    int width;
    int height;
    Option option;

    public FakePaddock(String name, int x, int y, int width, int height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public IPaddock convertToPaddock(Option option) 
            throws IncorrectDimensionsException, EmptyNameException{
        return new Paddock(this.name, new PaddockCoordinates(this.x, this.y, 
                this.width, this.height), new ArrayList<IPaddock>(), option);
    }
    
    
}
