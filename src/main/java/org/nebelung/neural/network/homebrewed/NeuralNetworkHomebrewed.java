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
        int outputWidth = 2;
        
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
                for(int i = 0; i<outputWidth; i++){
                    double error = output[i] - expectation[i];
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
        double sum = 0.0;
        double radius = 0.5;
        double[] result = new double[2];
        
        for (int i=0; i<inputs.length; i++){
            sum += Math.pow(inputs[i], 2.0);
        }
        
        if(Math.sqrt(sum)<= radius){
            result[0] = 1.0;
            result[1] = 0.0;
        } else {
            result[0] = 0.0;
            result[1] = 1.0;
        }
        
        return result;
    }
}
