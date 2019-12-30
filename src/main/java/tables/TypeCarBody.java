
package tables;

import javax.persistence.*;

@Entity
@Table(name = "TypeCarBody")
public class TypeCarBody {

	private int ID;
	private String type_body;

	public TypeCarBody() {
		
	}
	
	public TypeCarBody(int iD, String type_body) {
		ID = iD;
		this.type_body = type_body;
	}

	@Id
	@JoinColumn(name="ID")
	public int getID() {
		return ID;
	}

	@Column(name="type_body")
	public String getType_body() {
		return type_body;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void setType_body(String type_body) {
		this.type_body = type_body;
	}
	
	
	
	
}
