
public class User {
	private String firstName, lastName, userName, passWord, userType;
	private int id;
	
	public User(String firstName, String lastName, String userName, String passWord, String userType, int id) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.passWord = passWord;
		this.userType = userType;
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String toString(){
		return String.format("User [firstName=%s, lastName=%s, userName=%s, passWord=%s, userType=%s, id=%s]", firstName, lastName, userName, passWord, userType, id);
	}
	
}
