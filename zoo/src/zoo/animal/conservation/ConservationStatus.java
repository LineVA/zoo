package zoo.animal.conservation;

import exception.name.UnknownNameException;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum ConservationStatus {

    UNKNOWN(0),
    LEASTCONCERNED(1),
    NEARTHREATENED(2),
    VULNERABLE(3),
    ENDANGERED(4),
    CRITICALLYENDANGERED(5),
    EXTINCTINWILD(6),
    EXTINCT(7);

    @Getter
    int id;

    ConservationStatus(int id) {
        this.id = id;
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
