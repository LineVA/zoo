package zoo.statistics;

import java.util.Random;

/**
 *
 * @author doyenm
 */
public class Gaussian extends Random{

    double average;
    double standardDeviation;
    
    public  Gaussian(double average, double standardDeviation){
        super();
        this.average = average;
        this.standardDeviation = standardDeviation;
    }
    
    public double gaussianDouble(){
        double gaussian = super.nextGaussian()*this.standardDeviation + average;
        if(gaussian > 0.0){
            return gaussian;
        } else {
           return 0.01;
        }
    }
    
    public int gaussianInt(){
        int gaussian = (int) gaussianDouble();
        if(gaussian == 0){
            return 1;
        }
        return gaussian;
    }
}
