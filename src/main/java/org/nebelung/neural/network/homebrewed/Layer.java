/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.nebelung.neural.network.homebrewed;

/**
 *
 * @author emils
 */
public class Layer {
    public int inputWidth, layerWidth;
    public Neuron[] neurons;
    
    public Layer (int inputWidth, int layerWidth){
        this.inputWidth = inputWidth;
        this.layerWidth = layerWidth;
        neurons = new Neuron[layerWidth];
        for(int i=0; i<layerWidth; i++){
            neurons[i] = new Neuron(inputWidth);
        }
    }
    
    public double[] input(double[] inputs){
        double[] results = new double[layerWidth];
        for(int i = 0; i<layerWidth; i++){
            results[i] = neurons[i].input(inputs);
        }
        return results;
    }
    
    public double[] adjust (double[] errors){
        double[] adjustments = new double[inputWidth];
        for(int i = 0; i<layerWidth; i++){
            double[] neuronErrors = neurons[i].adjust(errors[i]);
            for (int t =0; t<inputWidth; t++){
                adjustments[t] += neuronErrors[t];
            }
        }
        return adjustments;
    }
}
