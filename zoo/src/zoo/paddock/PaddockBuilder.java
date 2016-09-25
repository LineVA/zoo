package zoo.paddock;

import exception.name.NameException;
import java.util.ArrayList;
import java.util.Map;
import launch.options.Option;
import zoo.animal.Animal;

/**
 *
 * @author doyenm
 */
public class PaddockBuilder {

    private String _name;
    private PaddockCoordinates _coordinates;
    private Map<String, Animal> _animals;
    private int _paddockType = 0;
    private ArrayList<IPaddock> _neightbourhood = new ArrayList<>();
    private Option _option;
    private int _biome = 0;

    public IPaddock buildPaddock() throws NameException {
        return new Paddock(_name, _coordinates, _neightbourhood,
                _biome, _paddockType, _option);
    }

    public PaddockBuilder name(String _name) {
        this._name = _name;
        return this;
    }

    public PaddockBuilder coordinates(PaddockCoordinates _coordinates) {
        this._coordinates = _coordinates;
        return this;
    }

    public PaddockBuilder animals(Map<String, Animal> _animals) {
        this._animals = _animals;
        return this;
    }

    public PaddockBuilder neightbourhood(ArrayList<IPaddock> _neightbourhood) {
        this._neightbourhood = _neightbourhood;
        return this;
    }

    public PaddockBuilder biome(int _biome) {
        this._biome = _biome;
        return this;
    }

    public PaddockBuilder paddockType(int _paddockType) {
        this._paddockType = _paddockType;
        return this;
    }

    public PaddockBuilder option(Option _option) {
        this._option = _option;
        return this;
    }
}
