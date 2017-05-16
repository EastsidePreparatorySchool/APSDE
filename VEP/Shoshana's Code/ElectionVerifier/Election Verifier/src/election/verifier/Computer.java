/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package election.verifier;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author snovik
 */
public abstract class Computer {
    //I made this class just so that public and election computer could share these three public values
    protected BigInteger n;
    protected BigInteger e;
    protected BigInteger c;
   
}
