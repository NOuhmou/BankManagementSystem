package bank;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    
    private List<Employee> employees;  
    private List<Client> clients;
    private List<Account> accounts;
    
    public Bank(List<Employee> employees) {
        this.clients = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.employees = employees; 
    }
    
    public void createAccount(Client client) {
        clients.add(client);
        Account compte = new Account(client);
        accounts.add(compte);
        client.setAccount(compte);
        System.out.println("✅ Compte créé pour " + client.getName());
    }
    
    public Account findAccountByRib(String rib) throws AccountNotFoundException {
        for (Account account : accounts) {
            if (account.getRib().equals(rib)) {
                return account;
            }
        }
        throw new AccountNotFoundException("RIB introuvable: " + rib);
    }
    
    public Account authenticateClient(String rib, String pin) 
            throws AccountNotFoundException, AuthenticationException {
        
        Account account = findAccountByRib(rib);
        
        if (!account.verifyPin(pin)) {
            throw new AuthenticationException("PIN incorrect pour le RIB: " + rib);
        }
        
        return account;
    }
    
    public void transferByRib(Account sender, String destinationRib, double amount) 
            throws AccountNotFoundException, InsufficientFundsException, InvalidAmountException {
        
        // Vérification du montant
        if (amount <= 0) {
            throw new InvalidAmountException("Le montant doit être positif");
        }
        
        // Vérification auto-transfert
        if (sender.getRib().equals(destinationRib)) {
            throw new InvalidAmountException("Impossible de transférer à soi-même");
        }
        
        Account receiver = findAccountByRib(destinationRib);
        
        // Effectuer le transfert
        sender.withdraw(amount);
        receiver.deposit(amount);
        
        System.out.println("✅ Virement de " + amount + " DH effectué vers " + 
                          receiver.getHolder().getName());
    }
    
    public void printClientsDebug() {
        System.out.println("\n=== Liste des clients ===");
        for (Client client : clients) {
            Account account = client.getAccount();
            System.out.println("Client: " + client.getName() + 
                             ", RIB: " + account.getRib() + 
                             ", PIN: " + account.getPin());
        }
    }
    
    
    public List<Employee> getEmployees() {
        return employees;
    }
}