package zoo.animal;

import java.util.Objects;
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
