package ma.youcode.utils;

import junit.framework.TestCase;

public class CollectorTest extends TestCase {

    public void testCollect() {
        int[][] arr1 = new int[2][];
        arr1[0] = new int[]{1,2};
        arr1[1] = new int[]{1,3};

        int[][] arr2 = new int[2][];
        arr2[0] = new int[]{2,2};
        arr2[1] = new int[]{2,3};

        int[][] result = Collector.collect(arr1, arr2);
        assertEquals(4,result.length);
        //assertEquals(2,result[2][0]);
        // assertEquals(2,result[2][1]);

    }
}