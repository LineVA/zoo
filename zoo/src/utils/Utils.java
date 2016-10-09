package utils;

/**
 *
 * @author doyenm
 */
public class Utils {

    public static boolean isPositivOrNull(int test){
        return test >= 0;
    }
    
     public static boolean isPositivOrNull(double test){
        return test >= 0.0;
    }
     
     public static boolean isPositiv(int test){
         return test > 0; 
     }
     
     public static boolean isPositiv(double test){
         return test > 0.0;
     }
}
