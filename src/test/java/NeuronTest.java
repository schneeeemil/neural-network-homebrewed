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
         neuron.setWeights(new double[] {1.0, 2.0, 3.0});
         double result = neuron.input(new double[] {0.0, 1.0, 2.0});
         assertEquals(8, result);
     }
     
     @Test
     public void neuronsAdjustAsExpected(){
         Neuron neuron = new Neuron(3);
         neuron.setWeights(new double[] {1.0, 2.0, 3.0});
         neuron.input(new double[] {0.0, 1.0, 2.0});
         double[] blame = neuron.adjust(2);
         double[] weights = neuron.getWeights();
         assertEquals(new double[] {0.0, 0.0, 0.0}, blame);
         assertEquals(new double[] {0.0, 0.0, 0.0}, weights);
     }
}
