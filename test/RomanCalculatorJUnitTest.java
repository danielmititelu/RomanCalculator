import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import romancalculator.RomanCalculator;

/**
 *
 * @author DumitruDaniel
 */
public class RomanCalculatorJUnitTest {
    ByteArrayOutputStream myOut=null;
    
    @Test
    public void CalculateJUnitTest() {
       String actual = RomanCalculator.calculate("X+V-II");
       String expected = "XIII";
       assertEquals(expected,actual); 
    }
    
    @Test
    public void NegativeNumberCalculationJUnitTest(){
        String actual = RomanCalculator.calculate("X-XV");
        String standardOutput = myOut.toString();
        assertEquals("Negative values cannot be represented in roman numerals\r\n",standardOutput); 
        assertEquals("(X-XV)",actual); 
    }
    
    @Before
    public void setUp() {
        myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
    }
}
