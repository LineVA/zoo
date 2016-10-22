package zoo.paddock;

import exception.IncorrectDimensionsException;
import exception.name.EmptyNameException;
import exception.name.NameException;
import exception.name.UnauthorizedNameException;
import exception.name.UnknownNameException;
import lombok.Getter;
import launch.options.Option;

/**
 *
 * @author doyenm
 */
public class FakePaddock {

    @Getter
    String name;
    @Getter
    int x;
    @Getter
    int y;
    @Getter
    int width;
    @Getter
    int height;
    @Getter
    int biome;
    @Getter
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
            throws IncorrectDimensionsException, EmptyNameException, 
            UnauthorizedNameException, UnknownNameException {
        return new PaddockBuilder().name(this.name)
                .coordinates(new PaddockCoordinates(this.x, this.y, this.width, this.height))
                .paddockType(this.paddockType)
                .option(option)
                .biome(this.biome)
                .buildPaddock();
    }

}
