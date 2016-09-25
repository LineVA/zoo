package zoo.paddock;

import exception.name.UnknownNameException;
import java.util.ArrayList;
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
    
    public PaddockTypes findById(int id) throws UnknownNameException {
        for (PaddockTypes pad : PaddockTypes.values()) {
            if (pad.getId() == id) {
                return pad;
            }
        }
        throw new UnknownNameException("POUET");
    }
    
      public String toStringByLanguage(){
        return "LIST";
    }
    
    public ArrayList<String> list(){
         ArrayList<String> list = new ArrayList<>();
        for (PaddockTypes pad : PaddockTypes.values()) {
            list.add("POUET");
        }
        return list;
    }
}
