package Oxana;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);         // creating a scanner

        System.out.println("Please enter amount of Cups or Spoons in recipe:");
        double initial = Double.parseDouble(scanner.nextLine());

        System.out.println("Are you measuring (C)ups or (S)poons?");
        String measurement = scanner.nextLine();

        System.out.println("Please enter amount of portions in recipe:");
        double num = Double.parseDouble(scanner.nextLine());

        System.out.println("Please enter desired amount of portions:");
        double desired = Double.parseDouble(scanner.nextLine());

        if (measurement.contains("C")) {
            Cups cup = new Cups();
            double cups =  cup.getNeededAmountDecimal(num, initial, desired);
            System.out.println(cup.getAmountInCups(cups));
        } else if (measurement.contains("S")) {
            Spoons spoon = new Spoons();
            double spoons =  spoon.getNeededAmountDecimal(num, initial, desired);
            System.out.println(spoon.getAmountInSpoons(spoons));
        } else {                                                            // if a user makes a typo.
            System.out.println("Please enter \"C\" or \"S\" upper case");
        }
        scanner.close();
    }
}
