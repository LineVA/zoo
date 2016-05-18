package zoo.statistics;

import java.util.Random;

/**
 *
 * @author doyenm
 */
public class Uniform extends Random{

    public int intAverage(int average){
       int min = average - average/2;
       if(min == 0){
           min = 1;
       }
       int max = average + average/2;
       int actual = super.nextInt(max);
       if(actual >= min){
           return actual;
       } else {
           return intAverage(average);
       }
   }
    
   public boolean isGreaterOrEquals(double lim){
       return super.nextDouble() >= lim;
   }
    
}
