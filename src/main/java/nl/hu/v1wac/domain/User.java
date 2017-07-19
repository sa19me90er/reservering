package nl.hu.v1wac.domain;

public class User {

	private String username;
	private String password;
	private String role;


	public User(String uNm, String pw, String rl) {
		username = uNm;
		password = pw;
		role = rl;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

/*	public boolean checkPassword(String pw) {
		if (wachtwoord.equals(pw)) {
			return true;
		}

		return false;
	}
*/
	public String getRole() {
		return role;
	}
	
}