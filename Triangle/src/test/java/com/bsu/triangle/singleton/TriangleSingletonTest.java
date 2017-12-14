package com.bsu.triangle.singleton;

import com.bsu.triangle.wrapper.TriangleList;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TriangleSingletonTest {



    @Test
    void testTriangleSingleton(){

        TriangleList t1 = TriangleList.getInstance();
        TriangleList t2 = TriangleList.getInstance();

        Assert.assertEquals(t1,t2);
    }
}
