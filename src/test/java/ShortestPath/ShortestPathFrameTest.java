package ShortestPath;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShortestPathFrameTest {
    private final PrintStream stdout = System.out;
    @Test
    public void shortestPathFrameTest(){
        ByteArrayOutputStream captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));

        File f = new java.io.File("src/main/shortestPathRes.csv");
        if (f.exists()){
            f.delete();
        }

        ShortestPathFrame spf = new ShortestPathFrame("src/main/maze.csv", "src/main/shortestPathRes.csv"); // target function
        assertEquals("Create new\r\nShortest Path exported to .csv\r\nShortest Path Frame started\r\n", captor.toString());

        System.setOut(stdout);

        captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));

        if (f.exists()){
            f.delete();
        }

        spf = new ShortestPathFrame("src", "src/main/shortestPathRes.csv"); // target function
        assertEquals("Error\r\nCreate new\r\nShortest Path exported to .csv\r\nShortest Path Frame started\r\n", captor.toString());

        ShortestPathFrame.main(null);
        assertTrue(true);
    }
}
