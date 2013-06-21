package data;

public class Userdata {
	int _id;
	int telephone;
	private String username;	
	private String password;	
	private String	email;	
	private String c_fname;	
	private String c_lname;
	private String ascesskey;
	public String get_ascesskey() {
		return ascesskey;
	}
	public void set_ascesskey(String ascesskey) {
		this.ascesskey = ascesskey;
	}
	public int get_id() 
	{
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getC_fname() {
		return c_fname;
	}
	public void setC_fname(String c_fname) {
		this.c_fname = c_fname;
	}
	public String getC_lname() {
		return c_lname;
	}
	public void setC_lname(String c_lname) {
		this.c_lname = c_lname;
	}
	
	
	

}
