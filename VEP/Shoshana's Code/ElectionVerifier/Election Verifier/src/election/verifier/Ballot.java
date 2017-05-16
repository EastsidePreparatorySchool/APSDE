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
    private final BigInteger encryptedBallot; //Encrypted ciphertext
    private final BigInteger encryptedM; //Padded message (pre-encryption)
    
    Ballot(BigInteger encryptedBallot, BigInteger encryptedM) {
        this.encryptedBallot = encryptedBallot;
        this.encryptedM = encryptedM;
    }
    
    public BigInteger getM() {
        return this.encryptedM;
    }
    
    public BigInteger getMainBallot() {
        return this.encryptedBallot;
    }
}
