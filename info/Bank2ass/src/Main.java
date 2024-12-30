import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
abstract class Person {
    private String name;
    private String id;
    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Person{name='" + name + "', id='" + id + "'}";
    }
    @Override
    public boolean equals(Object bank) {
        if (this == bank) return true;
        if (!(bank instanceof Person)) return false;
        Person person = (Person) bank;
        return Objects.equals(name, person.name) && Objects.equals(id, person.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
class Account {
    private String accountId;
    private double balance;
    private double zhyldyqTabys;
    public Account(String accountId, double balance, double zhyldyqTabys) {
        this.accountId = accountId;
        this.balance = balance;
        this.zhyldyqTabys = zhyldyqTabys;
    }
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public double getBalance() {
        return balance;
    }
    public double getZhyldyqTabys() {
        return zhyldyqTabys;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setZhyldyqTabys(double zhyldyqTabys) {
        if (zhyldyqTabys < 0) throw new IllegalArgumentException("Tabys kishi bolmau kerek");
        this.zhyldyqTabys = zhyldyqTabys;
    }
    public double Zhyldyqkiris_esepteu(int years) {
        if (years < 0) throw new IllegalArgumentException("Zhyl sany teris bolmau kerek");
        return balance * Math.pow(1 + (zhyldyqTabys / 100), years);
    }
    @Override
    public String toString() {
        return "Account{accountId='" + accountId + "', balance=" + balance + ", zhyldyqTabys=" + zhyldyqTabys + "%}";
    }
    @Override
    public boolean equals(Object bank) {
        if (this == bank) return true;
        if (!(bank instanceof Account)) return false;
        Account account = (Account) bank;
        return Double.compare(account.balance, balance) == 0 && Double.compare(account.zhyldyqTabys, zhyldyqTabys) == 0 && Objects.equals(accountId, account.accountId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(accountId, balance, zhyldyqTabys);
    }
}
class Customer extends Person {
    private Account account;
    public Customer(String name, String id, Account account) {
        super(name, id);
        this.account = account;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public void updateBalance(double newBalance) {
        this.account.setBalance(newBalance);
    }
    @Override
    public String toString() {
        return "Customer{name='" + getName() + "', id='" + getId() + "', Account{accountId='" + account.getAccountId() +
                "', balance=" + account.getBalance() + ", zhyldyqTabys=" + account.getZhyldyqTabys() + "%}}";
    }
    @Override
    public boolean equals(Object bank_client) {
        if (this == bank_client) return true;
        if (!(bank_client instanceof Customer)) return false;
        if (!super.equals(bank_client)) return false;
        Customer customer = (Customer) bank_client;
        return Objects.equals(account, customer.account);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), account);
    }
}
class Bank {
    private String name;
    private List<Customer> customers;
    public Bank(String name) {
        this.name = name;
        this.customers = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public List<Customer> getCustomers() {
        return customers;
    }
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    public List<Customer> filterCustomersByBalance(double minBalance) {
        return customers.stream().filter(customer -> customer.getAccount().getBalance() >= minBalance).collect(Collectors.toList());
    }
    public List<Customer> searchCustomerByName(String name) {
        return customers.stream().filter(customer -> customer.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }
    public void sortCustomersByName() {
        customers.sort(Comparator.comparing(Customer::getName));
    }
    @Override
    public String toString() {
        return "Bank{name='" + name + "', customers=" + customers + '}';
    }
    @Override
    public boolean equals(Object bank_1) {
        if (this == bank_1) return true;
        if (!(bank_1 instanceof Bank)) return false;
        Bank bank = (Bank) bank_1;
        return Objects.equals(name, bank.name) && Objects.equals(customers, bank.customers);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, customers);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank("Kaspi Bank");
        Account account1 = new Account("00157489", 1000.0, 5.0);
        Account account2 = new Account("00157490", 2000.0, 3.0);
        Customer customer1 = new Customer("Arman", "C003", account1);
        Customer customer2 = new Customer("Batyrkhan", "C002", account2);
        bank.addCustomer(customer1);
        bank.addCustomer(customer2);
        System.out.println("Bank aqparaty:");
        System.out.println(bank);
        bank.sortCustomersByName();
        System.out.println("Sorttau:");
        System.out.println(bank.getCustomers());
        System.out.println("Balance engiz: ");
        double minBalance = scanner.nextDouble();
        System.out.println("Clientterdi sorttau:");
        System.out.println(bank.filterCustomersByBalance(minBalance));
        System.out.println("Clienttyn atyn engiz:");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Izde:");
        System.out.println(bank.searchCustomerByName(name));
        for (Customer customer : bank.getCustomers()) {
            System.out.println("\n'" + customer.getName() + "' ushin zhyl sany: ");
            int years = scanner.nextInt();
            double newBalance = customer.getAccount().Zhyldyqkiris_esepteu(years);
            System.out.println("Balance  " + years + " zhyldan son: " + newBalance);
            customer.updateBalance(newBalance);
        }
        System.out.println("Zhanartylgan aqparat:");
        for (Customer customer : bank.getCustomers()) {
            System.out.println(customer);
        }
        scanner.close();
    }
}