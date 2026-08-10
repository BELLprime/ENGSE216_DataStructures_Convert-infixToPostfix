/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conversion_infixtopostfix;
/**
 *
 * @author Natthakit
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an infix expression: ");
        String infix = scanner.nextLine();
        try {
            InfixToPostfix conversion = new InfixToPostfix(infix);
            conversion.convert();
            System.out.println("-".repeat(40));
            System.out.println("Postfix: "+conversion.getPostfix());
        } 
        catch (IllegalArgumentException e) {System.out.println(e.getMessage());}
        catch (Exception e) {System.out.println("Some thing went wrong.");}
        finally {scanner.close();}
    }
}
