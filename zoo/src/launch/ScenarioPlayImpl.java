package launch;

import java.util.ResourceBundle;
import launch.play.Play;
import lombok.Getter;
import lombok.Setter;
import zoo.IZoo;
import zoo.Zoo;

/**
 *
 * @author doyenm
 */
public class ScenarioPlayImpl implements Play{

@Getter @Setter
    public IZoo zoo;

    public ScenarioPlayImpl() {
        this.zoo = new Zoo();
    }    

    @Override
    public ResourceBundle getBundle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IZoo getZoo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setZoo(IZoo zoo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}