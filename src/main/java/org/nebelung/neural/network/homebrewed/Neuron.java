/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.nebelung.neural.network.homebrewed;

/**
 *
 * @author emils
 */
import java.util.Random;

public class Neuron {
    public int inputWidth;
    public double[] weights;
    public double[] lastInputs;
    public double lastResult;
    
    
    public Neuron (int inputWidth){
        this.inputWidth = inputWidth;
        Random random = new Random();
        weights = new double[inputWidth];
        for(int i = 0; i<inputWidth; i++){
            weights[i] = random.nextBoolean() ? random.nextDouble() : -1 * random.nextDouble();
        }
    }
    
    public void setWeights(double[] weights){
//        if(weights.length != inputWidth){
//            throw new Exception("You Failed");
//        }
        this.weights = weights;
    }
    
    public double[] getWeights(){return weights;}
    
    public double input(double[] inputs){
        double result = 0;
        lastInputs = inputs;
        for(int i = 0; i<inputWidth; i++){
            result += weights[i] * inputs[i];
        }
        lastResult = result;
        return result;
    }

    public double[] adjust(double error){
        double[] activations = new double[inputWidth];
        double[] blame = new double[inputWidth];
        for(int i=0; i<inputWidth; i++){
            activations[i] = lastInputs[i]*weights[i];
            blame[i] = (activations[i]/lastResult) * error;
            
            weights[i] += blame[i]/100;
        }
        return blame;
    }
}
