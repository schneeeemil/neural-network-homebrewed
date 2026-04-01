/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.nebelung.neural.network.homebrewed;

/**
 *
 * @author emils
 */
public class Network {
    public int inputWidth, outputWidth;
    public Layer[] layers;
    
    public Network (int inputWidth, int[] hiddenLayerWidths, int outputWidth){
        this.inputWidth = inputWidth;
        this.outputWidth = outputWidth;
        
        layers = new Layer[hiddenLayerWidths.length + 1];
        
        int previousLayerWidth = inputWidth;             
        
        // input + intermediate layers
        for(int i=0; i<hiddenLayerWidths.length; i++){
            int currentLayerWidth = hiddenLayerWidths[i];
            layers[i] = new Layer(previousLayerWidth, currentLayerWidth);
            previousLayerWidth = currentLayerWidth;
        }
        
        // output layer
        layers[hiddenLayerWidths.length] = new Layer(previousLayerWidth, outputWidth);
    }
    
    public double[] input(double[] inputs){
        double[] results = layers[0].input(inputs);
        for(int i=1; i<layers.length; i++){
            results = layers[i].input(results);
        }
        return results;
    }
    
    public void adjust(double[] errors){
        double[] errorUp = errors;
        for(int i=layers.length-1; i>=0; i--){
            errorUp = layers[i].adjust(errorUp);
        }
    }
}