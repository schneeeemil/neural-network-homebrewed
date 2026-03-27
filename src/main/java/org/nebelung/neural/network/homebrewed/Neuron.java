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
    
    
    public Neuron (int inputWidth){
        this.inputWidth = inputWidth;
        Random random = new Random();
        weights = new double[inputWidth];
        for(int i = 0; i<inputWidth; i++){
            weights[i] = random.nextBoolean() ? random.nextDouble() : -1 * random.nextDouble();
        }
    }
    
    public double input(double[] inputs){
        double result = 0;
        for(int i = 0; i<inputWidth; i++){
            result += weights[i] * inputs[i];
        }
        return result;
    }
    
}
