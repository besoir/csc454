package besoir;

import besoir.Model.MachineNetwork;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter mode: ");
        String m = sc.nextLine();

        if(m.equals("b")) {
            try {
                sc = new Scanner(new File("src/main/resources/input.txt"));
                while(sc.hasNextLine()) {
                    String event  = sc.nextLine();
                    System.out.println("> " + event);
                    //add it to the heap
                }
                System.out.println("Continuing with user input...");
            } catch(FileNotFoundException fn) {
                System.out.println("File not found, continuing with user input...");
            }
            sc = new Scanner(System.in);
        }

        System.out.print("> ");
        String event = sc.nextLine();
        while(!event.equals("exit")) {
            //run the program

            System.out.print("> ");
            event = sc.nextLine();
        }

        System.out.println("Exiting...");
        sc.close();
    }
}