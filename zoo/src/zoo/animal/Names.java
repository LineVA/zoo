package zoo.animal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
    private final List<String> additionalFrenchNames;
    @Getter
    private final String englishName;
    @Getter
    private final List<String> additionalEnglishNames;
    @Getter
    private final String scientificName;

    public Names(String frenchName, String englishName, String scientificName,
            List<String> additionalFrenchNames, List<String> additionalEnglishNames) {
        this.frenchName = frenchName;
        this.englishName = englishName;
        this.scientificName = scientificName;
        this.additionalEnglishNames = additionalEnglishNames;
        this.additionalFrenchNames = additionalFrenchNames;
    }

    public String getNameaccordingToLanguage(Option option) {
        if (option.getLocale().getLanguage().equals("fr")) {
            return this.frenchName;
        } else {
            return this.englishName;
        }
    }

    public String getAdditionalNamesaccordingToLanguage(Option option) {
        if (option.getLocale().getLanguage().equals("fr")) {
            return this.additionalNamesToString(this.additionalFrenchNames);
        } else {
            return this.additionalNamesToString(this.additionalEnglishNames);
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

    private String additionalNamesToString(List<String> names) {
        String info = "";
        String next;
        Iterator it = names.iterator();
        while (it.hasNext()) {
            next = (String) it.next();
            info += next;
            if (it.hasNext()) {
                info += ", ";
            }
        }
        return info;
    }

    public List<String> infoAccordingToOtherLanguages(Option option) {
        List<String> others = new ArrayList<>();
        if (option.getLocale().getLanguage().equals("fr")) {
            others.add(option.getSpecieBundle().getString("ENGLISH_NAME") + this.englishName);
        } else if (option.getLocale().getLanguage().equals("en")) {
            others.add(option.getSpecieBundle().getString("FRENCH_NAME") + this.frenchName);
        }
        return others;
    }

}
