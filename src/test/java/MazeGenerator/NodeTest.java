package MazeGenerator;

import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {
    @Test
    public void TestNode(){ //test the node input correct or not
        int x = 2;
        int y = 3;
        Node node = new Node(x, y);

        assertEquals(x, node.x);
        assertEquals(y, node.y);
    }
}

