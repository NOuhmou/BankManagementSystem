package bank;

public abstract class Person {
	
	private String name;
	private String email;
	private int id;
	
	protected Person(int id,String name,String email) {
		this.id =id;
		this.name=name;	
		this.email=email;
		}
	//getters & setters 
	public int getID() {
		return this.id;
	}
	public void setID(int id) {
		this.id=id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	
}
