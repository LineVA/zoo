package zoo.Statistics;

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
    
    @Override
    public double nextDouble(){
        return super.nextGaussian()*this.standardDeviation + average;
    }
    
    @Override
    public int nextInt(){
        return (int) (super.nextInt()*this.standardDeviation + average);
    }
}
