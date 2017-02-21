package zoo.animal.reproduction;

import exception.name.UnknownNameException;
import launch.options.Option;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum ContraceptionMethods {
   NONE(0),
    FEMALE_TEMP_CONTRACEPTION(1),
    MALE_FINAL_CONTRACEPTION(2),
    FEMALE_FINAL_CONTRACEPTION(3);
    
    @Getter
    private int id;

    ContraceptionMethods(int id) {
        this.id = id;
    }
    
     /**
     * The option used to know the current language
     */
    private Option option;

    public void setOption(Option option) {
        for (ContraceptionMethods method : ContraceptionMethods.values()) {
            method.option = option;
        }
    }
    
   /**
     * Find a contraception method according to its name and the current language
     * @param name the name to search
     * @return the corresponding method
     * @throws UnknownNameException if the name matches none of the methods 
     */
    public ContraceptionMethods findByNameAccordingToLanguage(String name)
            throws UnknownNameException {
        for (ContraceptionMethods method : ContraceptionMethods.values()) {
            if (method.toStringByLanguage().equalsIgnoreCase(name)) {
                return method;
            }
        }
        throw new UnknownNameException(
                this.option.getAnimalBundle().getString("CONTRACEPTION.UNKNOWN_METHOD"));
    }

      /**
     * Find a method of contraception by its identifier
     * @param id the identifier to search
     * @return the corresponding method
     * @throws UnknownNameException if no method matches the identifier 
     */
    public ContraceptionMethods findById(int id) throws UnknownNameException {
        for (ContraceptionMethods method : ContraceptionMethods.values()) {
            if (method.getId() == id) {
                return method;
            }
        }
        throw new UnknownNameException(
                this.option.getAnimalBundle().getString("CONTRACEPTION.UNKNOWN_METHOD"));
    }

     /**
     * toString with the current language
     * @return the name of the method, according to the current language
     */
    public String toStringByLanguage() {
        return this.option.getAnimalBundle().getString("CONTRACEPTION_METHOD" + this.toString().toUpperCase());
    }
//
//      /**
//     * List all of the sexes
//     * @return the list, each item corresponding to an element of the enumeration
//     */
//    public List<String> list() {
//        List<String> list = new ArrayList<>();
//        for (Sex sex : Sex.values()) {
//            list.add(
//                    sex.getId() + " - " + sex.toStringByLanguage());
//        }
//        return list;
//    }
}
