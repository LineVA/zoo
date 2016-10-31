package zoo.animal.specie;

import java.util.Set;
import launch.options.Option;

/**
 *
 * @author doyenm
 */
public class Tags {

    private final Set<String> frenchTags;
    private final Set<String> englishTags;

    public Tags(Set<String> frenchTags, Set<String> englishTags) {
        this.frenchTags = frenchTags;
        this.englishTags = englishTags;
    }

    public Set<String> getTagsAccordingToLanguage(Option option) {
        if (option.getLocale().getLanguage().equals("fr")) {
            return this.frenchTags;
        } else {
            return this.englishTags;
        }
    }

}
