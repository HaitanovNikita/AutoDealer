package tables;

import javax.persistence.*;

@Entity
@Table(name="Client")
public class Client {

	private int ID;
	private String client_fname;
	private String client_lname;
	private String client_phone_num;
	private int client_age ;
	private String client_email;
	private String client_gender;

	public Client() {
		
	}
	
	public Client(int iD, String client_fname, String client_lname, String client_phone_num, int client_age,
                  String client_email, String client_gender) {
		
		ID = iD;
		this.client_fname = client_fname;
		this.client_lname = client_lname;
		this.client_phone_num = client_phone_num;
		this.client_age = client_age;
		this.client_email = client_email;
		this.client_gender = client_gender;
	}

	
	@Id
	@JoinColumn(name="ID")
	public int getID() {
		return ID;
	}

	@Column(name="client_fname")
	public String getClient_fname() {
		return client_fname;
	}

	@Column(name="client_lname")
	public String getClient_lname() {
		return client_lname;
	}

	@Column(name="client_phone_num")
	public String getClient_phone_num() {
		return client_phone_num;
	}

	@Column(name="client_age")
	public int getClient_age() {
		return client_age;
	}

	@Column(name="client_email")
	public String getClient_email() {
		return client_email;
	}
 
	@Column(name="client_gender")
	public String getClient_gender() {
		return client_gender;
	}
 
	public void setID(int iD) {
		ID = iD;
	}

	public void setClient_fname(String client_fname) {
		this.client_fname = client_fname;
	}

	public void setClient_lname(String client_lname) {
		this.client_lname = client_lname;
	}	 
	 
	public void setClient_phone_num(String client_phone_num) {
		this.client_phone_num = client_phone_num;
	}

	public void setClient_age(int client_age) {
		this.client_age = client_age;
	}

	public void setClient_email(String client_email) {
		this.client_email = client_email;
	}

	public void setClient_gender(String client_gender) {
		this.client_gender = client_gender;
	}

	
	@Override
	public String toString() {
		return "Client № "+ID+"\n[\n ID=" + ID + ",\n client_fname=" + client_fname + ",\n client_lname=" + client_lname
				+ ",\n client_phone_num=" + client_phone_num + ",\n client_age=" + client_age + ",\n client_email="
				+ client_email + ",\n client_gender=" + client_gender + "\n]";
	}

	
	
	
	
	
	
}
