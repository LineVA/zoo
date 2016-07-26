package zoo.animal.conservation;

import exception.name.UnknownNameException;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum ConservationStatus {

    UNKNOWN(0, 1.0),
    LEASTCONCERNED(1, 1.1),
    NEARTHREATENED(2, 1.2),
    VULNERABLE(3, 1.3),
    ENDANGERED(4, 1.4),
    CRITICALLYENDANGERED(5, 1.5),
    EXTINCTINWILD(6, 1.6),
    EXTINCT(7, 1.7);

    @Getter
    int id;
    @Getter
    double coefficient;

    ConservationStatus(int id, double coef) {
        this.id = id;
        this.coefficient = coef;
    }

    public ConservationStatus findById(int id) throws UnknownNameException {
        for (ConservationStatus status : ConservationStatus.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new UnknownNameException("This conservation status does not exist.");
    }
}
