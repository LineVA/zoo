package zoo.animal.conservation;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.List;
import launch.options.Option;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum ConservationStatus {

    UNKNOWN(0, 1.0, 0.1),
    LEASTCONCERNED(1, 1.1, 0.08),
    NEARTHREATENED(2, 1.2, 0.6),
    VULNERABLE(3, 1.3, 0.04),
    ENDANGERED(4, 1.4, 0.03),
    CRITICALLYENDANGERED(5, 1.5, 0.02),
    EXTINCTINWILD(6, 1.6, 0.01),
    EXTINCT(7, 1.7, 0.0);

    
    @Getter
    int id;
    @Getter
    double coefficient;
    @Getter
    double diameter;
    /**
     * Options used to know the current language
     */
    private Option option;

    public void setOption(Option option){
         for (ConservationStatus status : ConservationStatus.values()) {
             status.option = option;
         }
    }
    
    ConservationStatus(int id, double coef, double diam) {
        this.id = id;
        this.coefficient = coef;
        this.diameter = diam;
    }

    /**
     * Find a conservation status by its identifier
     * @param id the identifierto search
     * @return the corresponding conservation status
     * @throws UnknownNameException if no conservation status matches this identifier
     */
    public ConservationStatus findById(int id) throws UnknownNameException {
        for (ConservationStatus status : ConservationStatus.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new UnknownNameException(this.option.getConservationBundle().getString("UNKNOWN_ID"));
    }
    
    /**
     * Return the name of the conservation status according to the current language
     * @return the name of the status
     */
    public String toStringByLanguage(){
        return this.option.getConservationBundle().getString(this.toString().toUpperCase());
    }
    
    /**
     * List all of the conservation status
     * @return the list, each item corresponding to an element of the enumeration
     */
    public List<String> list() {
        List<String> list = new ArrayList<>();
        for (ConservationStatus status : ConservationStatus.values()) {
            list.add(status.id + " - " +
                    this.option.getConservationBundle().getString(status.toString().toUpperCase()));
        }
        return list;
    }
}
