package gui.visual;

import zoo.IZoo;
import zoo.Zoo;

/**
 *
 * @author doyenm
 */
public interface CommandVisual {

    public IZoo zoo = new Zoo();

    public void execute(String[] cmd);
}
