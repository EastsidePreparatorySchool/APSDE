/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package election.verifier;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author snovik
 */
public class ElectionComputer extends Computer{

    private ArrayList<Ballot> List = new ArrayList<Ballot>(); 
    
    public ElectionComputer(BigInteger c, BigInteger n, BigInteger g) {
        //First thing when the election starts up, it gets these three public numbers it needs
        this.c = c;
        this.n = n;
        this.g = g;
    }
    
    public Ballot EncryptBallot(BigInteger vote) {
        //Takes a vote
        //Multiplies c by a randomly generated number and adds the vote to create x
        //Takes public g to the power of x
        //Returns a ballot, which contains this encrypted vote as well as x
        
         Random generator = new Random();
         BigInteger randomNumber = new BigInteger(50, generator);
         
         BigInteger z = this.c.multiply(randomNumber);
         BigInteger x = z.add(vote);
         
         BigInteger encrypted = this.g.modPow(x, this.n);
         
         Ballot ballot = new Ballot(encrypted, x);
         
         return ballot;
         //this is a slight problem because the person gets to see x, but they still can't prove anything.... leave this as is for now
    }
    
    public BigInteger SpoilBallot(Ballot toSpoil) {
        //takes a ballot and returns its x
        //everyone knows g and n, so now they can take g to the x given to them mod n and make sure it was the encryption on their ballot before they spoiled it (on public computer)
        BigInteger x = toSpoil.getX();
        return x;
    }
    
    public void CastBallot(Ballot toCast) {             
        this.List.add(toCast);
    }
    
    public ElectionResult CountVotes() {
        int Count1 = 0;
        int Count2 = 0;
        
        
        //for every ballot in the list of ballots,
        //get its x and take it mod c to find the vote, 0 or 1
        //if 0, add one to the first candidate's tally
        //if 1, second candidate's
        //publish the tally as an 'Election Result' object
        for (Ballot i: this.List) {
            BigInteger x = i.getX();
            BigInteger vote = x.mod(this.c);
            
            if (vote == BigInteger.valueOf(0)) {
                Count1 = Count1 + 1;
            } else {
                Count2 = Count2 + 1;
            }
        }
        ElectionResult er = new ElectionResult(Count1, Count2);
        return er;
    }
    
    public ArrayList<BigInteger> PublishList() {
        //publish the encrypted votes for the public to use in verifying them
        ArrayList<BigInteger> EncryptedVotes = new ArrayList<BigInteger>();
        for (Ballot i: this.List) {
            BigInteger thisBallot = i.getMainBallot();
            EncryptedVotes.add(thisBallot);
        }
        return EncryptedVotes;
    }
    
    public BigInteger PublishXs() {
        //publish the x's, once again for the public's use
        BigInteger Sum = new BigInteger("0");
        for (Ballot i: this.List) {
            Sum = Sum.add(i.getX());
        }
        return Sum;
    }
}
