package zoo.paddock.biome;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.List;
import launch.options.Option;
import lombok.Getter;

/**
 * The terrestrial biomes according to the WWF
 *
 * @author doyenm
 */
public enum Biome implements Cloneable {
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

    NONE(0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    RAINFOREST(1, 22.0, 27.5, 2400.0, 150.0, 40.0, 0.0, 0.0, 0.8),
    DRYBROADLEAF(2, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    TROPICALCONIFEROUS(3, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    TEMPERATEBROADLEAF(4, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    TEMPERATECONIFEROUS(5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    TAIGA(6, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    TROPICALGRASSLANDS(7, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    TEMPERATEGRASSLANDS(8, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    FLOODEDGRASSLANDS(9, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    MONTANE(10, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    TUNDRA(11, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    MEDITERRANEAN(12, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    DESERT(13, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    MANGROVE(14, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

    @Getter
    private final int id;
    @Getter
    private BiomeAttributes attributes;
    /**
     * The option used to know the current language
     */
    private Option option;

    public void setOption(Option option) {
        for (Biome biome : Biome.values()) {
            biome.option = option;
        }
    }

    Biome(int id, double night, double day, double pluvio, double treeD,
            double treeH, double drop, double water, double humidity) {
        this.id = id;
        if (isPositivOrZero(pluvio) && isPositivOrZero(treeD)
                && isPositivOrZero(treeH) && isPositivOrZero(drop)
                && isPositivOrZero(water) && isPositivOrZero(water)
                && isPositivOrZero(humidity) && isLowerOrEqualsThanOne(humidity)) {
            this.attributes = new BiomeAttributes(night, water, pluvio, treeD,
                    treeH, drop, water, humidity);
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

    /**
     * Test the equality of two biomes according to their identifier
     * @param second the biome we compare to the current one
     * @return true if they are equals
     */
    public boolean equals(Biome second) {
        return second.getId() == this.id;
    }

       /**
     * Find a biome according to its name and the current language
     * @param name the name to search
     * @return the corresponding diet
     * @throws UnknownNameException if the name matches none of the biomes 
     */
    public Biome findByNameAccordingToLanguage(String name) throws UnknownNameException {
        for (Biome biome : Biome.values()) {
            if (biome.toStringByLanguage().equalsIgnoreCase(name)) {
                return biome;
            }
        }
        throw new UnknownNameException("No biome has this name.");
    }

    /**
     * Find a biome by its identifier
     *
     * @param id the identifier to search
     * @return the corresponding diet
     * @throws UnknownNameException if no biome matches the identifier
     */
    public Biome findById(int id) throws UnknownNameException {
        for (Biome biome : Biome.values()) {
            if (biome.getId() == id) {
                return biome;
            }
        }
        throw new UnknownNameException("No biome has this identifier.");
    }

 /**
     * toString with the current language
     * @return the name of the biome, according to the current language
     */    
    public String toStringByLanguage() {
        return this.option.getPaddockBundle().getString(this.toString().toUpperCase() + "_DESCRIPTION");
    }

      /**
     * List all of the biomes
     * @return the list, each item corresponding to an element of the enumeration
     */
    public List<String> list() {
        List<String> list = new ArrayList<>();
        for (Biome biome : Biome.values()) {
            list.add(biome.id + " - "
                    + this.option.getPaddockBundle().getString(biome.toString().toUpperCase() + "_DESCRIPTION"));
        }
        return list;
    }
}
