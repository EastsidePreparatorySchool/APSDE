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
public class Ballot {
    //read the encrypt ballot method in the election computer class to understand what x is
    private BigInteger encryptedBallot;
    private BigInteger encryptedX;
    
    Ballot(BigInteger encryptedBallot, BigInteger encryptedX) {
        this.encryptedBallot = encryptedBallot;
        this.encryptedX = encryptedX;
    }
    
    public BigInteger getX() {
        return this.encryptedX;
    }
    
    public BigInteger getMainBallot() {
        return this.encryptedBallot;
    }
}
