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
}
