package besoir;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws VendingMachineException {
       Scanner sc = new Scanner(System.in);
       System.out.print("Enter Mode: ");
       String m = sc.nextLine();
       System.out.print("Enter Starting State of the Machine: ");
        String ent = sc.nextLine();
        VendingMachine vm = new VendingMachine(
            (int) ent.chars().filter(ch -> ch == 'q').count(),
            (int) ent.chars().filter(ch -> ch == 'd').count(),
            (int) ent.chars().filter(ch -> ch == 'n').count()
        );
        
        if(m.equals("b")) {
            File f = new File("./../../input.txt");
            try {
                Scanner fScan = new Scanner(f);
                while(fScan.hasNextLine()) {
                    String line = fScan.nextLine();
                    System.out.println(line);
                    vm.run(line);
                }
                fScan.close();
            } catch(FileNotFoundException fn) {
                System.out.println("File not found, continuing in regular mode");
            }    
        }

        //Run the machine
        System.out.print("Enter Coin: ");
        ent = sc.nextLine();
        while(!ent.equals("exit")) {
            vm.run(ent);
            System.out.print("Enter Coin: ");
            ent = sc.nextLine();
        }
        sc.close();
    }
}