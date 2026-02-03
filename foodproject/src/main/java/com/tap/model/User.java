package com.tap.model;

import java.sql.Timestamp;

public class User {

    private int userId;   // Auto Increment from MySQL
    private String userName;
    private String email;
    private String phoneNumber;
    private String password;
    private String address;
    private String role;
    private Timestamp createdDate;
    private Timestamp lastLoginDate;

    public User()
    {
    	
    }

    // ✔ Constructor WITHOUT userId (because DB auto-generates it)
    public User(String userName, String email, String phoneNumber,
                String password, String address, String role) {

        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    // Getters & Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Timestamp getCreatedDate() { return createdDate; }
    public void setCreatedDate(Timestamp createdDate) { this.createdDate = createdDate; }

    public Timestamp getLastLoginDate() { return lastLoginDate; }
    public void setLastLoginDate(Timestamp lastLoginDate) { this.lastLoginDate = lastLoginDate; }

}





















/*package com.tap.model;

import java.sql.Timestamp;

public class User 
{
	    private int userId;
	    private String userName;
	    private String email;
	    private String phoneNumber;
	    private String password;
	    private String address;
	    private String role;
	    private Timestamp createdDate;
		private Timestamp lastLoginDate;
	    
	    public User()
	    {
	    	
	    }
	    
	    
	    
	    public User(int userId, String userName, String email, String phoneNumber, String password, String address,
				String role, Timestamp createdDate, Timestamp lastLoginDate)
	    {
			super();
			this.userId = userId;
			this.userName = userName;
			this.email = email;
			this.phoneNumber = phoneNumber;
			this.password = password;
			this.address = address;
			this.role = role;
			this.createdDate = createdDate;
			this.lastLoginDate = lastLoginDate;
		}



		public int getUserId() {
			return userId;
		}



		public void setUserId(int userId) {
			this.userId = userId;
		}



		public String getUsername() {
			return userName;
		}



		public void setUsername(String username) {
			this.userName = username;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}



		public String getPhoneNumber() {
			return phoneNumber;
		}



		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}



		public String getPassword() {
			return password;
		}



		public void setPassword(String password) {
			this.password = password;
		}



		public String getAddress() {
			return address;
		}



		public void setAddress(String address) {
			this.address = address;
		}



		public String getRole() {
			return role;
		}



		public void setRole(String role) {
			this.role = role;
		}



		public Timestamp getCreatedDate() {
			return createdDate;
		}



		public void setCreatedDate(Timestamp createdDate) {
			this.createdDate = createdDate;
		}



		public Timestamp getLastLoginDate() {
			return lastLoginDate;
		}



		public void setLastLoginDate(Timestamp lastLoginDate) {
			this.lastLoginDate = lastLoginDate;
		}



		@Override
		public String toString() {
			return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", phoneNumber="
					+ phoneNumber + ", password=" + password + ", address=" + address + ", role=" + role
					+ ", createdDate=" + createdDate + ", lastLoginDate=" + lastLoginDate + "]";
		}



		
	

}
*/
