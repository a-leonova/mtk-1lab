import com.nsu.fit.leonova.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTester {
    @Test
    public void checkNumber() throws IOException, BadLexemeException, BadExpressionException {
        StringReader reader = new StringReader("123456");
        Lexer lexer = new Lexer(reader);

        Parser parser = new Parser(lexer);
        int res = parser.calculate();
        assertEquals(123456, res);
    }
    @Test
    public void checkPlus() throws IOException, BadLexemeException, BadExpressionException {
        StringReader reader = new StringReader("101 + 404");
        Lexer lexer = new Lexer(reader);

        Parser parser = new Parser(lexer);
        int res = parser.calculate();
        assertEquals(505, res);
    }

    @Test
    public void checkMinus() throws IOException, BadLexemeException, BadExpressionException {
        StringReader reader = new StringReader("505 - 404");
        Lexer lexer = new Lexer(reader);

        Parser parser = new Parser(lexer);
        int res = parser.calculate();
        assertEquals(101, res);
    }


    @Test
    public void checkDifficultExpr() throws IOException, BadLexemeException, BadExpressionException {
        StringReader reader = new StringReader("100 + 200 + 5 + 10 - 400");
        Lexer lexer = new Lexer(reader);

        Parser parser = new Parser(lexer);
        int res = parser.calculate();
        assertEquals(-85, res);
    }

    @Test
    public void checkMult() throws IOException, BadLexemeException, BadExpressionException {
        StringReader reader = new StringReader("5 * 6");
        Lexer lexer = new Lexer(reader);

        Parser parser = new Parser(lexer);
        int res = parser.calculate();
        assertEquals(30, res);
    }


    @Test
    public void checkDiv() throws IOException, BadLexemeException, BadExpressionException {
        StringReader reader = new StringReader("100 / 5");
        Lexer lexer = new Lexer(reader);

        Parser parser = new Parser(lexer);
        int res = parser.calculate();
        assertEquals(20, res);
    }

    @Test
    public void checkDifficultExprWithPlusMinusMultDiv() throws IOException, BadLexemeException, BadExpressionException {
        StringReader reader = new StringReader("100 / 10 - 25 * 36 / 6 + 2 * 57 - 12");
        Lexer lexer = new Lexer(reader);

        Parser parser = new Parser(lexer);
        int res = parser.calculate();
        assertEquals(100 / 10 - 25 * 36 / 6 + 2 * 57 - 12, res);
    }

    @Test
    public void checkPow() throws IOException, BadLexemeException, BadExpressionException {
        StringReader reader = new StringReader("2^10");
        Lexer lexer = new Lexer(reader);

        Parser parser = new Parser(lexer);
        int res = parser.calculate();
        assertEquals(1024, res);
    }

    @Test
    public void checkFewPow() throws IOException, BadLexemeException, BadExpressionException {
        StringReader reader = new StringReader("2^10^2");
        Lexer lexer = new Lexer(reader);

        Parser parser = new Parser(lexer);
        int res = parser.calculate();
        assertEquals(1048576, res);
    }

    @Test
    public void checkDifficultExprWithPlusMinusMultDivPow() throws IOException, BadLexemeException, BadExpressionException {
        StringReader reader = new StringReader("2 * 10^4 - 5^2^3 + 1556-1458/2 - 4587");
        Lexer lexer = new Lexer(reader);

        Parser parser = new Parser(lexer);
        int res = parser.calculate();
        assertEquals(2 * 10000 - 15625 + 1556-1458/2 - 4587, res);
    }

    @Test
    public void checkUnMinus() throws IOException, BadLexemeException, BadExpressionException {
        StringReader reader = new StringReader("-404");
        Lexer lexer = new Lexer(reader);

        Parser parser = new Parser(lexer);
        int res = parser.calculate();
        assertEquals(-404, res);
    }

    @Test
    public void checkDifficultExprWithPlusMinusMultDivPowUnminus() throws IOException, BadLexemeException, BadExpressionException {
        StringReader reader = new StringReader("2 * 10^4 - 5^2^3 + 1556-1458/2 *-4587");
        Lexer lexer = new Lexer(reader);

        Parser parser = new Parser(lexer);
        int res = parser.calculate();
        assertEquals(2 * 10000 - 15625 + 1556-1458/2 * -4587, res);
    }

}
