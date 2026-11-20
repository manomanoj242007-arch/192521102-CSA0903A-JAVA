import java.util.InputMismatchException;
import java.util.Scanner;

public class ATMException {
    private static final int BALANCE = 5000;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("Enter account number: ");
            int account = input.nextInt();
            System.out.print("Enter withdrawal amount: ");
            int amount = input.nextInt();

            if (account <= 0) {
                throw new IllegalArgumentException("Invalid account number");
            }
            if (amount <= 0) {
                throw new IllegalArgumentException("Amount must be positive");
            }
            if (amount > BALANCE) {
                throw new IllegalStateException("Insufficient balance");
            }
            System.out.println("Withdrawal successful. Cash dispensed: Rs." + amount);
            System.out.println("Remaining balance: Rs." + (BALANCE - amount));
        } catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println("Input error: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("Transaction error: " + e.getMessage());
        } finally {
            System.out.println("ATM session closed.");
            input.close();
        }
    }
}