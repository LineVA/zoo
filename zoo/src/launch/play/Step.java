package launch.play;

import lombok.Getter;
import zoo.IZoo;

/**
 *
 * @author doyenm
 */
public abstract class Step {

    @Getter
    String initial;
    @Getter
    String help;
    @Getter
    String success;

    @Getter
    IZoo zoo;

    public Step(IZoo zoo, String previous, String help, String success) {
        this.zoo = zoo;
        this.initial = previous;
        this.help = help;
        this.success = success;
    }

    public abstract boolean check();
}
