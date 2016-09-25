package zoo.paddock;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import launch.options.Option;
import lombok.Getter;

/**
 *
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
    
    private Option option;

    public void setOption(Option option){
         for (PaddockTypes type : PaddockTypes.values()) {
             type.option = option;
         }
    }
    
    public PaddockTypes findById(int id) throws UnknownNameException {
        for (PaddockTypes pad : PaddockTypes.values()) {
            if (pad.getId() == id) {
                return pad;
            }
        }
        throw new UnknownNameException(this.option.getPaddockBundle().getString("PT_UNKNOWN_ID"));
    }
    
      public String toStringByLanguage(){
           return this.option.getPaddockBundle().getString("PT_" + this.toString().toUpperCase());
      }
    
    public ArrayList<String> list(){
         ArrayList<String> list = new ArrayList<>();
        for (PaddockTypes type : PaddockTypes.values()) {
            list.add(type.getId() + " - " + this.option.getPaddockBundle().getString(type.toStringByLanguage()));
        }
        return list;
    }
}
