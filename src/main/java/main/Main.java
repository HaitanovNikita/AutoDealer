package main;

import logic.ClientDaoMySql;
import logic.Server;
import tables.Client;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
//		AutomobileDaoMySQl automobileDaoMySQl = new AutomobileDaoMySQl();
//		List<Automobile> listAutomobiles = automobileDaoMySQl.readAllAutomobiles();
		
//		String sqlQuery = "Select " + " a.id, a.car_price, a.car_make, a.year_issue_car, "
//				+ " p.horse_power, m.name_model, e.type_engine, c.color_car, t.type_body  "
//				+ "	from Automobile as a " + "	inner join ModelCar as m on a.model_car = m.id "
//				+ "	inner join PowerCar p on a.power_car = p.ID "
//				+ " inner join EngineCar e on a.engine_car = e.ID "
//				+ " inner join ColorCar c on a.color_car = c.ID "
//				+ " inner join TypeCarBody  t on a.type_car_body = t.ID ";
//		
//		
//		
//		ArrayList<Automobile> listAutomobiles = automobileDaoMySQl.queryAboutAuto(sqlQuery);
//		listAutomobiles.stream().forEach((a) -> System.out.println(a.toString()));

//		queryObjectDemo.readAutomobile();
//		Automobile automobile = new Automobile(5,47000,"Audi",15,"2019-12-15",4,3,5,4);
//		automobileDaoMySQl.createAutomobile(null);
//		automobileDaoMySQl.updateAutomobile(automobile);
		
//		ManagerDaoMySql managerDaoMySql = new ManagerDaoMySql();
//		ArrayList<Manager> alManagers = managerDaoMySql.readAllManagers();
//		alManagers.stream().forEach((m) -> System.out.println(m.toString()));
//
//		ClientDaoMySql clientDaoMySql = new ClientDaoMySql();
////		clientDaoMySql.findClient(1);
//		ArrayList<Client> list = clientDaoMySql.readAllClients();
//		list.stream().forEach((c)-> System.out.println(c.toString()));
		new Server();
	}
}
