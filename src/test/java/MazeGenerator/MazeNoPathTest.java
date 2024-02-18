package MazeGenerator;

import MazeGenerator.MazeNoPath;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MazeNoPathTest {

    public MazeNoPath frame;

    @Before
    public void setUp() {
        frame = new MazeNoPath();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    @After
    public void tearDown() {
        frame.dispose();
    }

    @Test
    public void testExitButton() { //test the frame will pop up correctly
        MazeNoPath.test();
        MazeNoPath.exitTest.doClick();
        MazeNoPath.main(null);
        assertFalse(frame.isVisible());
    }
}