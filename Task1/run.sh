#!/bin/bash

java -ea -classpath build/ Quadtree.Main examples/testTXT.txt
# java -ea -classpath build/ Quadtree.Main examples/allTrues.txt
# java -ea -classpath build/ Quadtree.Main examples/allFalses.txt
# java -ea -classpath build/ Quadtree.Main examples/2x2x4.txt
# java -ea -classpath build/ Quadtree.Main examples/4x4x4.txt
# java -ea -classpath build/ Quadtree.Main examples/32x32.txt

# java -ea -classpath build/ Quadtree.Main examples/testCSV.csv