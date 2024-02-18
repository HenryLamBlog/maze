package MazeGenerator;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MazeGeneratorTest {

    private MazeGenerator mazeGenerator;

    @Before
    public void setUp() {
        int dimension = 30; // Set the desired dimension for the maze
        mazeGenerator = new MazeGenerator(dimension);
    }

    @Test
    public void testMazeGeneratorCreation() {
        assertNotNull(mazeGenerator);
    }

    @Test
    public void testMazeGeneratorDimension() {
        int expectedDimension = 30; // Set the expected dimension for the maze
        assertEquals(expectedDimension, MazeGenerator.dimension);
    }

    @Test
    public void testGenerateMaze() {
        mazeGenerator.generateMaze(true);
        int[][] maze = mazeGenerator.getMaze();

        File f = new java.io.File("src/main/maze.csv");
        if (f.exists()){
            f.delete();
        }

        int expectedDimension = 30; // Set the expected dimension for the maze
        MazeGenerator.savecsv(maze, "src/main/maze.csv");
        assertNotNull(maze);
        assertEquals(expectedDimension, maze.length);
        assertEquals(expectedDimension, maze[0].length);


        MazeGenerator.savecsv(maze, "src");
        assertTrue(true);
    }
}