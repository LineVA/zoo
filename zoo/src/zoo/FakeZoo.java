package zoo;

import java.io.IOException;
import java.util.Map;
import main.InstanciateSpecies;
import org.jdom2.JDOMException;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class FakeZoo {
    String name;
    int width;
    int height;
    int age;
    int monthsPerEvaluation;
    int horizon;

    public FakeZoo(String name, int width, int height, int age,
            int monthsPerEvaluation, int horizon) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.age = age;
        this.monthsPerEvaluation = monthsPerEvaluation;
        this.horizon = horizon;
    }
    
    public IZoo convertToZoo() throws IOException, JDOMException{
         Map<String, Specie> spec = InstanciateSpecies.instanciateSpecies("resources/species");
         IZoo zoo = new Zoo();
         zoo.initiateZoo(this.name, this.width, this.height, spec, this.age, 
                 this.monthsPerEvaluation, this.horizon);
         return zoo;
    }
}
