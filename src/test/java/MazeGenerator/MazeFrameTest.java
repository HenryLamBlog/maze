package MazeGenerator;

import org.junit.Test;

import static org.junit.Assert.*;


public class MazeFrameTest {
    @Test
    public void TestTwoToZeroFunction(){ //test the path to empty (2 to 0) function is work or not
        MazeFrame mazeFrame = new MazeFrame(false);
        int[][] maze = new int[30][30];
        maze[10][10] = 2;
        maze[12][25] = 2;
        mazeFrame.maze = maze;

        mazeFrame.TwoToZero();

        assertEquals(mazeFrame.maze[10][10], 0);
        assertEquals(mazeFrame.maze[12][25], 0); //should be changed to 0


    }

    @Test
    public void TestMazeFrame(){ //test the maze will be auto generate or not
        MazeFrame mazeFrameTest = new MazeFrame(false);
        assertNotNull(mazeFrameTest.maze);
    }

    @Test
    public void TestButtons(){
        MazeFrame mazeFrame = new MazeFrame(true);
        MazeFrame.newTest.doClick();
        MazeFrame.randomTest.doClick();
        MazeFrame.saveTest.doClick();
        MazeFrame.findTest.doClick();
        MazeFrame.testBlock.doClick();
        MazeFrame.findTest.doClick();
        MazeFrame.main(null);
        MazeFrame.testBlock.doClick();
        assertTrue(true);
    }
}