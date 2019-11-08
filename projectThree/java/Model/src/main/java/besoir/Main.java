package besoir;

import besoir.Framework.Port;
import besoir.Model.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //XORModel xor = new XORModel();
        Port<Integer> p1 = new Port<>("main in1", 0);
        Port<Integer> p2 = new Port<>("main in2", 0);
        XORNetwork xon = new XORNetwork(p1, p2);

        Scanner sc = new Scanner(System.in);
        Scanner strScn;
        System.out.print("Enter two bits: ");
        String cmd = sc.nextLine();
        while(!cmd.equals("exit")) {
            strScn = new Scanner(cmd);
            p1.setVal(strScn.nextInt());
            p2.setVal(strScn.nextInt());
            xon.lambda();
            xon.delta();
            strScn.close();
            System.out.print("Enter two bits: ");
            cmd = sc.nextLine();
        }
        sc.close();
    }
}
