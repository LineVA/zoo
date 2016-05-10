package zoo.paddock.biome;

import lombok.Getter;
import zoo.Statistics.Gaussian;

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

    public GaussianBiomeAttributes(BiomeAttributes average, BiomeAttributes sd) {
        this.nightTemperature = new Gaussian(average.getNightTemperature(), sd.getNightTemperature());
        this.dayTemperature = new Gaussian(average.getDayTemperature(), sd.getDayTemperature());
        this.pluviometry = new Gaussian(average.getNightTemperature(), sd.getPluviometry());
        this.treeDensity = new Gaussian(average.getTreeDensity(), sd.getTreeDensity());
        this.treeHeight = new Gaussian(average.getTreeHeight(), sd.getTreeHeight());
        this.drop = new Gaussian(average.getDrop(), sd.getDrop());
        this.waterSalinity = new Gaussian(average.getWaterSalinity(), sd.getWaterSalinity());
        this.humidity = new Gaussian(average.getHumidity(), sd.getHumidity());
    }
}
