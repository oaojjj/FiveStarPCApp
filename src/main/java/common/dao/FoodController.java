package main.java.common.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import main.java.common.dto.FoodDTO;

public class FoodController {
	private static FoodController foodconn;
	private ArrayList<FoodDTO> food;
	private boolean flag = true;

	private FoodController() {
		food = new ArrayList<>();
	}

	public static FoodController edit() {
		if (foodconn == null) {
			foodconn = new FoodController();
		}
		return foodconn;
	}

	public void readFile() throws IOException {
		if (flag) {
			BufferedReader in = new BufferedReader(new FileReader(new File("database/food.txt")));
			String line;
			while ((line = in.readLine()) != null) {
				addFood(line);
			}
		}
		flag = false;
	}

	public void addFood(FoodDTO dto) {
		food.add(dto);
	}

	public void addFood(String s) {
		StringTokenizer st = new StringTokenizer(s, "/");

		String name = st.nextToken();
		String money = st.nextToken();
		String count = st.nextToken();

		food.add(new FoodDTO(name, money, count));
	}

	public int changeFood(FoodDTO f) {
		for (int i = 0; i < food.size(); i++) {
			if (food.get(i).getName().equals(f.getName())) {
				food.set(i, f);
				return i;
			}

		}
		return -1;
	}

	public int deleteFood(String f) {
		for (int i = 0; i < food.size(); i++) {
			if (food.get(i).getName().equals(f)) {
				food.remove(i);
				return i;
			}
		}
		return -1;
	}

	public FoodDTO getFood(String name) {
		for (int i = 0; i < food.size(); i++) {
			if (food.get(i).getName().equals(name))
				return food.get(i);
		}
		return null;
	}

	public ArrayList<FoodDTO> getFoodList() {
		return food;
	}

	public void setFood(ArrayList<FoodDTO> food) {
		this.food = food;
	}

}
