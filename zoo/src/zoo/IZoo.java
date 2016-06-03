/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoo;

import exception.IncorrectDataException;
import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import backup.save.SaveImpl;
import zoo.animal.Animal;
import zoo.animal.specie.Specie;
import zoo.paddock.IPaddock;
import zoo.paddock.Paddock;
import zoo.paddock.PaddockCoordinates;

/**
 *
 * @author doyenm
 */
public interface IZoo {

    public void initiateZoo(String name, int width, int height, HashMap<String,
            Specie> species, int age, int monthsPerEvaluation)
            throws IncorrectDimensionsException, EmptyNameException, IOException;

    public void addPaddock(String paddockName, int x, int y, int width, int height)
            throws AlreadyUsedNameException, IncorrectDimensionsException;

    public void addPaddock(IPaddock paddock)
            throws AlreadyUsedNameException, IncorrectDimensionsException;

    public ArrayList<String> listPaddock();

    //  public ArrayList<String> detailledPaddock(String name) throws UnknownNameException, EmptyNameException;
    public ArrayList<PaddockCoordinates> map() throws IncorrectDimensionsException;

    public int evaluate();

    public void death() throws UnknownNameException;

    public void birth() throws AlreadyUsedNameException, IncorrectDataException;

    public IPaddock findPaddockByName(String paddockName) throws EmptyNameException, UnknownNameException;

    public Specie findSpeciebyName(String specieName) throws EmptyNameException, UnknownNameException;
//    public void setBiome(String paddockName, String biomeName)
//            throws UnknownNameException, EmptyNameException;

    public Animal findAnimalByName(String animalName) throws UnknownNameException, EmptyNameException;

    public ArrayList<String> listAnimal();

    public ArrayList<String> listSpecie();

    public void ageing();

    public int wellBeing();

    /**
     * Friend pattern : give access to each of the fields of Zoo only to the
     * save methods
     */
    public String getName(SaveImpl.FriendSave friend);

    public int getWidth(SaveImpl.FriendSave friend);

    public int getHeight(SaveImpl.FriendSave friend);

    public HashMap<String, IPaddock> getPaddocks(SaveImpl.FriendSave friend);

    public HashMap<String, Specie> getSpecies(SaveImpl.FriendSave friend);

    public int getMonthsPerEvaluation(SaveImpl.FriendSave friend);
    
     public int getAge(SaveImpl.FriendSave friend);

    // TEMPORARY !!!!!!!!!!!!!!
    public HashMap<String, IPaddock> getPaddocks();

    public HashMap<String, Specie> getSpecies();

}
