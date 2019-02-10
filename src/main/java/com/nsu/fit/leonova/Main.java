package com.nsu.fit.leonova;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String inputFileName = args[0];
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            Lexer lexer = new Lexer(reader);
            Parser parser = new Parser(lexer);
            int res = parser.calculate();
            System.out.println("Result is: " + res);
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't open file");
        } catch (IOException e) {
            System.err.println("Couldn't read or close file");
        } catch (BadLexemeException | BadExpressionException e) {
            System.err.println(e.getMessage());
        }
    }
}
