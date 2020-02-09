package filters.test;

import filters.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the parser.
 */
public class TestParser {
    @Test
    public void testBasic() throws SyntaxError {
        Filter f = new Parser("trump").parse();
        assertTrue(f instanceof BasicFilter);
        assertTrue(((BasicFilter)f).getWord().equals("trump"));
    }

    @Test
    public void testHairy() throws SyntaxError {
        Filter x = new Parser("trump and (evil or blue) and red or green and not not purple").parse();
        assertTrue(x.toString().equals("(((trump and (evil or blue)) and red) or (green and not not purple))"));
    }

    @Test
    public void testAndOr() throws SyntaxError {
        Filter f = new Parser("rice and beans or chicken and waffles").parse();
        assertTrue(f.toString().equals("((rice and beans) or (chicken and waffles))"));
    }

    @Test
    public void testMultipleOr() throws SyntaxError {
        Filter f = new Parser("(cars or trucks) and (cats or dogs)").parse();
        assertTrue(f.toString().equals("((cars or trucks) and (cats or dogs))"));
    }

    @Test
    public void testMultipleAnd() throws SyntaxError {
        Filter f = new Parser("(Birds and Bees) and (snakes and Lizards)").parse();
        assertTrue(f.toString().equals("((Birds and Bees) and (snakes and Lizards))"));
    }
}
