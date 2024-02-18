package ShortestPath;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GraphNodeTest {
    @Test
    public void graphNodeTest(){
        GraphNode node = new GraphNode(1, 2); // target function

        int[] expected = new int[]{1, 2};

        assertEquals(expected[0], node.x);
        assertEquals(expected[1], node.y);
        assertNull(node.prev);

    }
}
