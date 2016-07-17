package zoo.paddock;

import exception.IncorrectDimensionsException;

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

    public FakePaddock(String name, int x, int y, int width, int height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public IPaddock convertToPaddock() throws IncorrectDimensionsException{
        return new Paddock(this.name, new PaddockCoordinates(this.x, this.y, 
                this.width, this.height));
    }
    
    
}
