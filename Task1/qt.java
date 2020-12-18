package Task1;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class qt {

    boolean state, isLeaf;
    int size, max, index;
    qt parent, subTrees[];
    ArrayList<Integer> indeces;
    ArrayList<Integer> contained = new ArrayList<Integer>();
   

    public qt(int index) {
        this.index = index;
        this.isLeaf = false;
        
    }

    public qt init(int size, int max, char[] image) {
        this.max = max;
        this.indeces = indexShifter(max);


        if (size >= max) {
            this.isLeaf = true;
            subTrees = null;
            this.size = 1;
            parent.contained.add(index);
            this.contained.add(index);
            return this;
        }
        subTrees = new qt[4];

        int startIndex = 4 * this.index;

        for (int i = 0; i < subTrees.length; i++) {
            subTrees[i] = new qt(startIndex + i);
            subTrees[i].parent = this;
            subTrees[i].init(size * 4, max, image);
        }

        if (parent != null)
            parent.contained.addAll(this.contained);

        return this;
    }

    public ArrayList<Integer> indexShifter(int current) {
        ArrayList<Integer> prevIndeces = new ArrayList<Integer>();
        if (current <= 4) {
            prevIndeces = {1,2,3,4};
            prevIndeces.add(0);
            prevIndeces.add(1);
            prevIndeces.add(2);
            prevIndeces.add(3);
            return prevIndeces;
        }
        prevIndeces = indexShifter(current / 4);

        double size = (int) Math.sqrt(current) / 2;

        ArrayList<Integer> indexes = new ArrayList<Integer>();

        for (int i = 0; i < size; i += 1) { // how many y axis (*2)
            for (int j = 0; j < size; j++) {
                int now = 4 * prevIndeces.get(j) + 8 * prevIndeces.get(i);
                indexes.add(now + 0);
                indexes.add(now + 1);
            }
            for (int j = 0; j < size; j++) {
                int now = 4 * prevIndeces.get(j) + 8 * prevIndeces.get(i);
                indexes.add(now + 2);
                indexes.add(now + 3);
            }
        }
        return indexes;
    }

    public void assign(char[] image) {
        char[] formatted = new char[max];
        for (int i = 0; i < image.length; i++)
            formatted[indeces.get(i)] = image[i];
        for (int i = 1; i <= formatted.length; i++) 
            this.setPixel(formatted[i - 1], i);
        
    }

    public boolean setPixel(char pixel, int index) {
        if (this.isLeaf && this.index == index) {
            this.state = charToBool(pixel);
            return true;
        }
        if (!this.isLeaf)
            for (qt q : subTrees) {
                if (q.contains(index))
                    return q.setPixel(pixel, index);
            }
        if (this.isLeaf) {
            System.out.print("leaf with wrong index!!\t");
        }
        if (this.subTrees != null) {
            System.out.print("subtrees found!!\t");
            for (qt q : subTrees) {
                System.out.print("" + q.index + "\t");
            }
        }
        System.out.println("falsing");
        return false;
    }

    public boolean contains(int x) {
        if (this.contained.contains(x))
            return true;

        // if (this.start <= x && x <= this.end)
        // return true;
        return false;
    }

    public int getPixel(int index) {
        if (this.isLeaf && this.contains(index))
            return state ? 1 : 0;

        for (qt q : subTrees)
            if (q.contains(index))
                return q.getPixel(index);

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
                                                                                                                 

                // System.out.println("Absorbing indexes: " + this.contained.get(this); + " - " + subTrees[3].end);
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
        String formatted = "";
        for (int i : indeces)
            formatted += getPixelByChar(i+1);

        String split = ""; // Here we simply add spaces every 8 characters for formatting
        for (int i = 0; i < formatted.length(); i++) {
            split += formatted.charAt(i);
            if ((i+1) % newlineInterval == 0)
                split += '\n';
        }
        return split;
    }

    boolean charToBool(char c) {
        if (c == 'T')
            return true;
        return false;
    }
}
