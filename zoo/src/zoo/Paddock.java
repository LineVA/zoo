/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoo;

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

    /**
     * Its biome
     */
    @Getter
    private String biome;

    @Getter 
    private double nightTemperature;
    @Getter 
    private double dayTemperature;
    @Getter
    private double pluviometry;
    @Getter 
    private double treeDensity;
    @Getter 
    private double treeHeight;
    @Getter 
    private double drop;
    @Getter 
    private double waterSalinity;
    @Getter 
    private double humidity;
    
    /**
     * The main constructor of the class
     * Because no biome is known, the fields take the values
     * of the ones forme Biome.NONE
     * @param name the name of the paddock
     * @param coor the coordinates of the paddock
     */
    public Paddock(String name, PaddockCoordinates coor) {
        this.name = name;
        this.coordinates = coor;
        this.biome = Biome.NONE.getName();
        this.nightTemperature = Biome.NONE.getNightTemperature();
        this.dayTemperature = Biome.NONE.getDayTemperature();
        this.pluviometry = Biome.NONE.getPluviometry();
        this.treeDensity = Biome.NONE.getTreeDensity();
        this.treeHeight = Biome.NONE.getTreeHeight();
        this.drop = Biome.NONE.getDrop();
        this.waterSalinity = Biome.NONE.getWaterSalinity();
        this.humidity = Biome.NONE.getHumidity();
    }

    /**
     * Setetr of the fields linked to the biome
     * @param biome the new biome of the paddock
     */
    public void setBiome(Biome biome) {
        this.biome = biome.getName();
        this.nightTemperature = biome.getNightTemperature();
        this.dayTemperature = biome.getDayTemperature();
        this.pluviometry = biome.getPluviometry();
        this.treeDensity = biome.getTreeDensity();
        this.treeHeight = biome.getTreeHeight();
        this.drop = biome.getDrop();
        this.waterSalinity = biome.getWaterSalinity();
        this.humidity = biome.getHumidity();
    }

    
    private boolean isPositivOrZero(double test) {
        return test >= 0.0;
    }

    private boolean isLowerOrEqualsThanOne(double test) {
        return test <= 1.0;
    }

    public void setNightTemperature(double night) {
        this.nightTemperature = night;
    }

    public void setDayTemperature(double day) {
            this.dayTemperature = day;
    }

    public void setPluviometry(double pluvio) throws IncorrectDataException {
        if (isPositivOrZero(pluvio)) {
            this.pluviometry = pluvio;
        } else {
            throw new IncorrectDataException("The pluviometry must be "
                    + "greater or equals than zero.");
        }
    }

    public void setTreeDensity(double density) throws IncorrectDataException {
        if (isPositivOrZero(density)) {
            this.treeDensity = density;
        } else {
            throw new IncorrectDataException("The tree's density must be "
                    + "greater or equals than zero.");
        }
    }

    public void setTreeHeight(double height) throws IncorrectDataException {
        if (isPositivOrZero(height)) {
            this.treeHeight = height;
        } else {
            throw new IncorrectDataException("The tree's height must be "
                    + "greater or equals than zero.");
        }
    }

    public void setDrop(double drop) throws IncorrectDataException {
        if (isPositivOrZero(drop)) {
            this.drop = drop;
        } else {
            throw new IncorrectDataException("The drop must be "
                    + "greater or equals than zero.");
        }
    }

    public void setWaterSalinity(double water) throws IncorrectDataException {
        if (isPositivOrZero(water) && isLowerOrEqualsThanOne(water)) {
            this.waterSalinity = water;
        } else {
            throw new IncorrectDataException("The water salinity must be "
                    + "between 0 and 1.");
        }
    }

    public void setHumidity(double humidity) throws IncorrectDataException {
        if (isPositivOrZero(humidity) && isLowerOrEqualsThanOne(humidity)) {
            this.humidity = humidity;
        } else {
            throw new IncorrectDataException("The humidity must be "
                    + "between 0 and 1.");
        }
    }
}
