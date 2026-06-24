package org.mockito.junittesting.aaapaterntesting;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * BankAccountTest - JUnit 5 test class for BankAccount.
 *
 * Demonstrates:
 *  1. The Arrange-Act-Assert (AAA) pattern in every test method.
 *  2. @BeforeEach  - runs before every test  (replaces JUnit 4's @Before)
 *  3. @AfterEach   - runs after  every test  (replaces JUnit 4's @After)
 *  4. @BeforeAll   - runs once before all tests in this class
 *  5. @AfterAll    - runs once after  all tests in this class
 *  6. @DisplayName - gives each test a human-readable label in the report
 */
class BankAccountTest {

    // Test fixture — shared objects created fresh before each test
    private BankAccount account;
    private BankAccount targetAccount;

    // ------------------------------------------------------------------
    // SETUP & TEARDOWN
    // ------------------------------------------------------------------

    /**
     * @BeforeAll runs ONCE before any test in this class starts.
     * Use it for expensive one-time setup (e.g. starting a database).
     * Must be static in JUnit 5.
     */
    @BeforeAll
    static void initAll() {
        System.out.println("=== BankAccountTest suite started ===");
    }

    /**
     * @BeforeEach runs before EVERY individual test method.
     * This is the most common setup annotation.
     * Here we create a fresh BankAccount so each test starts
     * with a clean, predictable state — tests never affect each other.
     */
    @BeforeEach
    void setUp() {
        account       = new BankAccount("Alice", 1000.00);  // starting balance: $1000
        targetAccount = new BankAccount("Bob",    500.00);  // starting balance: $500
        System.out.println("setUp: fresh accounts created for this test.");
    }

    /**
     * @AfterEach runs after EVERY individual test method.
     * Use it to clean up resources (close files, reset state, etc.).
     */
    @AfterEach
    void tearDown() {
        account       = null;
        targetAccount = null;
        System.out.println("tearDown: accounts cleared after this test.\n");
    }

    /**
     * @AfterAll runs ONCE after every test in this class has finished.
     * Must be static in JUnit 5.
     */
    @AfterAll
    static void tearDownAll() {
        System.out.println("=== BankAccountTest suite finished ===");
    }

    // ------------------------------------------------------------------
    // TESTS — each follows the AAA pattern:
    //   ARRANGE  set up the specific inputs needed for this test
    //   ACT      call the method being tested
    //   ASSERT   verify the result is what we expected
    // ------------------------------------------------------------------

    @Test
    @DisplayName("Deposit increases the account balance correctly")
    void testDeposit() {
        // ARRANGE
        double depositAmount = 500.00;

        // ACT
        account.deposit(depositAmount);

        // ASSERT
        assertEquals(1500.00, account.getBalance(),
                "Balance should be 1500 after depositing 500 into a 1000 account.");
    }

    @Test
    @DisplayName("Withdraw decreases the account balance correctly")
    void testWithdraw() {
        // ARRANGE
        double withdrawAmount = 200.00;

        // ACT
        account.withdraw(withdrawAmount);

        // ASSERT
        assertEquals(800.00, account.getBalance(),
                "Balance should be 800 after withdrawing 200 from a 1000 account.");
    }

    @Test
    @DisplayName("Transfer moves funds from one account to another correctly")
    void testTransfer() {
        // ARRANGE
        double transferAmount = 300.00;

        // ACT
        account.transfer(targetAccount, transferAmount);

        // ASSERT
        assertEquals(700.00, account.getBalance(),
                "Alice's balance should be 700 after transferring 300.");
        assertEquals(800.00, targetAccount.getBalance(),
                "Bob's balance should be 800 after receiving 300.");
    }

    @Test
    @DisplayName("Withdraw throws exception when funds are insufficient")
    void testWithdrawInsufficientFunds() {
        // ARRANGE
        double withdrawAmount = 5000.00;  // more than the 1000 balance

        // ACT & ASSERT
        // assertThrows verifies that the method throws the expected exception.
        // The test PASSES if the exception IS thrown; FAILS if it is not.
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> account.withdraw(withdrawAmount),
                "Withdrawing more than the balance should throw IllegalStateException."
        );

        assertEquals("Insufficient funds.", exception.getMessage());
    }

    @Test
    @DisplayName("Deposit throws exception when amount is zero or negative")
    void testDepositNegativeAmount() {
        // ARRANGE
        double invalidAmount = -100.00;

        // ACT & ASSERT
        assertThrows(
                IllegalArgumentException.class,
                () -> account.deposit(invalidAmount),
                "Depositing a negative amount should throw IllegalArgumentException."
        );
    }

    @Test
    @DisplayName("Account balance is not null after creation")
    void testAccountNotNull() {
        // ARRANGE — account is already created in @BeforeEach

        // ACT — no action needed; we are testing the object's initial state

        // ASSERT
        assertNotNull(account,
                "Account object should not be null after creation.");
    }

    @Test
    @DisplayName("Initial balance is set correctly at construction")
    void testInitialBalance() {
        // ARRANGE
        double expectedBalance = 1000.00;

        // ACT
        double actualBalance = account.getBalance();

        // ASSERT
        assertEquals(expectedBalance, actualBalance,
                "Initial balance should be exactly 1000.00.");
    }

    @Test
    @DisplayName("Account holder name is stored correctly")
    void testAccountHolderName() {
        // ARRANGE
        String expectedName = "Alice";

        // ACT
        String actualName = account.getAccountHolder();

        // ASSERT
        assertEquals(expectedName, actualName,
                "Account holder name should be Alice.");
    }
}
