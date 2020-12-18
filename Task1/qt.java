package Task1;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class qt {

    int index;
    int start, end;
    // False = Black
    // True = White
    boolean state;
    boolean isLeaf;
    int size;
    int max;
    ArrayList<Integer> indeces;
    ArrayList<Integer> contained = new ArrayList<Integer>();
    qt subTrees[];
    qt parent;

    // List<Integer> subIndices = new ArrayList<Integer>();

    public qt(int index) {
        // System.out.println("I have been initated with index: "+index);
        this.index = index;
        this.isLeaf = false;
        // this.init(4, (end.x-start.x+1 * end.y-start.y+1));
    }

    public qt init(int size, int max) {
        this.max = max;
        this.indeces = indexShifter(max);
        if (size >= max) {
            this.isLeaf = true;
            subTrees = null;
            // var indexes = indexShifter();
            // this.index = indexes.get(index - 1);

            this.start = this.end = this.index;
            this.size = 1;
            parent.contained.add(index);
            this.contained.add(index);
            return this;
        }
        subTrees = new qt[4];

        int startIndex = 4 * (this.index - 1) + 1;
        int endIndex = 4 * (this.index);
        this.start = startIndex;
        this.end = endIndex;

        for (int i = 0; i < subTrees.length; i++) {
            subTrees[i] = new qt(startIndex + i);
            subTrees[i].parent = this;
            subTrees[i].init(size * 4, max);
        }
        this.start = subTrees[0].start;
        this.end = subTrees[3].end;
        if (parent != null)
            parent.contained.addAll(this.contained);
        System.out.print("not initalized and i contain: ");
        for (Integer integer : contained)
            System.out.print(integer + ", ");
        System.out.println();
        // System.out.println("node init: " + this.start + " - " + this.end);

        return this;
    }

    public ArrayList<Integer> indexShifter(int current) {
        ArrayList<Integer> prevIndeces = new ArrayList<Integer>();
        if (current <= 4) {
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

        // System.out.println("test indexes: "+current);

        // int j = 0;
        // if(this.indeces == null)
        // for (int i : indexes) {
        // System.out.print(i + "\t");
        // j++;

        // if (j == Math.sqrt(current)) {
        // System.out.println();
        // j = 0;
        // }
        // }
        return indexes;

    }

    public static int log(int x, int b) {
        return (int) (Math.log(x) / Math.log(b));
    }

    // public int recurseShift() {
    // System.out.println("shifting " + index + " to " + indexShifter().get(index -
    // 1));
    // this.index = indexShifter().get(index - 1);

    // if (this.isLeaf) {
    // // return getPixelByChar(this.index);
    // return index;
    // }
    // for (qt q : subTrees) {
    // q.recurseShift();
    // // System.out.print(' ');
    // if(q.isLeaf)
    // this.subIndices.add(q.index);
    // else
    // this.subIndices.addAll(q.subIndices);
    // }
    // this.start = subTrees[0].index;
    // this.end = subTrees[3].index;
    // this.subIndices.clear();
    // // System.out.println('\n');
    // return 0;
    // }

    public void testShift() {
        ArrayList<qt> childTrees = new ArrayList<qt>();

        for (qt q : subTrees) {
            childTrees.add(q);
        }
    }

    public void assign(char[] image) {

        char[] formatted = new char[max];
        for (int i = 0; i < image.length; i++) {
            formatted[indeces.get(i)] = image[i];
            System.out.println("formatting " + (indeces.get(i)) + " to " + image[i]);
        }
        String fString = formatted.toString();
        System.out.println("This is the forrmatted string: " + fString);

        for (int i = 1; i <= formatted.length; i++) {
            this.setPixel(formatted[i - 1], i);
            System.out.println("Setting " + i + " to " + formatted[i - 1] + "/" + getPixel(i));
        }
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
                    || (!subTrees[0].state && !subTrees[1].state && !subTrees[2].state && !subTrees[3].state)) { // can
                                                                                                                 // be
                                                                                                                 // absorbed
                // System.out.println("Absorbing indexes: " + indexes.get(subTrees[0].start-1) +
                // " - " + indexes.get(subTrees[3].end-1));
                System.out.println("Absorbing indexes: " + subTrees[0].start + " - " + subTrees[3].end);
                this.state = subTrees[0].state;
                this.size = 1;
                this.isLeaf = true;

                this.start = subTrees[0].start;
                this.end = subTrees[3].end;
                // for (qt q : subTrees) {
                // if (q.contained.size() > 0)
                // this.contained.addAll(q.contained);
                // else
                // System.err.println("empty container!");
                // }
                // System.out.print("now carrying: ");
                // for(int i: contained){
                // System.out.print(i+", ");
                // }

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
