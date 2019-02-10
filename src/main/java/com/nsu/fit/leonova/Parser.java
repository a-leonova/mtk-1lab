package com.nsu.fit.leonova;

import java.io.IOException;

public class Parser {

    private Lexeme current;
    private Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public int calculate() throws BadLexemeException, IOException, BadExpressionException {
        current = lexer.getNextLexeme();
        int res = parseExpr();
        if(current.getType() != LexemeType.EOF){
            throw new BadExpressionException("Not EOF in the end");
        }
        //Todo: current == EOF
        return res;
    }

    private int parseExpr() throws BadLexemeException, IOException, BadExpressionException {
        int temp = parseTerm();
        LexemeType type = current.getType();
        while(type == LexemeType.PLUS || type == LexemeType.MINUS){
            current = lexer.getNextLexeme();
            if(type == LexemeType.MINUS){
                temp -= parseTerm();
            }
            if(type == LexemeType.PLUS){
                temp += parseTerm();
            }
            type = current.getType();
        }
        return temp;
    }
    private int parseTerm() throws BadLexemeException, IOException, BadExpressionException {

        int temp = parseFactor();
        LexemeType type = current.getType();
        while(type == LexemeType.MULT || type == LexemeType.DIV){
            current = lexer.getNextLexeme();
            if(type == LexemeType.DIV){
                temp /= parseTerm();
            }
            if(type == LexemeType.MULT){
                temp *= parseTerm();
            }
            type = current.getType();
        }
        return temp;

    }
    private int parseFactor() throws BadExpressionException, BadLexemeException, IOException {
        if(current.getType() != LexemeType.NUMBER){
            throw new BadExpressionException("Bad expression. Not a number");
        }
        int i = Integer.parseInt(current.getText());
        current = lexer.getNextLexeme();
        return i;
    }
//    private int parsePower(){
//
//    }
//    private int parseAtom(){
//
//    }
}
