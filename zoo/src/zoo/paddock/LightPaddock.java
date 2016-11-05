package zoo.paddock;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
public class LightPaddock {

    @Setter
    @Getter
    Set<String> names;
    @Setter
    @Getter
    Set<Integer> biomes;
    @Setter
    @Getter
    Set<Integer> types;

    public LightPaddock(Set<String> names, Set<Integer> biomes, Set<Integer> types) {
        this.names = names;
        this.biomes = biomes;
        this.types = types;
    }

}
