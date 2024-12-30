class Account {
    private String id_esepshot;
    private double balance;
    public Account(String id_esepshot, double balance) {
        this.id_esepshot = id_esepshot;
        this.balance = balance;
    }
    public String getId_esepshot() {
        return id_esepshot;
    }
    public void setId_esepshot(String id_esepshot) {
        this.id_esepshot = id_esepshot;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) balance -= amount;
    }
    @Override
    public String toString() {
        return "Account{id_esepshot='" + id_esepshot + "', balance=" + balance + '}';
    }
}
class Customer {
    private String aty;
    private String customerId;
    private Account account;
    public Customer(String aty, String customerId, Account account) {
        this.aty = aty;
        this.customerId = customerId;
        this.account = account;
    }
    public String getAty() {
        return aty;
    }
    public void setAty(String aty) {
        this.aty = aty;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    @Override
    public String toString() {
        return "Customer{aty='" + aty + "', customerId='" + customerId + "', account=" + account + '}';
    }
}
class Bank {
    private String name;
    public Bank(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Bank{name='" + name + "'}";
    }
}
public class Main {
    public static void main(String[] args) {
        Account account1 = new Account("00157489", 1000.0);
        Account account2 = new Account("00157490", 2000.0);
        Customer customer1 = new Customer("Arman", "C003", account1);
        Customer customer2 = new Customer("Batyrkhan", "C002", account2);
        Bank bank = new Bank("Kaspi Bank");
        System.out.println(bank);
        System.out.println(customer1);
        System.out.println(customer2);
        System.out.println("Esepshot saikestigi: " + customer1.getAccount().getId_esepshot().equals(customer2.getAccount().getId_esepshot()));
        customer1.getAccount().deposit(500);
        customer2.getAccount().withdraw(300);
        System.out.println("Songy janarty:");
        System.out.println(customer1);
        System.out.println(customer2);
    }
}