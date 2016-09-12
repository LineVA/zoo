package zoo.paddock.biome;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import launch.options.Option;
import lombok.Getter;

/**
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
    TROPICALCONIFEROUS (3,  0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    TEMPERATEBROADLEAF(4,  0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    TEMPERATECONIFEROUS(5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    TAIGA(6, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    TROPICALGRASSLANDS(7, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    TEMPERATEGRASSLANDS(8, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    FLOODEDGRASSLANDS(9,  0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    MONTANE(10, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    TUNDRA(11,  0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    MEDITERRANEAN(12, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    DESERT(13,  0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
    MANGROVE(14, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

    @Getter
        private final int id;
    @Getter
        private BiomeAttributes attributes;
    Option option;
    
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

    public boolean equals(Biome second) {
        return second.getId() == this.id;
//        return this.getName().equals(second.getName());
    }
    
//    public Biome findByName(String name) throws UnknownNameException{
//        for(Biome biome : Biome.values()){
//            if(biome.getName().equalsIgnoreCase(name)){
//                return biome;
//            }
//        }
//        throw new UnknownNameException("No biome has this name.");
//    }
    
    public Biome findById(int id) throws UnknownNameException{
          for(Biome biome : Biome.values()){
            if(biome.getId() == id){
                return biome;
            }
        }
        throw new UnknownNameException("No biome has this identifier.");
    }
    
     public String toStringByLanguage(){
        return this.option.getPaddockBundle().getString(this.toString().toUpperCase() + "_DESCRIPTION");
    }
     
   public ArrayList<String> list() {
        ArrayList<String> list = new ArrayList<>();
        for (Biome biome : Biome.values()) {
            list.add(biome.id + " - " +
                    this.option.getPaddockBundle().getString(biome.toString().toUpperCase() +"_DESCRIPTION"));
        }
        return list;
    }
}
