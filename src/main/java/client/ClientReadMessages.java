package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReadMessages implements Runnable {

	private Socket s;
	private BufferedReader reader;

	public ClientReadMessages(Socket inS){

		try {
			s = inS;
			reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void readFromServer(){

		boolean isFinished = false;
		String servIn;
		//!isFinished
		try {
			while(!isFinished){

				servIn = reader.readLine();
				System.out.println("LICK My ASS" + servIn);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run(){
		readFromServer();
	}


}
