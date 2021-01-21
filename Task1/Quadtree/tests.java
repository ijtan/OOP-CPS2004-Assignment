package Quadtree;

public class tests {
    public static void runTests(){
    System.out.println("Starting Unit Tests");
    qt tester = new qt(1); // MyClass is tested
    String exampleImg = "TTTTTTTTTTTTTTTTTTTTFFFTFFFFTTFFFFFFTTTTTTTTTTTTTTTTTTFFFFFFTTFF";
    String image = imageProcessor.preprocess(exampleImg.replaceAll("\n", ""));
    int len = image.length();
    tester.init(1, image.toCharArray());
    System.out.print("Testing Leaf Count...\t");
    assert len==tester.leafCount() : "Leaf count does not match pixel count!";
    System.out.println("Passed");
    

    int nodeMax = 0;
    for (int i = len / 4; i >= 1; i /= 4) {
        nodeMax += i;
    }
    System.out.print("Testing Node Count...\t");
    assert nodeMax==tester.nodeCount() : "node count does not match calculated one!";

    tester.optimize();
    asertChildNodes(tester);

    System.out.println("Unit Testing Passed");

}

private static void asertChildNodes(qt quad){

        if (quad.isLeaf())
            return;
        boolean state = quad.getState();
        qt[] subTrees = quad.getSubtrees();
        for (int i = 0; i < subTrees.length; i++)
            asertChildNodes(subTrees[i]);            
        
        boolean allFalse = !subTrees[0].getState()&&!subTrees[1].getState()&&!subTrees[2].getState()&&!subTrees[3].getState();
        boolean allTrue = subTrees[0].getState()&&subTrees[1].getState()&&subTrees[2].getState()&&subTrees[3].getState();
        boolean allSame = allFalse||allTrue;
        assert !allSame : "Found unoptimized nodes!";
}
}
