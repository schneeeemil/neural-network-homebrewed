/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.nebelung.neural.network.homebrewed.Neuron;

/**
 *
 * @author emils
 */
public class NeuronTest {
    
    public NeuronTest() {
    }

//    @org.junit.jupiter.api.BeforeAll
//    public static void setUpClass() throws Exception {
//    }
//
//    @org.junit.jupiter.api.AfterAll
//    public static void tearDownClass() throws Exception {
//    }
//
//    @org.junit.jupiter.api.BeforeEach
//    public void setUp() throws Exception {
//    }
//
//    @org.junit.jupiter.api.AfterEach
//    public void tearDown() throws Exception {
//    }
//    
//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void neuronsCalculateInputAsExpected() {
         Neuron neuron = new Neuron(3);
         neuron.setWeights(new double[] {0.2, 0.3, 0.8});
         double result = neuron.input(new double[] {0.8, 0.3, 0.5});
         assertEquals(0.6570104626734988, result);       
     }
     
     @Test
     public void neuronsAdjustAsExpected(){
         Neuron neuron = new Neuron(3);
         neuron.setLearningRate(1.0);
         neuron.setWeights(new double[] {0.2, 0.3, 0.8});
         neuron.input(new double[] {0.8, 0.3, 0.5});
         
         double[] blame = neuron.adjust(0.2);         
         double[] weights = neuron.getWeights();        
         
         assertEquals(0.1639443656622314, weights[0]);
         assertEquals(0.2864791371233368, weights[1]);
         assertEquals(0.7774652285388947, weights[2]);
         
         assertEquals(0.009013908584442154, blame[0]);
         assertEquals(0.013520862876663231, blame[1]);
         assertEquals(0.036055634337768615, blame[2]);
     }
}
