package zoo.animal.specie;

import exception.name.UnknownNameException;
import lombok.Getter;
import zoo.animal.feeding.Diet;

/**
 *
 * @author doyenm
 */
public enum Family {
    UNKNOWN(0),
    CANIDAE(1),
    MUSTELIDAE(2),
    ODOBENIDAE(3),
    OTARIIDAE(4),
    PHOCIDAE(5),
    PROCYONIDAE(6),
    URSIDAE(7),
    FELIDAE(8),
    LEPILEMURIDAE(9),
    LEMURIDAE(10),
    INDRIIDAE(11),
    DAUBENTONIIDAE(12),
    VHEIROGALEIDAE(13),
    EQUIDAE(14),
    MACROPODIAE(15),
    ELEPHANTIDAE(16),
    GIRAFFIDAE(17),
    MYRMECOPHAGIDAE(18),
    BOVIDAE(19),
    CAMELIDAE(20),
    DASYPODIDAE(21),
    ANTILOCAPRIDAE(22),
    CAVIIDAE(23),
    RHEIDAE(24),
    SUIDAE(24);
    
//    HERPESTIDAE(9),
//    HYENIDAE(10),
//    VIVERRIDAe(11),
//    PTEROPODIAE(12),
//    CRASEONYCTERIDAE(13),
//    EMBALLONURIDAE(14),
//    FURIPTERIDAE(15),
//    MEGATERMATIDAE(16),
//    MOLOSSIDAE(17),
//    VESPERTILIONIDAE,
//    THYROPTERIDAE,
//    RHINOPOMATIDAE
//    RHINOLOPHIDAE
//    PHYLLOSTOMIDAE
//    NYCTERIDAE,
//    NOCTILIONIDAE,
//    NATALIDAE,
//    MYZOPODIDAE,
//    MYSTACINIDAE,
//    MORMOOPIDAE,
    
    ;
    
    @Getter
    private int id;
    
    Family(int id){
        this.id = id;
    }
    
     public static Family findById(int id) throws UnknownNameException {
        for (Family family : Family.values()) {
            if (family.getId() == id) {
                return family;
            }
        }
        throw new UnknownNameException("No family has this identifier.");
    }
    
}
