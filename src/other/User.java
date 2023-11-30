package other;


public class User {
	private String username, password;
	private int id;

	public int getID() {

		return this.id;
	}

	public void setID(int value) {
		this.id = value;
	}

	public String getUsername() {
		return username;
	}

	private void setUsername(String value) {
		this.username = value;
	}

	public String getPassword() {
		return password;
	}

	private void setPassword(String value) {
		this.password = value;
	}

	public void setNewID() {
		// create query to select the last created user ID
		String query = "SELECT user_id FROM user ORDER BY user_id DESC LIMIT 1;";
		// uses lambda function to return single ID as int
		int newID = DAL.SelectQuery(query, rset -> {
			// finds row in returned table
			if (rset.next()) {
				// returns ID from row
				return rset.getInt("user_id");
			} else {
				return null;
			}
		});
		setID(newID);
	}

	public boolean setUserInfo(String[] userInfo) {
		String query = String.format("INSERT INTO user (username, password) VALUES ('%s','%s');", userInfo[0],
				userInfo[1]);
		if (!DAL.InsertQuery(query)) {
			return false;
		}
		setUsername(userInfo[0]);
		setPassword(userInfo[1]);
		setNewID();
		return true;
	}

	// check details are same as in text file
	public boolean validLogIn(String[] userInfo) {
		
		String query = String.format("SELECT user_id FROM user WHERE username = '%s' and password = '%s';",userInfo[0],userInfo[1]);
		
		boolean loginValid = DAL.SelectQuery(query, rset->{
			if(rset.next()){
				int userID = rset.getInt("user_id");
				setID(userID);
				return true;
			}
			else{	

				return false;
			}
		});
		setUsername(userInfo[0]);
		setPassword(userInfo[1]);
		return loginValid;
	}
}