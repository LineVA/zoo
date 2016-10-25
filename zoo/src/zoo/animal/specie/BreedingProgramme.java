package zoo.animal.specie;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum BreedingProgramme {

    NONE(0),
    EEP(1),
    ESB(2);

    @Getter
    private int id;

    BreedingProgramme(int id) {
        this.id = id;
    }
}
