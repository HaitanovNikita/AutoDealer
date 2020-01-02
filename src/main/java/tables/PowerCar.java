package tables;

import javax.persistence.*;

@Entity
@Table(name = "PowerCar")
public class PowerCar {
	
	private int ID;
	private int horse_power;
	
	public PowerCar() {
		
	}
	
	public PowerCar(int iD, int horse_power) {
		super();
		ID = iD;
		this.horse_power = horse_power;
	}

	
	@Id
	@JoinColumn(name = "ID")
	public int getID() {
		return ID;
	}

	@Column(name="horse_power")
	public int getHorse_power() {
		return horse_power;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void setHorse_power(int horse_power) {
		this.horse_power = horse_power;
	}


	@Override
	public String toString() {
		return ID + " " + horse_power;
	}
}
