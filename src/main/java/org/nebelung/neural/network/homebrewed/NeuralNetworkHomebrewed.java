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
    
    public static void main(String[] args){
        singleNeuron();
    }
    
    public static void singleNeuron (){
        Neuron neuron = new Neuron(2);
        
        double[][] data = new double[][] {
            { 0.5,  0.5,  0.0 },
            { 0.6,  0.65, 0.0 },
            { 0.3,  0.75, 1.0 },
            { 0.35, 0.85, 1.0 },
            { 0.7,  0.75, 0.0 },
            { 0.2,  0.95, 1.0 },
            { 0.6,  0.55, 0.0 },
            { 0.35, 0.95, 1.0 },
        };
        
        for (int r=0; r<300; r++) {
            double error = 0;
            for (double[] line: data) {
                double output = neuron.input(new double[] {line[0], line[1]});
                error += output - line[2];
            }
            neuron.adjust(error);
        }
        double[] weights = neuron.getWeights();
        double bias = neuron.getBias();
        
        System.out.println(weights[0] + " : " + weights[1] + " : " + bias);
    }
    
    public static void wholeNetwork() {
        int inputWidth = 3;
        int outputWidth = 2;
        
        // baue netzwerk
        Network network = new Network(inputWidth, new int[] {6,6,6}, outputWidth);
        
        int rounds = 100;
        int trainingDataSize = 100;
        double[] roundErrors = new double[rounds];
        
        double[][] inputCollection = new double[trainingDataSize][inputWidth];
        double[][] expectationCollection = new double[trainingDataSize][outputWidth];
        
        // würfle input
        for(int i=0; i<trainingDataSize; i++){
            inputCollection[i] = prepareInput(inputWidth);
            expectationCollection[i] = determineExpectation(inputCollection[i]);
        }
        // determine expectation
        
        
        for(int q=0; q<rounds; q++) {
            for(int r=0; r<trainingDataSize; r++) {
                double[] inputs = inputCollection[r];
                double[] expectation = expectationCollection[r];
                
                // calculate output
                double[] output = network.input(inputs);

                // calculate error
                double[] errors = new double[outputWidth];
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
