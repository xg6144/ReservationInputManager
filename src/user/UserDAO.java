package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connect.DBConnection;

public class UserDAO {
	
	public static int SUCCESS = 1;
	public static int FAIL = -1;
	public static int NOT_EQUAL_PASSWORD = 2;
	private static UserDAO instance;
    private UserDAO(){
        //
    }
    
    public static UserDAO getInstance()
    {
    	if(instance == null) {
    		instance = new UserDAO();
    	}
    	return instance;
    }
    public int insertUser(String id, String pw, String name)
    {
    	int result = 0;
        //DB¿¬°á
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        String sql = "insert into users values(?, ?, ?) ";

        try{
            conn = DBConnection.getConnection();

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            pstmt.setString(3, name);

            pstmt.executeUpdate();
            result = SUCCESS;
            return result;
        }catch(Exception e)
        {
        	result = FAIL;  	
           e.printStackTrace();
            return result;
        }
        finally
        {
        	try {
        		if(conn != null) conn.close();
        		if(pstmt != null) pstmt.close();
        	}
        	catch(Exception e){
        		e.printStackTrace();
        
        	}
        }
    }
    
    public UserVO getUser(String id) {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	UserVO vo = null;
    	String sql = "select * from users where user_id = ?";
    	
    	try {
    		conn = DBConnection.getConnection();
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, id);
    		
    		rs = pstmt.executeQuery();
    		
    		while(rs.next()) {
    			vo = new UserVO();
    			vo.setId(rs.getString("user_id"));
    			vo.setPw(rs.getString("user_pw"));
    			vo.setName(rs.getString("user_name"));
    		}		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		try {
    			if(conn!=null) conn.close();
    			if(pstmt!=null) pstmt.close();
    			if(rs!=null) rs.close();
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	return vo;
    }
    public int loginUser(String id, String pw)
    {
    	int result = 0;
    	
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	String sql = "select user_pw from users where user_id = ?";
    	
    	try {
    		conn = DBConnection.getConnection();
    		
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, id);
    		
    		if(rs.next()) {
    			String user_pw = rs.getString("user_pw");
    			if(user_pw.equals(pw)) {
    				result = SUCCESS;
    			}else {
    				result = NOT_EQUAL_PASSWORD;
    			}
    		}else {
    			result = FAIL;
    		}
    		return result;
    	}catch(Exception e){
    		e.printStackTrace();
    		result = FAIL;
    		return result;
    	}finally {
    		try {
    			if(conn != null) conn.close();
    			if(pstmt!=null) pstmt.close();
    			if(rs!=null) rs.close();
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    
}
