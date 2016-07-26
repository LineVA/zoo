package zoo.statistics;

/**
 *
 * @author doyenm
 */
public class Compare {
    
    public static double compare(double optimal, double actual, double width){
          if ((actual >= (1.0 - width) * optimal) & (actual <= (1.0 + width) * optimal)) {
            return 5;
        } else if (actual >= (1.0 - 2*width) * optimal && actual <= (1.0 + 2*width) * optimal) {
            return 3;
        } else if (actual >= (1.0 - 3*width) * optimal && actual <= (1.0 + 3*width) * optimal) {
            return 2;
        } else if (actual >= (1.0 - 4*width) * optimal && actual <= (1.0 + 4*width) * optimal) {
            return 1;
        } else if (actual >= (1.0 - 5*width) * optimal && actual <= (1.0 + 5*width) * optimal) {
            return 0;
        } else if (actual >= (1.0 - 6*width) * optimal && actual <= (1.0 + 6*width) * optimal) {
            return -1;
        } else if (actual >= (1.0 - 7*width) * optimal && actual <= (1.0 + 7*width) * optimal) {
            return -2;
        } else if (actual >= (1.0 - 8*width) * optimal && actual <= (1.0 + 8*width) * optimal) {
            return -3;
        } else if (actual >= (1.0 - 9*width) * optimal && actual <= (1.0 + 9*width) * optimal) {
            return -4;
        } else {
            return -5;
        }  
    }
    
    public static double returnMin(){
        return -5.0;
    }
    
    public static double returnNegativMean(){
        return -2.0;
    }
}
