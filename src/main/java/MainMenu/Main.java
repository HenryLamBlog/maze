package MainMenu;

//import javax.swing.JFrame; //import the GUI file
import FunctionC.mazeGame;
import MazeGenerator.MazeFrame;
import ShortestPath.ShortestPathFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{
    public static JButton test1 = new JButton("Open Maze");
    public static JButton test2 = new JButton("Shortest Path");
    public static JButton test3 = new JButton("Play Maze Game");
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
    protected static void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Maze game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the components with vertical layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add greeting text labels
        // JLabel label1 = new JLabel("Hello! Here is Group52 !");
        JLabel label2 = new JLabel("Welcome to our Tom and Jerry Maze game!");
        JLabel label3 = new JLabel("Choose an option below:");

        // Customize the labels
        // label1.setFont(new Font("Arial", Font.BOLD, 30));
        // label2.setFont(new Font("Lato:300", Font.ROMAN_BASELINE, 24));
        // label3.setFont(new Font("Lato:300", Font.PLAIN, 24));
        Font latoFont = new Font("Lato", Font.PLAIN, 24);
        label2.setFont(latoFont);
        label3.setFont(latoFont);



        // Center align the labels
        // label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label3.setAlignmentX(Component.CENTER_ALIGNMENT);

        // panel.add(label1);
        panel.add(Box.createVerticalStrut(10)); // Add some vertical spacing
        panel.add(label2);
        panel.add(Box.createVerticalStrut(10)); // Add some vertical spacing
        panel.add(label3);

        // Add buttons
        JButton button1 = new JButton("Open Maze");
        JButton button2 = new JButton("Shortest Path");
        JButton button3 = new JButton("Play Maze Game");

        // Customize the buttons
        Font buttonFont = new Font("Lato", Font.PLAIN, 16);
        button1.setFont(buttonFont);
        button2.setFont(buttonFont);
        button3.setFont(buttonFont);

        button1.setBackground(Color.LIGHT_GRAY);
        button2.setBackground(Color.LIGHT_GRAY);
        button3.setBackground(Color.LIGHT_GRAY);

        button1.setForeground(Color.DARK_GRAY);
        button2.setForeground(Color.DARK_GRAY);
        button3.setForeground(Color.DARK_GRAY);

        // Center align the buttons
        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        button3.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(30)); // Add some vertical spacing
        panel.add(button1);
        panel.add(Box.createVerticalStrut(20)); // Add some vertical spacing
        panel.add(button2);
        panel.add(Box.createVerticalStrut(20)); // Add some vertical spacing
        panel.add(button3);

        // Create a label for the image
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("src/main/java/MainMenu/images.jpeg"); // Provide the image file path
        imageLabel.setIcon(imageIcon);

        imagePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));


        // Add the image label to the image panel
        imagePanel.add(imageLabel);

        // Add the image panel to the frame's NORTH position
        frame.add(imagePanel, BorderLayout.NORTH);

        // Event listener for button1
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to open a new window and run MazeMain class
                // Create a new instance of MazeFrame
                MazeFrame mazeFrame = new MazeFrame(false);
                mazeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                mazeFrame.setVisible(true);
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to open a new window and run MazeMain class
                JFrame FunctionB = new JFrame("FunctionB");
                FunctionB.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                FunctionB.getContentPane().setBackground(Color.WHITE);

                SwingUtilities.invokeLater(() -> {
                    ShortestPathFrame shortestPath = new ShortestPathFrame("src/main/maze.csv", "src/main/shortestPathRes.csv");
                    shortestPath.setVisible(true);
                });
            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to open a new window and run MazeMain class
                JFrame FunctionC = new JFrame("FunctionC");
                FunctionC.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                FunctionC.getContentPane().setBackground(Color.WHITE);
//                mazeGame.main(new String[0]);
                SwingUtilities.invokeLater(() -> {
                    mazeGame game = new mazeGame(new int[]{0, 0}, new int[]{29, 29}, new int[]{29, 29});
                    game.setVisible(true);
                });


            }
        });

        test1 = button1;
        test2 = button2;
        test3 = button3;

        // Center align the panel within the frame
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);

        // Add the panel to the frame
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getContentPane().add(panel);

        // Display the frame
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }
}