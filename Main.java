package bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	private static int readInt(Scanner scanner,String message) {
		while(true) {
			System.out.print(message);
			String input = scanner.nextLine().trim();
			try {
				return Integer.parseInt(input);
			}catch (NumberFormatException e) {
				System.out.println("❌ Veuillez entrer un nombre valide.");
			}
		}
	}
	
	private static double readDouble(Scanner scanner, String message) {
		while(true) {
			System.out.print(message);
			String input = scanner.nextLine().trim();
			try {
				return Double.parseDouble(input);
			}catch(NumberFormatException e) {
				System.out.println("❌ Veuillez entrer un montant valide.");
			}
		}
	}
	
	private static Account login(Scanner scanner , Bank bank) {
		while(true) {
			
			System.out.println("\n====== BANQUE - CONNEXION ========");
			System.out.println("RIB : ");
			String rib = scanner.nextLine();
			
			System.out.println("PIN : ");
			String pin = scanner.nextLine();
			
			try {
				Account account = bank.authenticateClient(rib, pin);
				System.out.println("Bienvenue "+account.getHolder().getName()+"!");
				
				return account;
			}catch (BankingException e) {
				
				System.out.println("Echec de connexion: "+e.getMessage());
				System.out.println("Voulez-vous réssayser ? (o/n)");
				String reponse = scanner.nextLine().trim().toLowerCase();
				if(!reponse.equals("o")) {
					System.out.println("Au revoir !");
					System.exit(0);
				}
			}
		}
		
		
	}

	public static void main(String[] args)  {
		
		Scanner scanner = new Scanner(System.in);
		
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee("Najat","najat@bank.com"));
		Bank bank = new Bank(employees);
		
		Client client1 = new Client("Ahmed","ahmed@gmail.com");
		Client client2 = new Client("Salma","salma@gmail.com");
		
		
		bank.createAccount(client1);
		bank.createAccount(client2);
		
		bank.printClientsDebug();
		
		Account currentAccount = login(scanner,bank);
		
		int choice ;
		do {
		    System.out.println("\n===== MENU PRINCIPAL =====");
		    System.out.println("1 - Voir le solde");
		    System.out.println("2 - Déposer de l'argent");
		    System.out.println("3 - Retirer de l'argent");
		    System.out.println("4 - Transférer de l'argent");
		    System.out.println("5 - Changer de compte");
		    System.out.println("0 - Quitter");
		    
		    choice = readInt(scanner,"choix");
		    
		    switch(choice) {
		    case 1:
		    	System.out.println("💰 Solde actuel:"+ currentAccount.getBalance()+" DH"	);
		    	currentAccount.displayDetails();
		    	break;
		    case 2:
		    	try {
		    		double montant = readDouble(scanner,"Montant à déposer");
		    		currentAccount.deposit(montant);
		    		System.out.println("✅ Nouveau solde:"+ currentAccount.getBalance()+" DH");
		    	}catch(InvalidAmountException e) {
		    		System.out.println("❌ " +e.getMessage());
		    	}
		    	break;
		    	
		    case 3:
		    	try {
		    		double montant = readDouble(scanner,"Montant à retirer");
		    		currentAccount.withdraw(montant);
		    		System.out.println("✅ Nouveau solde:"+ currentAccount.getBalance()+" DH");
		    		
		    	}catch(InvalidAmountException | InsufficientFundsException e	) {
		    		System.out.println("❌ " + e.getMessage());
		    	}
		    	
		    	break;
		    case 4:
		    	try {
		            System.out.print("RIB du destinataire: ");
		            String ribDest = scanner.nextLine().trim();
		            double montant = readDouble(scanner, "Montant à transférer: ");
		            
		            bank.transferByRib(currentAccount, ribDest, montant);
		            
		        } catch (AccountNotFoundException e) {
		            System.out.println("❌ Compte destinataire introuvable: " + e.getMessage());
		        } catch (InvalidAmountException e) {
		            System.out.println("❌ Montant invalide: " + e.getMessage());
		        } catch (InsufficientFundsException e) {
		            System.out.println("❌ Solde insuffisant: " + e.getMessage());
		        }
		    	
		    	break;
		    case 5:
		    	currentAccount = login(scanner,bank);
		    	
		    	break;
		    case 0:
		    	System.out.println("👋 Merci d'avoir utilisé notre banque. Au revoir !");
		    	break;
		    	
		    default:
		    	System.out.println("❌ Choix invalide. Veuillez réessayer.");
		    }
		}while(choice !=0);

        
        
	
		
	}

}
