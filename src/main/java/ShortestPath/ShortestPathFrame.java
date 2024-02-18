package ShortestPath;

import FunctionC.mazeGame;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ShortestPathFrame extends JFrame {
    protected int[][] maze;
    protected int dim;
    protected static int x1;
    protected static int y1;
    protected static int x2;
    protected static int y2;
    protected ArrayList<int[]> sol;
    protected ShortestPathPanel panel;

    public ShortestPathFrame(String mazeFile, String resFile){
        this.dim = 30;
        ShortestPathFrame.x1 = 0;
        ShortestPathFrame.y1 = 0;
        ShortestPathFrame.x2 = 29;
        ShortestPathFrame.y2 = 29;
//        this.readMazeFromFile("src/main/java/ShortestPath/MazeMap_SPT.csv");
        setTitle("Shortest Path");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        this.maze = new int[dim][dim];
        try (BufferedReader reader = new BufferedReader(new FileReader(mazeFile))) {
            String line;
            int rowIndex = 0;
            while ((line = reader.readLine()) != null) {
                String[] cells = line.split(",");
                for (int colIndex = 0; colIndex < dim; colIndex++) {
                    maze[rowIndex][colIndex] = Integer.parseInt(cells[colIndex].trim());
                }
                rowIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
        this.sol = BFS.findSolution(maze, dim, x1, y1, x2, y2);
        BFS.outputSol(this.sol, resFile);

        this.panel = new ShortestPathPanel(maze, dim, this.sol);
        panel.setPreferredSize(new Dimension(dim*20, dim*20));
        add(panel);
        panel.setFocusable(true);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);

        Thread sp = new Thread(() -> {
            int option = JOptionPane.showOptionDialog(null,
                    "Shortest Path Results:\n"+BFS.stringSol(this.sol)
                            +"\n Length: "+this.sol.size(),
                    "Result",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[]{"OK"},
                    "OK"
            );
        });
        System.out.println("Shortest Path Frame started");
        sp.start();
    }

    protected static class ShortestPathPanel extends JPanel{
        protected int[][] maze;
        protected int dim;
        protected ArrayList<int[]> sol;

        public ShortestPathPanel(int[][] maze, int dim, ArrayList<int[]> sol){
            this.maze = maze;
            this.dim = dim;
            this.sol = sol;
        }

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            int cellSize = 20;

            // Draw the maze cells
            for (int row = 0; row < dim; row++) {
                for (int col = 0; col < dim; col++) {
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

            for (int[] coord : sol) {
                g.setColor(Color.yellow);
                g.fillRect(coord[0] * cellSize, coord[1] * cellSize, cellSize, cellSize);
            }
        }
    }
    public static void main(String[] args){SwingUtilities.invokeLater(() -> {new ShortestPathFrame("src/main/maze.csv", "src/main/shortestPathRes.csv");});}
}