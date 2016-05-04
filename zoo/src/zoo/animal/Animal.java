package zoo.animal;

import zoo.paddock.Paddock;
import lombok.Getter;

/**
 *
 * @author doyenm
 */

public class Animal {

    @Getter
    private final Species specie;
    @Getter
    private final String name;
    @Getter
    private final Paddock paddock;
    @Getter
    private final Sex sex;
    @Getter
    private int age;
    

    public Animal(Species spec, String name, Paddock paddock, Sex sex) {
        this.specie = spec;
        this.name = name;
        this.paddock = paddock;
        this.sex = sex;
        this.age = 0;
    }
    
    
}
