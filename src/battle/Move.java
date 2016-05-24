package battle;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Move {

	private String name;
	private String type;
	private String category; //physical or special
	private int basePower;
	private int pp;
	private int accuracyInt;
	private boolean accuracyBool;
	private String desc;

	private String jsonName;

	public Move(String inName){

		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("data/movestemp.txt"));

			name = inName;
			jsonName = name.toLowerCase();
			jsonName = jsonName.replace("-","").replace("[","").replace("]","").replace(" ","");

			type = (String) ((JSONObject) jsonObject.get(jsonName)).get("type");
			category = (String) ((JSONObject) jsonObject.get(jsonName)).get("category");
			basePower = (int) (long) ((JSONObject) jsonObject.get(jsonName)).get("basePower");
			pp = (int) (long) ((JSONObject) jsonObject.get(jsonName)).get("pp");
			desc = (String) ((JSONObject) jsonObject.get(jsonName)).get("desc");


		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("RIP ");
		}
	}

	public String getName(){
		return name;
	}

	public String getType(){
		return type;
	}

	public String getCategory(){
		return category;
	}

	public int getBasePower(){
		return basePower;
	}

	public int getPP(){
		return pp;
	}

	public String getDesc(){
		return desc;
	}

	public String toString(){

		String str = name + " " + type + " " + category + " " + basePower + " " + pp + " " + desc;
		return str;
	}

}
