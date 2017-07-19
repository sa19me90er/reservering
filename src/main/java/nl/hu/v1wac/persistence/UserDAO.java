
package nl.hu.v1wac.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import nl.hu.v1wac.domain.User;

	
	public class UserDAO extends BaseDAO {
		public String findRoleForUsernameAndPassword(String username, String password) {
		String role = null;
		String query = "SELECT role FROM useraccount WHERE username = ? AND password = ?";
		try (Connection con = super.getConnection()) {
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
		role = rs.getString("role");
		} catch (SQLException sqle) {
		sqle.printStackTrace();
		}
		return role;
		}
	}
	
	// SelectUser functie
		/*		
	private ArrayList<User> selectUser(String query) {
		ArrayList<User> results = new ArrayList<User>();

		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				String username = dbResultSet.getString("username");
				String password = dbResultSet.getString("password");
				String role = dbResultSet.getString("role");
			

				results.add(new User(username, password, role));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}
	} **/

/*	// Save statement (Save country in Database)
	public void Save(User user) {
		String query = "INSERT INTO public.gebruikers(gebruikersnaam, wachtwoord, email, naam) VALUES ('" + user.getGebruikersnaam()
				+ "', '" + user.getWachtwoord() + "', '" + user.getEmail() + "', '" + user.getNaam() + "');";
		System.out.println(query);

		try (Connection con = getConnection()) {
			Statement stmt = con.createStatement();
			if (stmt.executeUpdate(query) == 1) { // 1 row updated!
				// Succesvol geupdate
				System.out.println("Succesvol gesaved");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}
*/
/*
	// Find all()
	public ArrayList<User> findAll() {
		return selectUser("SELECT * FROM gebruikers");
	}

	// Find by naam
	public User findByNaam(String name) {
		return selectUser("SELECT gebruikersnaam, wachtwoord, email, naam FROM public.gebruikers WHERE naam = " + name).get(0);
	}
	
}

/*	// +update(country : Country) : Country
	public void update(User user) {
		boolean userExists = findByNaam(user.getNaam()) != null;
		String query = "UPDATE public.gebruikers SET gebruikersnaam=" + user.getGebruikersnaam() +",wachtwoord=" +user.getWachtwoord()+ ",naam=" + user.getNaam()
				+ ", email = " + user.getEmail();

		if (userExists) {
			try (Connection con = getConnection()) {
				Statement stmt = con.createStatement();
				if (stmt.executeUpdate(query) == 1) { // 1 row updated!
					// Succesvol geupdate
					System.out.println("Succesvol geupdate");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	// +delete(country : Country) : boolean
	public boolean delete(User user) {
		boolean result = false;
		boolean countryExists = findByNaam(user.getNaam()) != null;

		if (countryExists) {
			String query = "DELETE * FROM gebruikers WHERE naam = " + user.getNaam();

			try (Connection con = getConnection()) {

				Statement stmt = con.createStatement();
				if (stmt.executeUpdate(query) == 1) { // 1 row updated!
					// Succesvol gedelete
					result = true;
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

		return result;
	}
}

*/




/**public class UserDAO extends BaseDAO {
	public String findRoleForUsernameAndPassword(String username, String password) {
		String role = null;
		String query = "SELECT role FROM useraccount WHERE username = ? AND password = ?";
		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				role = rs.getString("role");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return role;
	}

public List<User> findAll() {
		return selectUsers("SELECT * FROM gebruikers");
	}
}*/



