package ShortestPath;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Library for Shortest Path finding using Breadth-First Search
 */
public class BFS {
    /** Compute the shortest path between 2 nodes
     *
     * @param maze the maze
     * @param dim the dimension of the maze
     * @param x1 xloc of the starting node
     * @param y1 yloc of the starting node
     * @param x2 xloc of the ending node
     * @param y2 yloc of the ending node
     * @return shortest path solution
     */
    public static ArrayList<int[]> findSolution(int[][] maze, int dim, int x1, int y1, int x2, int y2){
        // queue for bfs
        ArrayList<GraphNode> queue = new ArrayList<>();
        // solution list
        ArrayList<int[]> sol = new ArrayList<>();

        // direction movement
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        // storage array for holding the state of the dimension
        boolean[][] visited = new boolean[dim][dim];
        for(int i = 0; i < dim; i++){
            for(int j = 0; j < dim; j++){
                visited[i][j] = (maze[i][j] == 1);
            }
        }

        // holding of the current node
        GraphNode cur = null;

        // Only find the shortest path if the two points are inbound
        if ((x1 >= 0 && x1 < dim) && (y1 >= 0 && y1 < dim) && (x2 >= 0 && x2 < dim) && (y2 >= 0 && y2 < dim)) {
            // only start to find the shortest path if the initial node is valid
            if (maze[y1][x1] == 0) {
                cur = new GraphNode(x1, y1);
                queue.add(cur);
                visited[cur.y][cur.x] = true;
            }

            // boolean indicator for finding the solution
            boolean found = false;

            // holder of the next node to be visited
            GraphNode next;

            // loop until the queue is empty
            while (!queue.isEmpty()) {
                // get the first element of the queue
                cur = queue.get(0);
                // dequeue
                queue.remove(0);

                // break the loop if the current node is at the target node
                if (cur.x == x2 && cur.y == y2) {
                    found = true;
                    break;
                }

                // check the four immediate adjacent direction
                for (int k = 0; k < 4; k++) {
                    int i = cur.x + dx[k];
                    int j = cur.y + dy[k];

                    // if the adjacent node is valid and unvisited
                    if ((i >= 0 && i < dim) && (j >= 0 && j < dim) && !visited[j][i]) {
                        // queue the next node into the queue
                        next = new GraphNode(i, j);
                        next.prev = cur;
                        visited[j][i] = true;
                        queue.add(next);
                    }
                }
            }

            // only construct the solution if a solution is found by backtracking
            if (found) {
                while (cur != null) {
                    sol.add(0, new int[]{cur.x, cur.y});
                    cur = cur.prev;
                }
            }
        }
        return  sol;
    }

    /** Stringify the given solution
     *
     * @param sol shortest path sequence
     * @return stringified solution sequence of the shortest path
     */
    public static String stringSol(ArrayList<int[]> sol){
        // if there is a solution, then turn it into a string
        if (!sol.isEmpty()){
            StringBuilder str = new StringBuilder("[");
            int[] coord;
            for (int k = 0; k < sol.size(); k++){
                coord = sol.get(k);
                str.append("(").append(coord[1]).append(",").append(coord[0]).append(")");
                if (k < sol.size()-1)
                    str.append(",");
                if (k % 10 == 9)
                    str.append("\n");
            }
            str.append("]");

            return str.toString();
        }
        // else just return no solution
        return "No Shortest Path is found";
    }

    /** Output the shortest path to a .csv solution
     *
     * @param sol the shortest path solution
     * @param path the path of the result to be output to
     * @throws IOException any error encountered in io operation
     */
    public static void outputSol(ArrayList<int[]> sol, String path) {
        try{
            boolean exists = new java.io.File(path).exists();

            FileWriter write = new FileWriter(path);
            PrintWriter print = new PrintWriter(path);

            if (!exists) {
                print.println("");
                System.out.println("Create new");
            }

            print.println("Shortest Path found by Breath-first Search:");
            if (sol.isEmpty()){
                print.println("No Shortest Path is found");
            }
            else{
                int[] coord;
                print.println("{");
                for (int k = 0; k < sol.size(); k++){
                    coord = sol.get(k);
                    print.printf("(%d, %d)", coord[1], coord[0]);
                    if (k < sol.size() - 1)
                        print.printf(",");
                    if (k % 10 == 9)
                        print.println();
                }
                print.println("\n}");
                print.printf("Length: %d", sol.size());
            }

            print.close();
            write.close();

            System.out.println("Shortest Path exported to .csv");
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Error");
        }
    }
 }