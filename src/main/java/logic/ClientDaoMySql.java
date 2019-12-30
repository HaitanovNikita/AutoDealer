package logic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import tables.Client;

import java.util.ArrayList;

public class ClientDaoMySql implements ClientDao {

	private SessionFactory factory;
	private Session session;

	public ClientDaoMySql() {
		factory = HibernateUtils.getSessionFactory();
		session = factory.getCurrentSession();
	}
	
	
	@Override
	public void createClient(Client client) {
		try {
		session.getTransaction().begin();
		session.persist(client);
		session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public void updateClient(Client client) {
		try {
			session.getTransaction().begin();
			session.update(client);
			session.getTransaction().commit();
			}catch(Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}
		
	}

	@Override
	public void deleteClient(Client client) {
		try {
			session.getTransaction().begin();
			session.delete(client);
			session.getTransaction().commit();
			}catch(Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}
		
	}

	@Override
	public Client findClient(int idClient) {
		Client client = null;
		try {
			session.getTransaction().begin();
			ArrayList<Client> alClients = (ArrayList<Client>) session.createQuery("Select c from Client as c where c.ID = "+ idClient).getResultList();
			if(alClients.isEmpty()!=true) {
				client = alClients.get(0);
				alClients.stream().forEach((c)-> System.out.println(c.toString()));
			}
			session.getTransaction().commit();
			}catch(Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}
		return client;
	}

	@Override
	public ArrayList<Client> readAllClients() {
		ArrayList<Client> alClients = null;
		try {
			session.getTransaction().begin();
			alClients = (ArrayList<Client>)session.createQuery("Select c from Client as c").getResultList();
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}

		return alClients;
	}

}
