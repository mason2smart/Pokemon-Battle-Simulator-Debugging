import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientBattle {

	Socket s;
	BufferedReader reader;
	PrintWriter writer;

	public void makeConnection(){

		try {
			s = new Socket("127.0.0.1", 5000);
			reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			writer = new PrintWriter(s.getOutputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
