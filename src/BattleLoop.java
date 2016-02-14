import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class BattleLoop {


	boolean isBattle;
	Team t1;
	Team t2;


	public void startBattle(){

		System.out.println("Welcome to the 2016 Pokemon Battle Simulator!!!");

//		Pokemon pok1 = new Pokemon("Volcarona", new String[]{"Bug","Fire"}, "Modest", 100, 85, 60, 65, 135, 105, 100, 31, 31, 31, 31, 31, 31, 72, 0, 0, 252, 0, 184);
//		Pokemon pok2 = new Pokemon("Garchomp", new String[]{"Dragon","Ground"}, "Adamant", 100, 108, 130, 95, 80, 85, 102, 31, 31, 31, 31, 31, 31, 4, 252, 0, 0, 0, 252);

//		Pokemon pok1_1 = new Pokemon("Volcarona", "Modest", 72, 0, 0, 252, 0, 184);
//		Pokemon pok1_2 = new Pokemon("Dragonite", "Adamant", 4, 252, 0, 0, 0, 252);
//		Pokemon pok1_3 = new Pokemon("Chandelure", "Modest", 4, 0, 0, 252, 0, 252);
//		Pokemon pok1_4 = new Pokemon("Lucario", "Adamant", 72, 252, 0, 0, 184, 0);
//		Pokemon pok1_5 = new Pokemon("Starmie", "Modest", 4, 0, 0, 252, 0, 184);
//		Pokemon pok1_6 = new Pokemon("Scizor", "Jolly", 72, 252, 0, 0, 0, 184);
//
//
		Pokemon pok2_1 = new Pokemon("Garchomp", "Adamant", 4, 252, 0, 0, 0, 252);
		Pokemon pok2_2 = new Pokemon("Salamence", "Adamant", 4, 252, 0, 0, 0, 252);
		Pokemon pok2_3 = new Pokemon("Dragonite", "Adamant", 4, 252, 0, 0, 0, 252);
		Pokemon pok2_4 = new Pokemon("Gyarados", "Adamant", 4, 252, 0, 0, 0, 252);
		Pokemon pok2_5 = new Pokemon("Hydreigon", "Adamant", 4, 252, 0, 0, 0, 252);
		Pokemon pok2_6 = new Pokemon("Rayquaza", "Adamant", 4, 252, 0, 0, 0, 252);
//
//		pok1_1.setMoves(new String[]{"Fire Blast","Bug Buzz","Giga Drain","Hyper Beam"});
//		pok1_2.setMoves(new String[]{"Outrage","Earthquake","Fire Punch","Brick Break"});
//		pok1_3.setMoves(new String[]{"Overheat","Shadow Ball","Hidden Power Ice","Energy Ball"});
//		pok1_4.setMoves(new String[]{"Drain Punch","Mach Punch","Ice Punch","ThunderPunch"});
//		pok1_5.setMoves(new String[]{"Hydro Cannon","Ice Beam","Thunder","Hyper Beam"});
//		pok1_6.setMoves(new String[]{"Iron Head","Bug Bite","Bullet Punch","Superpower"});
//
		pok2_1.setMoves(new String[]{"Outrage","Earthquake","Stone Edge","Giga Impact"});
		pok2_2.setMoves(new String[]{"Outrage","Earthquake","Stone Edge","Fire Fang"});
		pok2_3.setMoves(new String[]{"Outrage","Extremespeed","Fire Punch","Aeroblast"});
		pok2_4.setMoves(new String[]{"Waterfall","Sky Attack","Thrash","Giga Impact"});
		pok2_5.setMoves(new String[]{"Draco Meteor","Flamethrower","Earth Power","Hyper Beam"});
		pok2_6.setMoves(new String[]{"Outrage","Earthquake","Stone Edge","V-Create"});
//
		t1 = new Team();
//		t1.addPokemon(pok1_1);
//		t1.addPokemon(pok1_2);
//		t1.addPokemon(pok1_3);
//		t1.addPokemon(pok1_4);
//		t1.addPokemon(pok1_5);
//		t1.addPokemon(pok1_6);
//
		t2 = new Team();
		t2.addPokemon(pok2_1);
		t2.addPokemon(pok2_2);
		t2.addPokemon(pok2_3);
		t2.addPokemon(pok2_4);
		t2.addPokemon(pok2_5);
		t2.addPokemon(pok2_6);
//
//		pok1_1.statsToString();
//		pok2_1.statsToString();
//
//		battleDamage(pok1,0,pok2);
//		battleDamage(pok1,1,pok2);
//		battleDamage(pok1,2,pok2);
//		battleDamage(pok1,3,pok2);
//
//		battleDamage(pok2,0,pok1);
//		battleDamage(pok2,1,pok1);
//		battleDamage(pok2,2,pok1);
//		battleDamage(pok2,3,pok1);

		isBattle = true;

		parsePokemonFile();
	}

	public void battle(){

		Pokemon activePok1 = t1.getPokemon(0); //Grab the first Pokemon in each team
		Pokemon activePok2 = t2.getPokemon(0);

		System.out.println("Foe sent out " + activePok2.getName() + "!");
		System.out.println("Go, " + activePok1.getName() + "!");

		while(isBattle){

			System.out.println("What will " + activePok1.getName() + " do?");
			System.out.println("Moves: " + activePok1.movesToString());
			System.out.println("Enter move number | Press 4 to Switch");
			Scanner sc = new Scanner(System.in);
			int moveNum1 = Integer.valueOf(sc.nextLine()); //Get move decision/Decision to switch
			int moveNum2 = new Random().nextInt(4);
			boolean playerFirst = false; //controls for speed tie, without affecting Pokemon speeds
			boolean isAttacking = true; //Assume we are attacking, unless we're switching

			if(moveNum1 == 4){ //if switching out
				isAttacking = false; //Not attacking
				System.out.println("Select a new Pokemon");
				System.out.println(t1.toString());
				int pokeNum = Integer.valueOf(sc.nextLine()); //Grab Pokemon
				boolean isSamePokemon = t1.getPokemon(pokeNum).equals(activePok1); //check if same Pokemon
				while(t1.getPokemon(pokeNum).getState() == 0 || pokeNum > 5 || isSamePokemon){ //If fainted or same
					if(isSamePokemon){ //If it's the same
						System.out.println(t1.getPokemon(pokeNum).getName() + " is already in battle!");
						pokeNum = Integer.valueOf(sc.nextLine());
						isSamePokemon = t1.getPokemon(pokeNum).equals(activePok1); //check again for next while cycle
					}else{ //else if it's fainted,
						System.out.println(t1.getPokemon(pokeNum).getName() + " is unable to battle!");
						pokeNum = Integer.valueOf(sc.nextLine()); //grab new Pokemon
					}

				}
				activePok1 = t1.getPokemon(pokeNum); //Finally, switch in valid Pokemon
				System.out.println("Go, " + activePok1.getName() + "!");
				activePok1.setCurrentHP(activePok1.getCurrentHP() - battleDamage(activePok2,moveNum2,activePok1)); //Get hit by opponent
				activePok1.checkState(); //Check if fainted
			}

			if(isAttacking){ //If we are attacking,
			if(activePok1.getCurrentSpe() == activePok2.getCurrentSpe()){ //If speed tie,
				if(new Random().nextInt(2) == 0){ //Random decide who goes first
					playerFirst = true;
				}else{

				}
			}

			if(activePok1.getCurrentSpe() > activePok2.getCurrentSpe() || playerFirst == true){ //If Pokemon1 is faster

				activePok2.setCurrentHP(activePok2.getCurrentHP() - battleDamage(activePok1,moveNum1,activePok2)); //P1 attacks!
				activePok2.checkState(); //Check if fainted, or still active
				if(activePok2.getState() == 1){ //if still active
					activePok1.setCurrentHP(activePok1.getCurrentHP() - battleDamage(activePok2,moveNum2,activePok1)); //P2 attacks!
					activePok1.checkState(); //Check if fainted, or still active
				}
			}else{ //activePok1.getCurrentSpe() < activePok2.getCurrentSpe() || playerFirst == false

				activePok1.setCurrentHP(activePok1.getCurrentHP() - battleDamage(activePok2,moveNum2,activePok1));
				activePok1.checkState();
				if(activePok1.getState() == 1){
					activePok2.setCurrentHP(activePok2.getCurrentHP() - battleDamage(activePok1,moveNum1,activePok2));
					activePok2.checkState();
				}
			}
			}//if isAttacking end bracket


			if(activePok1.getState() == 0 && !t1.isFaintedTeam()){ //if active Pokemon is knocked out, and Pokemon left
				System.out.println("Select a new Pokemon, by number"); //Can switch to new Poke!
				System.out.println(t1.toString());
				int pokeNum = Integer.valueOf(sc.nextLine());
				while(t1.getPokemon(pokeNum).getState() == 0 || pokeNum > 5){ //While not fainted or over the boundaries,
					System.out.println(t1.getPokemon(pokeNum).getName() + " is unable to battle!");
					pokeNum = Integer.valueOf(sc.nextLine());
				}
				activePok1 = t1.getPokemon(pokeNum); //Switch Pokemon in.
				System.out.println("Go, " + activePok1.getName() + "!");
			}

			if(activePok2.getState() == 0 && !t2.isFaintedTeam()){ //Same for above, simply inversed for opponent
				System.out.println("Foe is thinking over his next Pokemon...");
				int pokeNum = new Random().nextInt(6);
				while(t2.getPokemon(pokeNum).getState() == 0){
					System.out.println(t2.getPokemon(pokeNum).getName() + " is unable to battle!");
					pokeNum = new Random().nextInt(6);
				}
				activePok2 = t2.getPokemon(pokeNum);
				System.out.println("Foe sent out " + activePok2.getName() + "!");
			}



			if(t1.isFaintedTeam()){ //If all team fainted,
				isBattle = false;
				System.out.println("Player whited out!"); //RIP in pepperoni
			}

			if(t2.isFaintedTeam()){
				isBattle = false;
				System.out.println("Player defeated Trainer!"); //gg no re
			}

		}


	}

	public void parsePokemonFile(){

		try {
			BufferedReader reader = new BufferedReader(new FileReader("data/team.txt"));
			String currLine = "";
			//currLine = reader.readLine();
			//while((currLine = reader.readLine()) != null){
			for(int j = 0; j < 6; j++){ //Hardcoding for 6 Pokemon being read

				currLine = reader.readLine(); //is typically above while statement

				int pos = currLine.indexOf('@'); //First line, look for @
				String name = currLine.substring(0,pos).trim(); //parse for name
				String item = currLine.substring(pos+1).trim(); //parse for item
				int[] EVs = new int[6];
				currLine = reader.readLine(); //Next line, "Ability: ______"
				pos = currLine.indexOf(':');
				String ability = currLine.substring(pos+1).trim(); //Parse ability from ":" on
				currLine = reader.readLine(); //Next line, EVs
				currLine = currLine.substring(currLine.indexOf(':')+1); //Get string after EVs:
				while(currLine.indexOf('/') != -1){ //while there are "/"s

					String temp = currLine.substring(0,currLine.trim().indexOf('/')).trim(); //Parse string with EV and typeEV
					int EV = Integer.valueOf(temp.substring(0,temp.indexOf(' ')).trim());
					String typeEV = temp.substring(temp.indexOf(' ')).trim();
					//System.out.println(typeEV + " eee");
					if(typeEV.equals("HP")){EVs[0] = EV;}
					if(typeEV.equals("Atk")){EVs[1] = EV;}
					if(typeEV.equals("Def")){EVs[2] = EV;}
					if(typeEV.equals("SpA")){EVs[3] = EV;}
					if(typeEV.equals("SpD")){EVs[4] = EV;}
					if(typeEV.equals("Spe")){EVs[5] = EV;}

					currLine = currLine.substring(currLine.indexOf('/')+1); //Cut off already parsed part of EV String
				}

				//Parse last section, which has no "/"

//				String temp = currLine.substring(0,currLine.indexOf('/')).trim();
				//System.out.println((currLine.trim()).indexOf(' '));
				//System.out.println(currLine.trim().substring((currLine.trim()).indexOf(' ')));
				int EV = Integer.valueOf(currLine.trim().substring(0,currLine.trim().indexOf(' ')).trim());
				//System.out.println("EV: " + EV);
				String typeEV = currLine.trim().substring(currLine.trim().indexOf(' ')).trim();
				//System.out.println(typeEV);
				if(typeEV.equals("HP")){EVs[0] = EV;}
				if(typeEV.equals("Atk")){EVs[1] = EV;}
				if(typeEV.equals("Def")){EVs[2] = EV;}
				if(typeEV.equals("SpA")){EVs[3] = EV;}
				if(typeEV.equals("SpD")){EVs[4] = EV;}
				if(typeEV.equals("Spe")){EVs[5] = EV;}

				currLine = reader.readLine();
				String nature = currLine.substring(0,currLine.indexOf(" ")).trim();

				String[] moves = new String[4];
				for(int i = 0; i < moves.length; i++){ //Parse moves from after "-"
					currLine = reader.readLine();
					moves[i] = currLine.substring(currLine.indexOf('-')+1).trim();
				}

				t1.addPokemon(new Pokemon(name, nature, EVs[0], EVs[1], EVs[2], EVs[3], EVs[4], EVs[5], moves));
				System.out.println(t1.getPokemon(j).getName() + " " + t1.getPokemon(j).movesToString());
				t1.getPokemon(j).statsToString();

				currLine = reader.readLine();

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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



	public int battleDamage(Pokemon p1, int moveNum, Pokemon p2){


//		System.out.println(p1.getLevel() + " " + p1.getCurrentSpAtk() + " " + p2.getCurrentSpDef());
//		int damage = (int) ((((2 * p1.getLevel() + 10)/(double) 250) * ((double) p1.getCurrentSpAtk()/p2.getCurrentSpDef()) * 120 + 2) * 1.5);
//
//		System.out.println("Fire Blast does " + damage + " dmg to " + p2.getName() + " (type effectiveness not implemented.)");

		int damage = 0;

		Move attack = p1.getMove(moveNum);

		double STAB = 1;

		for(String t : p1.getType()){
			if(t.equals(attack.getType())){ //If Pokemon type and move type are the same,
				STAB = 1.5; //give STAB
			}
		}

		double typeEff = checkTypeEffectiveness(p2.getType(), attack.getType());
		double rand = ((double) (new Random().nextInt(16) + 85))/100;

		if(attack.getCategory().equals("Physical")){
			damage = (int) (((int)(((2 * p1.getLevel() + 10)/(double) 250) * ((double) p1.getCurrentAtk()/p2.getCurrentDef()) * attack.getBasePower() + 2)) * STAB * typeEff * rand);
			System.out.println(attack.getName() + " does " + damage + " dmg to " + p2.getName() + " (type effectiveness implmented!)");
			//(int) cast because rounding occurs before multipliers, according to Pokemon Showdown calc
		}
		if(attack.getCategory().equals("Special")){
			damage = (int) (((int)(((2 * p1.getLevel() + 10)/(double) 250) * ((double) p1.getCurrentSpA()/p2.getCurrentSpD()) * attack.getBasePower() + 2)) * STAB * typeEff * rand);
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

		return damage;

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
