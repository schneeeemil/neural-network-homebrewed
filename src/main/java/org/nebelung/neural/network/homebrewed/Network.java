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
    public int inputWidth, outputWidth, networkWidth, networkDepth;
    public Layer[] layers;
    
    public Network (int inputWidth, int outputWidth, int networkWidth, int networkDepth){
        this.inputWidth = inputWidth;
        this.outputWidth = outputWidth;
        this.networkWidth = networkWidth;
        this.networkDepth = networkDepth;
        layers = new Layer[networkDepth];
        // input layer
        layers[0] = new Layer(inputWidth, networkWidth);
        // intermediate layers
        for(int i=1; i<(networkDepth - 1); i++){
            layers[i] = new Layer(networkWidth, networkWidth);
        }
        // output layer
        layers[networkDepth - 1] = new Layer(networkWidth, outputWidth);
    }
    
    public double[] input(double[] inputs){
        double[] results = layers[0].input(inputs);
        for(int i=1; i<networkDepth; i++){
                results = layers[i].input(results);
        }
        return results;
    }
}
