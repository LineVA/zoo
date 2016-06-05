package zoo.animal.feeding;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum Diet {

    NONE(0),
    APHIDIPHAGOUS(1),
    CARNIVOROUS(2),
    HEMATOPHAGOUS(3),
    INSECTIVOROUS(4),
    LEPIDOPHAGOUS(5),
    MOLLUSCIVOROUS(6),
    MYRMECOPHAGOUS(7),
    NECROPHAGOUS(8),
    OOPHAGOUS(9),
    OPHIOPHAGOUS(10),
    ORNITHOPHAGOUS(11),
    PISCIVOROUS(12),
    PLANCTONIVOROUS(13),
    VERMIVOROUS(14),
    BACCIVOROUS(15),
    EXSUDATIVOROUS(16),
    FRUGIVOROUS(17),
    GRANIVOROUS(18),
    HERBIVOROUS(19),
    MELLIPHAGOUS(20),
    MYCOPHAGOUS(21),
    NECTARIVOROUS(22),
    PHYLLOPHAGOUS(23),
    POLLINOVOROUS(24),
    XYLOPHAGOUS(25),
    ZEOPHAGOUS(26),
    FOLIVOROUS(27);

    @Getter
    int id;

    Diet(int id) {
        this.id = id;
    }
}
