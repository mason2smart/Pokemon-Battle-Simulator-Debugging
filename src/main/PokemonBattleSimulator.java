package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import battle.BattleLoop;
import client.ClientBattle;
import server.ServerBattle;

public class PokemonBattleSimulator {

	public void runSimulator(){

		System.out.println("Welcome to the 2016 Pokemon Battle Simulator!!!");
		displayArt();
		System.out.println("Local or Multiplayer? (Type \"1\" for local, \"2\" for multiplayer)");

		boolean isGoodInput = false;
		Scanner sc = new Scanner(System.in);
		int in = 0;

		while(!isGoodInput){ //get input, loop until input is good
			try {
				in = Integer.valueOf(sc.nextLine());
				isGoodInput = true;
				if(in != 1 && in != 2){
					isGoodInput = false;
				}
			} catch(Exception e) {
				System.out.println("Sorry, bad input. Make sure you are typing in a number.");
			}
		}

		if(in == 1){
			BattleLoop b = new BattleLoop();
			b.startBattle();
			b.battle();
		}

		if(in == 2){

			System.out.println("Server or Client Mode? (Type \"1\" for server, \"2\" for client)");
			isGoodInput = false;

			while(!isGoodInput){ //get input, loop until input is good - Maybe make this a single method, you can use to shorten code?
				try {
					in = Integer.valueOf(sc.nextLine());
					isGoodInput = true;
					if(in != 1 && in != 2){
						isGoodInput = false;
					}
				} catch(Exception e) {
					System.out.println("Sorry, bad input. Make sure you are typing in a number.");
				}
			}

			if(in == 1){
				ServerBattle sb = new ServerBattle();
				sb.checkConnections();
				sb.loadBattle();

				try {
					sb.battle();
				} catch (NumberFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if(in == 2){

				ClientBattle cb = new ClientBattle();
				cb.makeConnection();
				cb.writeToServer();

			}

		}



	}

	public void displayArt(){

		try {
			BufferedReader reader = new BufferedReader(new FileReader("data/art/mcharizardxoff-60.txt"));
			//Thanks http://www.glassgiant.com/ascii/ !!!!
			String currLine = null;

			System.out.println();

			while((currLine = reader.readLine()) != null){
				System.out.println(currLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}




}
