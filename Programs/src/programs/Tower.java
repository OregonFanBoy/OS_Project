package programs;
import java.io.PrintWriter;
import header.Program;
public class Tower extends Program{
	private static final long serialVersionUID = 1L;
	public Tower(){
		super("Towers of Hanoi");
	}
	public int run(PrintWriter out, String[] args){
		int numRings = Integer.parseInt(args[1]);
	 	   if (numRings<1) return(ILLEGAL_PARAMETER);
	 	
	 	   out.println(args[0]+ ": With " + numRings + " rings");
	 	  
	 	   doTowers(out,numRings, 'A', 'B', 'C');
	 	   
	 	   return SUCCESS;
	 	}	
	 	
	 	public static void doTowers(PrintWriter out,int topN, char from, char inter, char to) {
	 	    if (topN == 1){
	 	      out.println("Ring 1 from " + from + " to " + to);
	 	    }else {
	 	      doTowers(out,topN - 1, from, to, inter);
	 	      out.println("Ring " + topN + " from " + from + " to " + to);
	 	      doTowers(out,topN - 1, inter, from, to);
	 	    }
	}
		/*try{
	        int discs = Integer.parseInt(args[1]);
	        out.println("Tower: " + discs + " rings");
		    solve(out, discs, "1", "2", "3");
        } catch(Exception e){
        	return ILLEGAL_PARAMETER;
        }
              return SUCCESS;
     } 
     public void solve(PrintWriter out, int n, String start, String auxiliary, String end){
	       if (n == 1){
	           out.println("Moves from " + start + " to " + end);
	       } else {
	           solve(out,n - 1, start, end, auxiliary);
	           out.println("Moves from " + start + " to " + end);
	           solve(out, n - 1, auxiliary, start, end);
	       }
      } */
}
