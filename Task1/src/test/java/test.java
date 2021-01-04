package Task1.src.test.java;
import Task1.src.main.java.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class test{
    qt tester = new qt(1); // MyClass is tested
    String exampleImg = "TTTTTTTTTTTTTTTTTTTTFFFTFFFFTTFFFFFFTTTTTTTTTTTTTTTTTTFFFFFFTTFF";
    // System.out.println(exampleImg.length());
    String image = imageProcessor.preprocess(exampleImg.replaceAll("\n", ""));
    int len = image.length();

    @Test
    public void assertLeafCounts() {
        tester.init(1, image.toCharArray());
        assertEquals("Leaf count does not match pixel count!", len,tester.leafCount());
    }
    
    @Test
    public void assertNodeCounts() {
        tester.init(1, image.toCharArray());
        int nodeMax = 0;
        for (int i = len / 4; i >= 1; i /= 4) {
            nodeMax += i;
        }
        assertEquals("node count does not match calculated one!", nodeMax, tester.nodeCount());
    }
}
