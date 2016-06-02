package os;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class SystemCall extends Thread {

	ServerSocket socket;
	String[] dataBase;
	Socket client;
	
	public SystemCall(int port, int fileSize)throws IOException{
		socket = new ServerSocket(port);
		dataBase = new String[fileSize];
	}
	
	public void close(){
		try{
			socket.close();
		}
		catch(IOException ioe){
			
		}
	}
	public void run(){
		try{
			while(true){
				client = socket.accept();
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
						pout.println("Could not read string");
					}
					break;
				case "write":
					try{
						//hits the write case and overwrites argument 2 to argument 1.
						dataBase[Integer.parseInt(arr[1])] = arr[2];
						pout.println(arr[2]+" was written to "+ arr[1]);
					}
					catch(Exception ex){
						pout.println("Could not write to string");
					}
					break;
				case "size":
					try{
						//returning to client the size of the database length.
						pout.println(dataBase.length);
					}
					catch(Exception ex){
						pout.println("Could not obtain size");
					}
					break;
				default:
					pout.println("Error from client");
				}
				client.close();
				pout.close();
				in.close();
				bin.close();
			}
		}
		catch(SocketException s){}
		catch(IOException ioe){
			ioe.printStackTrace();
			try{
				client.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}//end run
}//end class
