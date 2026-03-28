/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.nebelung.neural.network.homebrewed;

import java.util.Random;
import java.util.Arrays;

/**
 *
 * @author emils
 */

public class NeuralNetworkHomebrewed {

    public static void main(String[] args) {
        int inputWidth = 3;
        int outputWidth = 3;
        
        // baue netzwerk
        Network network = new Network(inputWidth, outputWidth, 3, 3);
        
        int rounds = 100;
        double[] roundErrors = new double[rounds];
        
        for(int r=0; r<rounds; r++) {
            // würfle input 
            double[] inputs = prepareInput(inputWidth);

            // determine expectation
            double[] expectation = determineExpectation(inputs);

            // calculate output
            double[] output = network.input(inputs);

            // calculate error
            double[] error = new double[inputWidth];
            double cumulatedError = 0;
            for(int i = 0; i<inputWidth; i++){
                error[i] = expectation[i] - output[i];
                cumulatedError += Math.abs(expectation[i] - output[i]);
            }
            
            roundErrors[r] = cumulatedError;
            
            //@todo... use error for learning

            System.out.println(Arrays.toString(inputs) + " -> " + Arrays.toString(output) + " : " + Arrays.toString(error));
        }
        
        // System.out.println("min error " + roundErrors.min);
        // System.out.println("max error " + cumulatedError);
        // System.out.println("avg error " + cumulatedError);
        

    }
    
    public static double[] prepareInput(int inputWidth){
        double[] inputs = new double[inputWidth];
        Random random = new Random();       
        for(int i = 0; i<inputWidth; i++){
            inputs[i] = random.nextBoolean() ? random.nextDouble() : -1 * random.nextDouble();
        }
        return inputs;
    }
    
    public static double[] determineExpectation(double[] inputs){
        double[] result = inputs;
        Arrays.sort(result);
        return result;
    }
}
