/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package election.verifier;


import java.math.BigInteger;
import java.util.Random;
import java.util.ArrayList;

/**
 *
 * @author snovik
 */
public class ElectionVerifier {

    /**
     * @param args the command line arguments
     */
    
    //Could also have an election config class
    
    private static final BigInteger one = new BigInteger("1");
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        //the following code generates my large numbers that will be used in encryption and are available to the public
        //This is kind of a hack. We are treating n and g as the public key pair used by RSA algorithms
        //Read more about RSA encryption at: https://en.wikipedia.org/wiki/RSA_(cryptosystem)#Operation
        Random r = new Random();
        BigInteger p = new BigInteger(50, r);
        BigInteger q = new BigInteger(50, r);
        BigInteger c = new BigInteger("1000"); //This number is just used to pad the vote
        
        BigInteger n = p.multiply(q);
        BigInteger lambda = lcm(p.subtract(one), q.subtract(one));
        BigInteger e = coprime(lambda, r);
        
        BigInteger d = e.modInverse(lambda);
        
        //everything bellow here is my test case
        
        //create the election and public computers
        ElectionComputer EC = new ElectionComputer(c, n, e);
        PublicComputer PC = new PublicComputer(c, n, e);
        
        //create Example Ballot 1 and encrypt it
        BigInteger exampleVote = new BigInteger("0"); //this is a vote for Candidate 1, you can either vote 0 or 1
        Ballot ExampleBallot = EC.EncryptBallot(exampleVote); 
        
        //Create Example Ballot 2 and encrypt it. Then print it to show encrypt function works.
        BigInteger secondExampleVote = new BigInteger("1");
        Ballot secondExampleBallot = EC.EncryptBallot(secondExampleVote);
        System.out.println("This is an encrypted ballot:");
        printBallot(secondExampleBallot);
        
        //Create and encrypt Example Ballot 3
        BigInteger thirdExampleVote = new BigInteger("1");
        Ballot thirdExampleBallot = EC.EncryptBallot(thirdExampleVote);
        
        //Spoil Example Ballot 1 to test spoil function
        BigInteger testX = EC.SpoilBallot(ExampleBallot);
        System.out.println("This is a Spoiled Ballot:");
        System.out.println(testX);
        //Use public computer to verify that the spoiled vote was correct and check the verify vote function
        BigInteger ExampleVerifiedVote = PC.verifyVote(EC.SpoilBallot(ExampleBallot));
        System.out.println(ExampleVerifiedVote);
        
        //Cast the second and third example ballots
        EC.CastBallot(secondExampleBallot);
        EC.CastBallot(thirdExampleBallot);
        
        //Count up the casted ballots to test the tally function
        System.out.println("This is the tally for the two candidates, counted from the ballots which were cast:");
        ElectionResult exampleCount = EC.CountVotes();
        print(exampleCount);
        
        //Election computer publishes the encrypted votes for the public
        System.out.println("These are the cast encrypted votes, one of the things the election computer publishes for the public:");
        ArrayList<BigInteger> EncryptedVotes = EC.PublishList();
        System.out.println(EncryptedVotes);
        
        //Election computer also publishes the x value of each ballot
        //read the encrypt ballot method in the election computer class to understand what x is
        BigInteger electionPublishedXs = EC.PublishXs();
        
        //Public Computer verifies the election and returns "was counted correctly" or "was counted incorrectly".
        //This is fine, because we trust the Public Computer. The computer we don't trust is the Election Computer.
        String ExampleVerification = PC.verifyList(EncryptedVotes, exampleCount, electionPublishedXs);
        System.out.println(ExampleVerification);
    }
    
    public static void print(ElectionResult er) {
        System.out.println(er.getCan1Tally());
        System.out.println(er.getCan2Tally());
    }
    
    public static void printBallot(Ballot b) {
        System.out.println(b.getMainBallot());
        System.out.println(b.getM());
    }
    
    //I didn't want to redo the math for LCM, so it's here for readability
    private static BigInteger lcm(BigInteger p, BigInteger q) {
        BigInteger gcd = p.gcd(q); //return GCD
        
        return p.divide(gcd.multiply(q)); //Return LCM of p and q
    }
    
    //Randomly generate a BigInteger, check if it is coprime to lambda, and return it if so
    private static BigInteger coprime(BigInteger lambda, Random r) {
        BigInteger coprime;
        
        do {
            coprime = new BigInteger(25, r);
        } while (!coprime.gcd(lambda).equals(one));
        
        return coprime;
    }
}
