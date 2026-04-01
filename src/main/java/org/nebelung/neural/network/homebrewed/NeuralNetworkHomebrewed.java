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
        wholeNetwork();
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
        
        for (int r=0; r<5000; r++) {
            for (double[] line: data) {
                double result = neuron.input(new double[] {line[0], line[1]});
                double error = line[2] - result;
                neuron.adjust(error);
            }
        }

        // show how the traing data is processed eventually
        for (double[] line: data) {
            double output = neuron.input(new double[] {line[0], line[1]});
            double error = output - line[2];
            System.out.println(line[0] + " : " + line[1] + " : " + line[2] +" --> " + output + " / " + error);
        }      
        
        // show final state of neuron
        double[] weights = neuron.getWeights();
        double bias = neuron.getBias();
        System.out.println(weights[0] + " : " + weights[1] + " : " + bias);
    }
    
    public static void wholeNetwork() {
        int inputWidth = 2;
        int outputWidth = 2;
        
        // baue netzwerk
        Network network = new Network(inputWidth, new int[] {3}, outputWidth);
        
        int rounds = 1000;
        double[] roundErrors = new double[rounds];
        
        double[][] data = new double[][] {
            {0.2, 0.2, 1, 0},
            {0.2, 0.8, 1, 0},
            {0.8, 0.8, 0, 1},
            {0.8, 0.2, 1, 0},
            {0.5, 0.5, 0, 1},
        };
        
        for(int q=0; q<rounds; q++) {
            for(double[] line: data) {
                double[] inputs = new double[] {line[0], line[1]};
                double[] expectation = new double[] {line[2], line[3]};
                
                // calculate output
                double[] result = network.input(inputs);

                // calculate error
                double[] errors = new double[outputWidth];
                for(int i = 0; i<outputWidth; i++){
                    double error = expectation[i] - result[i];
                    errors[i] = error;
                }
                network.adjust(errors);
            }
            
            
        }
        
        for(double[] line: data) {
            double[] inputs = new double[] {line[0], line[1]};
            double[] expectation = new double[] {line[2], line[3]};

            // calculate output
            double[] result = network.input(inputs);

            // calculate error
            double[] errors = new double[outputWidth];
            for(int i = 0; i<outputWidth; i++){
                double error = expectation[i] - result[i];
                errors[i] = error;
            }
            
            System.out.println(expectation[0] + " -> " + result[0] + " : " + expectation[1] + " -> " + result[1]);
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
