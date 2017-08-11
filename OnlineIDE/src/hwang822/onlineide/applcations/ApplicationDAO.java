package hwang822.onlineide.applcations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

public class ApplicationDAO {
	
	public String sayHello(String name)
	{
		return "Hello " + name;
	}
	
	public Connection getConnection()
	{
		String CconnectionUrl = "jdbc:mysql://localhost:3306/onlineide";
		java.sql.Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(CconnectionUrl, "root", null);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	
	}
	
	public void colseConnection(Connection connection)
	{
		try{
			connection.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void create(Application application)
	{
		String sql = "insert into applications (name, price) values (?, ?)";
		Connection connection = getConnection();
		
		try {
			java.sql.PreparedStatement statment = connection.prepareStatement(sql);
			statment.setString(1, application.getName());
			statment.setDouble(2,  application.getPrice());
			statment.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		colseConnection(connection);
	}
	
	public void remote(int id)
	{
		String sql = "delete from applications where ID=?";
		Connection connection = getConnection();
		try{
			java.sql.PreparedStatement statemnt = connection.prepareStatement(sql);
			statemnt.setInt(1, id);
			statemnt.execute();
			
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			colseConnection(connection);
			
		}
		
	}

	public void update(int id, Application app)
	{
		String sql = "update applications set name=?, price=? where ID=?";		
		Connection connection = getConnection();
		try{
			java.sql.PreparedStatement statemnt = connection.prepareStatement(sql);
			statemnt.setString(1, app.getName());
			statemnt.setDouble(2, app.getPrice());
			statemnt.setInt(3, id);			
			statemnt.execute();
			
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			colseConnection(connection);
			
		}
		
	}
	
	public Application selctOne(int id)
	{
		Application app = null;
				
		String sql = "select * from applications where ID=?";
		
		Connection connection = getConnection();
		
		try{
			java.sql.PreparedStatement statemnt = connection.prepareStatement(sql);
			
			statemnt.setInt(1, id);
			ResultSet results = statemnt.executeQuery();
			if(results.next())
			{
				int idInt = results.getInt("ID");
				String name = results.getString("name");
				double price = results.getDouble("price");
				
				app = new Application(idInt, name, price);
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			colseConnection(connection);
			
		}
		return app;
	}
	
	
	public List<Application> selectAll()
	{
		List<Application> applcations = new ArrayList<Application>();
		
		String sql = "select * from applications";
		
		Connection connection = getConnection();
		try {
			java.sql.PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			
			while(results.next())
			{
				int id = results.getInt("ID");
				String name = results.getString("name");
				double price = results.getDouble("price");
				Application applcation = new Application(id, name, price);
				applcations.add(applcation);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			colseConnection(connection);
		}
		
		return applcations;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		
		ApplicationDAO dao = new ApplicationDAO();
		
		List<Application> applications = dao.selectAll();
		//for(Application app:applications)
		//{
		//	System.out.println(app.getName());
		//}
		
		//Connection connection = dao.getConnection();
		//Application app1 = new Application("Contact List", 3.99);
		//dao.create(app1);
		//dao.colseConnection(connection);
	}

}
