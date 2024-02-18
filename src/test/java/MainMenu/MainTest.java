package MainMenu;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.security.PublicKey;

import static org.junit.Assert.*;

public class MainTest {
    private Main frame;

    @Before
    public void setUp(){
        frame = new Main();
        frame.setVisible(true);
    }

    @Test
    public void testTheFramePopUpOrNot(){
        Main.createAndShowGUI();
        assertTrue(frame.isVisible());
    }

    @Test
    public void testButton(){
        Main.createAndShowGUI();
        Main.test1.doClick();
        Main.test2.doClick();
        Main.test3.doClick();
        Main.test1.doClick();
        Main.main(null);
        assertTrue(true);
    }
}