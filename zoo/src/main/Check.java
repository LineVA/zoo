package main;

import zoo.IZoo;

/**
 *
 * @author doyenm
 */
public class Check {

   public static void check(IZoo zoo){
       System.out.println("Beginning of the zoo's study : ");
       // Well-beeing of each animal
       // Special events : 
        // birth
        // death
       // Zoo evaluation
       int zooEvaluation = zoo.evaluate();
       System.out.println("The evaluation of the zoo : " + zooEvaluation);
   }
    
    
}
