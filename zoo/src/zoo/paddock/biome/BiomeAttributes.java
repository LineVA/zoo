package zoo.paddock.biome;

import exception.IncorrectDataException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
@EqualsAndHashCode()
public class BiomeAttributes implements Cloneable {

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

    public BiomeAttributes(double nightTemperature, double dayTemperature,
            double pluviometry, double treeDensity, double treeHeight,
            double drop, double waterSalinity, double humidity) {
        this.nightTemperature = nightTemperature;
        this.dayTemperature = dayTemperature;
        this.pluviometry = pluviometry;
        this.treeDensity = treeDensity;
        this.treeHeight = treeHeight;
        this.drop = drop;
        this.waterSalinity = waterSalinity;
        this.humidity = humidity;
    }

    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return o;
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

    @Override
    public String toString() {
        String info = "";
        info += "night temperature = " + this.nightTemperature + ", ";
        info += "day temperature = " + this.dayTemperature + ", ";
        info += "pluviometry = " + this.pluviometry + ", ";
        info += "tree's density = " + this.treeDensity + ", ";
        info += "tree's height = " + this.treeHeight + ", ";
        info += "drop = " + this.drop + ", ";
        info += "water salinity = " + this.waterSalinity + ", ";
        info += "humidity = " + this.humidity;
        return info;
    }

}
