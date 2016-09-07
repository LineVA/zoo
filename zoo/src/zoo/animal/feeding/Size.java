package zoo.animal.feeding;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum Size {

    UNKNOWN(0),
    EXTRAEXTRASMALL(1),
    EXTRASMALL(2),
    SMALL(3),
    MEDIUM(4),
    LARGE(5),
    EXTRALARGE(6),
    EXTRAEXTRALARGE(7),
    EXTRAEXTRAEXTRALARGE(8);

    @Getter
    int id;

    Size(int id) {
        this.id = id;
    }
}
