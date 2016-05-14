package zoo.animal;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class Names {

    @Getter
    private final String frenchName;
    @Getter
    private final String englishName;
    @Getter
    private final String scientificName;

    public Names(String frenchName, String englishName, String scientificName) {
        this.frenchName = frenchName;
        this.englishName = englishName;
        this.scientificName = scientificName;
    }
}
