package main.java.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.java.common.dto.MemberDTO;
import main.java.common.utill.DBManager;

public interface DBController {

	public default void insert(MemberDTO member) {
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close();
		}
	}

	default void select() {

	}

	default void delete() {
	}

	default void update(MemberDTO member) {
	}

	default boolean idCheck(String name) {
		ResultSet rs;
		try {
			Connection conn = DBManager.getConnection();

			String sql = "SELECT * FROM member WHERE user_id=" + "'" + name + "'";
			Statement stm = conn.prepareStatement(sql);

			rs = stm.executeQuery(sql);

			if (rs.next()) {
				// TODO 테이블 개수가 바뀌면 숫자도 바꿔야 해서 별로 안좋은 방법같음
				// 나중에 시간나면 다시 생각
				String s = rs.getString(3);
				if (s.equals(name)) {
					return true;
				} else
					return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
