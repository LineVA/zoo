package zoo.animal.conservation;

import exception.name.UnknownNameException;
import launch.options.Option;
import lombok.Getter;
import lombok.Setter;

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
    @Setter
    Option option;

    ConservationStatus(int id, double coef, double diam) {
        this.id = id;
        this.coefficient = coef;
        this.diameter = diam;
    }

    public ConservationStatus findById(int id) throws UnknownNameException {
        for (ConservationStatus status : ConservationStatus.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new UnknownNameException(this.option.getConservationBundle().getString("UNKNOWN_ID"));
    }
}
