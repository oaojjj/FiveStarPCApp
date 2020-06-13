package main.java.common.utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.common.dao.DBController;
import main.java.common.dto.MemberDTO;

public class DBManager implements DBController {
	private final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	// 원래는 db이름까지만 추가하면 되지만 mysql 버전이 달라서 jdbc와 타임존의 시간표현 포맷이 달라서 인식을 못하기 때문에
	// 인식하도록 추가
	private final static String DB = "jdbc:mysql://localhost:3306/fivestarpc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	private final static String USER = "root";
	private final static String PASSWORD = "root123";

	private static Connection connection;
	private static DBManager dbManager;

	// 싱글톤 구현
	private DBManager() {
	}

	// 싱글톤 구현
	private static Connection getConnection() {
		if (connection == null) {
			// Statement state = null;
			try {
				Class.forName(JDBC_DRIVER);
				connection = DriverManager.getConnection(DB, USER, PASSWORD);
				// state = connection.createStatement();
				System.out.println("데이터베이스 연결 완료..");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	public static DBManager getInstance() {
		if (dbManager == null) {
			dbManager = new DBManager();
		}
		return dbManager;
	}

	public static void close() {
		if (connection != null) {
			try {
				if (!connection.isClosed())
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void insert(MemberDTO member) {
		try {
			Connection conn = DBManager.getConnection();

			String sql = "INSERT INTO member (name,user_id,password,email) VALUES (?,?,?,?)";
			PreparedStatement pstm = conn.prepareStatement(sql);

			pstm.setString(1, member.getName());
			pstm.setString(2, member.getId());
			pstm.setString(3, member.getPassword());
			pstm.setString(4, member.getEmail());

			int result = pstm.executeUpdate();
			if (result == 0) {
				System.out.println("데이터 입력 실패");
			} else {
				System.out.println("데이터 입력 성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close();
		}
	}

	@Override
	public MemberDTO select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(MemberDTO member) {
		// TODO Auto-generated method stub

	}

}
