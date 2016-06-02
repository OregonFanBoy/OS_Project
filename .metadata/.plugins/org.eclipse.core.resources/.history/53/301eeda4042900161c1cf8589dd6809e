package os;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

	private ServerSocket socket;
	private String[] dataBase;
	public Server(int port, int fileSize)throws IOException{
		socket = new ServerSocket(port);
		dataBase = new String[fileSize];
	}
	
	public void close(){
		
	}
	public void run(){
		try{
			while(true){
				Socket client = socket.accept();
				PrintWriter pout = new PrintWriter(client.getOutputStream(),true);
				InputStream in = client.getInputStream();
				BufferedReader bin = new BufferedReader(new InputStreamReader(in));
				String line = bin.readLine();
				String[] arr = line.split(" ");
				switch (arr[0]){
				case "read":
					try{
						//hits the read case and convert the next argument as an integer.
						pout.println(dataBase[Integer.parseInt(arr[1])]);
					}
					catch(Exception ex){
						
					}
					break;
				case "write":
					try{
						//hits the write case and overwrites argument 2 to argument 1.
						dataBase[Integer.parseInt(arr[1])] = arr[2];
						pout.println(arr[2]+" was written to "+ arr[1]);
					}
					catch(Exception ex){
						
					}
					break;
				case "size":
					try{
						//returning to client the size of the database length.
						pout.println(dataBase.length);
					}
					catch(Exception ex){
						
					}
					break;
				}
				client.close();
				pout.close();
				in.close();
				bin.close();
			}
		}
		catch(IOException ioe){
			System.err.println(ioe);
		}
	}//end run
}//end class
