import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerUtilities {

	private ServerUtilities(){

	}

	public static void writeToFileFromReader(BufferedReader reader, PrintWriter fileWriter){

		String currLine;

		try {
			while((currLine = reader.readLine()) != null){
				fileWriter.println(currLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void writeToReaderFromFile(BufferedReader fileReader, PrintWriter writer){
		//This code is exactly the same... maybe will fuse down the road
		String currLine;

		try {
			while((currLine = fileReader.readLine()) != null){
				writer.println(currLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
