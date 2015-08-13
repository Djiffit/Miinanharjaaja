/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinanharjaaja.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Konsta
 */
public class PeliTest {
    
    public PeliTest() {
    }
    
    @Test
    public void testGetAlue() {
        Alue expResult = new Alue(3, 1);
        Peli instance = new Peli(expResult);
        
        Alue result = instance.getAlue();
        assertEquals(expResult, result);
    }
    
}
