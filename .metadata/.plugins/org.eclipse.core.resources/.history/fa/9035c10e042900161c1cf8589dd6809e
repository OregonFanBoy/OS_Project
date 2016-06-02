package header;
import java.io.*;
import java.net.Socket;

public abstract class Program extends Thread implements Serializable {
	public final static long serialVersionUID =1;
		public final static int SUCCESS =0;
		public final static int ILLEGAL_PARAMETER = 1;
		static Thread me;
		
		public Program(String name){
			this.setPriority(NORM_PRIORITY);
			this.start();
		}
		
		
		//Abstract run mehtod
		public abstract int run(PrintWriter out, String[] args)throws InterruptedException;
		
		// Fair share scheduling and check time limit interrupts
		public static void schedule() throws InterruptedException{
		
			me = Thread.currentThread();
			if(me.isInterrupted())
				throw new InterruptedException();
			yield();
		}
		
		//OS System call interface (this is the client)
		public String system(String call){
			String line = null;
			try{
				Socket sock = new Socket("127.0.0.1",6013);
				OutputStream out = sock.getOutputStream();
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(out),true);
				InputStream in = sock.getInputStream();
				BufferedReader bin = new BufferedReader(new InputStreamReader(in));
			
				pw.println(call);//reader writer calls system. and system will pass call to server.
				line = bin.readLine(); //this is when the server kicks back to you.
				//close sockets when done	
				sock.close();
				//close writer and reader.
				bin.close();
				in.close();
				out.close();
				pw.close();
			}
			catch(Exception e){
				System.err.println(e);
			}
			//returns to reader/writer (line)
			return line;
		}// end System method.
		
		//Method for orderly shutdown (overrrideen in applications in a later lab)
		public void terminate(){
			
		};
}
