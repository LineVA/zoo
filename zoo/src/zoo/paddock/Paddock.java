/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoo.paddock;

import zoo.paddock.biome.BiomeAttributes;
import zoo.paddock.biome.Biome;
import exception.IncorrectDataException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
// @EqualsAndHashCode(exclude={"nightTemperature", "dayTemperature", "pluviometry",
// "treeDensity", "treeHeight", "drop", "waterSalinity", "humidity"})
@EqualsAndHashCode()
public class Paddock implements Cloneable{

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

    /**
     * Its biome's name
     */
    @Getter
    private String biome;

    @Getter
    BiomeAttributes attributes;

    /**
     * The main constructor of the class Because no biome is known, the fields
     * take the values of the ones forme Biome.NONE
     *
     * @param name the name of the paddock
     * @param coor the coordinates of the paddock
     */
    public Paddock(String name, PaddockCoordinates coor) {
        this.name = name;
        this.coordinates = coor;
        this.biome = Biome.NONE.getName();
        this.attributes = (BiomeAttributes) Biome.NONE.getAttributes().clone();
    }

    /**
     * Setetr of the fields linked to the biome
     *
     * @param biome the new biome of the paddock
     */
    public void setBiome(Biome biome) {
        this.biome = biome.getName();
        this.attributes = (BiomeAttributes) biome.getAttributes().clone();
    }

    private boolean isPositivOrZero(double test) {
        return test >= 0.0;
    }

    private boolean isLowerOrEqualsThanOne(double test) {
        return test <= 1.0;
    }

    public void setNightTemperature(double night) {
        this.attributes.setNightTemperature(night);
    }

    public void setDayTemperature(double day) {
        this.attributes.setDayTemperature(day);
    }

    public void setPluviometry(double pluvio) throws IncorrectDataException {
        if (isPositivOrZero(pluvio)) {
            this.attributes.setPluviometry(pluvio);
        } else {
            throw new IncorrectDataException("The pluviometry must be "
                    + "greater or equals than zero.");
        }
    }

    public void setTreeDensity(double density) throws IncorrectDataException {
        if (isPositivOrZero(density)) {
            this.attributes.setTreeDensity(density);
        } else {
            throw new IncorrectDataException("The tree's density must be "
                    + "greater or equals than zero.");
        }
    }

    public void setTreeHeight(double height) throws IncorrectDataException {
        if (isPositivOrZero(height)) {
            this.attributes.setTreeHeight(height);
        } else {
            throw new IncorrectDataException("The tree's height must be "
                    + "greater or equals than zero.");
        }
    }

    public void setDrop(double drop) throws IncorrectDataException {
        if (isPositivOrZero(drop)) {
            this.attributes.setDrop(drop);
        } else {
            throw new IncorrectDataException("The drop must be "
                    + "greater or equals than zero.");
        }
    }

    public void setWaterSalinity(double water) throws IncorrectDataException {
        if (isPositivOrZero(water) && isLowerOrEqualsThanOne(water)) {
            this.attributes.setWaterSalinity(water);
        } else {
            throw new IncorrectDataException("The water salinity must be "
                    + "between 0 and 1.");
        }
    }

    public void setHumidity(double humidity) throws IncorrectDataException {
        if (isPositivOrZero(humidity) && isLowerOrEqualsThanOne(humidity)) {
            this.attributes.setHumidity(humidity);
        } else {
            throw new IncorrectDataException("The humidity must be "
                    + "between 0 and 1.");
        }
    }
    
    public Object clone() {
	    Paddock pad = null;
	    try {
	      	pad = (Paddock) super.clone();
	    } catch(CloneNotSupportedException cnse) {
	      	// Ne devrait jamais arriver car nous implémentons 
	      	// l'interface Cloneable
	      	cnse.printStackTrace(System.err);
	    }
	    
	    // On clone l'attribut de type Patronyme qui n'est pas immuable.
	    pad.attributes = (BiomeAttributes) attributes.clone();
	    
	    // on renvoie le clone
	    return pad;
	}
}
