package br.com.ucsal.olimpiadas.input;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private final Scanner scanner;

    public ConsoleInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public String nextLine() {
        return scanner.nextLine();
    }
}
