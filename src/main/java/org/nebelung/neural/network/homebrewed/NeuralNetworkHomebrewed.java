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
        
        // würfle input 
        double[] inputs = new double[inputWidth];
        Random random = new Random();       
        for(int i = 0; i<inputWidth; i++){
            inputs[i] = random.nextBoolean() ? random.nextDouble() : -1 * random.nextDouble();
        }
        
        // determine expectation
        double[] expected = inputs;
        Arrays.sort(expected);
        
        // gebe input aus
        System.out.println("in " + Arrays.toString(inputs));
        
        // gebe output aus        
        double[] output = network.input(inputs);
        System.out.println("out " + Arrays.toString(output));
        
        // calculate error
        double[] error = new double[inputWidth];
        double cumulatedError = 0;
        for(int i = 0; i<inputWidth; i++){
            error[i] = expected[i] - output[i];
            cumulatedError += Math.abs(expected[i] - output[i]);
        }
        System.out.println("err " + Arrays.toString(output));
       
        // cumulate error
        System.out.println("cumulated " + cumulatedError);
    }
}
