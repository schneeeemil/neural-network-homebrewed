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
    public double bias;
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
        this.bias = 0.0;
        this.learningRate = 1.0;
    }

    public double input(double[] inputs){
        double sum = this.bias;
        this.lastInputs = inputs.clone();
        for(int i = 0; i<this.inputWidth; i++){
            sum += this.weights[i] * inputs[i];
        }
        this.lastOutput = sigmoid(sum);
        return this.lastOutput;
    }

    public double[] adjust(double error){               
        double derivative = sigmoidDerivative(this.lastOutput);
        double[] propagatedErrors = new double[weights.length];
        
        for(int i=0; i<weights.length; i++){
            weights[i] += learningRate * error * derivative * this.lastInputs[i];
            propagatedErrors[i] = weights[i] * error * derivative;
        }        
        bias += this.learningRate * error * derivative;        
        return propagatedErrors;
    }
    
    private double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    private double sigmoidDerivative(double x) {
        return x * (1.0 - x);
    }
    
    public void setLearningRate(double learningRate){
        this.learningRate = learningRate;
    }
    
    // getter and setter for testing
    
    public void setWeights(double[] weights){
        this.weights = weights;
    }
    
    public double[] getWeights(){
        return weights;
    }
    
    public void setBias(double bias){
        this.bias = bias;
    }
    
    public double getBias(){
        return this.bias;
    }
}
