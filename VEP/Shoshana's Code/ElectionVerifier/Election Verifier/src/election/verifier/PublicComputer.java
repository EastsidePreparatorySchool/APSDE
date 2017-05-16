/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package election.verifier;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author snovik
 */
public class PublicComputer extends Computer {
    
    PublicComputer(BigInteger c, BigInteger n, BigInteger e){
        this.c = c;
        this.n = n;
        this.e = e;
    }
    
    public BigInteger verifyVote(BigInteger m) {
        //someone voted
        //they then spoiled a ballot, and the machine gave them x
        //now they can take g to the x mod n and make sure they get the correct number
        
        BigInteger whatItShouldBe = m.modPow(this.n, this.e);
        
        return whatItShouldBe;

    }
    
    public String verifyList(ArrayList<BigInteger> List, ElectionResult result, BigInteger allXs) {

        //The election publishes the sum of all x’s for each candidate as well 
        //as the encrypted votes (g^x).
        
        //This function multiplies all the encryptions (g^x1 * g^x2 * g^x3....), which should give us 
        //g^(sum of all x’s), so we can take g^(sum of all x’s that the
        //election published) and make sure it is correct
        

        BigInteger multipliedBallots = new BigInteger("1");
        
        //get all the main ballots and multiply them together
        //multiplied ballots IS g ^ what should be sum of all x's
        for (BigInteger i: List) {
            multipliedBallots = multipliedBallots.multiply(i);
            multipliedBallots = multipliedBallots.mod(this.n);
        }
        
        //now I need to take g ^ sum of all x's election published
        BigInteger verifiedCount = allXs.modPow(this.n, this.e);
        
        int comparison = verifiedCount.compareTo(multipliedBallots);
        //hopefully this returns 0, then we know it is correct
        
        
        if (comparison == 0) {
            
            BigInteger Candidate2Count = allXs.mod(this.c);
            BigInteger PublishedCount = BigInteger.valueOf(result.getCan2Tally());
            if (Candidate2Count.compareTo(PublishedCount) == 0) {
                return "The election was counted correctly";
            } else {
                return "The election was not counted correctly";
            }                       
        } else {
            return "The election was not counted correctly";
        }
            
        
    }
    
}
