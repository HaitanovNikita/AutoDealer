package logic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import tables.Manager;

import java.util.ArrayList;

public class ManagerDaoMySql implements ManagerDao {
	
	private SessionFactory factory;
	private Session session;

	public ManagerDaoMySql() {
		factory = HibernateUtils.getSessionFactory();
		session = factory.getCurrentSession();
	}


	public void createManager(Manager manager) {
		try {
			session.getTransaction().begin();
			session.persist(manager);
			session.getTransaction().commit();
		}catch(Exception exception) {
			exception.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	public void updateManager(Manager manager) {
		try {
			session.getTransaction().begin();
			session.update(manager);
			session.getTransaction().commit();
		}catch(Exception exception) {
			exception.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	public void deleteManager(Manager manager) {
		try {
			session.getTransaction().begin();
			session.delete(manager);
			session.getTransaction().commit();
		}catch(Exception exception) {
			exception.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	public Manager findManager(int idManager) {
		Manager manager=null;
		try {
			session.getTransaction().begin();
			String querySql = "Select m from Manager as m where m.ID = "+ idManager;
			ArrayList<Manager> arrayListManagers = (ArrayList<Manager>) session.createQuery(querySql).getResultList();
			if(arrayListManagers.isEmpty()!=true) 
				manager=arrayListManagers.get(0);
			
			session.getTransaction().commit();
		}catch(Exception exception) {
			exception.printStackTrace();
			session.getTransaction().rollback();
		}
		return manager;
	}

	public ArrayList<Manager> readAllManagers() {
		ArrayList<Manager> arrayListManagers = null;
		try {
			session.getTransaction().begin();
			String querySql = "Select m from Manager as m";
			arrayListManagers = (ArrayList<Manager>) session.createQuery(querySql).getResultList();
			session.getTransaction().commit();
		}catch(Exception exception) {
			exception.printStackTrace();
			session.getTransaction().rollback();
		}	
		return arrayListManagers;
	}
	
	

}
