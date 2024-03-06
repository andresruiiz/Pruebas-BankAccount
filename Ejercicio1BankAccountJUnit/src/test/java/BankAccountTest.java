import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.function.Executable;
import bank.BankAccount;

public class BankAccountTest {

    BankAccount bankAccount;

    @BeforeEach
    public void setup() {
        // Arrange
        bankAccount = new BankAccount(100);
    }

    @Test 
    @DisplayName("Test withdraw with sufficient balance")
    public void testWithdrawWithSufficientBalance() {
        // Arrange
        int withdrawalAmount = 50;

        // Act
        boolean result = bankAccount.withdraw(withdrawalAmount);

        // Assert
        assertTrue(result);
        assertEquals(50, bankAccount.getBalance());
    }

    @Test 
    @DisplayName("Test withdraw with insufficient balance")
    public void testWithdrawWithInsufficientBalance() {
        // Arrange
        int withdrawalAmount = 200;

        // Act
        boolean result = bankAccount.withdraw(withdrawalAmount);

        // Assert
        assertFalse(result);
        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    @DisplayName("Test withdraw with 0")
    public void testWithdrawWithZero() {
        // Arrange
        int withdrawalAmount = 0;

        // Act
        boolean result = bankAccount.withdraw(withdrawalAmount);

        // Assert
        assertTrue(result);
        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    @DisplayName("Test withdraw with negative amount")
    public void testWithdrawWithNegativeAmount() {
        // Arrange
        int withdrawalAmount = -100;

        // Act
        Executable executable = () -> bankAccount.withdraw(withdrawalAmount);

        // Assert
        assertThrows(IllegalArgumentException.class, executable);
        assertEquals(100, bankAccount.getBalance());
    }

    @Test 
    @DisplayName("Test deposit with positive amount")
    public void testDepositWithPositiveAmount() {
        // Arrange
        int depositAmount = 100;

        // Act
        int newBalance = bankAccount.deposit(depositAmount);

        // Assert
        assertEquals(200, newBalance);
        assertEquals(200, bankAccount.getBalance());
    }

    @Test 
    @DisplayName("Test deposit with negative amount")
    public void testDepositWithNegativeAmount() {
        // Arrange
        int depositAmount = -100;

        // Act
        Executable executable = () -> bankAccount.deposit(depositAmount);

        // Assert
        assertThrows(IllegalArgumentException.class, executable);
        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    @DisplayName("Test payment calculation")
    public void testPaymentCalculation() {
        // Arrange
        double totalAmount = 10000;
        double interest = 0.01;
        int npayments = 12;

        // Act
        double payment = bankAccount.payment(totalAmount, interest, npayments);

        // Assert
        assertEquals(888.4878867834167, payment, 0.01);
    }

    @Test
    @DisplayName("Test pending amount calculation")
    public void testPendingAmountCalculation() {
        // Arrange
        double amount = 10000;
        double interest = 0.01;
        int npayments = 12;
        int month = 2;

        // Act
        double pendingAmount = bankAccount.pending(amount, interest, npayments, month);

        // Assert
        assertEquals(8415.139347565333, pendingAmount, 0.01);
    }

    
}