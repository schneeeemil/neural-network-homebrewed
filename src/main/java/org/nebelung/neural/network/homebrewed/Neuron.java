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
    public double lastOutput;
    public double learningRate;
    
    
    public Neuron (int inputWidth){
        this.inputWidth = inputWidth;
        Random random = new Random();
        this.weights = new double[inputWidth];
        for(int i = 0; i<inputWidth; i++){
            this.weights[i] = random.nextBoolean() ? random.nextDouble() : -1 * random.nextDouble();
        }
        this.learningRate = 0.1;
    }
    
    public void setLearningRate(double learningRate){
        this.learningRate = learningRate;
    }
    
    public void setWeights(double[] weights){
        this.weights = weights;
    }
    
    public double[] getWeights(){return weights;}
    
    public double input(double[] inputs){
        double sum = 0;
        this.lastInputs = inputs;
        for(int i = 0; i<this.inputWidth; i++){
            sum += this.weights[i] * inputs[i];
        }
        this.lastOutput = sigmoid(sum);
        return this.lastOutput;
    }

    public double[] adjust(double error){
        // derivative of sigmoid(output) = output * (1 - output)
        double delta = error * sigmoidDerivative(this.lastOutput);        
        double[] blame = new double[this.inputWidth];
        for(int i=0; i<this.inputWidth; i++){
            // error to propagate backward
            blame[i] = this.weights[i] * delta;            
            // gradient descent weight update
            this.weights[i] -= this.learningRate * delta * this.lastInputs[i];        }
        return blame;
    }
    
    private double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    private double sigmoidDerivative(double output) {
        return output * (1.0 - output);
    }
}
