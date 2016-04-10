/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoo;

/**
 *
 * @author doyenm
 */
public enum Species {
    ELEPHANT(0), 
    JAGUAR(1);
    
    
    private final int idSpecies;
    Species(int id){
        this.idSpecies = id;
    }

    public int getIdSpecies() {
        return idSpecies;
    }
    
    
}
