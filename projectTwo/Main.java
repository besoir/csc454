import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws VendingMachineException {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Starting State of the Machine: ");
        String ent = sc.nextLine();
        VendingMachine vm = new VendingMachine(
            (int)ent.chars().filter(ch -> ch == 'q').count(),
            (int) ent.chars().filter(ch -> ch == 'd').count(),
            (int) ent.chars().filter(ch -> ch == 'n').count(),
            false);
        System.out.print("Enter Change, c to Cancel: ");
        ent = sc.nextLine();
        while(!ent.equals("exit")) {
            vm.input((int) ent.chars().filter(ch -> ch == 'q').count(),
                     (int) ent.chars().filter(ch -> ch == 'd').count(),
                     (int) ent.chars().filter(ch -> ch == 'n').count(),
                     ent.contains("c"), ent.contains("w"));
            System.out.print("Enter Change, c to Cancel: ");
            ent = sc.nextLine();
        }

        sc.close();
    }
}