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
        return super.nextGaussian()*this.standardDeviation + average;
    }
    
    public int gaussianInt(){
        int gaussian = (int) gaussianDouble();
        if(gaussian == 0){
            return 1;
        }
        return gaussian;
    }
}
