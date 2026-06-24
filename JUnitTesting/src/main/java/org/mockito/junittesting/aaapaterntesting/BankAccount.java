package org.mockito.junittesting.aaapaterntesting;

/**
 * BankAccount - the class under test.
 *
 * Represents a simple bank account with deposit, withdraw,
 * and transfer operations. The JUnit tests in BankAccountTest
 * verify that each of these behaves correctly.
 */
public class BankAccount {

    private String accountHolder;
    private double balance;

    public BankAccount(String accountHolder, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    // Adds the given amount to the balance.
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
    }

    // Deducts the given amount from the balance.
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds.");
        }
        balance -= amount;
    }

    // Transfers an amount from this account to another account.
    public void transfer(BankAccount target, double amount) {
        this.withdraw(amount);
        target.deposit(amount);
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }
}
