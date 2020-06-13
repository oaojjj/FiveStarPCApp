package main.java.common.dao;

import main.java.common.dto.MemberDTO;

public interface DBController {
	void insert(MemberDTO member);

	MemberDTO select();

	void delete();

	void update(MemberDTO member);
}
