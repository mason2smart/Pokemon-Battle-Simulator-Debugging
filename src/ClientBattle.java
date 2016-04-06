import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientBattle {

	Socket s;
	PrintWriter writer;
	Scanner sc;

	public void makeConnection(){

		try {
			s = new Socket("127.0.0.1", 5000);
			writer = new PrintWriter(s.getOutputStream());
			sc = new Scanner(System.in);

			BufferedReader reader = new BufferedReader(new FileReader("data/team.txt"));

			//ServerUtilities.writeToReaderFromFile(reader, writer);

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
