package bank;

public class Employee extends Person implements BankEntity{
	private static int compteur =0;
	
	public Employee(String name,String email) {
		super(++compteur,name,email);
	}
	
	@Override
	public void displayDetails() {
	    System.out.println("┌────────────────────────┐");
	    System.out.println("│  INFORMATION EMPLOYEE  │");
	    System.out.println("├────────────────────────┤");
	    System.out.printf(" │ID    : %-14d │\n", getID());
	    System.out.printf(" │Nom   : %-14s │\n", getName());
	    System.out.printf(" │Email : %-14s │\n", getEmail());
	    System.out.println("└────────────────────────┘");
	}

}
