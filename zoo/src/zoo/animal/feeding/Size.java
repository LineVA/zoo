package zoo.animal.feeding;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum Size {

    UNKNOWN(0),
    XXS(1),
    XS(2),
    S(3),
    M(4),
    L(5),
    XL(6),
    XXl(7),
    XXXL(8);

    @Getter
    int id;

    Size(int id) {
        this.id = id;
    }
}
