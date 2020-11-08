package Task1;

public class qt {
    point start, end;

    // False = Black
    // True = White
    boolean state;
    boolean isLeaf;
    int size;

    qt subTrees[];

    public qt( point start, point end) {
        this.start = start;
        this.end = end;
        this.isLeaf = false;
        // this.init(4, (end.x-start.x+1 * end.y-start.y+1));
    }

    public qt init(int size, int max) {
        if((start.x == end.x && start.y == end.y) || size >= max){
            this.isLeaf = true;
            subTrees = null;
            this.size = 1;
            return this;
        }
        subTrees = new qt[4];

        for (int i = 0;i< subTrees.length;i++) {
            subTrees[i] = new qt(start,end).init(size*4,max);
            this.size += subTrees[i].size;
        }

            // tmp = new point(start.x + md, start.y + md);
            // subTrees[0] = new qt(start, tmp).init(makeLeaf);

            // tmp = new point(end.x - md, start.y);
            // tmp1 = new point(end.x, start.y + md);
            // subTrees[1] = new qt(tmp, tmp1).init(makeLeaf);

            // tmp = new point(start.x, end.y - md);
            // tmp1 = new point(start.x + md, end.y);
            // subTrees[2] = new qt(tmp, tmp1).init(makeLeaf);

            // tmp = new point(end.x - md, end.y - md);
            // subTrees[3] = new qt(tmp, end).init(makeLeaf);
            // return this;
        // }
        // else
        //     md = start.x + (end.x - start.x) / 2; // md stand for mid Distance which is distance to middle area of the
                                                  // parent tree
        // for end.x = 7 we get md = 3.5 which = 3


        // point tmp,tmp1;

        // tmp = new point(start.x + md, start.y + md);
        // subTrees[0] = new qt(start, tmp).init(makeLeaf);

        // tmp = new point(end.x-md, start.y);
        // tmp1 = new point(end.x,start.y+md);
        // subTrees[1] = new qt(tmp, tmp1).init(makeLeaf);


        // tmp = new point(start.x, end.y-md);
        // tmp1 = new point(start.x+md, end.y);
        // subTrees[2] = new qt(tmp, tmp1).init(makeLeaf);

        // tmp = new point(end.x-md, end.y - md);
        // subTrees[3] = new qt(tmp, end).init(makeLeaf);
    
        
        return this;
    }

    public void assign(char[][] img) {
        int skipTimes = 0;
        for(int i = 0; i < img[0].length;i++){
            for(int j = 0; j < img.length; j++){
                for(int x = 0; x<subTrees.length;x++){
                    if(subTrees[x].isLeaf){
                        subTrees[x].state = charToBool(img[i][j]); }
                    else{
                        subTrees[x].assign(img);
                        skipTimes = subTrees[x].size;
                    }
                }
                
            }
        }
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
            if (subTrees[0].state == subTrees[1].state == subTrees[2].state == subTrees[3].state) { // can be absorbed
                this.state = subTrees[0].state;
                this.size = 1;
                this.isLeaf = true;
                this.subTrees = null;
            }
        }
    }

    public int getSize(){
        int count = 0;
        if(isLeaf)
            return 1;
        for(int i = 0; i < subTrees.length; i++)
            if(subTrees[i].isLeaf)
                count += subTrees[i].getSize();
        return count;
    }

    public boolean absorbs(point p) {
        if (p.x >= start.x && p.x <= end.x && p.y >= start.y && p.y <= end.y)
            return true;
        return false;
    }

    public char[][] print(char[][] ret) {
        return ret;
    }

    char stateToChar() {
        if (state == true)
            return 'T';
        return 'F';
    }

    boolean charToBool(char c) {
        if (c == 'T')
            return true;
        return false;
    }
}
