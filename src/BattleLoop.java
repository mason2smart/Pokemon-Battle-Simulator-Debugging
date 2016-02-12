import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class BattleLoop {


	public void startBattle(){

		System.out.println("Welcome to the 2016 Pokemon Battle Simulator!!!");

//		Pokemon pok1 = new Pokemon("Volcarona", new String[]{"Bug","Fire"}, "Modest", 100, 85, 60, 65, 135, 105, 100, 31, 31, 31, 31, 31, 31, 72, 0, 0, 252, 0, 184);
//		Pokemon pok2 = new Pokemon("Garchomp", new String[]{"Dragon","Ground"}, "Adamant", 100, 108, 130, 95, 80, 85, 102, 31, 31, 31, 31, 31, 31, 4, 252, 0, 0, 0, 252);

		Pokemon pok1 = new Pokemon("Volcarona", "Modest", 72, 0, 0, 252, 0, 184);
		Pokemon pok2 = new Pokemon("Garchomp", "Adamant", 4, 252, 0, 0, 0, 252);

		//pok1.setStrMoves(new String[]{"Fire Blast","Ice Beam","Thunder","Hyper Beam"});
		pok1.setMoves(new String[]{"Fire Blast","Ice Beam","Thunder","Hyper Beam"});
		pok2.setMoves(new String[]{"Outrage","Earthquake","Stone Edge","Giga Impact"});


		pok1.statsToString();
		pok2.statsToString();

		battleDamage(pok1,0,pok2);
		battleDamage(pok1,1,pok2);
		battleDamage(pok1,2,pok2);
		battleDamage(pok1,3,pok2);

		battleDamage(pok2,0,pok1);
		battleDamage(pok2,1,pok1);
		battleDamage(pok2,2,pok1);
		battleDamage(pok2,3,pok1);
	}

	public void parseFile(){

		try {
			BufferedReader reader = new BufferedReader(new FileReader("data/moves.txt"));
			String currLine = null;
			PrintWriter output = new PrintWriter("data/outputmoves.txt");
			boolean hasPassed = false;
			boolean isFunction = false;
			int cnt = 0;

			while ((currLine = reader.readLine()) != null){


				String pattern = "\"\\w+\":"; //In case we are out of a function...
				Pattern p = Pattern.compile(pattern);
				Matcher m = p.matcher(currLine);

				if(m.find()){ //check to break
					//output.println(currLine);
					isFunction = false;
				}


				if(isFunction == false){
				pattern = "\\w+:"; //looks for naked JSON objects
				p = Pattern.compile(pattern);
				m = p.matcher(currLine);

				//System.out.println(currLine);

				while(m.find()){


					int s1 = m.start();
					int s2 = m.end() - 1;
					String str = currLine.substring(s1,s2);
					String begStr = currLine.substring(0,s1);
					String endStr = currLine.substring(s2);
					str = "\"" + str + "\"";
					currLine = begStr + str + endStr;
					//output.println(begStr + str + endStr);
					//output.println(s1 + "  " + s2);
					m = p.matcher(currLine);
				}

				//cnt++;

				//if(cnt < 18){

				pattern = "\\'\\w+\\'"; //this looks for single-quoted stuff
				p = Pattern.compile(pattern);
				m = p.matcher(currLine);

				if(m.find()){ //if found,
					int s1 = m.start() +1;
					int s2 = m.end() - 1;
					String str = currLine.substring(s1,s2);
					String begStr = currLine.substring(0,s1-1);
					String endStr = currLine.substring(s2+1);
					str = "\"" + str + "\"";

					currLine = begStr + str + endStr; //cut it out from line, replace it with null
					output.println(currLine);//push to output
				}

				pattern = "\\/\\/"; //this looks for double-slash commented out stuff
				p = Pattern.compile(pattern);
				m = p.matcher(currLine);

				if(m.find()){ //if found,
					int s1 = m.start();

					String begStr = currLine.substring(0,m.start());
					currLine = begStr;
				}

				pattern = "function"; //this looks for "function"
				p = Pattern.compile(pattern);
				m = p.matcher(currLine);

				if(m.find()){ //if found,
					String begStr = currLine.substring(0,m.start());
					currLine = begStr + "null,"; //cut it out from line, replace it with null
					output.println(currLine);//push to output
					isFunction = true;
				}

				pattern = "\"on\\w+\":"; //this looks for "on"
				p = Pattern.compile(pattern);
				m = p.matcher(currLine);

				if(m.find()){ //if "on" is found

					String begStr = currLine.substring(0,m.end()+1);
					currLine = begStr + "null,"; //cut it out from line, replace it with null
					output.println(currLine);//push to output

					isFunction = true;
					System.out.println(isFunction);
				}
				}


				if(isFunction == false){ //if we're in a function, don't print anything
					output.println(currLine);//}
				}

			}
			output.close();
			reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}



	public void battleDamage(Pokemon p1, int moveNum, Pokemon p2){


//		System.out.println(p1.getLevel() + " " + p1.getCurrentSpAtk() + " " + p2.getCurrentSpDef());
//		int damage = (int) ((((2 * p1.getLevel() + 10)/(double) 250) * ((double) p1.getCurrentSpAtk()/p2.getCurrentSpDef()) * 120 + 2) * 1.5);
//
//		System.out.println("Fire Blast does " + damage + " dmg to " + p2.getName() + " (type effectiveness not implemented.)");

		Move attack = p1.getMove(moveNum);

		double STAB = 1;

		for(String t : p1.getType()){
			if(t.equals(attack.getType())){ //If Pokemon type and move type are the same,
				STAB = 1.5; //give STAB
			}
		}

		double typeEff = checkTypeEffectiveness(p2.getType(), attack.getType());

		if(attack.getCategory().equals("Physical")){
			int damage = (int) (((((2 * p1.getLevel() + 10)/(double) 250) * ((double) p1.getCurrentAtk()/p2.getCurrentDef()) * attack.getBasePower() + 2) * STAB) * typeEff);
			System.out.println(attack.getName() + " does " + damage + " dmg to " + p2.getName() + " (type effectiveness implmented!)");

		}
		if(attack.getCategory().equals("Special")){
			int damage = (int) (((((2 * p1.getLevel() + 10)/(double) 250) * ((double) p1.getCurrentSpAtk()/p2.getCurrentSpDef()) * attack.getBasePower() + 2) * STAB) * typeEff);
			System.out.println(attack.getName() + " does " + damage + " dmg to " + p2.getName() + " (type effectiveness implmented!)");

		}

		if(typeEff == 0){
			System.out.println("It doesn't affect " + p2.getName() + "...");
		}
		if(typeEff == 0.5){
			System.out.println("It's not very effective...");
		}
		if(typeEff == 1){

		}
		if(typeEff == 2){
			System.out.println("It's super effective!");
		}
		if(typeEff == 4){
			System.out.println("It's ultra effective!");
		}

		//STAB, type effectiiveness, implemented. Critical, Random, and held items, abilities, and weather not included.
	}

	public double checkTypeEffectiveness(String[] pokeType, String moveType){

		double typeEff = 1;

		try {

			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("data/typechart.txt"));

			for(String type : pokeType){
				int val = (int) (long) ((JSONObject)((JSONObject) jsonObject.get(type)).get("damageTaken")).get(moveType);

				if(val == 0){

				}
				if(val == 1){
					typeEff = typeEff * 2;
				}
				if(val == 2){
					typeEff = typeEff * 0.5;
				}
				if(val == 3){
					typeEff = typeEff * 0;
				}

			}


		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return typeEff;
	}

}
