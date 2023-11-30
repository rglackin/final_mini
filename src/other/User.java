package other;


import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
		int newID = 1;
		try {
			File myObj = new File("userID.txt");
			if (!myObj.exists()) {
				myObj.createNewFile();
				BufferedWriter writer = new BufferedWriter(new FileWriter("userID.txt", true));
				writer.write("0");
				writer.close();
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("userID.txt", true));
			BufferedReader reader = new BufferedReader(new FileReader("userID.txt"));
			String line = reader.readLine();
			String prevLine = "0";
			while (line != null) {
				System.out.println(line);
				// read next line
				prevLine =line;
				line = reader.readLine();
				
			}
			
			newID += Integer.parseInt(prevLine);
			writer.append(String.format("\n%s", newID));
			writer.close();
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		setID(newID);
		// return id;
	}

	public void setUserInfo(String[] userInfo) {
		setNewID();
		try {
			File myObj = new File("userProfiles.txt");
			myObj.createNewFile();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("userProfiles.txt", true));
			//try {
				Scanner reader = new Scanner("userProfiles.txt");
				while (reader.hasNextLine()) {
					//writer.write("\n");
					reader.nextLine();

				}
				reader.close();
			//}// catch (IOException e) {
			//	e.printStackTrace();
			//}

			writer.write(getID() + " " + userInfo[0] + " " + userInfo[1]+ "\n") ;
			writer.close();
			setUsername(userInfo[0]);
			setPassword(userInfo[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// check details are same as in text file
	public boolean validLogIn(String[] userInfo) {
		try {
			File file = new File("userProfiles.txt");
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()) {
				String nextLine = reader.nextLine().trim();
				String[] infoArr = nextLine.split("[ ]");

				if (userInfo[0].equals(infoArr[1]) && userInfo[1].equals(infoArr[2])) {
					reader.close();
					setID(Integer.parseInt(infoArr[0]));
					setUsername(infoArr[1]);
					setPassword(infoArr[2]);
					return true;
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return false;
	}
}