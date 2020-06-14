package main.java.common.dto;

public class MemberDTO {
	String name;
	String id;
	String password;
	String email;
	int saveTime;

	public MemberDTO() {

	}

	public MemberDTO(String name, String id, String password, String email) {
		this.name = name;
		this.id = id;
		this.password = password;
		this.email = email;
		saveTime = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSaveTime() {
		return saveTime;
	}

	public void setSaveTime(int saveTime) {
		this.saveTime = saveTime;
	}

}
