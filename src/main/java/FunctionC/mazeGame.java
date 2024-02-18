package FunctionC;
import ShortestPath.BFS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class mazeGame extends JFrame {
    public int[][] maze;
    public int dimension;
    public static int jerryX;
    public static int jerryY;
    public static int tomX;
    public static int tomY;
    public static int exitX;
    public static int exitY;
    public boolean isJerryMoving;
    public MazePanel mazePanel;
    public boolean isGameOver; // Flag to track if the game is over or the player returned to the main menu
    public ArrayList<int[]> tomPath;

//    public void setMaze(int[][] maze) {
//        this.maze = maze;
//    }
//    public static int getJerryX() {
//        return jerryX;
//    }
//
//    public static int getJerryY() {
//        return jerryY;
//    }
//
//    public static int getTomX() {
//        return tomX;
//    }
//
//    public static int getTomY() {
//        return tomY;
//    }
//
//    public static int getExitX() {
//        return exitX;
//    }
//
//    public static int getExitY() {
//        return exitY;
//    }
//
//    public boolean getIsJerryMoving() {
//        return isJerryMoving;
//    }
//
//    public boolean getIsGameOver() {
//        return isGameOver;
//    }
//
//    public ArrayList<int[]> getTomPath() {
//        return tomPath;
//    }

    protected static class MazePanel extends JPanel {
        protected int[][] maze;
        protected int dimension;

        protected ArrayList<int[]> tomPath;

        public MazePanel(int[][] maze, int dimension, ArrayList<int[]> tomPath) {
            this.maze = maze;
            this.dimension = dimension;
            this.tomPath = tomPath;
        }


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int cellSize = 20;  // Adjust the cell size as needed

            // Draw the maze cells
            for (int row = 0; row < dimension; row++) {
                for (int col = 0; col < dimension; col++) {
                    int x = col * cellSize;
                    int y = row * cellSize;
                    if (maze[row][col] == 0) {
                        g.setColor(Color.WHITE);
                    } else {
                        g.setColor(Color.BLACK);
                    }
                    g.fillRect(x, y, cellSize, cellSize);
                }
            }

            // Draw Jerry at the new position
            g.setColor(Color.ORANGE);
            g.fillRect(jerryX * cellSize, jerryY * cellSize, cellSize, cellSize);

            // Draw the exit point
            g.setColor(Color.GREEN);
            g.fillRect(exitX * cellSize, exitY * cellSize, cellSize, cellSize);
            // Draw the tom at exit point
            g.setColor(Color.gray);
            g.fillRect(tomX * cellSize, tomY * cellSize, cellSize, cellSize);
        }
    }

    public mazeGame(int[] jerry, int[] tom, int[] exit) {
        // Read the maze from the CSV file
//        readMazeFromFile("src/main/TestOnly/Sample_MazeMap_SPT.csv");
        readMazeFromFile("src/main/maze.csv");

        // Set initial positions
        jerryX = jerry[0]; // Entry point x-coordinate
        jerryY = jerry[1]; // Entry point y-coordinate
        tomX = tom[0];
        tomY= tom[0];
        exitX = exit[0]; // Exit point x-coordinate
        exitY = exit[1]; // Exit point y-coordinate

        isGameOver = false; // Initialize the game over flag

        tomPath = BFS.findSolution(maze,30, tomX, tomY, jerryX, jerryY);
        // Create the game window
        setTitle("Maze Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        mazePanel = new MazePanel(maze, dimension,tomPath);
        mazePanel.setPreferredSize(new Dimension(dimension * 20, dimension * 20));  // Adjust the size as needed

        add(mazePanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Set up key listener for Jerry's movement
        mazePanel.setFocusable(true);
        mazePanel.requestFocus();
        mazePanel.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!isGameOver) { // Ignore key events if the game is over
                    moveJerry(e.getKeyChar());
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        // Start the game loop
        isJerryMoving = true;

        Thread gameLoopThread = new Thread(() -> {
            final boolean[] gameRunning = {true};
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    gameRunning[0] = false; // Stop the game loop
                    dispose(); // Close the game window
                }
            });


            while (gameRunning[0]) {
                mazePanel.repaint();

                // Check if Jerry reached the exit
                if (jerryX == exitX && jerryY == exitY) {
                    JOptionPane.showMessageDialog(null, "Congratulations! Jerry escaped the maze. You win!");
                    // Prompt to play again or return to main menu
                    int option = JOptionPane.showOptionDialog(null, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Play Again", "Main Menu", "Exit"}, "Play Again");
                    if (option == JOptionPane.YES_OPTION) {
                        // Reset game and play again
                        resetGame();
                        isGameOver = false; // Reset the game over flag
                    } else if (option == JOptionPane.NO_OPTION) {
                        // Return to main menu
                        isGameOver = true; // Reset the game over flag
                        mazePanel.removeKeyListener(mazePanel.getKeyListeners()[0]);
                        gameRunning[0] = false;
                        dispose(); // Close the game frame
                    } else {
                        // Exit the game
                        System.exit(0);
                    }

                }
                // Check if Jerry collided with Tom
                if (jerryX == tomX && jerryY == tomY) {
                    JOptionPane.showMessageDialog(null, "Tom caught Jerry. You lost.");
                    // Prompt to play again or return to main menu
                    int option = JOptionPane.showOptionDialog(null, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Play Again", "Main Menu", "Exit"}, "Play Again");
                    if (option == JOptionPane.YES_OPTION) {
                        // Reset game and play again
                        resetGame();
                        isGameOver = false; // Reset the game over flag
                    } else if (option == JOptionPane.NO_OPTION) {
                        // Return to main menu
                        isGameOver = true; // Reset the game over flag
                        mazePanel.removeKeyListener(mazePanel.getKeyListeners()[0]);
                        gameRunning[0] = false;
                        dispose(); // Close the game frame
                    } else {
                        // Exit the game
                        isGameOver = true; // game over flag
                        tomPath=null;
                        System.exit(0);
                    }
                }

                // Update Jerry's position if moving
                if (isJerryMoving) {
                    moveTom();
                }

                // Delay between frames
                try {
                    Thread.sleep(200); // Adjust the delay as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameLoopThread.start();
    }
    public void resetGame() {
        // Reset Tom's position
        tomX = 29;
        tomY = 29;

        // Reset Jerry's position
        jerryX = 0;
        jerryY = 0;


        exitX=29;
        exitY=29;

        // Recalculate the shortest path for Tom
        tomPath = BFS.findSolution(maze,30, tomX, tomY, jerryX, jerryY);

        // Reset other variables and game state as needed
        isGameOver = false;
        isJerryMoving = true;

        // Repaint the maze panel
        mazePanel.repaint();
    }

    public void readMazeFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int rowIndex = 0;
            while ((line = reader.readLine()) != null) {
                String[] cells = line.split(",");
                if (maze == null) {
                    dimension = cells.length;
                    maze = new int[dimension][dimension];
                }
                for (int colIndex = 0; colIndex < dimension; colIndex++) {
                    maze[rowIndex][colIndex] = Integer.parseInt(cells[colIndex].trim());
                }
                rowIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveJerry(char move) {

        // Calculate the potential new position
        int newJerryX = jerryX;
        int newJerryY = jerryY;

        // Update Jerry's movement direction
        switch (move) {
            case 'w':
            case 'W':
                newJerryY--;
                break;
            case 's':
            case 'S':
                newJerryY++;
                break;
            case 'a':
            case 'A':
                newJerryX--;
                break;
            case 'd':
            case 'D':
                newJerryX++;
                break;
            default:
                return;
        }

        // Check if the new position is valid
        if (newJerryX >= 0 && newJerryX < dimension && newJerryY >= 0 && newJerryY < dimension && maze[newJerryY][newJerryX] == 0) {
            // Update Jerry's position
            jerryX = newJerryX;
            jerryY = newJerryY;
        }


        mazePanel.repaint();
    }


    public void moveTom() {
//        updateTomPath();
        if(!isGameOver) {
            tomPath = BFS.findSolution(maze,30, tomX, tomY, jerryX, jerryY);
            if (tomPath.isEmpty()) {
                System.out.println("No path available");
                isGameOver = true; // Reset the game over flag
                mazePanel.removeKeyListener(mazePanel.getKeyListeners()[0]);
                return; // No path available
            }
            tomPath.remove(0);
        }
        if (tomPath.isEmpty()) {
            return; // No path available
        }

        // Get the next position from the path
        int[] nextTomPosition = tomPath.remove(0);
        int nextTomX=nextTomPosition[0];
        int nextTomY=nextTomPosition[1];

        // Update Tom's position
        tomX = nextTomX;
        tomY = nextTomY;

        // Check if Tom reaches Jerry
        if (jerryX == tomX && jerryY == tomY) {
            isGameOver = true;
            return;
        }

        mazePanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { new mazeGame(new int[]{0,0}, new int[]{29, 29}, new int[]{29, 29});});
    }
}