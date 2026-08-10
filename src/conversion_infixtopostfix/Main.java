/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conversion_infixtopostfix;
/**
 *
 * @author Natthakit
 */
import java.util.EmptyStackException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an infix expression: ");
        String infix = scanner.nextLine();
        infix = infix.replace(" ", "")
                 .replace("**", "^")
                 .replace("×", "*")
                 .replace("÷", "/");

        InfixToPostfix conversion = new InfixToPostfix(infix);
        try {
            conversion.convert();
            System.out.println(conversion.getPostfix());
        } catch (EmptyStackException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}

