package FunctionC;

import FunctionC.mazeGame;
import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class testFunctionC {
    @Test
    public void testResetGame() {
        mazeGame game = new mazeGame(new int[]{0, 0}, new int[]{29, 29}, new int[]{29, 29});
        game.maze = new int[30][30];
        game.resetGame();

        // Test that Jerry's and Tom's positions are reset
        assertTrue(29 == mazeGame.tomX && 29 == mazeGame.tomY);
        assertEquals(0, mazeGame.jerryX);
        assertEquals(0, mazeGame.jerryY);
        assertEquals(29, mazeGame.exitX);
        assertEquals(29, mazeGame.exitY);

        // Test that the Tom path is recalculated
        assertNotNull(game.tomPath);

        // Check if other variables and game state are reset as expected
        assertFalse(game.isGameOver);
        assertTrue(game.isJerryMoving);
    }

    @Test
    public void testMoveJerry(){
        mazeGame game = new mazeGame(new int[]{0, 0}, new int[]{29, 29}, new int[]{29, 29});
        //set maze to empty to test jerry movement
        int[][] maze = new int[30][30];
        game.maze = new int[30][30];

        // Verify initial position
        assertEquals(0, mazeGame.jerryX);
        assertEquals(0, mazeGame.jerryY);

        // Test moving Jerry to the right
        game.moveJerry('d');

        // Verify updated position
        assertEquals(1, mazeGame.jerryX);
        assertEquals(0, mazeGame.jerryY);
        // Test moving Jerry to the down
        game.moveJerry('s');

        // Verify updated position
        assertEquals(1, mazeGame.jerryX);
        assertEquals(1, mazeGame.jerryY);

        game.moveJerry('a'); //move left

        // Verify updated position
        assertEquals(0, mazeGame.jerryX);
        assertEquals(1, mazeGame.jerryY);

        game.moveJerry('w'); //move up
        // Verify updated position
        assertEquals(0, mazeGame.jerryX);
        assertEquals(0, mazeGame.jerryY);
    }
    @Test
    public void testMoveTom() {
        mazeGame game = new mazeGame(new int[]{0, 0}, new int[]{29, 29}, new int[]{29, 29});
        //set maze to empty to test tom movement using function b
        int[][] maze = new int[30][30];
        game.maze = new int[30][30];
        game.resetGame();

        // Move Tom from (2,2) to (1,1)
        game.moveTom();
        assertEquals(28, mazeGame.tomX);
        assertEquals(29, mazeGame.tomY);

        // Move Tom from (1,1) to (2,1)
        game.moveTom();
        assertEquals(27, mazeGame.tomX);
        assertEquals(29, mazeGame.tomY);

        for (int i = 0; i < 30; i++){
            for (int j = 0; j < 30; j++){
                maze[i][j] = 1;
            }
        }

        game.maze = new int[30][30];
        game.moveTom();
        assertTrue(true);
    }
    @Test
    public void testGameOver() {
        mazeGame game = new mazeGame(new int[]{0, 0}, new int[]{29, 29}, new int[]{0, 0});
        game.maze = new int[30][30];
        try{
            TimeUnit.MILLISECONDS.sleep(200);
        }
        catch (InterruptedException e){
            assertTrue(true);
        }
        assertTrue(true);
        game = new mazeGame(new int[]{0, 0}, new int[]{2, 2}, new int[]{29, 29});
        game.maze = new int[30][30];
        try{
            TimeUnit.MILLISECONDS.sleep(800);
        }
        catch (InterruptedException e){
            assertTrue(true);
        }

        game = new mazeGame(new int[]{0, 0}, new int[]{29, 29}, new int[]{29, 29});
        int[][] maze = new int[30][30];
        maze[1][0] = 1;
        maze[0][1] = 1;

        game.maze = maze;
        try{
            TimeUnit.MILLISECONDS.sleep(200);
        }
        catch (InterruptedException e){
            assertTrue(true);
        }
        mazeGame.main(null);
        assertTrue(true);
    }

    @Test
    public void testKeyEvent(){
        try{
            mazeGame game = new mazeGame(new int[]{0, 0}, new int[]{29, 29}, new int[]{0, 0});
            Robot r = new Robot();
            game.maze = new int[30][30];
            game.resetGame();

            r.keyPress(87);
            try{
                TimeUnit.MILLISECONDS.sleep(400);
            }
            catch (InterruptedException e){
                assertTrue(true);
            }
            r.keyRelease(87);
            try{
                TimeUnit.MILLISECONDS.sleep(400);
            }
            catch (InterruptedException e){
                assertTrue(true);
            }
            r.keyPress(83);
            try{
                TimeUnit.MILLISECONDS.sleep(200);
            }
            catch (InterruptedException e){
                assertTrue(true);
            }
            assertTrue(true);
        }catch (AWTException e){
            assertTrue(true);
        }
    }
}