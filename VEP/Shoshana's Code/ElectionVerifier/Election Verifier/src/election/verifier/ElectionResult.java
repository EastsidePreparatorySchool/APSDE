/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package election.verifier;

import java.math.BigInteger;

/**
 *
 * @author snovik
 */
public class ElectionResult {
    private int Candidate1Tally;
    private int Candidate2Tally;
    
    ElectionResult(int Candidate1Tally, int Candidate2Tally) {
        this.Candidate1Tally = Candidate1Tally;
        this.Candidate2Tally = Candidate2Tally;
    }
    
    public int getCan1Tally() {
        return this.Candidate1Tally;
    }
    
    public int getCan2Tally() {
        return this.Candidate2Tally;
    }
    
}
