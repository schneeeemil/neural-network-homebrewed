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
        double[] inputs = new double[inputWidth];
        Random random = new Random();
        
        // baue netzwerk
        Network network = new Network(inputWidth, outputWidth, 3, 3);
        // würfle input 
        for(int i = 0; i<inputWidth; i++){
            inputs[i] = random.nextBoolean() ? random.nextDouble() : -1 * random.nextDouble();
        }
        // gebe input aus
        System.out.println(Arrays.toString(inputs));
        // gebe output aus        
        double[] output = network.input(inputs);
        System.out.println(Arrays.toString(output));
    }
}
