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
	private String desc;

	public Move(String inName){

		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("data/outputmoves.txt"));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("RIP ");
		}


	}

}
