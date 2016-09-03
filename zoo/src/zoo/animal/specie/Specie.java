package zoo.animal.specie;

import exception.name.UnknownNameException;
import java.util.ArrayList;
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
import zoo.paddock.biome.BiomeAttributes;
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
    private final int diet;
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
    private final ConservationStatus conservation;
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

    public Specie(Names names, BiomeAttributes biome, FeedingAttributes feeding,
            int diet, ReproductionAttributes repro,
            LifeSpanAttributes lifeSpan, ConservationStatus conservation,
            SocialAttributes social, TerritoryAttributes territory,
            int ecoregion, int family) {
        this.names = names;
        this.specieBiome = biome;
        this.diet = diet;
        this.family = family;
        this.ecoregion = ecoregion;
        this.specieFeeding = feeding;
        this.specieReproduction = repro;
        this.specieLifeSpan = lifeSpan;
        this.specieSocial = social;
        this.specieTerritory = territory;
        this.conservation = conservation;
        this.gaussianBiome = new GaussianBiomeAttributes(biome);
        this.gaussianFeeding = new GaussianFeedingAttributes(feeding);
        this.gaussianReproduction = new GaussianReproductionAttributes(repro);
        this.gaussianLifeSpanAttributesSpan = new GaussianLifeSpanAttributes(lifeSpan);
        this.gaussianSocialAttributes = new GaussianSocialAttributes(social);
        this.gaussianTerritoryAttributes = new GaussianTerritoryAttributes(territory);
    }

    public boolean canBeInTheSamePaddock(Specie specie) throws UnknownNameException {
        return Diet.NONE.findDietById(this.diet).isCompatible(specie.diet)
                && Ecoregion.UNKNOWN.findById(this.ecoregion).equals(specie.ecoregion);
    }

    public boolean canBeAfraidOf(Specie specie) throws UnknownNameException {
        return Diet.NONE.findDietById(this.diet).canBeEatenBy(specie.diet)
                && this.ecoregion == specie.ecoregion;
    }

    public ArrayList<String> info(Option option) throws UnknownNameException {
        ArrayList<String> info = new ArrayList<>();
        ResourceBundle bundle = option.getSpecieBundle();
        info.add(bundle.getString("NAME") + this.names.getEnglishName());
        info.add(bundle.getString("SCIENTIFIC_NAME") + this.names.getScientificName());
        info.add(bundle.getString("CONSERVATION") + this.conservation.toString());
        info.add(bundle.getString("ECOREGION") + Ecoregion.findById(this.ecoregion).toString());
        info.add(bundle.getString("FAMILY") + Family.findById(this.family).toString());
        info.add(bundle.getString("DIET") + Diet.NONE.findDietById(diet).toString());
        info.add(bundle.getString("REPRODUCTION_ATT") + this.specieReproduction.toStringByLanguage(option));
        info.add(bundle.getString("LIFESPAN_ATT") + this.specieLifeSpan.toStringByLanguage(option));
        info.add(bundle.getString("SOCIAL_ATT") + this.specieSocial.toStringByLanguage(option));
        info.add(bundle.getString("FEEDING_ATT") + this.specieFeeding.toStringByLanguage(option));
        info.add(bundle.getString("TERRITORY_ATT") + this.specieTerritory.toStringByLanguage(option));
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
        if (!Objects.equals(this.names, other.names)) {
            return false;
        }
        return true;
    }

    public String getNameAccordingLanguage(Option option) {
        if (option.getLocale().getLanguage().equals("fr")) {
            return this.names.getFrenchName();
        } else {
            return this.names.getEnglishName();
        }
    }

}
