package client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import server.ServerUtilities;

public class ClientBattle {

	private Socket s;
	private PrintWriter writer;
	private Scanner sc;

	public void makeConnection(){

		try {
			s = new Socket("127.0.0.1", 5000);
			writer = new PrintWriter(s.getOutputStream());
			sc = new Scanner(System.in);

			BufferedReader teamReader = new BufferedReader(new FileReader("data/team.txt"));

			ServerUtilities.writeToReaderFromFile(teamReader, writer);

			ClientReadMessages crm = new ClientReadMessages(s);
			Thread t = new Thread(crm);
			t.start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void writeToServer(){

		boolean isFinished = false;
		String in;
		while(!isFinished){

			in = sc.nextLine();
			writer.println(in);
			writer.flush();

		}

	}


}
