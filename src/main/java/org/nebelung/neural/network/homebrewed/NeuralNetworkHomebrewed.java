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
        Network network = new Network(inputWidth, outputWidth, 8, 8);
        
        int rounds = 100;
        double[] roundErrors = new double[rounds];
        
        for(int q=0; q<rounds; q++) {
            for(int r=0; r<rounds; r++) {
                // würfle input 
                double[] inputs = prepareInput(inputWidth);

                // determine expectation
                double[] expectation = determineExpectation(inputs);

                // calculate output
                double[] output = network.input(inputs);

                // calculate error
                double[] errors = new double[inputWidth];
                double cumulatedError = 0;
                for(int i = 0; i<inputWidth; i++){
                    double error = expectation[i] - output[i];
                    errors[i] = error;
                    cumulatedError += Math.abs(error);
                }

                roundErrors[r] = cumulatedError;
                network.adjust(errors);
            }

            System.out.println(Arrays.stream(roundErrors).min() + " : " + Arrays.stream(roundErrors).max() + " : " + Arrays.stream(roundErrors).average());
        }
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
