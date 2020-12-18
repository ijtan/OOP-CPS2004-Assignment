package Task1;

import java.util.ArrayList;

import sun.jvm.hotspot.tools.SysPropsDumper;

public class qt {

    boolean state, isLeaf;
    int size, max, index;
    qt parent, subTrees[];
    // ArrayList<Integer> indeces;
    ArrayList<Integer> contained = new ArrayList<Integer>();

    public qt(int index) {
        
        this.index = index;
        this.isLeaf = false;
        System.out.println("My index is: " + index);
    }

    public qt init(int size, char[] image) {
        if(index>64){
            System.err.println("index out of bonds: "+index);
            return null;
        }
        this.max = image.length;

        if (size >= max) {
            this.isLeaf = true;
            subTrees = null;
            this.size = 1;
            parent.contained.add(index);
            this.contained.add(index);

            this.state = charToBool(image[index - 1]);
            return this;
        }
        subTrees = new qt[4];
        final int startIndex = 4* (this.index-1);
        for (int i = 0; i < subTrees.length; i++) {
            subTrees[i] = new qt(startIndex + i+1);
            subTrees[i].parent = this;
            subTrees[i].init(size * 4, image);
        }
        if (parent != null)
            parent.contained.addAll(this.contained);
        return this;
    }

    // public void assign(char[] image) {
    // char[] formatted = new char[max];
    // for (int i = 0; i < image.length; i++)
    // formatted[indeces.get(i)] = image[i];
    // for (int i = 1; i <= formatted.length; i++)
    // this.setPixel(formatted[i - 1], i);

    // }

    // public boolean setPixel(char pixel, int index) {
    // if (this.isLeaf && this.index == index) {
    // this.state = charToBool(pixel);
    // return true;
    // }
    // if (!this.isLeaf)
    // for (qt q : subTrees) {
    // if (q.contains(index))
    // return q.setPixel(pixel, index);
    // }
    // if (this.isLeaf) {
    // System.out.print("leaf with wrong index!!\t");
    // }
    // if (this.subTrees != null) {
    // System.out.print("subtrees found!!\t");
    // for (qt q : subTrees) {
    // System.out.print("" + q.index + "\t");
    // }
    // }
    // System.out.println("falsing");
    // return false;
    // }

    public boolean contains(int x) {
        if (this.contained.contains(x))
            return true;
        return false;
    }

    public int getPixel(int index) {
        var indices = imageProcessor.indices;
        return getNode(indices.get(index - 1)+1);
    }

    public int getNode(int index) {
        if (this.isLeaf && this.contains(index))
            return state ? 1 : 0;

        for (qt q : subTrees)
            if (q.contains(index))
                return q.getNode(index);

        System.err.println("Dead end when fetching pixel!");
        return 3; // singals error
    }

    public void optimize() {
        if (this.isLeaf)
            return;

        for (qt q : subTrees)
            q.optimize();

        boolean allChildrenAreLeaves = true;
        for (qt q : subTrees) {
            if (q.isLeaf == false)
                allChildrenAreLeaves = false;
        }
        if (allChildrenAreLeaves) {
            if ((subTrees[0].state && subTrees[1].state && subTrees[2].state && subTrees[3].state)
                    || (!subTrees[0].state && !subTrees[1].state && !subTrees[2].state && !subTrees[3].state)) {

                // System.out.println("Absorbing indexes: " + this.contained.get(this); + " - "
                // + subTrees[3].end);
                this.state = subTrees[0].state;
                this.size = 1;
                this.isLeaf = true;

                this.subTrees = null;
                return;
            }
        }
    }

    public int nodeCount() {
        int count = 0;
        if (this.isLeaf)
            return 0;
        for (int i = 0; i < subTrees.length; i++)
            count += subTrees[i].nodeCount();

        return count + 1;
    }

    public int leafCount() {
        int count = 0;
        if (this.isLeaf)
            return 1;
        for (int i = 0; i < subTrees.length; i++)
            count += subTrees[i].leafCount();

        return count;
    }

    public char getPixelByChar(int index) {
        if (getPixel(index) == 1)
            return 'T';
        if (getPixel(index) == 0)
            return 'F';
        return 'E';
    }

    public String print() {
        var newlineInterval = Math.sqrt(max);
        String split = ""; // Here we simply add spaces every 8 characters for formatting
        for (int i = 1; i <= max; i++) {
            split += getPixelByChar(i);
            if ((i) % newlineInterval == 0)
                split += '\n';
        }
        return split;
    }

    private boolean charToBool(char c) {
        if (c == 'T')
            return true;
        return false;
    }
}
