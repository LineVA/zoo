package zoo.paddock;

import exception.IncorrectDimensionsException;
import exception.name.EmptyNameException;
import exception.name.NameException;
import exception.name.UnauthorizedNameException;
import exception.name.UnknownNameException;
import java.util.Objects;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + this.x;
        hash = 59 * hash + this.y;
        hash = 59 * hash + this.width;
        hash = 59 * hash + this.height;
        hash = 59 * hash + this.biome;
        hash = 59 * hash + this.paddockType;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FakePaddock other = (FakePaddock) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.width != other.width) {
            return false;
        }
        if (this.height != other.height) {
            return false;
        }
        if (this.biome != other.biome) {
            return false;
        }
        if (this.paddockType != other.paddockType) {
            return false;
        }
        return true;
    }
    
    

}
