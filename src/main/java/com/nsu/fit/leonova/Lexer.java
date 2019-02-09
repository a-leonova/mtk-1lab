package com.nsu.fit.leonova;

import java.io.IOException;
import java.io.Reader;

public class Lexer {
    private int current;
    private Reader reader;
    private StringBuilder number = new StringBuilder();

    public Lexer(Reader reader) throws IOException {
        this.reader = reader;
        current = reader.read();
    }

    public Lexeme getNextLexeme() throws BadLexemeException, IOException {

        while(current == '\n' || current == '\t' || current == '\r' || current == ' '){
            current = reader.read();
        }

        Lexeme lexeme;
        switch (current){
            case -1 :
                lexeme = new Lexeme(LexemeType.EOF, "");
                break;
            case '+' :
                lexeme = new Lexeme(LexemeType.PLUS, "+");
                break;
            case '-' :
                lexeme = new Lexeme(LexemeType.MINUS, "-");
                break;
            case '*' :
                lexeme = new Lexeme(LexemeType.MULT, "*");
                break;
            case '/' :
                lexeme = new Lexeme(LexemeType.DIV, "/");
                break;
            case '^' :
                lexeme = new Lexeme(LexemeType.POWER, "^");
                break;
            case '(' :
                lexeme = new Lexeme(LexemeType.LEFT_BRACE, "(");
                break;
            case ')' :
                lexeme = new Lexeme(LexemeType.RIGHT_BRACE, ")");
                break;
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case '0':
                while (current >= '0' && current <= '9'){
                    number.append((char)current);
                    current = reader.read();
                }
                lexeme = new Lexeme(LexemeType.NUMBER, number.toString());
                number.setLength(0);
                number.setLength(0);
                number.trimToSize();
                return lexeme;
            default:
                throw new BadLexemeException("Bad symbol: " + (char)current);
        }
        current = reader.read();
        return lexeme;
    }
}
