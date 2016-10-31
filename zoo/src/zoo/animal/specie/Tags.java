package zoo.animal.specie;

import java.util.Set;
import launch.options.Option;

/**
 * Class with all the tags used to qualify a specie
 * @author doyenm
 */
public class Tags {

    /**
     * The french tags
     */
    private final Set<String> frenchTags;
    /**
     * The english tags
     */
    private final Set<String> englishTags;

    /**
     * Only constructor
     * @param frenchTags Set of french tags 
     * @param englishTags Set of english tags  
     */
    public Tags(Set<String> frenchTags, Set<String> englishTags) {
        this.frenchTags = frenchTags;
        this.englishTags = englishTags;
    }

    /**
     * Getter of the set of tags according to the language
     * @param option the current options
     * @return frenchTags if the current language of the option's Locale is "fr", englishTags else.
     */
    public Set<String> getTagsAccordingToLanguage(Option option) {
        if (option.getLocale().getLanguage().equals("fr")) {
            return this.frenchTags;
        } else {
            return this.englishTags;
        }
    }

}
