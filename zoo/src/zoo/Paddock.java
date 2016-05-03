/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
 @EqualsAndHashCode()
public class Paddock {
     
     /**
      * The name of the paddock
      */
    @Getter 
    private final String name;
    /**
     * Its coordinates
     */
    @Getter
    private final PaddockCoordinates coordinates;

    public Paddock(String name, PaddockCoordinates coor) {
        this.name = name;
        this.coordinates = coor;
    }
    
}
