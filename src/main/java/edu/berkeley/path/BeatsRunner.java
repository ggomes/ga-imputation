package edu.berkeley.path;
import edu.berkeley.path.beats.simulator.Scenario;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;
import org.jgap.impl.DoubleGene;

/**
 * Created by gomes on 10/18/14.
 */
public class BeatsRunner extends FitnessFunction {

    private Scenario scenario;

    public BeatsRunner(String configfile){

        scenario = null;
    }

    @Override
    protected double evaluate(IChromosome iChromosome) {

        double sum = 0d;
        for(Gene g : iChromosome.getGenes() ){
            DoubleGene db = (DoubleGene) g;
            sum += db.doubleValue();
        }

        return 1/sum;
    }

}
