package handler;

import java.util.Scanner;

public class ConsoleHandler {
    private final Scanner input;

    public ConsoleHandler() {
        this.input = new Scanner(System.in);
    }

    public String read() {
        return input.nextLine();
    }

    public void write(String str) {
        System.out.println(str);
    }

    public void writeLine() {
        System.out.println();
    }
}
