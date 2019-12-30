/**
 * 
 */
package logic;

import tables.Automobile;

import java.util.ArrayList;

/**
 * @author User
 *
 */

public interface AutomobileDao {

	public ArrayList<Automobile> readAllAutomobiles();
	public ArrayList<Automobile> queryAboutAuto(String querySqlString);
	public void createAutomobile(Automobile automobile);
	public void updateAutomobile(Automobile automobile);
	public void deleteAutomobile(Automobile automobile);
}
