package zoo.animal.feeding;

import launch.options.Option;
import lombok.Getter;
import lombok.Setter;

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
    XXL(7),
    XXXL(8);

    @Getter
    int id;

    Size(int id) {
        this.id = id;
    }
    
    @Setter
    private Option option;
    
    public String toStringByLanguage(){
        return this.option.getDietBundle().getString(this.toString().toUpperCase());
    }
}
