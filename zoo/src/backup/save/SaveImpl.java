package backup.save;

import exception.name.EmptyNameException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
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
    private static final FriendSave friendSave = new FriendSave();

    /**
     * The main method to create the expected file and save the zoo inside
     *
     * @param zoo
     * @param fileName
     * @throws EmptyNameException
     */
    @Override
    public void saveZoo(IZoo zoo, String fileName) throws EmptyNameException {
        Element zooEl = createElementZoo(zoo);
        org.jdom2.Document doc = new Document(zooEl);
        zooEl.addContent(createElementPaddocks(zoo));
        zooEl.addContent(createElementKeepers(zoo));
        //  zooEl.addContent(createElementAnimals(zoo));
        saveInFile(doc, createFileName(fileName));
    }

    /**
     * Create the name of the file in wich the backup of the zoo will be saved
     *
     * @param name the name of the file without any extension
     * @return the name of the file with the extension ".xml"
     * @throws EmptyNameException throws if the name is empty
     */
    private String createFileName(String name) throws EmptyNameException {
        if (name.trim().equals("")) {
            throw new EmptyNameException("This name's zoo is the empty string");
        }
        return "./gameBackUps/" + name + ".xml";
    }

    private void saveInFile(Document doc, String fichier) {
        try {
            //On utilise ici un affichage classique avec getPrettyFormat()
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            //Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
            //avec en argument le nom du fichier pour effectuer la sérialisation.
            sortie.output(doc, new FileOutputStream(fichier));
        } catch (java.io.IOException e) {
        }
    }

    private Element createElementWithText(String name, String value) {
        Element el = new Element(name);
        el.setText(value);
        return el;
    }

    private Attribute createAttribute(String name, String value) {
        return new Attribute(name, value);
    }

    private Element createElementKeepers(IZoo zoo) {
        Element el = new Element("animalKeepers");
        zoo.getAnimalKeepers(friendSave).entrySet().stream().forEach((keeper) -> {
            el.addContent(createElementKeeper((AnimalKeeper) keeper.getValue()));
        });
        return el;
    }

    private Element createElementKeeper(AnimalKeeper keeper) {
        Element el = new Element("animalKeeper");
        el.setAttribute(createAttribute("name", keeper.getName(friendSave)));
        el.addContent(createElementTimedPaddocks(keeper.getTimedPaddocks(friendSave)));
        el.addContent(createElementTimedTasksPerPaddock(keeper.getTimedTaskPerPaddock(friendSave)));
        el.addContent(createElementManagedFamilies(keeper.getManagedFamilies(friendSave)));
        el.addContent(createElementManagedTasks(keeper.getManagedTasks(friendSave)));
        return el;
    }

    private Element createElementTimedPaddocks(Map<IPaddock, Double> timedPaddocks) {
        Element el = new Element("timedPaddocks");
        for (HashMap.Entry<IPaddock, Double> entry : timedPaddocks.entrySet()) {
            el.addContent(createElementTimedPaddock(entry.getKey().getName(friendSave), entry.getValue()));
        }
        return el;
    }

    private Element createElementTimedTasksPerPaddock(Map<TaskPaddock, Double> timedTasksPerPaddock) {
        Element el = new Element("timedTasksPerPaddock");
        for (HashMap.Entry<TaskPaddock, Double> entry : timedTasksPerPaddock.entrySet()) {
            el.addContent(createElementTimedTaskPerPaddock(
                    entry.getKey().getPaddock().getName(friendSave),
                    entry.getKey().getTask(),
                    entry.getValue()));
        }
        return el;
    }

    private Element createElementManagedFamilies(Map<Integer, Double> managedFamilies) {
        Element el = new Element("managedFamilies");
        for (HashMap.Entry<Integer, Double> entry : managedFamilies.entrySet()) {
            el.addContent(createElementManagedFamily(
                    entry.getKey(), entry.getValue()));
        }
        return el;
    }

    private Element createElementManagedTasks(Map<Integer, Double> managedTasks) {
        Element el = new Element("managedTasks");
        for (HashMap.Entry<Integer, Double> entry : managedTasks.entrySet()) {
            el.addContent(createElementManagedTask(
                    entry.getKey(), entry.getValue()));
        }
        return el;
    }

    private Element createElementTimedPaddock(String name, Double time) {
        Element el = new Element("timedPaddock");
        el.addContent(createElementWithText("paddock", name));
        el.addContent(createElementWithText("time", Double.toString(time)));
        return el;
    }

    private Element createElementTimedTaskPerPaddock(String name, int task, Double time) {
        Element el = new Element("timedTaskPerPaddock");
        el.addContent(createElementWithText("paddock", name));
        el.addContent(createElementWithText("task", Integer.toString(task)));
        el.addContent(createElementWithText("time", Double.toString(time)));
        return el;
    }

    private Element createElementManagedFamily(int family, Double time) {
        Element el = new Element("managedFamily");
        el.addContent(createElementWithText("family", Integer.toString(family)));
        el.addContent(createElementWithText("time", Double.toString(time)));
        return el;
    }

    private Element createElementManagedTask(int task, Double time) {
        Element el = new Element("managedTask");
        el.addContent(createElementWithText("task", Integer.toString(task)));
        el.addContent(createElementWithText("time", Double.toString(time)));
        return el;
    }

    private Element createElementPaddocks(IZoo zoo) {
        Element el = new Element("paddocks");
        zoo.getPaddocks(friendSave).entrySet().stream().forEach((pad) -> {
            el.addContent(createElementPaddock((IPaddock) pad.getValue()));
        });
        return el;
    }

    private Element createElementPaddock(IPaddock pad) {
        Element el = new Element("paddock");
        el.setAttribute(createAttribute("name", pad.getName(friendSave)));
        el.addContent(createElementWithText("biome", Integer.toString(pad.getBiome(friendSave))));
        el.addContent(createElementWithText("paddockType", Integer.toString(pad.getPaddockType(friendSave))));
        el.addContent(createElementWithText("x", Integer.toString(pad.getCoordinates(friendSave).getX())));
        el.addContent(createElementWithText("y", Integer.toString(pad.getCoordinates(friendSave).getY())));
        el.addContent(createElementWithText("width", Integer.toString(pad.getCoordinates(friendSave).getWidth())));
        el.addContent(createElementWithText("height", Integer.toString(pad.getCoordinates(friendSave).getHeight())));
        el.addContent(createElementAnimals(pad));
        return el;
    }

    private Element createElementAnimals(IPaddock pad) {
        Element el = new Element("animals");
        for (HashMap.Entry<String, Animal> animal : pad.getAnimals(friendSave).entrySet()) {
            el.addContent(createElementAnimal((Animal) animal.getValue()));
        }
        return el;
    }

    private Element createElementAnimal(Animal animal) {
        Element el = new Element("animal");
        el.setAttribute(createAttribute("name", animal.getName(friendSave)));
        el.addContent(createElementWithText("specie", animal.getSpecie(friendSave).getNames().getScientificName()));
        el.addContent(createElementWithText("sex", Integer.toString(animal.getSex(friendSave).getId())));
        el.addContent(createElementWithText("age", String.valueOf(animal.getAge(friendSave))));
        el.addContent(createElementPersonalityAttributes(animal.getPersonality(friendSave)));
        el.addContent(createElementOptimalFeedingAttributes(animal.getOptimalFeeding(friendSave)));
        el.addContent(createElementActualFeedingAttributes(animal, animal.getActualFeeding(friendSave)));
        el.addContent(createElementReproductionAttributes(animal.getActualReproduction(friendSave)));
        el.addContent(createElementLifeSpanAttributes(animal.getActualLifeSpan(friendSave)));
        el.addContent(createElementSocialAttributes(animal.getOptimalSocial(friendSave)));
        el.addContent(createElementTeritoryAttributes(animal.getOptimalTerritory(friendSave)));
        return el;
    }
    
    private Element createElementPersonalityAttributes(PersonalityAttributes att) {
        Element el = new Element("personality");
        el.addContent(createElementWithText("bravery", String.valueOf(att.getBravery())));
        return el;
    }

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

    private Element createElementOptimalFeedingAttributes(FeedingAttributes att) {
        Element el = new Element("optimalFeedingAttributes");
        el.addContent(createElementWithText("quantity", String.valueOf(att.getFoodQuantity())));
        return el;
    }

    private Element createElementActualFeedingAttributes(Animal animal, FeedingAttributes att) {
        Element el = new Element("actualFeedingAttributes");
        el.addContent(createElementWithText("diet", String.valueOf(animal.getDiet(friendSave))));
        el.addContent(createElementWithText("quantity", String.valueOf(att.getFoodQuantity())));
        return el;
    }

    private Element createElementLifeSpanAttributes(LifeSpanLightAttributes att) {
        Element el = new Element("actualLifeSpanAttributes");
        el.addContent(createElementWithText("lifeSpan", String.valueOf(att.getLifeSpan())));
        return el;
    }

    private Element createElementReproductionAttributes(ReproductionAttributes att) {
        Element el = new Element("actualReproductionAttributes");
        el.addContent(createElementWithText("femaleMaturityAge", String.valueOf(att.getFemaleMaturityAge())));
        el.addContent(createElementWithText("maleMaturityAge", String.valueOf(att.getMaleMaturityAge())));
        el.addContent(createElementWithText("gestationFrequency", String.valueOf(att.getGestationFrequency())));
        el.addContent(createElementWithText("litterSize", String.valueOf(att.getLitterSize())));
        return el;
    }

    private Element createElementSocialAttributes(SocialAttributes att) {
        Element el = new Element("optimalSocialAttributes");
        el.addContent(createElementWithText("groupSize", String.valueOf(att.getGroupSize())));
        return el;
    }

    private Element createElementTeritoryAttributes(TerritoryAttributes att) {
        Element el = new Element("optimalTerritoryAttributes");
        el.addContent(createElementWithText("territorySize", String.valueOf(att.getTerritorySize())));
        return el;
    }

    private Element createElementDimensionsZoo(int width, int height) {
        Element dimEl = new Element("dimensions");
        dimEl.addContent(createElementWithText("width", Integer.toString(width)));
        dimEl.addContent(createElementWithText("height", Integer.toString(height)));
        return dimEl;
    }

    private Element createElementZoo(IZoo zoo) {
        Element zooEl = new Element("zoo");
        zooEl.setAttribute(createAttribute("name", zoo.getName(friendSave).trim()));
        zooEl.addContent(createElementDimensionsZoo(zoo.getWidth(friendSave), zoo.getHeight(friendSave)));
        zooEl.addContent(createElementWithText("age", Integer.toString(zoo.getAge(friendSave))));
        zooEl.addContent(createElementWithText("monthsPerEvaluation", Integer.toString(zoo.getMonthsPerEvaluation(friendSave))));
        zooEl.addContent(createElementWithText("horizon", Integer.toString(zoo.getHorizon(friendSave))));
        zooEl.addContent(createElementWithText("language", zoo.getOption(friendSave).getLocale(friendSave)));
        return zooEl;
    }
}
