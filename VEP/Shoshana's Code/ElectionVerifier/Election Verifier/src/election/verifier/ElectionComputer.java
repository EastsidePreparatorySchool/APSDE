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
public class ElectionComputer extends Computer {

    private ArrayList<Ballot> List = new ArrayList<Ballot>(); 
    
    public ElectionComputer(BigInteger c, BigInteger n, BigInteger e) {
        //First thing when the election starts up, it gets these three public numbers it needs
        this.c = c; //Constant for padding
        this.n = n; //Public key
        this.e = e; //Public key
    }
    
    public Ballot EncryptBallot(BigInteger vote) {
        //Takes a vote
        //Multiplies c by a randomly generated number and adds the vote to create x
        //Takes public g to the power of x
        //Returns a ballot, which contains this encrypted vote as well as x
        
         Random generator = new Random();
         BigInteger randomNumber = new BigInteger(50, generator);
         
         BigInteger z = this.c.multiply(randomNumber);
         BigInteger m = z.add(vote); //Padded message
         
         BigInteger cipherText = m.modPow(this.n, this.e);
         
         Ballot ballot = new Ballot(cipherText, m);
         
         return ballot;
         //this is a slight problem because the person gets to see x, but they still can't prove anything.... leave this as is for now
    }
    
    public BigInteger SpoilBallot(Ballot toSpoil) {
        //Return the padded message M which a ballot contains
        BigInteger m = toSpoil.getM();
        return m;
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
            BigInteger x = i.getM();
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
            Sum = Sum.add(i.getM());
        }
        return Sum;
    }
}
