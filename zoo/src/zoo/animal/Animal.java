package zoo.animal;

import backup.save.SaveImpl;
import exception.IncorrectDataException;
import exception.IncorrectLoadException;
import exception.name.EmptyNameException;
import exception.name.UnauthorizedNameException;
import exception.name.UnknownNameException;
import java.util.List;
import zoo.animal.death.LifeSpanLightAttributes;
import zoo.animal.feeding.Diet;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.personality.PersonalityAttributes;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.animal.reproduction.Sex;
import zoo.animal.social.SocialAttributes;
import zoo.animal.specie.LightSpecie;
import zoo.animal.specie.Specie;
import zoo.animalKeeper.AnimalKeeper;
import zoo.paddock.IPaddock;
import zoo.paddock.TerritoryAttributes;

/**
 *
 * @author doyenm
 */
public interface Animal {

    public boolean compare(LightAnimal lightAnimal);

    public boolean isMature();

    public boolean isTooOld();

    public boolean isTooStarving();

    public boolean isTooDrowning();

    public List<String> info() throws UnknownNameException, IncorrectDataException;

    public void ageing(int monthsPerEvaluation);

    public double wellBeing(List<AnimalKeeper> keepers) throws UnknownNameException;

    public boolean isFromTheSameSpecie(Specie specie);

    public boolean isFromTheSameSpecie(LightSpecie specie);

    public boolean hasTheSameDiet(Diet diet);

    public List<Animal> findRoommatesOfTheSameSpecie();

    public boolean canBePregnant();

    public boolean isAlreadyPregnant();

    public boolean updateGestationDuration(int months);

    public boolean canFecundateAFemale();

    public boolean isRelatedTo(Animal potentialRelation);

    public boolean isEnoughHappy();

    public void changeDiet(Object obj) throws UnknownNameException;

    public void changeFoodQuantity(Double quantity) throws IncorrectLoadException;

    public void changeFastDays(int fastDays) throws IncorrectLoadException;
    
    public void changeContraceptionMethod(Object contraceptionMethod)
            throws UnknownNameException;

    public String getName();

    public Specie getSpecie();

    public Sex getSex();

    public int getActualFastDays();

    public int getActualStarvation();

    public int getActualDrowning();

    public int getActualLitterSize();

    public double getActualGestationFrequency();

    public IPaddock getPaddock();

    public String getMother();

    public String getFather();

    public void setName(String name) throws EmptyNameException, UnauthorizedNameException;

    /**
     * Friend pattern : give access to each of the fields of Animal only to the
     * save methods
     */
    public String getName(SaveImpl.FriendSave save);

    public Specie getSpecie(SaveImpl.FriendSave save);

    public Sex getSex(SaveImpl.FriendSave save);

    public int getAge(SaveImpl.FriendSave save);

    public FeedingAttributes getOptimalFeeding(SaveImpl.FriendSave save);

    public FeedingAttributes getActualFeeding(SaveImpl.FriendSave save);

    public ReproductionAttributes getActualReproduction(SaveImpl.FriendSave save);

    public LifeSpanLightAttributes getActualLifeSpan(SaveImpl.FriendSave save);

    public SocialAttributes getOptimalSocial(SaveImpl.FriendSave save);

    public TerritoryAttributes getOptimalTerritory(SaveImpl.FriendSave save);

    public int getDiet(SaveImpl.FriendSave friend);

    public PersonalityAttributes getPersonality(SaveImpl.FriendSave friend);

    public double getWellBeeing(SaveImpl.FriendSave save);

    public int getStarvation(SaveImpl.FriendSave save);

    public int getDrowning(SaveImpl.FriendSave save);

    public int getCurrentlyGestationDuration(SaveImpl.FriendSave save);

    public String getMother(SaveImpl.FriendSave save);

    public String getFather(SaveImpl.FriendSave save);

}
