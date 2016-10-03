package commandLine;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class ReturnExec {
    @Getter
    private final String message;
    @Getter
    private final TypeReturn type;

    public ReturnExec(String msg, TypeReturn type){
        this.message = msg;
        this.type = type;
    }
}