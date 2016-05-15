/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoo;

import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public interface IZoo {
    
    public void initiateZoo(String name, int width, int height, HashMap<String, Specie> species)
            throws IncorrectDimensionsException, EmptyNameException, IOException;
    
    public void addPaddock(String paddockName, int x, int y, int width, int height)
            throws AlreadyUsedNameException, IncorrectDimensionsException;

    public ArrayList<String> listPaddock();

    public String detailledPaddock(String name) throws UnknownNameException, EmptyNameException;

    public int evaluate();

    public void death() throws UnknownNameException;
    
    public void birth() throws AlreadyUsedNameException;
}


