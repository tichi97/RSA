import java.io.IOException;
import java.io.PrintStream;
import java.security.SecureRandom;
import java.math.BigInteger;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;


public class Alice {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
        // TODO code application logic here
        
        BigInteger testp = new BigInteger(generate(10));
        BigInteger testq = new BigInteger(generate(10));
        BigInteger n, e,d,totient,p,q,y,z,cipher, message;
        Random r;
        final BigInteger ONE = BigInteger.ONE;
        p=BigInteger.valueOf(1);       
        q=BigInteger.valueOf(1);
        r=new Random();
        int x=0;
        
        while(x==0){
            if(testp.isProbablePrime(50)){
                p=testp;
                x=1;
            }
        testp = new BigInteger(generate(10));
    }
        x=0;
        while(x==0){
            if(testq.isProbablePrime(50)){
                q=testq;
                x=1;
            }
            testq = new BigInteger(generate(10));
        }
        
        
        n=p.multiply(q);
   
        totient=(p.subtract(ONE)).multiply(q.subtract(ONE));
       
        e=BigInteger.probablePrime(512/2,r);
      
        while(totient.gcd(e).compareTo(ONE)>0 && e.compareTo(totient)<0){
            e.add(ONE);
        }
        d=e.modInverse(totient);
        
       // System.out.println(p + " " + q + " " + n + " " + totient + " " + e + " " + d );
        //WE GOOOD
        
        @SuppressWarnings("resource")
		Socket s = new Socket("10.66.27.172",1342);
        PrintStream ps = new PrintStream(s.getOutputStream());
        ps.println(e);
        ps.println(n);
       
       
       //TO RECEIVE FROM BOB
       Scanner sc = new Scanner(s.getInputStream());
       cipher = sc.nextBigInteger();
       message = cipher.modPow(d, n);
       
       System.out.println("The ciphertext is: " + cipher);
       System.out.println("The decrypted message: "+ message);
       
       
    }
    
   static String generate(int len){
   SecureRandom sr = new SecureRandom();
   String result = (sr.nextInt(9)+1) +"";
   for(int i=0; i<len-2; i++) result += sr.nextInt(10);
   result += (sr.nextInt(9)+1);
   return result;
}
	
}
