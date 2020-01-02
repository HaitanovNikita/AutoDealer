package tables;

import logic.MachinePartsDao;

import javax.persistence.*;

@Entity
@Table(name = "ColorCar")
public class ColorCar{

	private int ID; 
	private String color_car;
	
	public ColorCar() {
		
	}
	
	public ColorCar(int iD, String color_car) {
		super();
		ID = iD;
		this.color_car = color_car;
	}

	@Id
	@JoinColumn(name = "ID")
	public int getID() {
		return ID;
	}

	@Column(name="color_car")
	public String getColor_car() {
		return color_car;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void setColor_car(String color_car) {
		this.color_car = color_car;
	}


	@Override
	public String toString() {
		return ID + " " + color_car ;

	}

}
