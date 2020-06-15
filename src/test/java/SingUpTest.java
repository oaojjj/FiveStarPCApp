package test.java;

import java.sql.SQLException;

import main.java.common.dao.DBController;
import main.java.common.dto.MemberDTO;
import main.java.common.utill.DBManager;

public class SingUpTest {
	public static void main(String[] args) throws SQLException {
		DBController dbController = DBManager.getInstance();
		dbController.insert(new MemberDTO("test", "test", "test", "test@test.com"));
	}
}
