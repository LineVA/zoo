package zoo;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import launch.InstanciateSpecies;
import lombok.Getter;
import launch.options.Option;
import org.jdom2.JDOMException;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class FakeZoo {

    @Getter
    String name;
    @Getter
    int width;
    @Getter
    int height;
    @Getter
    int age;
    @Getter
    int monthsPerEvaluation;
    @Getter
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
    
    public IZoo convertToZoo(Option option) throws IOException, JDOMException{
         Map<String, Specie> spec = InstanciateSpecies.instanciateSpecies("resources/species", option);
         IZoo zoo = new Zoo();
         zoo.initiateZoo(this.name, this.width, this.height, spec, this.age, 
                 this.monthsPerEvaluation, this.horizon);
         zoo.setOption(option);
         return zoo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + this.width;
        hash = 11 * hash + this.height;
        hash = 11 * hash + this.age;
        hash = 11 * hash + this.monthsPerEvaluation;
        hash = 11 * hash + this.horizon;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FakeZoo other = (FakeZoo) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.width != other.width) {
            return false;
        }
        if (this.height != other.height) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        if (this.monthsPerEvaluation != other.monthsPerEvaluation) {
            return false;
        }
        if (this.horizon != other.horizon) {
            return false;
        }
        return true;
    }
    
    
}
