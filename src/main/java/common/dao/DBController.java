package main.java.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

import main.java.common.dto.MemberDTO;

public interface DBController {

	public default void insert(MemberDTO member) throws SQLException {
		Connection conn = DBManager.getConnection();

		// 쿼리문 작성
		String sql = "INSERT INTO member (name,user_id,password,email) VALUES (?,?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);

		// 데이터 바인딩
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
	}

	// 회원정보 이름으로 조회
	default MemberDTO selectName(String name) throws SQLException {
		ResultSet rs;
		Statement stm;
		Connection conn = DBManager.getConnection();

		String sql = "SELECT * FROM member WHERE user_id=" + "'" + name + "'";

		stm = conn.createStatement();

		rs = stm.executeQuery(sql);

		if (rs.next()) {
			String userName = rs.getString(2);
			String userID = rs.getString(3);
			String userPassword = rs.getString(4);
			String userEmail = rs.getString(5);
			String saveTime = rs.getString(6);
			return new MemberDTO(userName, userID, userPassword, userEmail, Integer.parseInt(saveTime));
		}
		return null;
	}

	// 회원정보 아이디로 조회
	default MemberDTO selectId(String id) throws SQLException {
		ResultSet rs;
		Statement stm;
		Connection conn = DBManager.getConnection();

		String sql = "SELECT * FROM member WHERE user_id=" + "'" + id + "'";

		stm = conn.createStatement();

		rs = stm.executeQuery(sql);

		if (rs.next()) {
			String name = rs.getString(2);
			String userID = rs.getString(3);
			String userPassword = rs.getString(4);
			String userEmail = rs.getString(5);
			String saveTime = rs.getString(6);
			return new MemberDTO(name, userID, userPassword, userEmail, Integer.parseInt(saveTime));
		}
		return null;
	}

	// 회원삭제할 때 구현
	default void delete() {
	}

	// 회원정보 업데이트 할 때?
	default void update(MemberDTO member) {
	}

	// 사용자가 로그인 시도할 때 회원 아이디가 존재하는지 체크 메소드
	static boolean checkID(String name) throws SQLException {
		ResultSet rs;
		Connection conn = DBManager.getConnection();

		String sql = "SELECT * FROM member WHERE user_id=" + "'" + name + "'";
		Statement stm = conn.prepareStatement(sql);

		rs = stm.executeQuery(sql);

		// 중복일 경우
		if (rs.next()) {
			return false;
		}
		return true;
	}

	static boolean checkLogin(String id, String password) throws SQLException {
		ResultSet rs;

		Connection conn = DBManager.getConnection();

		String sql = "SELECT * FROM member WHERE user_id=" + "'" + id + "'";
		Statement stm = conn.prepareStatement(sql);

		rs = stm.executeQuery(sql);

		if (rs.next()) {
			// TODO 비밀번호는 암호화 같은거를 해야할듯
			String userId = rs.getString(3);
			String userPassword = rs.getString(4);
			if (id.equals(userId) && password.equals(userPassword)) {
				return true;
			} else
				return false;
		}
		return false;
	}

	// 사용자가 컴퓨터를 종료하거나 사용을 중지할 때 남은 시간 세이브 메소드
	static void saveTime(String id, int time) throws SQLException {
		// int rows;

		Connection conn = DBManager.getConnection();

		String sql = "UPDATE member SET save_time=" + "'" + time + "'" + " WHERE user_id=" + "'" + id + "'";
		Statement stm = conn.prepareStatement(sql);

		int r = stm.executeUpdate(sql);
		System.out.println(r);
	}

}
