package exception;

import java.io.IOException;

/**
 *
 * @author doyenm
 */
public class IncorrectLoadException extends IOException {

    public IncorrectLoadException(String msg) {
        super(msg);
    }
}
