package zoo.animal;

import java.util.ArrayList;
import java.util.Objects;
import launch.options.Option;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class Names {

    @Getter
    private final String frenchName;
    @Getter
    private final ArrayList<String> additionalFrenchNames;
    @Getter
    private final String englishName;
    @Getter
    private final ArrayList<String> additionalEnglishNames;
    @Getter
    private final String scientificName;

    public Names(String frenchName, String englishName, String scientificName,
            ArrayList<String> additionalFrenchNames, ArrayList<String> additionalEnglishNames) {
        this.frenchName = frenchName;
        this.englishName = englishName;
        this.scientificName = scientificName;
        this.additionalEnglishNames = additionalEnglishNames;
        this.additionalFrenchNames = additionalFrenchNames;
    }

    public String getNameAccordingLanguage(Option option) {
        if (option.getLocale().getLanguage().equals("fr")) {
            return this.frenchName;
        } else {
            return this.englishName;
        }
    }
    
     public ArrayList<String> getAdditionalNamesAccordingLanguage(Option option) {
        if (option.getLocale().getLanguage().equals("fr")) {
            return this.additionalFrenchNames;
        } else {
            return this.additionalEnglishNames;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.englishName);
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
        final Names other = (Names) obj;
        if (!Objects.equals(this.englishName, other.englishName)) {
            return false;
        }
        return true;
    }

}
