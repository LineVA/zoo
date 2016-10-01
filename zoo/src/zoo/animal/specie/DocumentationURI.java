package zoo.animal.specie;

import launch.options.Option;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class DocumentationURI {

    @Getter
    private String frenchWikipedia;
    @Getter
    private String englishWikipedia;
    @Getter
    private String animalDiversity;

    public DocumentationURI(String frenchWikipedia, String englishWikipedia, String animalDiversity) {
        this.frenchWikipedia = frenchWikipedia;
        this.englishWikipedia = englishWikipedia;
        this.animalDiversity = animalDiversity;
    }
    
    public String getWikipediaAccordingLanguage(Option option){
        if (option.getLocale().getLanguage().equals("fr")) {
            return this.frenchWikipedia;
        } else {
            return this.englishWikipedia;
        }
    }
}
