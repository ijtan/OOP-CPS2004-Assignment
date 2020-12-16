package Task1;

import java.util.ArrayList;

public class qt {
    int index;
    int start, end;
    // False = Black
    // True = White
    boolean state;
    boolean isLeaf;
    int size;
    int max;

    char[] img;

    qt subTrees[];
    ArrayList<Integer> absorbed = new ArrayList<Integer>();
    ArrayList<Integer> subIndeces = new ArrayList<Integer>();

    public qt(int index, char[] imgArray) {
        // System.out.println("I have been initated with index: "+index);
        this.index = index;
        this.isLeaf = false;
        this.img = imgArray;
        // this.init(4, (end.x-start.x+1 * end.y-start.y+1));
    }

    public qt init(int size, int max) {
        this.max = max;
        if (size >= max) {
            this.isLeaf = true;
            subTrees = null;
            // var indexes = indexShifter();
            // this.index = indexes.get(index - 1);
            this.start = this.end = this.index;
            this.size = 1;
            // this.subIndeces.add(index);
            return this;
        }
        subTrees = new qt[4];

        int startIndex = 4 * (this.index - 1) + 1;
        int endIndex = 4 * (this.index);
        this.start = startIndex;
        this.end = endIndex;

        

        for (int i = 0; i < subTrees.length; i++) {
            subTrees[i] = new qt(startIndex + i, img);
            subTrees[i].init(size * 4, max);

            if(!subTrees[i].isLeaf)
                subIndeces.addAll(subTrees[i].subIndeces);
            else
                subIndeces.add(subTrees[i].index);
        }
        this.start = subTrees[0].start;
        this.end = subTrees[3].end;
        

        System.out.println("node init: "+this.start + " - "+this.end);
        return this;
    }
    public int recursePrint(){
        
        if(this.isLeaf){
            // return getPixelByChar(this.index);
            return index;
        }
        for (qt q : subTrees) {
            System.out.print( q.recursePrint() );
            System.out.print(' ');
        }
        System.out.println('\n');
        return 0;
    }

    public void assign(char[] img) {
        // var indexes = indexShifter();
        for (int i = 0; i < img.length; i++){
            // this.setPixel(img[indexes.get(i - 1) - 1], i);
            this.setPixel(img[i], i+1);
            System.out.println("Setting " + (i+1) + " to " + img[i]+ "/"+ getPixel(i+1));
        }
    }

    public boolean setPixel(char pixel, int index) {
        if (this.isLeaf && (this.index == index || this.contains(index) || this.subIndeces.contains(index))) {
            this.state = charToBool(pixel);
            System.out.println("I with index: "+this.index +" was called wiith index: "+index+" and have been assigned: "+pixel);
            return true;
        }
        
        else if (this.isLeaf && this.index != index){
            System.out.println("wrong leaf!");
            return true;
        }
            

        if (!this.isLeaf)
            for (qt q : subTrees) {
                if (q.contains(index)  || q.absorbed.contains(index) || q.index == index)
                    return q.setPixel(pixel, index);
            }
        else
            System.err.println("am leaf but didnt assign");
        System.err.println("unable to assign!");
        return false;
    }

    public boolean contains(int x) {
        if(subIndeces.contains(x))
            return true;
        return false;
    }

    public int getPixel(int index) {
        if (this.isLeaf && (this.index == index || this.contains(index) || this.absorbed.contains(index)))
            if(this.index == index)
                return state ? 1 : 0;
            else
                return state ? 20 : 10;
        if(subTrees != null){
            for (qt q : subTrees) 
                if(q.contains(index) || q.absorbed.contains(index) || q.index == index)
                    return q.getPixel(index);
        }
        else            
            return 3;

        return 4;
    }

    public void optimize() {
        // var indexes = indexShifter();
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
                // System.out.println("Absorbing indexes: " + indexes.get(subTrees[0].start-1) + " - " + indexes.get(subTrees[3].end-1));
                System.out.println("Absorbing indexes: " + subTrees[0].start + " - " + subTrees[3].end);
                this.state = subTrees[0].state;
                this.size = 1;
                this.isLeaf = true;

                this.start = subTrees[0].start;
                this.end = subTrees[3].end;
                for (qt q : subTrees) {
                    if(q.subIndeces != null)
                        this.absorbed.addAll(q.subIndeces);
                }
                

                this.subTrees = null;
                return;
            }
        }
    }

    public int nodeCount() {
        int count = 0;
        if (this.isLeaf)
            return 1;
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
        
        if (getPixel(index) == 20)
            return 'U';
        if (getPixel(index) == 10)
            return 'D';


        if (getPixel(index) == 3)
            return 'X';
        if (getPixel(index) == 4)
            return 'Y';
        return 'Z';
    }

    public String print() {

        var newlineInterval = Math.sqrt(max);

        String split = ""; // Here we simply add spaces every 8 characters for formatting

        for (int i = 1; i <= max; i++) {
            split += getPixelByChar(i);
            if (i % newlineInterval == 0)
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
