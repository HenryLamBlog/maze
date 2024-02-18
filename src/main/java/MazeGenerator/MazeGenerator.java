package MazeGenerator;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;
import java.util.Arrays;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import MazeGenerator.Node;

public class MazeGenerator {

    protected Stack<Node> stack = new Stack<>();
    protected Random rand = new Random();
    protected int[][] maze;
    protected static int dimension;

    public MazeGenerator(int dim) {
        maze = new int[dim][dim];
        dimension = dim;
    }

    public void generateMaze(boolean test) {
        stack.push(new Node(0, 0));
        while (!stack.empty()) {
            Node next = stack.pop();
            if (validNextNode(next)) {
                maze[next.y][next.x] = 1;
                ArrayList<Node> neighbors = findNeighbors(next);
                randomlyAddNodesToStack(neighbors);
            }
        }
        for(int x = 0; x < 30; x++){
            for(int y = 0; y < 30; y++){
                if(maze[x][y] == 0){
                    maze[x][y] = 1;
                }else{
                    maze[x][y] = 0;
                }
            }
        }
        if (test)
            maze[29][29] = 1;
    }


    protected boolean validNextNode(Node node) {
        int numNeighboringOnes = 0;
        for (int y = node.y - 1; y < node.y + 2; y++) {
            for (int x = node.x - 1; x < node.x + 2; x++) {
                if (pointOnGrid(x, y) && pointNotNode(node, x, y) && maze[y][x] == 1) {
                    numNeighboringOnes++;
                }
            }
        }
        return (numNeighboringOnes < 3) && maze[node.y][node.x] != 1;
    }

    protected void randomlyAddNodesToStack(ArrayList<Node> nodes) {
        int targetIndex;
        while (!nodes.isEmpty()) {
            targetIndex = rand.nextInt(nodes.size());
            stack.push(nodes.remove(targetIndex));
        }
    }

    protected ArrayList<Node> findNeighbors(Node node) {
        ArrayList<Node> neighbors = new ArrayList<>();
        for (int y = node.y - 1; y < node.y + 2; y++) {
            for (int x = node.x - 1; x < node.x + 2; x++) {
                if (pointOnGrid(x, y) && pointNotCorner(node, x, y)
                        && pointNotNode(node, x, y)) {
                    neighbors.add(new Node(x, y));
                }
            }
        }
        return neighbors;
    }

    protected Boolean pointOnGrid(int x, int y) {
        return x >= 0 && y >= 0 && x < dimension && y < dimension;
    }

    protected Boolean pointNotCorner(Node node, int x, int y) {
        return (x == node.x || y == node.y);
    }

    protected Boolean pointNotNode(Node node, int x, int y) {
        return !(x == node.x && y == node.y);
    }

    public int[][] getMaze() {
        /*int[][] temp = this.maze;
        for(int x = 0; x < 30; x++){
            for(int y = 0; y < 30; y++){
                if(temp[x][y] == 0){
                    temp[x][y] = 1;
                }
                if(temp[x][y] == 1){
                    temp[x][y] = 0;
                }
            }
        }*/

        return maze;
    }


    public static void savecsv(int[][] savemaze, String path) {
        try {
            // Create the directory if it doesn't exist
            boolean exists = new java.io.File(path).exists();

            // Open the file in append mode
            FileWriter fileWriter = new FileWriter(path);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // If file doesn't exist, write the header row
            if (!exists) {
                System.out.println("file is not find create a new one");
            }

            // Write the data to the file
            //printWriter.println(data);
            //iterate the array
            StringBuilder sc = new StringBuilder();
            /*for (int[] row : maze) {
                //sc.append(Arrays.toString(row) + "\n");
                printWriter.println(Arrays.toString((row)));
            }*/
            String temp;
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    //sb.append(maze[i][j] == 0 ? "*" : " ");
                    //sb.append("  ");
                    //printWriter.println(((maze[i][j] == 0 ? "0" : "1")));
                    //printWriter.println("  ");
                    temp = Integer.toString(savemaze[i][j]);
                    printWriter.print(temp);
                    if (j != dimension - 1) {
                        printWriter.print(", ");
                    }
                }
                printWriter.print("\n");
            }
            printWriter.flush();
            // Close the writers
            printWriter.close();
            fileWriter.close();

            System.out.println("Data appended to CSV file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
    }
}

