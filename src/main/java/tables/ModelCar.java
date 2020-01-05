package tables;

import javax.persistence.*;

@Entity
@Table(name = "ModelCar")
public class ModelCar {

	
	private int ID;
	private String name_model;

	public ModelCar() {
		
	}
	
	public ModelCar(int id, String name_model) {
		this.ID = id;
		this.name_model = name_model;
	}


	@Id
	@JoinColumn(name = "ID")
	public int getID() {
		return ID;
	}
	
	
	@Column(name = "name_model")
	public String getName_model() {
		return name_model;
	}

	@Override
	public String toString() {
		return ID + " " + name_model;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public void setName_model(String name_model) {
		this.name_model = name_model;
	}
	
	
}
