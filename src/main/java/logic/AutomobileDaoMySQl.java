package logic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import tables.Automobile;

import java.util.ArrayList;

public class AutomobileDaoMySQl implements AutomobileDao {

	private SessionFactory factory;
	private Session session;

	public AutomobileDaoMySQl() {
		factory = HibernateUtils.getSessionFactory();
		session = factory.getCurrentSession();
	}

	public ArrayList<Automobile> readAllAutomobiles() {
		//TODO дописать машины которые есть в наличии! 
		ArrayList<Automobile> arrayListAutomobiles = null;
		try {
			session.getTransaction().begin();

			String sqlQuery = "Select " + " a.id, a.car_price, a.car_make, a.year_issue_car, "
					+ " p.horse_power, m.name_model, e.type_engine, c.color_car, t.type_body  "
					+ "	from Automobile as a " + "	inner join ModelCar as m on a.model_car = m.id "
					+ "	inner join PowerCar p on a.power_car = p.ID "
					+ " inner join EngineCar e on a.engine_car = e.ID "
					+ " inner join ColorCar c on a.color_car = c.ID "
					+ " inner join TypeCarBody  t on a.type_car_body = t.ID";

			Query<Object[]> query = session.createQuery(sqlQuery);
			ArrayList<Object[]> datasList = (ArrayList<Object[]>) query.getResultList();
			arrayListAutomobiles = convertToAutomobiles(datasList);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return arrayListAutomobiles;
	}

	public ArrayList<Automobile> queryAboutAuto(String querySqlString) {
		ArrayList<Automobile> arrayListAutomobiles = null;
		try {
			session.getTransaction().begin();
			ArrayList<Object[]> datasList = (ArrayList<Object[]>)session.createQuery(querySqlString).getResultList();
			arrayListAutomobiles = convertToAutomobiles(datasList);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return arrayListAutomobiles;
		
	}


	
	
	public void createAutomobile(Automobile automobile) {
		try {
			session.getTransaction().begin();
			session.persist(automobile);
			session.getTransaction().commit();
		}catch(Exception e ) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	public void updateAutomobile(Automobile automobile) {
		try {
			session.getTransaction().begin();
			session.update(automobile);
			session.getTransaction().commit();
		}catch(Exception exception ) {
			exception.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
	public void deleteAutomobile(Automobile automobile) {
		try {
			session.getTransaction().begin();
			session.delete(automobile);
			session.getTransaction().commit();
			
		}catch(Exception exception) {
		exception.printStackTrace();
		session.getTransaction().rollback();
		}
	}

	private ArrayList<Automobile> convertToAutomobiles(ArrayList<Object[]> datasList) {
	if (datasList.isEmpty() == true)
		throw new IllegalArgumentException("Collection is empty! AutomobileDaoMuSQl/createAutomobile()");
	
		ArrayList<Automobile> arrayListAutomobiles = new ArrayList<Automobile>();
		
		try {
			for (Object[] autoArr : datasList) {
				arrayListAutomobiles.add(
						new Automobile(
								Integer.valueOf(autoArr[0].toString()),
								(long) Integer.valueOf(autoArr[1].toString()),
								autoArr[2].toString(),
								autoArr[3].toString(),
								Integer.valueOf(autoArr[4].toString()),
								autoArr[5].toString(),
								autoArr[6].toString(),
								autoArr[7].toString(),
								autoArr[8].toString()));
			}
		} catch (RuntimeException exception) {
			exception.printStackTrace();
		}
	return arrayListAutomobiles;
	}

	
	

	

}

/*
 * Select Automobile.car_make, ModelCar.name_model, year_issue_car,
 * PowerCar.horse_power, EngineCar.type_engine, ColorCar.color_car,
 * TypeCarBody.type_body from Automobile inner join ModelCar on
 * Automobile.model_car = ModelCar.ID inner join PowerCar on
 * Automobile.power_car = PowerCar.ID inner join EngineCar on
 * Automobile.engine_car = EngineCar.ID inner join ColorCar on
 * Automobile.color_car = ColorCar.ID inner join TypeCarBody on
 * Automobile.type_car_body= TypeCarBody.ID
 * 
 * 
 * public ArrayList<Automobile> readAutomobile() {
		ArrayList<Automobile> arrayListAutomobiles = null;
		try {
			session.getTransaction().begin();
			String sql = "Select a from " + Automobile.class.getName() + " a ";

			Query<Automobile> query = session.createQuery(sql);

			arrayListAutomobiles = (ArrayList<Automobile>) query.getResultList();
			for (Automobile automobile : arrayListAutomobiles) {
				System.out.println(automobile.toString());
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return arrayListAutomobiles;
	}
 */
