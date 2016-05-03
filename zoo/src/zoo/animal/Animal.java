/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoo.animal;

import zoo.animal.Species;
import zoo.animal.Sex;
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

    public Animal(Species spec, String name, Paddock paddock, Sex sex) {
        this.specie = spec;
        this.name = name;
        this.paddock = paddock;
        this.sex = sex;
    }
    
    
}
