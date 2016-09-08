package zoo.animal.feeding;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import launch.options.Option;
import lombok.Getter;
import lombok.Setter;
import zoo.animal.reproduction.Sex;
import zoo.animal.specie.Family;

/**
 *
 * @author doyenm
 */
public enum Size {

    UNKNOWN(0),
    XXS(1),
    XS(2),
    S(3),
    M(4),
    L(5),
    XL(6),
    XXL(7),
    XXXL(8);

    @Getter
    int id;

    Size(int id) {
        this.id = id;
    }
    
    private Option option;
    
    public void setOption(Option option) {
        for (Size size : Size.values()) {
             size.option = option;
         }
    }
    
    public String toStringByLanguage(){
        return this.option.getSizeBundle().getString(this.toString().toUpperCase());
    }
    
    public Size findSizeById(int id) throws UnknownNameException {
        for (Size size : Size.values()) {
            if (size.getId() == id) {
                return size;
            }
        }
        throw new UnknownNameException(
                this.option.getDietBundle().getString("UNKNOWN_SIZE_BY_ID"));
    }
    
    public boolean isEnoughNearFrom(int size){
        return this.id >= size - 2 && this.id <= size + 2;
    }
    
    public boolean areCloseEnough(int size){
        return ((this.getId() - size >= -1) && (this.getId() - size<= 2));
    }
    
      public ArrayList<String> list(){
         ArrayList<String> list = new ArrayList<>();
        for (Size size : Size.values()) {
            list.add(
                    this.option.getSizeBundle().getString(size.toString().toUpperCase()));
        }
        return list;
    }
}
