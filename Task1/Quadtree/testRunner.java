package Quadtree;

public class testRunner {
    public static void runTests(){
    qt tester = new qt(1); // MyClass is tested
    String exampleImg = "TTTTTTTTTTTTTTTTTTTTFFFTFFFFTTFFFFFFTTTTTTTTTTTTTTTTTTFFFFFFTTFF";
    String image = imageProcessor.preprocess(exampleImg.replaceAll("\n", ""));
    int len = image.length();
    tester.init(1, image.toCharArray());
    assert len==tester.leafCount() : "Leaf count does not match pixel count!";
    

    int nodeMax = 0;
    for (int i = len / 4; i >= 1; i /= 4) {
        nodeMax += i;
    }
    assert nodeMax==tester.nodeCount() : "node count does not match calculated one!";

    tester.optimize();
    asertChildNodes(tester);

}

private static void asertChildNodes(qt quad){

        if (quad.isLeaf())
            return;
        boolean state = quad.getState();
        qt[] subTrees = quad.getSubtrees();
        for (int i = 0; i < subTrees.length; i++){
            asertChildNodes(subTrees[i]);            
        }
        boolean allFalse = !subTrees[0].getState()&&!subTrees[1].getState()&&!subTrees[2].getState()&&!subTrees[3].getState();
        boolean allTrue = subTrees[0].getState()&&subTrees[1].getState()&&subTrees[2].getState()&&subTrees[3].getState();
        boolean allSame = allFalse||allTrue;
        assert !allSame : "Found unoptimized nodes!";
}
}
