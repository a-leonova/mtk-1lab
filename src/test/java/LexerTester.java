import com.nsu.fit.leonova.BadLexemeException;
import com.nsu.fit.leonova.Lexeme;
import com.nsu.fit.leonova.LexemeType;
import com.nsu.fit.leonova.Lexer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class LexerTester {

    @Test
    public void checkOperations() throws IOException, BadLexemeException {
        StringReader reader = new StringReader("+-*/^()");
        Lexer lexer = new Lexer(reader);

        Lexeme lexeme = lexer.getNextLexeme();
        assertEquals(LexemeType.PLUS, lexeme.getType());

        lexeme = lexer.getNextLexeme();
        assertEquals(LexemeType.MINUS, lexeme.getType());

        lexeme = lexer.getNextLexeme();
        assertEquals(LexemeType.MULT, lexeme.getType());

        lexeme = lexer.getNextLexeme();
        assertEquals(LexemeType.DIV, lexeme.getType());

        lexeme = lexer.getNextLexeme();
        assertEquals(LexemeType.POWER, lexeme.getType());

        lexeme = lexer.getNextLexeme();
        assertEquals(LexemeType.LEFT_BRACE, lexeme.getType());

        lexeme = lexer.getNextLexeme();
        assertEquals(LexemeType.RIGHT_BRACE, lexeme.getType());
    }

    @Test
    public void checkNumber() throws IOException, BadLexemeException {
        StringReader reader = new StringReader("123456");
        Lexer lexer = new Lexer(reader);

        Lexeme lexeme = lexer.getNextLexeme();
        assertEquals("123456", lexeme.getText());
    }

    @Test
    public void checkExpressionWithSpaces() throws IOException, BadLexemeException {
        StringReader reader = new StringReader("\r123456 + 12 \n * (748 - \t\t\t 156)");
        Lexer lexer = new Lexer(reader);

        Lexeme lexeme = lexer.getNextLexeme();
        assertEquals("123456", lexeme.getText());

        lexeme = lexer.getNextLexeme();
        assertEquals(LexemeType.PLUS, lexeme.getType());

        lexeme = lexer.getNextLexeme();
        assertEquals("12", lexeme.getText());

        lexeme = lexer.getNextLexeme();
        assertEquals(LexemeType.MULT, lexeme.getType());

        lexeme = lexer.getNextLexeme();
        assertEquals(LexemeType.LEFT_BRACE, lexeme.getType());

        lexeme = lexer.getNextLexeme();
        assertEquals("748", lexeme.getText());

        lexeme = lexer.getNextLexeme();
        assertEquals(LexemeType.MINUS, lexeme.getType());

        lexeme = lexer.getNextLexeme();
        assertEquals("156", lexeme.getText());

        lexeme = lexer.getNextLexeme();
        assertEquals(LexemeType.RIGHT_BRACE, lexeme.getType());
    }


    @Test
    public void checkExceptionWhenBadSymbol() throws IOException, BadLexemeException {
        StringReader reader = new StringReader("1234 - #12");
        Lexer lexer = new Lexer(reader);
        Lexeme lexeme = lexer.getNextLexeme();
        assertEquals("1234", lexeme.getText());
        lexeme = lexer.getNextLexeme();
        assertEquals(LexemeType.MINUS, lexeme.getType());

        Throwable exception = assertThrows(BadLexemeException.class, ()->lexer.getNextLexeme());
        assertEquals("Bad symbol: #", exception.getMessage());
    }

}
