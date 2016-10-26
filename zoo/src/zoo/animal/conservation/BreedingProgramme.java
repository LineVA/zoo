package zoo.animal.conservation;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import launch.options.Option;
import lombok.Getter;
import zoo.animal.feeding.Diet;

/**
 *
 * @author doyenm
 */
public enum BreedingProgramme {

    NONE(0),
    EEP(1),
    ESB(2);

    @Getter
    private int id;

    BreedingProgramme(int id) {
        this.id = id;
    }
    
    Option option;

     public void setOption(Option option) {
        for (BreedingProgramme programme : BreedingProgramme.values()) {
             programme.option = option;
         }
    }
     
         public BreedingProgramme findById(int id) throws UnknownNameException {
        for (BreedingProgramme programme: BreedingProgramme.values()) {
            if (programme.getId() == id) {
                return programme;
            }
        }
        throw new UnknownNameException(
                this.option.getSpecieBundle().getString("UNKNOWN_NAME_BP"));
    }
    
     public String toStringByLanguage(){
        return this.option.getSpecieBundle().getString("BP_" + this.toString().toUpperCase());
    }
     
      public ArrayList<String> list() {
        ArrayList<String> list = new ArrayList<>();
        for (BreedingProgramme programme : BreedingProgramme.values()) {
            list.add(programme.id + " - " +
                    this.option.getSpecieBundle().getString("BP_" + programme.toString().toUpperCase() + "_DESCRIPTION"));
        }
        return list;
    }
}
