import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Pokemon {

	private String name;
	private int num;
	private String[] type = new String[2];
	private String nature;
	private int level;
	private int[] baseStats = new int[6];
	private int[] IVs = new int[6];
	private int[] EVs = new int[6];
	private int[] stats = new int[6];
	private int[] currentStats;
	private int natureVal = 1; //temp int, until implement nature
	private int state; //1 is alive, 0 is fainted

	private String[] strMoves;
	private Move[] moves = new Move[4];
//	private int HP;
//	private int attack;
//	private int defense;
//	private int spAttack;
//	private int spDefense;
//	private int speed;

	public Pokemon(String nam, String nat, int hpEV, int atkEV, int defEV, int spAEV, int spDEV, int speEV){

		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(new FileReader("data/pokedex.txt"));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		name = nam;
		String jsonName = name.toLowerCase();
		type[0] = (String) ((JSONArray)((JSONObject) jsonObject.get(jsonName)).get("types")).get(0);
		type[1] = (String) ((JSONArray)((JSONObject) jsonObject.get(jsonName)).get("types")).get(1);
		//System.out.println(type[0] + "   " + type[1]);
		num = (int) (long) ((JSONObject) jsonObject.get(jsonName)).get("num");
		nature = nat;
		level = 100;
		state = 1;

		baseStats[0] = (int) (long) ((JSONObject)((JSONObject) jsonObject.get(jsonName)).get("baseStats")).get("hp");
		baseStats[1] = (int) (long) ((JSONObject)((JSONObject) jsonObject.get(jsonName)).get("baseStats")).get("atk");
		baseStats[2] = (int) (long) ((JSONObject)((JSONObject) jsonObject.get(jsonName)).get("baseStats")).get("def");
		baseStats[3] = (int) (long) ((JSONObject)((JSONObject) jsonObject.get(jsonName)).get("baseStats")).get("spa");
		baseStats[4] = (int) (long) ((JSONObject)((JSONObject) jsonObject.get(jsonName)).get("baseStats")).get("spd");
		baseStats[5] = (int) (long) ((JSONObject)((JSONObject) jsonObject.get(jsonName)).get("baseStats")).get("spe");

		for(int i = 0; i < IVs.length; i++){
			IVs[i] = 31;
		}

		EVs[0] = hpEV;
		EVs[1] = atkEV;
		EVs[2] = defEV;
		EVs[3] = spAEV;
		EVs[4] = spDEV;
		EVs[5] = speEV;


		calculateStats();
		currentStats = stats;
	}

	public Pokemon(String nam, String[] t, String nat, int lv, int hp, int atk, int def, int spA, int spD, int spe, int hpIV, int atkIV, int defIV, int spAIV, int spDIV, int speIV, int hpEV, int atkEV, int defEV, int spAEV, int spDEV, int speEV){

		name = nam;
		type = t;
		nature = nat;
		level = lv;

		baseStats[0] = hp;
		baseStats[1] = atk;
		baseStats[2] = def;
		baseStats[3] = spA;
		baseStats[4] = spD;
		baseStats[5] = spe;

		IVs[0] = hpIV;
		IVs[1] = atkIV;
		IVs[2] = defIV;
		IVs[3] = spAIV;
		IVs[4] = spDIV;
		IVs[5] = speIV;

		EVs[0] = hpEV;
		EVs[1] = atkEV;
		EVs[2] = defEV;
		EVs[3] = spAEV;
		EVs[4] = spDEV;
		EVs[5] = speEV;


		calculateStats();
		currentStats = stats;
		state = 1;
	}

	public void calculateStats(){

		stats[0] = (int) (((2 * baseStats[0] + IVs[0] + ((double) EVs[0]/4)) * level)/100) + level + 10;

		for(int i = 1; i < stats.length; i++){

			stats[i] = (int) ((((2 * baseStats[i] + IVs[i] + ((double) EVs[i]/4)) * level)/100) + 5) * natureVal;
		}


	}

	public void calculate(){


	}

	public String getName(){

		return name;

	}

	public String[] getType(){

		return type;

	}

	public String getNature(){

		return nature;
	}

	public int getLevel(){

		return level;

	}

	public int getHP(){

		return stats[0];

	}

	public int getAttack(){

		return stats[1];

	}

	public int getDefense(){

		return stats[2];

	}

	public int getSpAttack(){

		return stats[3];

	}

	public int getSpDefense(){

		return stats[4];

	}

	public int getSpeed(){

		return stats[5];

	}

	public int getCurrentHP(){

		return currentStats[0];

	}

	public int getCurrentAtk(){

		return currentStats[1];

	}

	public int getCurrentDef(){

		return currentStats[2];

	}

	public int getCurrentSpAtk(){

		return currentStats[3];

	}

	public int getCurrentSpDef(){

		return currentStats[4];

	}

	public int getCurrentSpe(){

		return currentStats[5];

	}

	public void setCurrentHP(int currHP){

		currentStats[0] = Math.max(0, currHP); //prevents from getting lower than 0
	}

	public void setStrMoves(String[] inMoves){

		strMoves = inMoves;
	}

	public void setMoves(String[] inMoves){

		for(int i = 0; i < moves.length; i++){
			moves[i] = new Move(inMoves[i]);
		}
	}

	public Move getMove(int num){

		return moves[num];

	}

	public String movesToString(){

		String movesStr = "";
		for(int i = 0; i < moves.length; i++){
			movesStr = movesStr + " (" + i + ") " + moves[i].getName();
		}

		return movesStr;

	}

	public int getState(){
		return state;
	}

	public void checkState(){

		if(getCurrentHP() == 0){
			System.out.println(name + " has fainted!");
			state = 0;
		}else{
			state = 1;
		}

	}

	public void statsToString(){

		for(int i : stats){
		System.out.print(i + " ");}
		System.out.println();
	}
}
