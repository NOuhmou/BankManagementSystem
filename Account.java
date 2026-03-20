package bank;

import java.security.SecureRandom;

public class Account implements BankEntity{
	
	private Client holder;
	private String rib;
	private String pin;
	private double balance;
	
	
	private static final SecureRandom random = new SecureRandom();
	
	public Account(Client client){
		this.holder=client;
		this.rib=generateRib();
		this.pin=generatePin();
		this.balance=0;
	}
	
	private String generateRib() {
		long number = 100000000000000L + (long)(random.nextDouble()*900000000000000L);
		
		return String.valueOf(number);
	}
	
	private String generatePin() {
		
		int number = 1000 + random.nextInt(9000);
		return String.valueOf(number);
	}
	
	
	public void deposit(double amount)throws InvalidAmountException{
		if (amount <= 0) {
			throw new InvalidAmountException("Montant doit être positif");
		}
		balance += amount;
		System.out.println("✅ Dépot de "+amount+" DH effectué");
	}
	
	public void withdraw (double amount) throws InvalidAmountException,InsufficientFundsException {
		if ( amount <= 0) {
			throw new InvalidAmountException("Montant doit être positif");
		}
		if (amount > balance ) {
			throw new InsufficientFundsException("Solde Insuffisant. Solde actuel:"+balance +" DH");
			
		}
		balance -=amount;
		System.out.println(" Vous avez retirer un montant  de "+amount+" DH avec Succès ✅");
	}
	
	public boolean verifyPin(String entreedPin) {
		return pin.equals(entreedPin);
	}
	
	public String getRib() {return rib;}
	public String getPin() {return pin;}
	public double getBalance() {return balance;}
	public Client getHolder() {return holder;}
	
	@Override
	public void displayDetails() {
        System.out.println("┌────────────────────────┐");
        System.out.println("│    INFORMATION COMPTE  │");
        System.out.println("├────────────────────────┤");
        System.out.printf("│ Titulaire: %-13s │\n", holder.getName());
        System.out.printf("│ RIB: %-19s │\n", rib);
        System.out.printf("│ PIN: %-19s │\n", "****");
        System.out.printf("│ Solde: %-17.2f DH │\n", balance);
        System.out.println("└────────────────────────┘");
		
	}
	

}
