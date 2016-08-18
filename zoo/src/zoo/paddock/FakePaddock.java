package zoo.paddock;

import exception.IncorrectDimensionsException;
import exception.name.EmptyNameException;
import java.util.ArrayList;
import lombok.Getter;

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

    public FakePaddock(String name, int x, int y, int width, int height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public IPaddock convertToPaddock() throws IncorrectDimensionsException, EmptyNameException {
//        throw new UnsupportedOperationException("Management of neightbourhood in the mload of a zoo");
        return new Paddock(this.name, new PaddockCoordinates(this.x, this.y,
                this.width, this.height), new ArrayList<IPaddock>());
    }

}
