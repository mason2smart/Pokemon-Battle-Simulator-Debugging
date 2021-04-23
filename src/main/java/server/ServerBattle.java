package server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import battle.Move;
import battle.Pokemon;
import battle.Team;

public class ServerBattle {

	private boolean isBattle;
	private Team t1;
	private Team t2;

	private Scanner sc;

	private Socket s1; //perhaps Sockets should instance variables of a Player object?
	private Socket s2;

	private BufferedReader reader1; //similarly... ^^^
	private BufferedReader reader2;

	private PrintWriter writer1;
	private PrintWriter writer2;

	public ServerBattle(){
		sc = new Scanner(System.in);
	}

	public void checkConnections(){

		try {
			boolean isConnectionGood = false;
			ServerSocket servSocket = new ServerSocket(5000);

			while(!isConnectionGood){

				s1 = servSocket.accept();
				reader1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
				writer1 = new PrintWriter(s1.getOutputStream());
				writer1.flush();
				System.out.println("Connection established with Player 1!");

				s2 = servSocket.accept(); //again, can be refactored, perhaps as a while loop
				reader2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
				writer2 = new PrintWriter(s2.getOutputStream());
				writer2.flush();
				System.out.println("Connection established with Player 2!");

				System.out.println("Is this connection ok? (Type Y/N)");
				String in = sc.nextLine(); //too lazy to add sanitization of input here, rip if you type wrong
				if(in.equals("Y") || in.equals("y")){
					isConnectionGood = true;
				} //inherently, if it's not Y, it just cycles again
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loadBattle(){

		//System.out.println("Welcome to the 2016 Pokemon Battle Simulator!!!");

		t1 = new Team();
		t2 = new Team();

		PrintWriter tempWriter1 = null;
		PrintWriter tempWriter2 = null;
		try {
			tempWriter1 = new PrintWriter("data/tmp/tempopponentteam1.txt"); // initializing writer
			tempWriter2 = new PrintWriter("data/tmp/tempopponentteam2.txt"); // initializing writer
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ServerUtilities.writeToFileFromReader(reader1, tempWriter1);
		System.out.println("IT'S CLOSE TO THE AIR");
		ServerUtilities.writeToFileFromReader(reader2, tempWriter2);
		System.out.println("HOW DO WE SWAY TOWARDS A CONCLUSION YEAH");

//		parsePokemonFile(t1,"data/team.txt");
//		parsePokemonFile(t2,"data/opponentteam.txt");

		parsePokemonFile(t1,"data/tmp/tempopponentteam1.txt");
		parsePokemonFile(t2,"data/tmp/tempopponentteam2.txt");

		isBattle = true;
	}

	public void battle() throws NumberFormatException, IOException{

		Pokemon activePok1 = t1.getPokemon(0); //Grab the first Pokemon in each team
		Pokemon activePok2 = t2.getPokemon(0);

		writer1.println("Foe sent out " + activePok2.getName() + "!");
		writer1.println("Go, " + activePok1.getName() + "!");
		writer1.flush();

		writer2.println("Foe sent out " + activePok1.getName() + "!");
		writer2.println("Go, " + activePok2.getName() + "!");
		writer2.flush();

		while(isBattle){

			writer1.println("What will " + activePok1.getName() + " do?");
			writer1.println("Moves: " + activePok1.movesToString());
			writer1.println("Enter move number | Press 4 to Switch");
			writer1.flush();

			writer2.println("What will " + activePok2.getName() + " do?");
			writer2.println("Moves: " + activePok2.movesToString());
			writer2.println("Enter move number | Press 4 to Switch");
			writer2.flush();

			int moveNum1 = Integer.valueOf(reader1.readLine()); //Get move decision/Decision to switch
			int moveNum2 = Integer.valueOf(reader2.readLine());
			boolean playerFirst = false; //controls for speed tie, without affecting Pokemon speeds
			boolean isAttacking1 = true; //Assume we are attacking, unless we're switching
			boolean isAttacking2 = true;

			if(moveNum1 == 4){ //if switching out
				isAttacking1 = false; //Not attacking
				writer1.println("Select a new Pokemon");
				writer1.println(t1.toString());
				writer1.flush();
				int pokeNum = Integer.valueOf(reader1.readLine()); //Grab Pokemon
				boolean isSamePokemon = t1.getPokemon(pokeNum).equals(activePok1); //check if same Pokemon
				while(t1.getPokemon(pokeNum).getState() == 0 || pokeNum > 5 || isSamePokemon){ //If fainted or same
					if(isSamePokemon){ //If it's the same
						writer1.println(t1.getPokemon(pokeNum).getName() + " is already in battle!");
						writer1.flush();
						pokeNum = Integer.valueOf(reader1.readLine());
						isSamePokemon = t1.getPokemon(pokeNum).equals(activePok1); //check again for next while cycle
					}else{ //else if it's fainted,
						writer1.println(t1.getPokemon(pokeNum).getName() + " is unable to battle!");
						writer1.flush();
						pokeNum = Integer.valueOf(reader1.readLine()); //grab new Pokemon
					}

				}
				activePok1 = t1.getPokemon(pokeNum); //Finally, switch in valid Pokemon
				writer1.println("Go, " + activePok1.getName() + "!");
				writer1.flush();
				writer2.println("Foe sent out " + activePok1.getName() + "!");
				writer2.flush();
//				activePok1.setCurrentHP(activePok1.getCurrentHP() - battleDamage(activePok2,moveNum2,activePok1)); //Get hit by opponent
//				activePok1.checkState(); //Check if fainted
			}

			if(moveNum2 == 4){ //if switching out
				isAttacking2 = false; //Not attacking - BUT DIFF FOR P2???? - Yup, just edited
				writer2.println("Select a new Pokemon");
				writer2.println(t2.toString());
				writer2.flush();
				int pokeNum = Integer.valueOf(reader2.readLine()); //Grab Pokemon
				boolean isSamePokemon = t2.getPokemon(pokeNum).equals(activePok2); //check if same Pokemon
				while(t2.getPokemon(pokeNum).getState() == 0 || pokeNum > 5 || isSamePokemon){ //If fainted or same
					if(isSamePokemon){ //If it's the same
						writer2.println(t2.getPokemon(pokeNum).getName() + " is already in battle!");
						writer2.flush();
						pokeNum = Integer.valueOf(reader2.readLine());
						isSamePokemon = t2.getPokemon(pokeNum).equals(activePok2); //check again for next while cycle
					}else{ //else if it's fainted,
						writer2.println(t2.getPokemon(pokeNum).getName() + " is unable to battle!");
						writer2.flush();
						pokeNum = Integer.valueOf(reader2.readLine()); //grab new Pokemon
					}

				}
				activePok2 = t2.getPokemon(pokeNum); //Finally, switch in valid Pokemon
				writer2.println("Go, " + activePok2.getName() + "!");
				writer2.flush();
				writer1.println("Foe sent out " + activePok2.getName() + "!");
				writer1.flush();
//				activePok1.setCurrentHP(activePok1.getCurrentHP() - battleDamage(activePok2,moveNum2,activePok1)); //Get hit by opponent
//				activePok1.checkState(); //Check if fainted
			}

			//NOTE::: THIS METHOD NEEDS EDITING. THE FASTER POKEMON SHOULD SWITCH OUT FIRST, NEEDS TO CONSIDER CASES LIKE BELOW


			if(isAttacking1 || isAttacking2){ //If we are attacking,   -- NOTE; FOR P2, this means ONLY DMG CALC will occur is P1is Attacking
				if(activePok1.getCurrentSpe() == activePok2.getCurrentSpe()){ //If speed tie,
					if(new Random().nextInt(2) == 0){ //Random decide who goes first
						playerFirst = true;
					}else{

					}
				}

				if(activePok1.getCurrentSpe() > activePok2.getCurrentSpe() || playerFirst == true){ //If Pokemon1 is faster

					if(isAttacking1){
						activePok2.setCurrentHP(activePok2.getCurrentHP() - battleDamage(activePok1,moveNum1,activePok2)); //P1 attacks!
						activePok2.checkState(); //Check if fainted, or still active
					}
					if(activePok2.getState() == 1 && isAttacking2){ //if still active
						activePok1.setCurrentHP(activePok1.getCurrentHP() - battleDamage(activePok2,moveNum2,activePok1)); //P2 attacks!
						activePok1.checkState(); //Check if fainted, or still active
					}
				}else{ //activePok1.getCurrentSpe() < activePok2.getCurrentSpe() || playerFirst == false

					if(isAttacking2){
						activePok1.setCurrentHP(activePok1.getCurrentHP() - battleDamage(activePok2,moveNum2,activePok1));
						activePok1.checkState();
					}
					if(activePok1.getState() == 1 && isAttacking1){
						activePok2.setCurrentHP(activePok2.getCurrentHP() - battleDamage(activePok1,moveNum1,activePok2));
						activePok2.checkState();
					}
				}
			}//if isAttacking end bracket


			if(activePok1.getState() == 0 && !t1.isFaintedTeam()){ //if active Pokemon is knocked out, and Pokemon left
				writer1.println("Select a new Pokemon, by number"); //Can switch to new Poke!
				writer1.println(t1.toString());
				writer1.flush();
				writer2.println("Foe is thinking over his next Pokemon...");
				writer2.flush();
				int pokeNum = Integer.valueOf(reader1.readLine());
				while(t1.getPokemon(pokeNum).getState() == 0 || pokeNum > 5){ //While not fainted or over the boundaries,
					writer1.println(t1.getPokemon(pokeNum).getName() + " is unable to battle!");
					writer1.flush();
					pokeNum = Integer.valueOf(reader1.readLine());
				}
				activePok1 = t1.getPokemon(pokeNum); //Switch Pokemon in.
				writer1.println("Go, " + activePok1.getName() + "!");
				writer1.flush();
				writer2.println("Foe sent out " + activePok1.getName() + "!");
				writer2.flush();
			}

			if(activePok2.getState() == 0 && !t2.isFaintedTeam()){ //Same for above, simply inversed for opponent
				writer2.println("Select a new Pokemon, by number"); //Can switch to new Poke!
				writer2.println(t2.toString());
				writer2.flush();
				writer1.println("Foe is thinking over his next Pokemon...");
				writer1.flush();
				int pokeNum = Integer.valueOf(reader2.readLine());
				while(t2.getPokemon(pokeNum).getState() == 0){
					writer2.println(t2.getPokemon(pokeNum).getName() + " is unable to battle!");
					writer2.flush();
					pokeNum = Integer.valueOf(reader2.readLine());
				}
				activePok2 = t2.getPokemon(pokeNum);
				writer2.println("Go, " + activePok2.getName() + "!");
				writer2.flush();
				writer1.println("Foe sent out " + activePok2.getName() + "!");
				writer1.flush();
			}



			if(t1.isFaintedTeam()){ //If all team fainted,
				isBattle = false;
				writer1.println("Player whited out!"); //RIP in pepperoni
				writer1.flush();
				writer2.println("Player defeated Trainer!");
				writer2.flush();
			}

			if(t2.isFaintedTeam()){
				isBattle = false;
				writer2.println("Player whited out!"); //RIP in pepperoni
				writer2.flush();
				writer1.println("Player defeated Trainer!"); //gg no re
				writer1.flush();
			}

		}


	}

	public void parsePokemonFile(Team t, String filePath){

		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
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
				//System.out.println(name + " " + nature + EVs[0] + EVs[1] + moves[0] + " " + moves[1] + " " + moves[2] + " " + moves[3]);
				t.addPokemon(new Pokemon(name, nature, EVs[0], EVs[1], EVs[2], EVs[3], EVs[4], EVs[5], moves));
				//System.out.println(t1.getPokemon(j).getName() + " " + t.getPokemon(j).movesToString());
				t.getPokemon(j).statsToString();

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
			writer1.println(attack.getName() + " does " + damage + " dmg to " + p2.getName() + " (type effectiveness implmented!)");
			writer1.flush();
			writer2.println(attack.getName() + " does " + damage + " dmg to " + p2.getName() + " (type effectiveness implmented!)");
			writer2.flush();

			//(int) cast because rounding occurs before multipliers, according to Pokemon Showdown calc
		}
		if(attack.getCategory().equals("Special")){
			damage = (int) (((int)(((2 * p1.getLevel() + 10)/(double) 250) * ((double) p1.getCurrentSpA()/p2.getCurrentSpD()) * attack.getBasePower() + 2)) * STAB * typeEff * rand);
			writer1.println(attack.getName() + " does " + damage + " dmg to " + p2.getName() + " (type effectiveness implmented!)");
			writer1.flush();
			writer2.println(attack.getName() + " does " + damage + " dmg to " + p2.getName() + " (type effectiveness implmented!)");
			writer2.flush();

		}

		if(typeEff == 0){
			writer1.println("It doesn't affect " + p2.getName() + "...");
			writer1.flush();
			writer2.println("It doesn't affect " + p2.getName() + "...");
			writer2.flush();
		}
		if(typeEff == 0.5){
			writer1.println("It's not very effective...");
			writer1.flush();
			writer2.println("It's not very effective...");
			writer2.flush();
		}
		if(typeEff == 1){

		}
		if(typeEff == 2){
			writer1.println("It's super effective!");
			writer1.flush();
			writer2.println("It's super effective!");
			writer2.flush();
		}
		if(typeEff == 4){
			writer1.println("It's ultra effective!");
			writer1.flush();
			writer2.println("It's ultra effective!");
			writer2.flush();
		}

		return damage;

		//STAB, type effectiveness, implemented. Critical, Random, and held items, abilities, and weather not included.
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
