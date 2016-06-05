package com.example.qjm3662.newproject;

import org.junit.AfterClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void TestString(){
        String a = "a";
        String b = "a";
        assertEquals(a,b);
    }

}