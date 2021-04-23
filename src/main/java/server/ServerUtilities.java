package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerUtilities {

	private ServerUtilities(){

	}

	public static void writeToFileFromReader(BufferedReader reader, PrintWriter fileWriter){

		String currLine;

		try { int cnt = 0;
			while((currLine = reader.readLine()) != null){

				fileWriter.println(currLine);
				fileWriter.flush();
				System.out.println("I'M READING FROM READER WRITING TO FILE!!" + "  " + cnt);
				cnt++;
			}
			//System.out.println("Wow ur a fgt for getting here");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void writeToReaderFromFile(BufferedReader fileReader, PrintWriter writer){
		//This code is exactly the same... maybe will fuse down the road
		String currLine;

		try {int cnt = 0;
			while((currLine = fileReader.readLine()) != null){
				writer.println(currLine);
				writer.flush();
				System.out.println("I'M WRITING TO READER FROM FILE!!   " + cnt);
				cnt++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
