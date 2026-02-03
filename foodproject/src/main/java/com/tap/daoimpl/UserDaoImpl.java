package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.UserDao;
import com.tap.model.User;

public class UserDaoImpl implements UserDao {

    // DATABASE DETAILS
    private static final String URL = "jdbc:mysql://localhost:3306/food_application";
    private static final String USER = "root";
    private static final String PASSWORD = "Pradeep@8951501954";

    // QUERIES
    private static final String INSERT_USER =
            "INSERT INTO users (Username, Email, Phonenumber, Password, address, Role, CreatedDate, LastLoginDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_USER =
            "UPDATE users SET Username=?, Email=?, Password=?, address=? WHERE UserID=?";

    private static final String SELECT_ONE =
            "SELECT * FROM users WHERE UserID=?";

    private static final String DELETE_USER =
            "DELETE FROM users WHERE UserID=?";

    private static final String SELECT_ALL =
            "SELECT * FROM users";

    // FUNCTION TO GET CONNECTION
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // ADD USER
    @Override
    public void addUser(User user) {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, user.getUserName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPhoneNumber());
            pst.setString(4, user.getPassword());
            pst.setString(5, user.getAddress());
            pst.setString(6, user.getRole());
            pst.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            pst.setTimestamp(8, new Timestamp(System.currentTimeMillis()));

            int result = pst.executeUpdate();
            System.out.println(result > 0 ? "User Added!" : "Insert Failed!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GET ONE USER
    @Override
    public User getUser(int userId) {
        User user = null;

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ONE)) {

            pst.setInt(1, userId);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("UserID"));
                user.setUserName(rs.getString("Username"));
                user.setEmail(rs.getString("Email"));
                user.setPhoneNumber(rs.getString("Phonenumber"));
                user.setPassword(rs.getString("Password"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("Role"));
                user.setCreatedDate(rs.getTimestamp("CreatedDate"));
                user.setLastLoginDate(rs.getTimestamp("LastLoginDate"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    // UPDATE USER
    @Override
    public void updateUser(User user) {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_USER)) {

            pst.setString(1, user.getUserName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getAddress());
            pst.setInt(5, user.getUserId());

            int result = pst.executeUpdate();
            System.out.println(result > 0 ? "User Updated!" : "Update Failed!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE USER
    @Override
    public void deleteUser(int userId) {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_USER)) {

            pst.setInt(1, userId);

            int result = pst.executeUpdate();
            System.out.println(result > 0 ? "User Deleted!" : "No User Found!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GET ALL USERS
    @Override
    public List<User> getAllUser() {

        List<User> list = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("UserID"));
                user.setUserName(rs.getString("Username"));
                user.setEmail(rs.getString("Email"));
                user.setPhoneNumber(rs.getString("Phonenumber"));
                user.setPassword(rs.getString("Password"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("Role"));
                user.setCreatedDate(rs.getTimestamp("CreatedDate"));
                user.setLastLoginDate(rs.getTimestamp("LastLoginDate"));

                list.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public User loginUser(String email, String password) throws SQLException {

        Connection con = getConnection();

        String sql = "SELECT * FROM users WHERE Email=? AND Password=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            User user = new User();

            user.setUserId(rs.getInt("UserID"));
            user.setUserName(rs.getString("Username"));
            user.setEmail(rs.getString("Email"));
            user.setPhoneNumber(rs.getString("Phonenumber"));
            user.setPassword(rs.getString("Password"));
            user.setAddress(rs.getString("address"));
            user.setRole(rs.getString("Role"));
            user.setCreatedDate(rs.getTimestamp("CreatedDate"));
            user.setLastLoginDate(rs.getTimestamp("LastLoginDate"));

            return user;
        }

        return null;
    }
    
    @Override
    public UserDao.RegisterResult registerUser(User user) {

        // Ensure role is valid
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("Customer");
        }

        String sql = "INSERT INTO users (Username, Email, Phonenumber, Password, address, Role) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhoneNumber());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getRole());

            int rows = ps.executeUpdate();
            return rows > 0 ? UserDao.RegisterResult.SUCCESS : UserDao.RegisterResult.ERROR;

        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) { // duplicate email
                return UserDao.RegisterResult.EMAIL_EXISTS;
            }
            ex.printStackTrace(); // <-- check console for real SQL error
            return UserDao.RegisterResult.ERROR;
        }
    }

    
    
    
    
    
}












































/*package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.UserDao;
import com.tap.model.User;
import com.tap.util.DBConnection;

public class UserDaoImpl implements UserDao {

    private static final String INSERT_USER ="INSERT INTO users (Username, Email, Phonenumber, Password, address, Role, CreatedDate, LastLoginDate) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String UPDATE="UPDATE users SET Username=?, Email=?,Password=?,address=?  where UserID=?";

	private static final String Get_user = "SELECT * from users";
	
	private static final String DELETE_USER = "DELETE FROM users WHERE UserID = ?";
	
	
	

    @Override
    public void addUser(User user) 
    {

        try (Connection con = DBConnection.getConnection();
        		PreparedStatement pst = con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);) {

            pst.setString(1, user.getUserName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPhoneNumber());
            pst.setString(4, user.getPassword());
            pst.setString(5, user.getAddress());
            pst.setString(6, user.getRole());
            pst.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            pst.setTimestamp(8, new Timestamp(System.currentTimeMillis()));

            int result = pst.executeUpdate();

            if (result > 0)
                System.out.println("User inserted successfully!");
            else
                System.out.println("User not inserted!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    

    @Override
    public User getUser(int userId) 
    {
    	User user=null;
    	 try (Connection con = DBConnection.getConnection();
         		PreparedStatement pst = con.prepareStatement(Get_user);)
    	 {
               pst.setInt(1, userId); 
               ResultSet res=pst.executeQuery();
               while(res.next())
               {
            	   int id=res.getInt("UserID");
            	   String name=res.getString("userName");
            	   String Email=res.getString("Email");
            	   String Phonenumber=res.getString("Phonenumber");
            	   String Password=res.getString("Password");
            	   String address=res.getString(" address");
            	   String Role=res.getString("Role");
            	   
            	   user=new User(name,Email,Phonenumber,Password,address,Role);
               }
               

         } 
    	 catch (SQLException e) {
             e.printStackTrace();
         }
    	 
    	 return user;
    }
    
    
    
    


    @Override
    public void updateUser(User user) 
    {
    	 try (Connection con = DBConnection.getConnection();
         		PreparedStatement pst = con.prepareStatement(UPDATE);)
         {
    	   pst.setString(1, user.getUserName());
    	   pst.setString(2, user.getEmail());
    	   pst.setString(3, user.getPassword());
    	   pst.setString(4, user.getAddress());
    	   pst.setInt(5,  user.getUserId());
    	   
    	   int res=pst.executeUpdate();
    	   System.out.println(res);
    	   
         }
    	 catch(SQLException e)
    	 {
    		 e.printStackTrace();
    	 }
    }
    
    

    @Override
    public void deleteUser(int userId)
    {
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_USER)) {

            pst.setInt(1, userId);
            int affectedRows = pst.executeUpdate();

            if (affectedRows > 0) 
            {
                System.out.println("User deleted successfully.");
            } 
            else 
            {
                System.out.println("No user found with the given ID.");
            }

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    	
    }





@Override
public List<User> getAllUser()
{
	   List<User> users = new ArrayList<>();
	    String SELECT_ALL = "SELECT * FROM users";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement pst = con.prepareStatement(SELECT_ALL);
	         ResultSet rs = pst.executeQuery()) 
	       {

	        while (rs.next()) {
	            User user = new User();
	            user.setUserId(rs.getInt("UserID"));
	            user.setUserName(rs.getString("Username"));
	            user.setEmail(rs.getString("Email"));
	            user.setPhoneNumber(rs.getString("Phonenumber"));
	            user.setPassword(rs.getString("Password"));
	            user.setAddress(rs.getString("address"));
	            user.setRole(rs.getString("Role"));
	            user.setCreatedDate(rs.getTimestamp("CreatedDate"));
	            user.setLastLoginDate(rs.getTimestamp("LastLoginDate"));

	            users.add(user);
	        }

	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    }

	    return users;
	
    }
}
*/