package MazeGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MazeNoPath extends JFrame {
    public static JButton exitTest;
    public MazeNoPath() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("No Path Found");

        // Create a label with the text "No path is found!"
        JLabel label = new JLabel("No path is found!");
        label.setFont(new Font("Lato", Font.BOLD,  24));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        // Create an exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the frame when the button is clicked
            }
        });

        exitTest = exitButton;

        // Create a panel to hold the label and button
        JPanel panel = new JPanel(new BorderLayout());
        //panel.add(new JLabel(" "));
        panel.add(label, BorderLayout.CENTER);
        //panel.add(new JLabel(" "));
        panel.add(exitButton, BorderLayout.SOUTH);

        // Add the panel to the frame's content pane
        getContentPane().add(panel);

        getContentPane().setPreferredSize(new Dimension(250, 100));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void test(){

    }

    public static void main(String[] args) {
        boolean condition = true; // Replace with your condition

        if (condition) {
            SwingUtilities.invokeLater(MazeNoPath::new);
        }
    }
}