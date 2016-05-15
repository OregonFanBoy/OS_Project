package programs;

import java.io.PrintWriter;

import header.Program;

public class Primes extends Program {
	private static final long serialVersionUID = 1L;
	final int MAX = 213;
	public Primes(){
		super("Find All the Primes");
	}
	public int run(PrintWriter out, String[] args){	
		 
	   
		try{
			int input = Integer.parseInt(args[1]);
			if(input<1){
				out.println("Must be a positive integer");
				return ILLEGAL_PARAMETER;
			}
			if(input>MAX){
				out.println("This program only does the first "+ MAX +" prime numbers");
				return ILLEGAL_PARAMETER;
			}
			out.println("Prime: List of the first " + input + " primes.");
			int[] prime = new int[MAX];
			int prime_count = 0;
			for(int i = 1;i<MAX;i++){
				boolean isPrime = true;
				for(int j = 2; j < i; j++){
					if(i%j ==0){
						isPrime = false;
						break;
					}
				}
				if(isPrime){
					prime[prime_count]= i;
					prime_count ++;
				}
			}
			
			for(int i=0;i<input;i++){
				out.println(prime[i]);
				schedule();
			}
		} catch(Exception interupt){
			return ILLEGAL_PARAMETER;
		}
		return SUCCESS;
	}
}
