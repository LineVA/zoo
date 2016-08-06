package zoo.animal;

import backup.save.SaveImpl;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import zoo.animal.death.LifeSpanLightAttributes;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.animal.reproduction.Sex;
import zoo.animal.social.SocialAttributes;
import zoo.animal.specie.Specie;
import zoo.animal.wellbeing.WellBeing;
import zoo.paddock.IPaddock;
import zoo.paddock.TerritoryAttributes;

/**
 *
 * @author doyenm
 */
public interface Animal {

    public boolean isMature();

    public boolean isTooOld();

    public ArrayList<String> info() throws UnknownNameException;

    public void ageing(int monthsPerEvaluation);

    public double wellBeing() throws UnknownNameException;

    public boolean isFromTheSameSpecie(Specie specie);

    public ArrayList<Animal> findRoommatesOfTheSameSpecie();

    public boolean canBePregnant();
    
    public boolean canFecundateAFemale();
    
    public boolean isEnoughHappy();

    public String getName();

    public Specie getSpecie();
    
    public int getActualLitterSize();
    
    public double getActualGestationFrequency();
    
    public IPaddock getPaddock();
    
    public void setName(String name);
    
    public Sex getSex();
    
    /**
     * Friend pattern : give access to each of the fields of Animal only to the
     * save methods
     */
    public String getName(SaveImpl.FriendSave friend);

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

}
