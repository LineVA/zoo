package zoo.paddock;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@EqualsAndHashCode()
public class BiomeAttributes implements Cloneable {

    @Getter
    @Setter
    private double nightTemperature;
    @Getter
    @Setter
    private double dayTemperature;
    @Getter
    @Setter
    private double pluviometry;
    @Getter
    @Setter
    private double treeDensity;
    @Getter
    @Setter
    private double treeHeight;
    @Getter
    @Setter
    private double drop;
    @Getter
    @Setter
    private double waterSalinity;
    @Getter
    @Setter
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

}
