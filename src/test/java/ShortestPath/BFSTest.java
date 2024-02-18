package ShortestPath;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BFSTest {
    private final PrintStream stdout = System.out;

    protected void readMazeFromFile(String filename, int[][] maze, int dim){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int rowIndex = 0;
            while ((line = reader.readLine()) != null) {
                String[] cells = line.split(",");
                if (maze == null) {
                    dim = cells.length;
                    maze = new int[dim][dim];
                }
                for (int colIndex = 0; colIndex < dim; colIndex++) {
                    maze[rowIndex][colIndex] = Integer.parseInt(cells[colIndex].trim());
                }
                rowIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkShortestPathSol(){
        int[][] sampleMaze = new int[30][30];
        readMazeFromFile("src/test/MazeMap_SPT.csv", sampleMaze, 30);

        ArrayList<int[]> sol = BFS.findSolution(sampleMaze, 30, 0, 12, 29, 1); // target function
        assertEquals(sol.size(), 49);

        int[][] noSolmaze = new int[30][30];
        readMazeFromFile("src/test/blockedExit.csv", noSolmaze, 30);

        ArrayList<int[]> noSol = BFS.findSolution(noSolmaze, 30, 0, 0, 29, 29); // target function
        assertEquals(noSol.size(), 0);

        int[][] emptyMaze = new int[30][30];
        readMazeFromFile("src/test/emptymap.csv", emptyMaze, 30);

        ArrayList<int[]> emptySol = BFS.findSolution(emptyMaze, 30, 0,0, 29, 29); // target function
        assertEquals(emptySol.size(), 59);
    }

    @Test
    public void checkStringifySol(){
        int[][] sampleMaze = new int[30][30];
        readMazeFromFile("src/test/MazeMap_SPT.csv", sampleMaze, 30);

        ArrayList<int[]> sol = BFS.findSolution(sampleMaze, 30, 0, 12, 29, 1);

        String sampleExpected1 = "[(12,0),(12,1),(12,2),(13,2),(13,3),(13,4),(14,4),(14,5),(14,6),(14,7),\n"
                +"(14,8),(13,8),(12,8),(12,9),(12,10),(11,10),(10,10),(10,11),(10,12),(9,12),\n"
                +"(9,13),(8,13),(7,13),(7,14),(7,15),(6,15),(5,15),(4,15),(4,16),(4,17),\n"
                +"(4,18),(4,19),(4,20),(4,21),(5,21),(6,21),(6,22),(6,23),(6,24),(5,24),\n"
                +"(5,25),(5,26),(4,26),(3,26),(2,26),(1,26),(1,27),(1,28),(1,29)]";
        String sampleExpected2 = "[(12,0),(12,1),(12,2),(13,2),(13,3),(13,4),(14,4),(14,5),(14,6),(14,7),\n"
                +"(14,8),(13,8),(12,8),(12,9),(12,10),(11,10),(10,10),(10,11),(10,12),(9,12),\n"
                +"(9,13),(9,14),(9,15),(8,15),(7,15),(6,15),(5,15),(4,15),(4,16),(4,17),\n"
                +"(4,18),(4,19),(4,20),(4,21),(5,21),(6,21),(6,22),(6,23),(6,24),(5,24),\n"
                +"(5,25),(5,26),(4,26),(3,26),(2,26),(1,26),(1,27),(1,28),(1,29)]";
        String sampleActual = BFS.stringSol(sol);

        assertTrue(sampleExpected1.equals(sampleActual) || sampleExpected2.equals(sampleActual)); // target funciton

        int[][] noSolmaze = new int[30][30];
        readMazeFromFile("src/test/blockedExit.csv", noSolmaze, 30);

        ArrayList<int[]> noSol = BFS.findSolution(noSolmaze, 30, 0, 0, 29, 29);

        String expected = "No Shortest Path is found";
        String actual = BFS.stringSol(noSol); // target function

        assertEquals(expected, actual);

        int[][] emptyMaze = new int[30][30];
        readMazeFromFile("src/test/emptymap.csv", emptyMaze, 30);

        ArrayList<int[]> emptySol = BFS.findSolution(emptyMaze, 30, 0,0, 29, 29);

        String expect1 = "[(0,0),(1,0),(2,0),(3,0),(4,0),(5,0),(6,0),(7,0),(8,0),(9,0),\n" +
                "(10,0),(11,0),(12,0),(13,0),(14,0),(15,0),(16,0),(17,0),(18,0),(19,0),\n" +
                "(20,0),(21,0),(22,0),(23,0),(24,0),(25,0),(26,0),(27,0),(28,0),(29,0),\n" +
                "(29,1),(29,2),(29,3),(29,4),(29,5),(29,6),(29,7),(29,8),(29,9),(29,10),\n" +
                "(29,11),(29,12),(29,13),(29,14),(29,15),(29,16),(29,17),(29,18),(29,19),(29,20),\n" +
                "(29,21),(29,22),(29,23),(29,24),(29,25),(29,26),(29,27),(29,28),(29,29)]";
        String expect2 = "[(0,0),(0,1),(0,2),(0,3),(0,4),(0,5),(0,6),(0,7),(0,8),(0,9),\n" +
                "(0,10),(0,11),(0,12),(0,13),(0,14),(0,15),(0,16),(0,17),(0,18),(0,19),\n" +
                "(0,20),(0,21),(0,22),(0,23),(0,24),(0,25),(0,26),(0,27),(0,28),(0,29),\n" +
                "(1,29),(2,29),(3,29),(4,29),(5,29),(6,29),(7,29),(8,29),(9,29),(10,29),\n" +
                "(11,29),(12,29),(13,29),(14,29),(15,29),(16,29),(17,29),(18,29),(19,29),(20,29),\n" +
                "(21,29),(22,29),(23,29),(24,29),(25,29),(26,29),(27,29),(28,29),(29,29)]";
        String actual1 = BFS.stringSol(emptySol); // target function

        assertTrue(expect1.equals(actual1)||expect2.equals(actual1));
    }

    @Test
    public void checkCSVResSys(){
        ByteArrayOutputStream captor = new ByteArrayOutputStream();
        String path = "src/main/shortestPathRes.csv";
        File f = new java.io.File(path);
        if (f.exists()){
            f.delete();
        }

        int[][] maze = new int[30][30];
        readMazeFromFile("src/test/MazeMap_SPT.csv", maze, 30);
        ArrayList<int[]> sol = BFS.findSolution(maze, 30, 0, 12, 29, 1);

        System.setOut(new PrintStream(captor));
        BFS.outputSol(sol, path); // target function

        assertEquals("Create new\r\nShortest Path exported to .csv\r\n", captor.toString());
        System.setOut(stdout);

        if (f.exists()){
            f.delete();
        }

        readMazeFromFile("src/test/blockedExit.csv", maze, 30);
        sol = BFS.findSolution(maze, 30, 0, 0, 29, 29);

        captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
        BFS.outputSol(sol, path); // target function

        assertEquals("Create new\r\nShortest Path exported to .csv\r\n", captor.toString());
        System.setOut(stdout);

        if (f.exists()){
            f.delete();
        }

        readMazeFromFile("src/test/emptymap.csv", maze, 30);
        sol = BFS.findSolution(maze, 30, 0, 0, 29, 29);

        captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
        BFS.outputSol(sol, "src"); // target function
        assertEquals("Error\r\n", captor.toString());
        System.setOut(stdout);
    }
}
