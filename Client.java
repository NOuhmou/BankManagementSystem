package bank;

public class Client extends Person implements BankEntity{
	//private Compte compte;
	private static int compteur =0;
	private Account account;
	
	public Client (String name, String email) {
		super(++compteur,name,email);
	}
	
	public void setAccount(Account account) {
		this.account=account;
	}
	public Account getAccount() {
		return account;
	}
	
	@Override
	public void displayDetails() {
	    System.out.println("┌────────────────────────┐");
	    System.out.println("│    INFORMATION CLIENT  │");
	    System.out.println("├────────────────────────┤");
	    System.out.printf("│ ID    : %-14d │\n", getID());
	    System.out.printf("│ Nom   : %-14s │\n", getName());
	    System.out.printf("│ Email : %-14s │\n", getEmail());
	    System.out.println("└────────────────────────┘");
	}
	
	
}
