package backup.save;

import exception.name.EmptyNameException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Map;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import utils.Constants;
import zoo.IZoo;
import zoo.animal.Animal;
import zoo.animal.death.LifeSpanLightAttributes;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.personality.PersonalityAttributes;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.animal.social.SocialAttributes;
import zoo.animalKeeper.AnimalKeeper;
import zoo.animalKeeper.TaskPaddock;
import zoo.paddock.IPaddock;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 * the concrete class used to save a zoo inside a XML file
 *
 * @author doyenm
 */
public class SaveImpl implements Save {

    /**
     * The private class used to access to all the fields of zoo, paddocks and
     * animals
     */
    public static final class FriendSave {

        private FriendSave() {
        }
    }
    /**
     * Instanciation of the private class
     */
    private static final FriendSave friendSave = new FriendSave();

    /**
     * @see backup.save.Save
     */
    @Override
    public boolean isFileAlreadyExisting(String fileName) throws EmptyNameException {
        String name = createFileName(fileName);
        try {
            new FileReader(new File(name));
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        }
    }

    /**
     * @see backup.save.Save
     */
    @Override
    public void saveZoo(IZoo zoo, String fileName) throws EmptyNameException {
        Element zooEl = createElementZoo(zoo);
        org.jdom2.Document doc = new Document(zooEl);
        zooEl.addContent(createElementPaddocks(zoo));
        zooEl.addContent(createElementKeepers(zoo));
        saveInFile(doc, createFileName(fileName));
    }

    /**
     * Create the name of the file in wich the backup of the zoo will be saved
     *
     * @param name the name of the file without any extension
     * @return the name of the file with the extension ".xml" in the repository
     * "./gameBackUps"
     * @throws EmptyNameException throws if the name is empty
     */
    private String createFileName(String name) throws EmptyNameException {
        if (name.trim().equals("")) {
            throw new EmptyNameException("This name's zoo is the empty string");
        }
        return "./gameBackUps/" + name + ".xml";
    }

    /**
     * Save a Document into a file
     *
     * @param doc the Document to save
     * @param fichier the name of the file
     */
    private void saveInFile(Document doc, String fichier) {
        try {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(doc, new FileOutputStream(fichier));
        } catch (java.io.IOException e) {
        }
    }

    /**
     * Create an element with text
     *
     * @param nam the name of the element
     * @param value the texxt of this element
     * @return the element
     */
    private Element createElementWithText(String name, String value) {
        Element el = new Element(name);
        el.setText(value);
        return el;
    }

    /**
     * Create an attribute
     *
     * @param name the name of the attribute
     * @param value the value of the attribute
     * @return the attribute
     */
    private Attribute createAttribute(String name, String value) {
        return new Attribute(name, value);
    }

    /**
     * Create element "animalKeepers" with the information of the keepers
     *
     * @param zoo the zoo from where the keepers come
     * @return the element "animalKeeper" with all the information about the
     * keeper
     */
    private Element createElementKeepers(IZoo zoo) {
        Element el = new Element(Constants.ANIMALKEEPERS);
        zoo.getAnimalKeepers(friendSave).entrySet().stream().forEach((keeper) -> {
            el.addContent(createElementKeeper((AnimalKeeper) keeper.getValue()));
        });
        return el;
    }

    /**
     * Create element "animalKeeper" with the information of a keeper
     *
     * @param zoo the zoo from where the keeper come
     * @return the element "animalKeeper" with all the infoirmation about the
     * current keeper
     */
    private Element createElementKeeper(AnimalKeeper keeper) {
        Element el = new Element(Constants.ANIMALKEEPER);
        el.setAttribute(createAttribute(Constants.NAME, keeper.getName(friendSave)));
        el.addContent(createElementTimedPaddocks(keeper.getTimedPaddocks(friendSave)));
        el.addContent(createElementTimedTasksPerPaddock(keeper.getTimedTaskPerPaddock(friendSave)));
        el.addContent(createElementManagedFamilies(keeper.getManagedFamilies(friendSave)));
        el.addContent(createElementManagedTasks(keeper.getManagedTasks(friendSave)));
        return el;
    }

    /**
     * Create element "timedPaddocks" with the information the timed paddocks of
     * a keeper
     *
     * @param timedPAddocks the timedPaddocks to save
     * @return the element "timedPAddocks" with all the information about the
     * current timedPaddocks
     */
    private Element createElementTimedPaddocks(Map<IPaddock, Double> timedPaddocks) {
        Element el = new Element(Constants.TIMEDPADDOCKS);
        for (Map.Entry<IPaddock, Double> entry : timedPaddocks.entrySet()) {
            el.addContent(createElementTimedPaddock(entry.getKey().getName(friendSave), entry.getValue()));
        }
        return el;
    }

    /**
     * Create element "timedTasksPerPaddock" with the information the timed
     * tasks per paddock of a keeper
     *
     * @param timedPAddocks the timedTasksPerPaddock to save
     * @return the element "timedTasksPerPaddock" with all the information about
     * the current timed tasks per paddock
     *
     */
    private Element createElementTimedTasksPerPaddock(Map<TaskPaddock, Double> timedTasksPerPaddock) {
        Element el = new Element(Constants.TIMEDTASKS);
        for (Map.Entry<TaskPaddock, Double> entry : timedTasksPerPaddock.entrySet()) {
            el.addContent(createElementTimedTaskPerPaddock(
                    entry.getKey().getPaddock().getName(friendSave),
                    entry.getKey().getTask(),
                    entry.getValue()));
        }
        return el;
    }

    /**
     * Create element "managedFamilies" with the information the managed
     * families of a keeper
     *
     * @param timedPAddocks the managedFamilies to save
     * @return the element "tmanagedFamilies" with all the information about the
     * current managed families
     *
     */
    private Element createElementManagedFamilies(Map<Integer, Double> managedFamilies) {
        Element el = new Element(Constants.MANAGEDFAMILIES);
        for (Map.Entry<Integer, Double> entry : managedFamilies.entrySet()) {
            el.addContent(createElementManagedFamily(
                    entry.getKey(), entry.getValue()));
        }
        return el;
    }

    /**
     * Create element "managedTasks" with the information the managed tasks of a
     * keeper
     *
     * @param timedPAddocks the managedTasks to save
     * @return the element "managedTasks" with all the information about the
     * current managed tasks
     *
     */
    private Element createElementManagedTasks(Map<Integer, Double> managedTasks) {
        Element el = new Element(Constants.MANAGEDTASKS);
        for (Map.Entry<Integer, Double> entry : managedTasks.entrySet()) {
            el.addContent(createElementManagedTask(
                    entry.getKey(), entry.getValue()));
        }
        return el;
    }

    /**
     * Create element "timedPaddock"
     *
     * @param timedPAddocks the timedPaddock to save
     * @return the element "timedPaddock" with two fields : paddock and time
     *
     */
    private Element createElementTimedPaddock(String name, Double time) {
        Element el = new Element(Constants.TIMEDPADDOCK);
        el.addContent(createElementWithText(Constants.PADDOCK, name));
        el.addContent(createElementWithText(Constants.TIME, Double.toString(time)));
        return el;
    }

    /**
     * Create element "timedTaskPerPaddock"
     *
     * @param timedPAddocks the timedTaskPerPaddock to save
     * @return the element "timedTaskPerPaddock" with three fields : task,
     * paddock and time
     *
     */
    private Element createElementTimedTaskPerPaddock(String name, int task, Double time) {
        Element el = new Element(Constants.TIMEDTASK);
        el.addContent(createElementWithText(Constants.PADDOCK, name));
        el.addContent(createElementWithText(Constants.TASK, Integer.toString(task)));
        el.addContent(createElementWithText(Constants.TIME, Double.toString(time)));
        return el;
    }

    /**
     * Create element "managedFamily"
     *
     * @param timedPAddocks the managedFamily to save
     * @return the element "managedFamily" with two fields : family and grade
     *
     */
    private Element createElementManagedFamily(int family, Double time) {
        Element el = new Element(Constants.MANAGEDFAMILY);
        el.addContent(createElementWithText(Constants.FAMILY, Integer.toString(family)));
        el.addContent(createElementWithText(Constants.TIME, Double.toString(time)));
        return el;
    }

    /**
     * Create element "managedTask"
     *
     * @param timedPAddocks the managedTask to save
     * @return the element "managedTask" with two fields : task and grade
     *
     */
    private Element createElementManagedTask(int task, Double time) {
        Element el = new Element(Constants.MANAGEDTASK);
        el.addContent(createElementWithText(Constants.TASK, Integer.toString(task)));
        el.addContent(createElementWithText(Constants.TIME, Double.toString(time)));
        return el;
    }

    /**
     * Create an element "paddocks"
     *
     * @param zoo the zoo grom where we want to save the paddocks
     * @return an element "paddocks" containing a list of elements "paddock"
     */
    private Element createElementPaddocks(IZoo zoo) {
        Element el = new Element(Constants.PADDOCKS);
        zoo.getPaddocks(friendSave).entrySet().stream().forEach((pad) -> {
            el.addContent(createElementPaddock((IPaddock) pad.getValue()));
        });
        return el;
    }

    /**
     * Create an element "paddock"
     *
     * @param pad the paddock to save
     * @return an element paddock with an attribute "name" and six fields :
     * biome, paddockType, x, y , width and height
     */
    private Element createElementPaddock(IPaddock pad) {
        Element el = new Element(Constants.PADDOCK);
        el.setAttribute(createAttribute(Constants.NAME, pad.getName(friendSave)));
        el.addContent(createElementWithText(Constants.BIOME, Integer.toString(pad.getBiome(friendSave))));
        el.addContent(createElementWithText(Constants.PADDOCKTYPE, Integer.toString(pad.getPaddockType(friendSave))));
        el.addContent(createElementWithText(Constants.X, Integer.toString(pad.getCoordinates(friendSave).getX())));
        el.addContent(createElementWithText(Constants.Y, Integer.toString(pad.getCoordinates(friendSave).getY())));
        el.addContent(createElementWithText(Constants.WIDTH, Integer.toString(pad.getCoordinates(friendSave).getWidth())));
        el.addContent(createElementWithText(Constants.HEIGHT, Integer.toString(pad.getCoordinates(friendSave).getHeight())));
        el.addContent(createElementAnimals(pad));
        return el;
    }

    /**
     * Create an element "animals"
     *
     * @param pad the paddock in where the animals to save are
     * @return an element "animals" containing a list of elements "animal"
     */
    private Element createElementAnimals(IPaddock pad) {
        Element el = new Element(Constants.ANIMALS);
        for (Map.Entry<String, Animal> animal : pad.getAnimals(friendSave).entrySet()) {
            el.addContent(createElementAnimal((Animal) animal.getValue()));
        }
        return el;
    }

    /**
     * Create an element "animal"
     *
     * @param animal the animal to save
     * @return an element "animal" with an attribute "name" and ten sub-elements
     * : specie, sex, age, personality, optimalFeeding, actualFeeding,
     * actualReproduction, actualLifeSpan, optimalSocial and optimalTeritory
     */
    private Element createElementAnimal(Animal animal) {
        Element el = new Element(Constants.ANIMAL);
        el.setAttribute(createAttribute("name", animal.getName(friendSave)));
        el.addContent(createElementWithText(Constants.SPECIE, animal.getSpecie(friendSave).getNames().getScientificName()));
        el.addContent(createElementWithText(Constants.SEX, Integer.toString(animal.getSex(friendSave).getId())));
        el.addContent(createElementWithText(Constants.AGE, String.valueOf(animal.getAge(friendSave))));
        el.addContent(createElementWithText(Constants.WELLBEING, String.valueOf(animal.getWellBeeing(friendSave))));
        el.addContent(createElementWithText(Constants.STARVATION, String.valueOf(animal.getStarvation(friendSave))));
        el.addContent(createElementPersonalityAttributes(animal.getPersonality(friendSave)));
        el.addContent(createElementOptimalFeedingAttributes(animal.getOptimalFeeding(friendSave)));
        el.addContent(createElementActualFeedingAttributes(animal, animal.getActualFeeding(friendSave)));
        el.addContent(createElementReproductionAttributes(animal.getActualReproduction(friendSave)));
        el.addContent(createElementLifeSpanAttributes(animal.getActualLifeSpan(friendSave)));
        el.addContent(createElementSocialAttributes(animal.getOptimalSocial(friendSave)));
        el.addContent(createElementTeritoryAttributes(animal.getOptimalTerritory(friendSave)));
        return el;
    }

    /**
     * Create an element "personality"
     *
     * @param att the personality attributes to save
     * @return an element "personality" with a sub-element "bravery"
     */
    private Element createElementPersonalityAttributes(PersonalityAttributes att) {
        Element el = new Element(Constants.PERSONALITY);
        el.addContent(createElementWithText(Constants.BRAVERY, String.valueOf(att.getBravery())));
        el.addContent(createElementWithText(Constants.INTELLIGENCE, String.valueOf(att.getIntelligence())));
        el.addContent(createElementWithText(Constants.METICULOUSNESS, String.valueOf(att.getMeticulousness())));
        el.addContent(createElementWithText(Constants.GREED, String.valueOf(att.getGreed())));
        el.addContent(createElementWithText(Constants.CURIOSITY, String.valueOf(att.getCuriosity())));
        return el;
    }

    /**
     * Create an element "optimalBiomeAttributes"
     *
     * @param att the biome attributes to save
     * @return an element "optimalBiomeAttributes" with eight sub-elements :
     * night- and dayTemperature, pluviometry, treeDensity, treeHeight, drop,
     * humidity and waterSalinity
     * @deprecated 
     */
    private Element createElementBiomeAttributes(BiomeAttributes att) {
        Element el = new Element("optimalBiomeAttributes");
        el.addContent(createElementWithText("nightTemperature", String.valueOf(att.getNightTemperature())));
        el.addContent(createElementWithText("dayTemperature", String.valueOf(att.getDayTemperature())));
        el.addContent(createElementWithText("pluviometry", String.valueOf(att.getPluviometry())));
        el.addContent(createElementWithText("treeDensity", String.valueOf(att.getTreeDensity())));
        el.addContent(createElementWithText("treeHeight", String.valueOf(att.getTreeHeight())));
        el.addContent(createElementWithText("drop", String.valueOf(att.getDrop())));
        el.addContent(createElementWithText("humidity", String.valueOf(att.getHumidity())));
        el.addContent(createElementWithText("waterSalinity", String.valueOf(att.getWaterSalinity())));
        return el;
    }

    /**
     * Create an element "optimalFeedingAttributes"
     *
     * @param att the attributes to save
     * @return an element "optimalFeedingAttributes with a sub-element quantity
     */
    private Element createElementOptimalFeedingAttributes(FeedingAttributes att) {
        Element el = new Element(Constants.OPTIMALFEEDING_ATT);
        el.addContent(createElementWithText(Constants.QUANTITY, String.valueOf(att.getFoodQuantity())));
        return el;
    }

    /**
     * Create an element "actualFeedingAttributes"
     *
     * @param animal the current animal
     * @param att the attributes to save
     * @return an element "actualFeedingAttributes" with two sub-elements : diet
     * and foodQuantity
     */
    private Element createElementActualFeedingAttributes(Animal animal, FeedingAttributes att) {
        Element el = new Element(Constants.ACTUALFEEDING_ATT);
        el.addContent(createElementWithText(Constants.DIET, String.valueOf(animal.getDiet(friendSave))));
        el.addContent(createElementWithText(Constants.QUANTITY, String.valueOf(att.getFoodQuantity())));
        return el;
    }

    /**
     * Create an element "actualLifeSpanAttributes"
     *
     * @param att the attributes to save
     * @return an element "actualLifeSpanAttributes" with one sub-element :
     * lifeSpan
     */
    private Element createElementLifeSpanAttributes(LifeSpanLightAttributes att) {
        Element el = new Element(Constants.ACTUALLIFESPAN_ATT);
        el.addContent(createElementWithText(Constants.LIFESPAN, String.valueOf(att.getLifeSpan())));
        return el;
    }

    /**
     * Create an element "actualReproductionAttributes"
     *
     * @param att the attributes to save
     * @return an element "actualReproductionsAttributes" with four sub-elements
     * : femaleMaturityAge, maleMaturityAge, gestationFrequency and litterSize
     */
    private Element createElementReproductionAttributes(ReproductionAttributes att) {
        Element el = new Element(Constants.ACTUALREPRODUCTION_ATT);
        el.addContent(createElementWithText(Constants.FEMALEMATURITYAGE, String.valueOf(att.getFemaleMaturityAge())));
        el.addContent(createElementWithText(Constants.MALEMATURITYAGE, String.valueOf(att.getMaleMaturityAge())));
        el.addContent(createElementWithText(Constants.GESTATIONFREQUENCY, String.valueOf(att.getGestationFrequency())));
        el.addContent(createElementWithText(Constants.LITTERSIZE, String.valueOf(att.getLitterSize())));
        return el;
    }

    /**
     * Create an element "optimalSocialAttributes"
     *
     * @param att the attributes to save
     * @return an element "optimalSocialAttributes" with one sub-element :
     * groupSize
     */
    private Element createElementSocialAttributes(SocialAttributes att) {
        Element el = new Element(Constants.OPTIMALSOCIAL_ATT);
        el.addContent(createElementWithText(Constants.GROUPSIZE, String.valueOf(att.getGroupSize())));
        return el;
    }

    /**
     * Create an element "optimalTerritoryAttributes"
     *
     * @param att the attributes to save
     * @return an element "optimalTerritoryAttributes" with one sub-element :
     * territorySize
     */
    private Element createElementTeritoryAttributes(TerritoryAttributes att) {
        Element el = new Element(Constants.OPTIMALTERRITORY_ATT);
        el.addContent(createElementWithText(Constants.TERRITORYSIZE, String.valueOf(att.getTerritorySize())));
        return el;
    }

    /**
     * Create an element "dimensions"
     *
     * @param width the width to save
     * @param height the height to save
     * @return an element "dimensions" with two sub-elements : width and height
     */
    private Element createElementDimensionsZoo(int width, int height) {
        Element dimEl = new Element(Constants.DIMENSIONS);
        dimEl.addContent(createElementWithText(Constants.WIDTH, Integer.toString(width)));
        dimEl.addContent(createElementWithText(Constants.HEIGHT, Integer.toString(height)));
        return dimEl;
    }

    /**
     * Create an element zoo
     *
     * @param zoo the zoo to save
     * @return an element zoo with an attribute "name" and five sub-elements :
     * dimensions, age, monthsPerEvaluation, horizon and language
     */
    private Element createElementZoo(IZoo zoo) {
        Element zooEl = new Element(Constants.ZOO);
        zooEl.setAttribute(createAttribute(Constants.NAME, zoo.getName(friendSave).trim()));
        zooEl.addContent(createElementDimensionsZoo(zoo.getWidth(friendSave), zoo.getHeight(friendSave)));
        zooEl.addContent(createElementWithText(Constants.AGE, Integer.toString(zoo.getAge(friendSave))));
        zooEl.addContent(createElementWithText(Constants.MONTHSPEREVALUATION, Integer.toString(zoo.getMonthsPerEvaluation(friendSave))));
        zooEl.addContent(createElementWithText(Constants.HORIZON, Integer.toString(zoo.getHorizon(friendSave))));
        zooEl.addContent(createElementWithText(Constants.LANGUAGE, zoo.getOption(friendSave).getLocale(friendSave)));
        return zooEl;
    }
}
