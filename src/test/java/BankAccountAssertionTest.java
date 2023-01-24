import com.mycompany.main.java.forunittest.BankAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

@DisplayName("Test BankAccount class")
public class BankAccountAssertionTest {
    @Test
    @DisplayName("Withdraw tested successfully.")
    public void testWithdraw() {
        BankAccount bankAccount = new BankAccount(500, -1000);
        bankAccount.withdraw(100);
        Assertions.assertEquals(400, bankAccount.getBalance());
    }

    @Test
    @DisplayName("Withdraw tested successfully with not equals.")
    public void testWithdraw1() {
        BankAccount bankAccount = new BankAccount(900, 0);
        bankAccount.withdraw(100);
        Assertions.assertNotEquals(400, bankAccount.getBalance());
    }

    @Test
    @DisplayName("Deposit tested successfully.")
    public void testDeposit() {
        BankAccount bankAccount = new BankAccount(500, 0);
        bankAccount.deposit(250);
        Assertions.assertEquals(750., bankAccount.getBalance());
    }

    @Test
    @DisplayName("Active status tested successfully.")
    public void testIsActive() {
        BankAccount bankAccount = new BankAccount(500, 0);
        Assertions.assertTrue(bankAccount.isActive());
    }

    @Test
    @DisplayName("Account holder name tested successfully.")
    public void testHolderNameNull() {
        BankAccount bankAccount = new BankAccount(500, 0);
        bankAccount.setHolderName("me");
        Assertions.assertNotNull(bankAccount.getHolderName());
    }

    @Test
    @DisplayName("No withdraw below minimum tested successfully.")
    public void testNoWithdrawBelowMin() {
        BankAccount bankAccount = new BankAccount(500, 0);
        Assertions.assertThrows(RuntimeException.class, ()->bankAccount.withdraw(600));
    }

    @Test
    @DisplayName("Withdraw/Deposit with no exceptions tested successfully.")
    public void testWithdrawAndDepositWithoutException() {
        BankAccount bankAccount = new BankAccount(500, -100);
        Assertions.assertAll(()->bankAccount.deposit(100), ()->bankAccount.withdraw(700));
    }

    @Test
    @DisplayName("Withdraw/Deposit no timeout tested successfully.")
    public void testWithdrawAndDepositTimeout() {
        BankAccount bankAccount = new BankAccount(500, -100);
        Assertions.assertTimeout(Duration.ofNanos(1), ()->bankAccount.deposit(100));
        Assertions.assertTimeout(Duration.ofNanos(1), ()->bankAccount.withdraw(100));
    }
}
