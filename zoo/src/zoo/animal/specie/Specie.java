package zoo.animal.specie;

import zoo.animal.conservation.BreedingProgramme;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import launch.options.Option;
import lombok.Getter;
import zoo.animal.Names;
import zoo.animal.conservation.ConservationStatus;
import zoo.animal.death.GaussianLifeSpanAttributes;
import zoo.animal.death.LifeSpanAttributes;
import zoo.animal.feeding.Diet;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.feeding.GaussianFeedingAttributes;
import zoo.animal.reproduction.GaussianReproductionAttributes;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.animal.social.GaussianSocialAttributes;
import zoo.animal.social.SocialAttributes;
import zoo.paddock.GaussianTerritoryAttributes;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.Biome;
import zoo.paddock.biome.BiomeAttributes;
import zoo.paddock.biome.Continent;
import zoo.paddock.biome.Ecoregion;
import zoo.paddock.biome.GaussianBiomeAttributes;

/**
 *
 * @author doyenm
 */
public class Specie {

    @Getter
    private final BiomeAttributes specieBiome;
    @Getter
    private final List<Integer> diets;
    @Getter
    private final int family;
    @Getter
    private final FeedingAttributes specieFeeding;
    @Getter
    private final ReproductionAttributes specieReproduction;
    @Getter
    private final LifeSpanAttributes specieLifeSpan;
    @Getter
    private final SocialAttributes specieSocial;
    @Getter
    private final TerritoryAttributes specieTerritory;
    @Getter
    private final Names names;
    @Getter
    private final int ecoregion;
    @Getter
    private final int conservation;
    @Getter
    private final List<Integer> biomes;
    @Getter
    private final int size;
    @Getter
    private final List<Integer> continents;
    @Getter
    private GaussianBiomeAttributes gaussianBiome;
    @Getter
    private GaussianFeedingAttributes gaussianFeeding;
    @Getter
    private GaussianReproductionAttributes gaussianReproduction;
    @Getter
    private GaussianLifeSpanAttributes gaussianLifeSpanAttributesSpan;
    @Getter
    private GaussianSocialAttributes gaussianSocialAttributes;
    @Getter
    private GaussianTerritoryAttributes gaussianTerritoryAttributes;
    @Getter
    private DocumentationURI documentation;
    @Getter
    int breedingProgramme;

    public Specie(Names names, DocumentationURI docu, BiomeAttributes biomeAtt, FeedingAttributes feeding,
            List<Integer> diets, ReproductionAttributes repro,
            LifeSpanAttributes lifeSpan, int conservation,
            SocialAttributes social, TerritoryAttributes territory,
            int ecoregion, int family, List<Integer> biomes,
            int size, List<Integer> continents, int breedingProgramme) {
        this.names = names;
        this.specieBiome = biomeAtt;
        this.diets = diets;
        this.family = family;
        this.ecoregion = ecoregion;
        this.specieFeeding = feeding;
        this.specieReproduction = repro;
        this.specieLifeSpan = lifeSpan;
        this.specieSocial = social;
        this.specieTerritory = territory;
        this.conservation = conservation;
        this.biomes = biomes;
        this.continents = continents;
        this.gaussianBiome = new GaussianBiomeAttributes(biomeAtt);
        this.gaussianFeeding = new GaussianFeedingAttributes(feeding);
        this.gaussianReproduction = new GaussianReproductionAttributes(repro);
        this.gaussianLifeSpanAttributesSpan = new GaussianLifeSpanAttributes(lifeSpan);
        this.gaussianSocialAttributes = new GaussianSocialAttributes(social);
        this.gaussianTerritoryAttributes = new GaussianTerritoryAttributes(territory);
        this.size = size;
        this.documentation = docu;
        this.breedingProgramme = breedingProgramme;
    }

    public boolean canBeInTheSamePaddock(Specie specie) throws UnknownNameException {
        for (Integer diet : this.diets) {
            if (Diet.NONE.findById(diet).isCompatible(specie.diets)
                    && this.ecoregion == specie.ecoregion) {
                return Size.UNKNOWN.findById(this.size).areCloseEnough(specie.size);
            }
        }
        return false;
    }

    public boolean canBeAfraidOf(Specie specie) throws UnknownNameException {
        for (Integer diet : this.diets) {
            for (Integer specDiet : specie.getDiets()) {
                if (Diet.NONE.findById(diet).canBeEatenBy(specDiet)
                        && this.ecoregion == specie.ecoregion) {
                    return Size.UNKNOWN.findById(this.size).areCloseEnough(specie.size);
                }
            }
        }
        return false;
    }

    public List<String> info(Option option) throws UnknownNameException {
        List<String> info = new ArrayList<>();
        ResourceBundle bundle = option.getSpecieBundle();
        info.add(bundle.getString("NAME") + this.names.getNameAccordingLanguage(option));
        info.add(bundle.getString("OTHER_NAME") + this.names.getAdditionalNamesAccordingLanguage(option));
        info.add(bundle.getString("SCIENTIFIC_NAME") + this.names.getScientificName());
        info.add(bundle.getString("CONSERVATION") + ConservationStatus.UNKNOWN.findById(this.conservation).toStringByLanguage());
        info.add(bundle.getString("BREEDING_PROGRAMME") + BreedingProgramme.NONE.findById(this.breedingProgramme).toStringByLanguage());
        info.add(bundle.getString("CONTINENT") + this.continentsToString());
        info.add(bundle.getString("BIOME") + this.biomesToString());
        info.add(bundle.getString("ECOREGION") + Ecoregion.UNKNOWN.findById(this.ecoregion).toStringByLanguage());
        info.add(bundle.getString("FAMILY") + Family.UNKNOWN.findById(this.family).toStringByLanguage());
        info.add(bundle.getString("DIET") + this.dietsToString());
        info.add(bundle.getString("SIZE") + Size.UNKNOWN.findById(size).toStringByLanguage());
        info.add(bundle.getString("REPRODUCTION_ATT") + this.specieReproduction.toStringByLanguage(option));
        info.add(bundle.getString("LIFESPAN_ATT") + this.specieLifeSpan.toStringByLanguage(option));
        info.add(bundle.getString("SOCIAL_ATT") + this.specieSocial.toStringByLanguage(option));
        info.add(bundle.getString("FEEDING_ATT") + this.specieFeeding.toStringByLanguage(option));
        info.add(bundle.getString("TERRITORY_ATT") + this.specieTerritory.toStringByLanguage(option));
        info.add(bundle.getString("ADD_INFO"));
        return info;
    }

    private String dietsToString() throws UnknownNameException {
        String info = "";
        int next;
        Iterator it = this.diets.iterator();
        while (it.hasNext()) {
            next = (Integer) it.next();
            info += Diet.NONE.findById(next).toStringByLanguage();
            if (it.hasNext()) {
                info += ", ";
            }
        }
        return info;
    }

    private String continentsToString() throws UnknownNameException {
        String info = "";
        int next;
        Iterator it = this.continents.iterator();
        while (it.hasNext()) {
            next = (Integer) it.next();
            info += Continent.UNKNOWN.findById(next).toStringByLanguage();
            if (it.hasNext()) {
                info += ", ";
            }
        }
        return info;
    }

    private String biomesToString() throws UnknownNameException {
        String info = "";
        int next;
        Iterator it = this.biomes.iterator();
        while (it.hasNext()) {
            next = (Integer) it.next();
            info += Biome.NONE.findById(next).toStringByLanguage();
            if (it.hasNext()) {
                info += ", ";
            }
        }
        return info;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.names);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Specie other = (Specie) obj;
        return Objects.equals(this.names, other.names);
    }

    public String getNameAccordingLanguage(Option option) {
        return this.names.getNameAccordingLanguage(option);
    }

    public boolean equals(LightSpecie light) {
        return Objects.equals(this.names, light.getNames());
    }

    public boolean compare(LightSpecie lightSpecie) {
        boolean isCorresponding = true;
        if (null != lightSpecie.getBiome()) {
            isCorresponding &= this.biomes.containsAll((lightSpecie.getBiome()));
        }
        if (null != lightSpecie.getEcoregion()) {
            isCorresponding &= lightSpecie.getEcoregion().get(0) == this.ecoregion;
        }
        if (null != lightSpecie.getDiet()) {
            isCorresponding &= this.diets.containsAll(lightSpecie.getDiet());
        }
        if (null != lightSpecie.getFamily()) {
            if (lightSpecie.getFamily().size() > 1) {
                return false;
            }
            isCorresponding &= lightSpecie.getFamily().get(0) == this.family;
        }
        if (null != lightSpecie.getConservation() && lightSpecie.getConservation().size() < 2) {
            if (lightSpecie.getConservation()
                    .size() > 1) {
                return false;
            }
            isCorresponding &= lightSpecie.getConservation().get(0) == this.conservation;
        }
        if (null != lightSpecie.getSize()) {
            if (lightSpecie.getSize().size() > 1) {
                return false;
            }
            isCorresponding &= lightSpecie.getSize().get(0) == this.size;
        }
        if (null != lightSpecie.getContinent()) {
            if (lightSpecie.getContinent().size() > 1) {
                return false;
            }
            isCorresponding &= this.continents.containsAll(lightSpecie.getContinent());
        }
        if (null != lightSpecie.getBreedingProgramme()) {
            if (lightSpecie.getBreedingProgramme().size() > 1) {
                return false;
            }
            isCorresponding &= lightSpecie.getBreedingProgramme().get(0) == this.breedingProgramme;
        }
        return isCorresponding;
    }

}
