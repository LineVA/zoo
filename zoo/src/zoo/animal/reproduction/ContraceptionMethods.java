package zoo.animal.reproduction;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import launch.options.Option;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum ContraceptionMethods {

    NONE(0),
    FEMALE_TEMP_CONTRACEPTION_PILL(1),
    FEMALE_TEMP_CONTRACEPTION_IMPLANT(2),
    MALE_FINAL_CONTRACEPTION(3),
    FEMALE_FINAL_CONTRACEPTION(4);

    @Getter
    private int id;

    private final static List<Sex> femaleAndMale = Arrays.asList(Sex.FEMALE, Sex.MALE);
    private final static List<Sex> female = Arrays.asList(Sex.FEMALE);
    private final static List<Sex> male = Arrays.asList(Sex.MALE);

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
     * Find a contraception method according to its name and the current
     * language
     *
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
     *
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
     *
     * @return the name of the method, according to the current language
     */
    public String toStringByLanguage() {
        return this.option.getAnimalBundle().getString("CONTRACEPTION_METHOD." + this.toString().toUpperCase());
    }

    /**
     * List all of the methods of contraception
     *
     * @return the list, each item corresponding to an element of the
     * enumeration
     */
    public List<String> list() {
        List<String> list = new ArrayList<>();
        for (ContraceptionMethods method : ContraceptionMethods.values()) {
            list.add(
                    method.getId() + " - " + method.toStringByLanguage());
        }
        return list;
    }

    public List<Sex> getAuthorizedSexes(ContraceptionMethods method) {
        switch (method) {
            case NONE:
                return femaleAndMale;
            case FEMALE_TEMP_CONTRACEPTION_IMPLANT:
            case FEMALE_TEMP_CONTRACEPTION_PILL:
                return female;
            case MALE_FINAL_CONTRACEPTION:
                return male;
            default:
                return femaleAndMale;
        }
    }

    public boolean isDefinitiv() {
        switch (this) {
            case NONE:
            case FEMALE_TEMP_CONTRACEPTION_IMPLANT:
            case FEMALE_TEMP_CONTRACEPTION_PILL:
                return false;
            case FEMALE_FINAL_CONTRACEPTION:
            case MALE_FINAL_CONTRACEPTION:
                return true;
            default:
                return false;
        }
    }
}
