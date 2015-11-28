
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import romancalculator.NumeralConverter;

/**
 *
 * @author DumitruDaniel
 */
public class NumeralConverterJUnitTest {
    ByteArrayOutputStream myOut=null;
    
    @Test
    public void ConvertRomanToIntJUnitTest() {
        String[] romanList = {"I","II","X","LIV","XCV","CM","MDCCC"};
        int[] intList = {1,2,10,54,95,900,1800};
        for(int i=0;i<7;i++){
            int actual = NumeralConverter.convertRomanToInt(romanList[i]);
            int expected = intList[i];
            assertEquals(expected,actual); 
        }
    }
    
    @Test
    public void ConvertIntToRomanJUnitTest() {
        String[] romanList = {"I","II","X","LIV","XCV","CM","MDCCC"};
        int[] intList = {1,2,10,54,95,900,1800};
        for(int i=0;i<7;i++){
            String actual = NumeralConverter.convertIntToRoman(intList[i]);
            String expected = romanList[i];
            assertEquals(expected,actual); 
        }
    }
    
    @Test
    public void ConvertRomanToIntInvalidRomanNumeralJUnitTest() {
        String[] romanList = {"XIIV","IXV","XVVI","ASD","CMIIX"};
        String standardOutput;
        for (int i = 0; i < 5; i++) {         
            int actual = NumeralConverter.convertRomanToInt(romanList[i]);
            standardOutput = myOut.toString();
            assertEquals(romanList[i]+":Invalid roman numeral.\r\n",standardOutput); 
            assertEquals(0,actual); 
            myOut.reset();
        }
    }
    
    
    @Before
    public void setUp() {
        myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
    }
}
