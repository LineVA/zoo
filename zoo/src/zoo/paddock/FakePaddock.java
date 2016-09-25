package zoo.paddock;

import exception.IncorrectDimensionsException;
import exception.name.EmptyNameException;
import exception.name.NameException;
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
    int biome;
    int paddockType;
    Option option;

    public FakePaddock(String name, int x, int y, int width, int height, int biome, int paddockType) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.biome = biome;
        this.paddockType = paddockType;
    }
    
    public IPaddock convertToPaddock(Option option) 
            throws IncorrectDimensionsException, EmptyNameException, NameException{
        IPaddock tmpPad =  new Paddock(this.name, new PaddockCoordinates(this.x, this.y, 
                this.width, this.height), new ArrayList<>(), this.paddockType, option);
        tmpPad.setBiome(Integer.toString(this.biome));
        return tmpPad;
    }
    
    
}
