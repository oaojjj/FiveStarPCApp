package main.java.common.dto;

import java.util.StringTokenizer;

public class FoodDTO {
	private String name;
	private String money;
	private String count;

	public FoodDTO(String n, String m, String c) {
		name = n;
		money = m;
		count = c;
	}

	public FoodDTO(String s) {
		StringTokenizer st = new StringTokenizer(s, "/");
		name = st.nextToken();
		money = st.nextToken();
		count = st.nextToken();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
