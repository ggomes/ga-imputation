package edu.berkeley.path;

import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.DoubleGene;

/**
 * Created by gomes on 10/18/14.
 */
public class Runner {

    public static void main( String [] args){

        int num_ramps = 20;
        int population_size = 500;

        // create configuration
        Configuration conf =   get_config(num_ramps,population_size);

        // create initial population
        Genotype population = create_initial_population(conf);

        for(int i=0; i<200 ; i++){
            population.evolve();
            System.out.println(i + "\t" + population.getFittestChromosome().getFitnessValue());
        }

        for(Gene g : population.getFittestChromosome().getGenes())
            System.out.println(((DoubleGene)g).doubleValue());

    }

    private static Configuration get_config(int num_ramps,int population_size){

        try {

            Configuration conf = new DefaultConfiguration();

            // set fitness function
            FitnessFunction myFunc = new BeatsRunner( "sfd" );
            conf.setFitnessFunction( myFunc );

            // provide a sample chromosome
            Gene[] sampleGenes = new Gene[num_ramps];
            for(int i=0;i<num_ramps;i++){
                double lowerBound = 0;
                double upperBound = 1;
                sampleGenes[i] = new DoubleGene(conf,lowerBound,upperBound);
            }

            Chromosome sampleChromosome = new Chromosome( conf);
            sampleChromosome.setGenes(sampleGenes);
            conf.setSampleChromosome( sampleChromosome );

            // set population size
            conf.setPopulationSize( population_size );

            return conf;

        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
            return null;
        }

    }

    private static Genotype create_initial_population(Configuration conf){
        try {
            return Genotype.randomInitialGenotype( conf );
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
