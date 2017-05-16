/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryptiontest;

import java.math.*;
import java.util.*;

/**
 *
 * @author mshenoy_2
 */
public class EncryptionTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //This is a refashioning of the RSA cryptosystem that hopefully makes more sense
        test();
    }
    
    public static Map returnKey (){
        //Creates a Map to assign the values to later on
        Map keys = new HashMap();
        
        Random rand = new Random();
        BigInteger p = BigInteger.probablePrime(rand.nextInt(11)+50, rand);
        BigInteger q = BigInteger.probablePrime(rand.nextInt(11)+60, rand);
        
        //Creating the keys requires several steps. The first one is to find n
        //n = p*q
        BigInteger n = p.multiply(q);
        
        //Find the totient of n. This will always be the p and q - 1
        //This is simply used to find the lcm of the totients
        BigInteger p1 = p.subtract(new BigInteger("1")), q1 = q.subtract(new BigInteger("1"));
        BigInteger eRange = (p1.multiply(q1)).divide(p1.gcd(q1));
        
                
        //The lcm is supposed to act as the range that e (a randomly generated prime) could be found
        //e is the encryptor, Your Message^e is the first part of the encryption process
        BigInteger e = BigInteger.probablePrime(eRange.bitLength()/2, rand);
       
        if (eRange.mod(e).compareTo(BigInteger.ZERO) == 0) {
            e = e.nextProbablePrime();
        }
        
        //Finding d is simply taking the inverse mod of the lcm of e.
        //This gives you a number that, when powing and modding, cancel out e
        //and leave you with 1, or the message you sent
        BigInteger d = e.modInverse(eRange);
        
        //Assigns keys to the map
        keys.put("n", n);
        keys.put("e", e);
        keys.put("d", d);
        
        //Returns map
        return keys;
    }
    
    public static BigInteger encrypt (Map<String, BigInteger> map, BigInteger num) {
        BigInteger encrypted;
        //returns your message to the power of e and then mod n
        encrypted = num.modPow(map.get("e"), map.get("n"));
        return encrypted;
    }
    
    public static BigInteger decrypt (Map<String, BigInteger> map, BigInteger num) {
        BigInteger decrypted;
        //returns your message to the power of d and mod n
        //This reverses the effects of the ecnrypt function
        decrypted = num.modPow(map.get("d"), map.get("n"));
        return decrypted;
    }
    
    public static void test() {
        //Takes the number to encrypt
        Scanner reader = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Number to encrypt:");
        //String bi = reader.next();
        BigInteger n = new BigInteger(reader.next());
        
        //Using a map here to keep each important number (The encryptor, decryptor, and mod)
        //assigned to its respective String
        //This makes it easier to understand the process
        Map keys = returnKey();
        
        //Encrypts and decrypts
        BigInteger encryptN = encrypt(keys, n);
        System.out.println(encryptN);
        BigInteger decryptN = decrypt(keys, encryptN);
        System.out.println(decryptN);
    }
}