package MazeGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MazeFrame extends JFrame {
    public static JButton newTest;
    public static JButton randomTest;
    public static JButton saveTest;
    public static JButton findTest;
    public static JButton testBlock;
    protected JButton[][] buttons;
    public int[][] maze;
    protected static final int SIZE = 30;
    protected static final int WALL = 1;
    protected static final int PATH = 0;
    protected static final int VISITED = 2;
    public MazeFrame(boolean test) {

        MazeGenerator mazeGenerator = new MazeGenerator(30);
        int NoPathBugCount = 0;
        int b = 0;
        while(b == 0) {
            mazeGenerator.generateMaze(test);
            //mazeGenerator.writecsv();
            maze = mazeGenerator.getMaze();
            boolean pathExists = findPath(maze, 0, 0);
            if (pathExists) {
                b = 1;
                maze[SIZE - 1][SIZE - 1] = VISITED;
            } else {
                System.out.println("Open game No Gen path found!");
                NoPathBugCount++;
                if(NoPathBugCount>=2){
                    //******************************************************************************************************
                    int a = 0;
                    while (a == 0) {
                        for (int x = 0; x < 30; x++) {
                            for (int y = 0; y < 30; y++) {
                                maze[x][y] = PATH;
                                //buttons[x][y].setBackground(Color.WHITE);
                            }
                        }
                        // Code to open a new window and run MazeMain class
                        // Create a new instance of MazeFrame
                        mazeGenerator.generateMaze(false);
                        maze = mazeGenerator.getMaze();
                        boolean pathExists2 = findPath(maze, 0, 0);
                        if (pathExists2) {
                            a = 1;
                            maze[SIZE - 1][SIZE - 1] = VISITED;
                        }
                    }
                    TwoToZero();
                    //******************************************************************************************************
                    break;
                }

            }
        }
        TwoToZero();

        // Set the layout manager for the mazeFrame
        setLayout(new BorderLayout());
        // Create the maze grid component
        JPanel mazeMain = new JPanel();
        mazeMain.setSize(1200, 900);
        mazeMain.setLayout(new GridLayout(30, 30)); // 30x30 grid layout
        mazeMain.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2)); // Add dark border
        String temp;

        buttons = new JButton[30][30];
        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 30; y++) {
                buttons[x][y] = new JButton();
                buttons[x][y].setPreferredSize(new Dimension(10, 10)); // Set square size
                buttons[x][y].addActionListener(new ButtonListener(x, y));
                temp = Integer.toString(maze[x][y]);
                if (temp.equals("1") == true) {
                    buttons[x][y].setBackground(Color.black);
                }
                if (temp.equals("0") == true) {
                    buttons[x][y].setBackground(Color.white);
                }
                mazeMain.add(buttons[x][y]);
            }
        }

        // Create the buttons for "New", "Edit", and "Save"
        JButton newButton = new JButton("New");
        JButton RandomButton = new JButton("Random");
        JButton saveButton = new JButton("Save");
        JButton findButton = new JButton("Find Path");

        // Create the buttonPanel and add the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
        buttonPanel.add(newButton);
        buttonPanel.add(RandomButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(findButton);

        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int x = 0; x < 30; x++) {
                    for (int y = 0; y < 30; y++) {
                        maze[x][y] = 0;
                        buttons[x][y].setBackground(Color.WHITE);
                    }
                }
                MazeGenerator.savecsv(maze, "src/main/maze.csv");

            }
        });
        RandomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Random button clicked");
                int a = 0;

                while (a == 0) {
                    for (int x = 0; x < 30; x++) {
                        for (int y = 0; y < 30; y++) {
                            maze[x][y] = PATH;
                            buttons[x][y].setBackground(Color.WHITE);
                        }
                    }
                    // Code to open a new window and run MazeMain class
                    // Create a new instance of MazeFrame
                    mazeGenerator.generateMaze(false);
                    maze = mazeGenerator.getMaze();
                    String temp2;
                    for (int x = 0; x < 30; x++) {
                        for (int y = 0; y < 30; y++) {

                            temp2 = Integer.toString(maze[x][y]);
                            if (temp2.equals("0") == true) {
                                buttons[x][y].setBackground(Color.white);
                            }
                            if (temp2.equals("1") == true) {
                                buttons[x][y].setBackground(Color.black);
                            }
                        }
                    }
                    boolean pathExists = findPath(maze, 0, 0);
                    if (pathExists) {
                        a = 1;
                        maze[SIZE - 1][SIZE - 1] = VISITED;
                    }
                }
                TwoToZero();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TwoToZero();
                MazeGenerator.savecsv(maze, "src/main/maze.csv");
            }
        });

        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TwoToZero();
                MazeGenerator.savecsv(maze, "src/main/maze.csv");
                TwoToZero();
                String temp2;
                for (int x = 0; x < 30; x++) {
                    for (int y = 0; y < 30; y++) {

                        temp2 = Integer.toString(maze[x][y]);
                        if (temp2.equals("0") == true) {
                            buttons[x][y].setBackground(Color.WHITE);
                        }
                    }
                }
                boolean pathExists = findPath(maze, 0, 0);

                if (pathExists) {
                    maze[SIZE - 1][SIZE - 1] = VISITED;
                } else {
                    System.out.println("No path found!");
                    MazeNoPath mazeNoPath = new MazeNoPath();
                    mazeNoPath.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    mazeNoPath.setVisible(true);
                }
                String temp3;
                for (int x = 0; x < 30; x++) {
                    for (int y = 0; y < 30; y++) {

                        temp3 = Integer.toString(maze[x][y]);
                        if (temp3.equals("2") == true) {
                            buttons[x][y].setBackground(Color.GREEN);
                        }
                    }
                }
                TwoToZero();
            }
        });

        newTest = newButton;
        randomTest = RandomButton;
        saveTest = saveButton;
        findTest = findButton;
        testBlock = buttons[0][0];

        JLabel label1 = new JLabel("Click once to select, another " +
                "\r\n" + "time to change the grid");
        label1.setFont(new Font("Arial", Font.PLAIN, 12));
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(10)); // Add some vertical spacing
        panel.add(label1);

        JLabel entrylabel = new JLabel("  Entry->");
        JLabel exitlabel = new JLabel("<- Exit  ");
        JLabel emptylabel = new JLabel("  ");
        //JLabel label2 = new JLabel("  ");


        JPanel entrypanel = new JPanel();
        entrypanel.setLayout(new GridLayout(32, 1));
        /*for(int i = 0; i < 0 ; i++) {
            entrypanel.add(new JLabel("  "));
        };*/
        entrypanel.add(entrylabel);

        JPanel exitpanel = new JPanel();
        exitpanel.setLayout(new GridLayout(32, 1));
        for(int i = 0; i < 31 ; i++) {
            exitpanel.add(new JLabel("  "));
        }
        exitpanel.add(exitlabel);

        getContentPane().setPreferredSize(new Dimension(600, 600));

        getContentPane().add(entrypanel, BorderLayout.WEST);

        getContentPane().add(exitpanel, BorderLayout.EAST);

        getContentPane().add(panel, BorderLayout.NORTH);

        // Add the mazeMain panel to the center
        getContentPane().add(mazeMain, BorderLayout.CENTER);

        // Add the buttonPanel to the south
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Set JFrame properties
        setTitle("Maze Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }

    protected class ButtonListener implements ActionListener {
        protected int row;
        protected int col;

        public ButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (maze[row][col] == PATH) {
                maze[row][col] = WALL;
                buttons[row][col].setBackground(Color.BLACK);
                savepath();
            } else {
                maze[row][col] = PATH;
                buttons[row][col].setBackground(Color.WHITE);
                savepath();
            }
        }

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MazeFrame(false);
        });
    }
    public void savepath(){
        MazeGenerator.savecsv(maze, "src/main/maze.csv");
    }

    // Find a path from (1,1) to (30,30) using depth-first search (DFS)
    protected static boolean findPath(int[][] maze, int row, int col) {
        if (row < 0 || col < 0 || row >= SIZE || col >= SIZE) {
            return false; // Out of bounds
        }

        if (maze[row][col] != PATH) {
            return false; // Already visited or is a wall
        }

        if (row == SIZE - 1 && col == SIZE - 1) {
            return true; // Reached the destination
        }

        // Mark current cell as visited
        maze[row][col] = VISITED;

        // Recursively explore neighboring cells
        if (findPath(maze, row + 1, col) ||
                findPath(maze, row - 1, col) ||
                findPath(maze, row, col + 1) ||
                findPath(maze, row, col - 1)) {
            return true;
        }

        // No path found, mark current cell as unvisited
        maze[row][col] = PATH;

        return false;
    }
    public void TwoToZero(){
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (maze[i][j] == 2) {
                    maze[i][j] = PATH;
                    //buttons[i][j].setBackground(Color.GREEN); // Change color to indicate the path
                }
            }
        }
    }

}