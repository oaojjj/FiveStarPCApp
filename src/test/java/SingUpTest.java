package test.java;

import java.sql.SQLException;

import main.java.common.dao.DBController;
import main.java.common.dao.DBManager;
import main.java.common.dto.MemberDTO;

public class SingUpTest {
	public static void main(String[] args) throws SQLException {
		DBController dbController = DBManager.getInstance();
		dbController.insert(new MemberDTO("test", "test", "test", "test@test.com", 0));
	}
}
