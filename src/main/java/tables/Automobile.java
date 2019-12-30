package tables;

import javax.persistence.*;

@Entity
@Table(name = "Automobile")
public class Automobile {

	private Integer ID;
	private long car_price;
	private String car_make;
	private long model_car;
	private String year_issue_car;
	private Integer power_car;
	private long engine_car;
	private long color_car;
	private long type_car_body;

	private String model_carString;
	private String engine_carString;
	private String color_carString;
	private String type_car_bodyString;

	public Automobile() {
	}

	public Automobile(Integer id, long car_price, String car_make, long model_car, String year_issue_car,
                      Integer power_car, long engine_car, long color_car, long type_car_body) {
		this.ID = id;
		this.car_price = car_price;
		this.car_make = car_make;
		this.model_car = model_car;
		this.year_issue_car = year_issue_car;
		this.power_car = power_car;
		this.engine_car = engine_car;
		this.color_car = color_car;
		this.type_car_body = type_car_body;
	}

	public Automobile(Integer iD, long car_price, String car_make, String year_issue_car, Integer power_car,
                      String model_carString, String engine_carString, String color_carString, String type_car_bodyString) {
		this.ID = iD;
		this.car_price = car_price;
		this.car_make = car_make;
		this.year_issue_car = year_issue_car;
		this.power_car = power_car;
		this.model_carString = model_carString;
		this.engine_carString = engine_carString;
		this.color_carString = color_carString;
		this.type_car_bodyString = type_car_bodyString;
	}

	@Id
	@JoinColumn(name = "ID")
	public int getId() {
		return ID;
	}

	@Column(name = "year_issue_car")
	public String getYear_issue_car() {
		return year_issue_car;
	}

	@Column(name = "car_price")
	public long getCar_price() {
		return car_price;
	}

	@Column(name = "car_make")
	public String getCar_make() {
		return car_make;
	}

	@Column(name = "model_car")
	public long getModel_car() {
		return model_car;
	}

	@Column(name = "power_car")
	public long getPower_car() {
		return power_car;
	}

	@Column(name = "engine_car")
	public long getEngine_car() {
		return engine_car;
	}

	@Column(name = "color_car")
	public long getColor_car() {
		return color_car;
	}

	@Column(name = "type_car_body")
	public long getType_car_body() {
		return type_car_body;
	}

//	public String getModel_carString() {
//		return model_carString;
//	}
//
//	public String getEngine_carString() {
//		return engine_carString;
//	}
//
//	public String getColor_carString() {
//		return color_carString;
//	}
//
//
//	public String getType_car_bodyString() {
//		return type_car_bodyString;
//	}

	public void setId(int id) {
		this.ID = id;
	}

	public void setCar_price(long car_price) {
		this.car_price = car_price;
	}

	public void setCar_make(String car_make) {
		this.car_make = car_make;
	}

	public void setModel_car(long model_car) {
		this.model_car = model_car;
	}

	public void setPower_car(Integer power_car) {
		this.power_car = power_car;
	}

	public void setEngine_car(long engine_car) {
		this.engine_car = engine_car;
	}

	public void setColor_car(long color_car) {
		this.color_car = color_car;
	}

	public void setType_car_body(long type_car_body) {
		this.type_car_body = type_car_body;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setYear_issue_car(String year_issue_car) {
		this.year_issue_car = year_issue_car;
	}

	@Override
	public String toString() {

		if (type_car_body == 0 && color_car == 0) {
			return "Automobile №"+ID+" \n[\n" + "ID=" + ID + ",\n" + "car_price=" + car_price + ",\n" + "car_make=" + car_make + ",\n"
					+ "year_issue_car=" + year_issue_car + ",\n" + "power_car=" + power_car + ",\n" + "model_carString="
					+ model_carString + ",\n" + "engine_carString=" + engine_carString + ",\n" + "color_carString="
					+ color_carString + ",\ntype_car_bodyString=" + type_car_bodyString + "\n]\n";
		} else {
			return "Automobile №"+ID+ "\n[\n" + "ID=" + ID + ",\n" + "car_price=" + car_price + ",\n" + "car_make=" + car_make + ",\n"
					+ "model_car=" + model_car + ",\n" + "year_issue_car=" + year_issue_car + ",\n" + "power_car="
					+ power_car + ",\n" + "engine_car=" + engine_car + ",\n" + "color_car=" + color_car + ",\n"
					+ "type_car_body=" + type_car_body +  "\n]\n";
		}
	}



//	public void setModel_carString(String model_carString) {
//		this.model_carString = model_carString;
//	}
//
//	public void setEngine_carString(String engine_carString) {
//		this.engine_carString = engine_carString;
//	}
//
//	public void setColor_carString(String color_carString) {
//		this.color_carString = color_carString;
//	}
//
//	public void setType_car_bodyString(String type_car_bodyString) {
//		this.type_car_bodyString = type_car_bodyString;
//	}

}
