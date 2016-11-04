package zoo.animal.specie;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.List;
import launch.options.Option;
import lombok.Getter;

/**
 * The sizes
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
    
    /**
     * Option used to select the current language
     */
    private Option option;
    
    public void setOption(Option option) {
        for (Size size : Size.values()) {
             size.option = option;
         }
    }
    
    /**
     * Return the name of the size, According to the current language
     * @return the name of the size
     */
    public String toStringByLanguage(){
        return this.option.getSpecieBundle().getString("SIZE." + this.toString().toUpperCase());
    }
    
    /**
     * Find a size by it identifier
     * @param id the identifier to search
     * @return the corresponding size
     * @throws UnknownNameException if no size matches the identifier 
     */
    public Size findById(int id) throws UnknownNameException {
        for (Size size : Size.values()) {
            if (size.getId() == id) {
                return size;
            }
        }
        throw new UnknownNameException(
                this.option.getSpecieBundle().getString("SIZE.UNKNOWN_SIZE_BY_ID"));
    }
    
    /**
     * Check if the current size is in a diameter o 2 around a given size
     * @param size the size we compare with the current one
     * @return true if current_size greater or equals than (size - 2)
     * and current_size lower or equals than (size + 2)
     */
    public boolean isEnoughNearFrom(int size){
        return this.id >= size - 2 && this.id <= size + 2;
    }
    
    /**
     * Check if there is less than two units between two sizes
     * @param size the size with which we compare the current one
     * @return true if there is less than two units between them
     */
    public boolean areCloseEnough(int size){
        return ((this.getId() - size >= -1) && (this.getId() - size<= 2));
    }
    
    /**
     * List all of the sizes
     * @return the list, each item corresponding to an element of the enumeration
     */
      public List<String> list(){
         List<String> list = new ArrayList<>();
        for (Size size : Size.values()) {
            list.add(size.id + " - " + 
                    size.toStringByLanguage());
        }
        return list;
    }
}
