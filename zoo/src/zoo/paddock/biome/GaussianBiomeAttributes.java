package zoo.paddock.biome;

import lombok.Getter;
import zoo.statistics.Gaussian;

/**
 *
 * @author doyenm
 */
public class GaussianBiomeAttributes {

    @Getter
    private Gaussian nightTemperature;
    @Getter
    private Gaussian dayTemperature;
    @Getter
    private Gaussian pluviometry;
    @Getter
    private Gaussian treeDensity;
    @Getter
    private Gaussian treeHeight;
    @Getter
    private Gaussian drop;
    @Getter
    private Gaussian waterSalinity;
    @Getter
    private Gaussian humidity;

    public GaussianBiomeAttributes(BiomeAttributes average) {
        this.nightTemperature = new Gaussian(average.getNightTemperature(), average.getNightTemperature()/10.0);
        this.dayTemperature = new Gaussian(average.getDayTemperature(), average.getDayTemperature()/10.0);
        this.pluviometry = new Gaussian(average.getNightTemperature(), average.getPluviometry()/10.0);
        this.treeDensity = new Gaussian(average.getTreeDensity(), average.getTreeDensity()/10.0);
        this.treeHeight = new Gaussian(average.getTreeHeight(), average.getTreeHeight()/10.0);
        this.drop = new Gaussian(average.getDrop(), average.getDrop()/10.0);
        this.waterSalinity = new Gaussian(average.getWaterSalinity(), average.getWaterSalinity()/10.0);
        this.humidity = new Gaussian(average.getHumidity(), average.getHumidity()/10.0);
    }
}
