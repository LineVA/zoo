package zoo.paddock;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
public enum Biome {
    /*
     The 14 biomes according to the WWF
     RAINFOREST -  Forets de feuillus humides tropicales et subtropicales - Tropical rainforest
     DRYBROADLEAF - Forets de feuillus seches tropicales et subtropicales - Tropical and subtropical dry broadleaf forests
     TROPICALCONIFEROUS - Forets de coniferes tropicales et subtropicales - Tropical and subtropical coniferous forests
     TEMPERATEBROADLEAF - Forets de feuillus et forets mixtes temperees - Temperate broadleaf and mixed forest
     TEMPERATECONIFEROUS - Forets de coniferes temperees - Temperate coniferous forest
     TAIGA - Forets boreales et taiga - Taiga
     TROPICALGRASSLAND - Prairies, savanes et brousses tropicales et subtropicales - Tropical and subtropical grasslands, savannas, and shrublands
     TEMPERATEGRASSLAND - Prairies, savanes et brousses temperees - Temperate grasslands, savannas, and shrublands
     FLOODEDGRASSLAND - Prairies et savanes inondables - Flooded grasslands and savannas
     MONTANE - Prairies et brousses d altitude - Montane grasslands and shrublands 
     TUNDRA - Toundra - Tundra
     MEDITERRANEAN - Forets, bois et maquis mediterraneens - Mediterranean forests, woodlands, and scrub
     DESERT - Deserts et brousses xeriques - Deserts and xeric shrublands
     MANGROVE - Mangroves   - Mangrove 
     */

    NONE("No biome", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    RAINFOREST("Tropical rainforest", 22.0, 27.5, 2400.0, 150.0, 40.0, 0.0, 0.0, 0.8),
    DRYBROADLEAF("Tropical and subtropical dry broadleaf forests", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    TROPICALCONIFEROUS("Tropical and subtropical coniferous forests", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    TEMPERATEBROADLEAF("Temperate broadleaf and mixed forest", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    TEMPERATECONIFEROUS("Temperate coniferous forest", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    TAIGA("Taiga", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    TROPICALGRASSLAND("Tropical and subtropical grasslands, savannas, and shrublands", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    TEMPERATEGRASSLAND("Temperate grasslands, savannas, and shrublands", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    FLOODEDGRASSLAND("Flooded grasslands and savannas", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    MONTANE("Montane grasslands and shrublands ", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    TUNDRA("Tundra", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    MEDITERRANEAN("Mediterranean forests, woodlands, and scrub", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    DESERT("Deserts and xeric shrublands", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    MANGROVE("Mangrove", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

    @Getter
    private final String name;
    @Getter @Setter
    private  double nightTemperature;
    @Getter @Setter
    private double dayTemperature;
    @Getter @Setter
    private double pluviometry;
    @Getter @Setter
    private double treeDensity;
    @Getter @Setter
    private double treeHeight;
    @Getter @Setter
    private double drop;
    @Getter @Setter
    private double waterSalinity;
    @Getter @Setter
    private double humidity;

    Biome(String name, double night, double day, double pluvio, double treeD,
            double treeH, double drop, double water, double humidity){
        if (isPositivOrZero(pluvio) && isPositivOrZero(treeD)
                && isPositivOrZero(treeH) && isPositivOrZero(drop)
                && isPositivOrZero(water) && isPositivOrZero(water) 
                && isPositivOrZero(humidity) && isLowerOrEqualsThanOne(humidity)) {
            this.name = name;
            this.nightTemperature = night;
            this.dayTemperature = day;
            this.pluviometry = pluvio;
            this.treeDensity = treeD;
            this.treeHeight = treeH;
            this.drop = drop;
            this.waterSalinity = water;
            this.humidity = humidity;
        } else {
            throw new ExceptionInInitializerError("One or more data are incorrects ;"
                    + " see 'man biome' for more information.");
        }
    }

    private boolean isPositivOrZero(double test) {
        return test >= 0.0;
    }

    private boolean isLowerOrEqualsThanOne(double test) {
        return test <= 1.0;
    }
}
