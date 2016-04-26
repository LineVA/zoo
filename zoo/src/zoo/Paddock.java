/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoo;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class Paddock {

    @Getter
    private String name;
    @Getter
    private PaddockCoordinates coordinates;

    public Paddock(String name, PaddockCoordinates coor) {
        this.name = name;
        this.coordinates = coor;
    }
}
