package zoo.paddock;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.List;
import launch.options.Option;
import lombok.Getter;

/**
 *  The paddock types
 * @author doyenm
 */
public enum PaddockTypes {

    UNKNOWN(0),
    AQUARIUM(1),
    AVIARY(2),
    CONTACT(3),
    ISLAND(4),
    LOWLAND(5),
    PIT(6),
    VIVARIUM(7);
    
    @Getter
    private int id;

    PaddockTypes(int id){
        this.id = id;
    }
    
 /**
     * The option used to know the current language
     */
    private Option option;
    
    public void setOption(Option option){
         for (PaddockTypes type : PaddockTypes.values()) {
             type.option = option;
         }
    }
    
        /**
     * Find a paddock type by its identifier
     *
     * @param id the identifier to search
     * @return the corresponding type
     * @throws UnknownNameException if no type matches the identifier
     */
    public PaddockTypes findById(int id) throws UnknownNameException {
        for (PaddockTypes pad : PaddockTypes.values()) {
            if (pad.getId() == id) {
                return pad;
            }
        }
        throw new UnknownNameException(this.option.getPaddockBundle().getString("PT_UNKNOWN_ID"));
    }
    
        /**
     * Find a paddock type according to its name and the current language
     * @param name the name to search
     * @return the corresponding diet
     * @throws UnknownNameException if the name matches none of the types 
     */
    public PaddockTypes findByNameAccordingToLanguage(String name) throws UnknownNameException {
        for (PaddockTypes pad : PaddockTypes.values()) {
            if (pad.toStringByLanguage().equalsIgnoreCase(name)) {
                return pad;
            }
        }
        throw new UnknownNameException(this.option.getPaddockBundle().getString("PT_UNKNOWN_NAME"));
    }
    
     /**
     * toString with the current language
     * @return the name of the paddock type, according to the current language
     */    
      public String toStringByLanguage(){
           return this.option.getPaddockBundle().getString("PT_" + this.toString().toUpperCase());
      }
    
         /**
     * List all of the paddock types
     * @return the list, each item corresponding to an element of the enumeration
     */
    public List<String> list(){
         List<String> list = new ArrayList<>();
        for (PaddockTypes type : PaddockTypes.values()) {
            list.add(type.getId() + " - " + type.toStringByLanguage());
        }
        return list;
    }
}
