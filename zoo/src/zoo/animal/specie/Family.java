package zoo.animal.specie;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.List;
import launch.options.Option;
import lombok.Getter;

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
    SUIDAE(25),
    CEBIDAE(26),
    TAPIRIDAE(27),
    CERVIDAE(28),
    CASTORIDAE(29),
    HYLOBATIDAE(30), 
    CERCOPITHECIDAE(31),
    PITHECIIDAE(32),
    CRICETINAE(33);
    
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

    @Getter
    private int id;
      /**
     * The option used to know the current language
     */
    private Option option;

     public void setOption(Option option) {
        for (Family family : Family.values()) {
             family.option = option;
         }
    }
    
    Family(int id) {
        this.id = id;
    }

      /**
     * Find a family by its identifier
     * @param id the identifier to search
     * @return the corresponding diet
     * @throws UnknownNameException if no family matches the identifier 
     */
    public Family findById(int id) throws UnknownNameException {
        for (Family family : Family.values()) {
            if (family.getId() == id) {
                return family;
            }
        }
        throw new UnknownNameException(
                this.option.getFamilyBundle().getString("UNKNOWN_NAME"));
    }
    
     /**
     * toString with the current language
     * @return the name of the family, according to the current language
     */
     public String toStringByLanguage(){
         return this.toString();
//        return this.option.getFamilyBundle().getString(this.toString().toUpperCase());
    }
     
       /**
     * List all of the families
     * @return the list, each item corresponding to an element of the enumeration
     */
     public List<String> list() {
        List<String> list = new ArrayList<>();
        for (Family family : Family.values()) {
            list.add(family.id + " - " +
                    family.toString());
        }
        return list;
    }

}
