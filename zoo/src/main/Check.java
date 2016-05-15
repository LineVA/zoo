package main;

import zoo.Zoo;

/**
 *
 * @author doyenm
 */
public class Check {

   public static void check(Zoo zoo){
       System.out.println("Beginning of the zoo's study : ");
       // Well-beeing of each animal
       // Special events : 
        // birth
       zoo.birth();
        // death
       zoo.death();
       // Zoo evaluation
       int zooEvaluation = zoo.evaluate();
       System.out.println("The evaluation of the zoo : " + zooEvaluation);
   }
    
    
}
