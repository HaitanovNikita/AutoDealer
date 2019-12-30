package logic;

import tables.Manager;

import java.util.ArrayList;

public interface ManagerDao {

	public void createManager(Manager manager);
	public void updateManager(Manager manager);
	public void deleteManager(Manager manager);
	public Manager findManager(int idManager);
	public ArrayList<Manager> readAllManagers();
}
