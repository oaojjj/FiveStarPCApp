package main.java.common.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.common.dto.MemberDTO;

public class DBManager implements DBController {
	private final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	// 원래는 db이름까지만 추가하면 되지만 mysql 버전이 달라서 jdbc와 타임존의 시간표현 포맷이 달라서 인식을 못하기 때문에
	// 인식하도록 추가
	private final static String DB = "jdbc:mysql://pcbangtuto1.iptime.org:3306/fivestarpc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	private final static String USER = "root";
	private final static String PASSWORD = "root";

	private static Connection connection;
	private static DBManager dbManager;

	// 싱글톤 구현
	private DBManager() {
	}

	public static Connection getConnection() {
		if (connection == null) {
			// Statement state = null;
			try {
				// 드라이버 로딩
				Class.forName(JDBC_DRIVER);
				// 데이터베이스 연결
				connection = DriverManager.getConnection(DB, USER, PASSWORD);
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

}
