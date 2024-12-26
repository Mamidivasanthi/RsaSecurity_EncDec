package com.Instanpe.RSAEncryption;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

@Service
public class EncryptDecryptService {
    private static final String RSA="RSA";
    private PrivateKey privateKey;
    private PublicKey publicKey;

    //To  start the actual encryption, we need to generate our RSA key pair. We can easily do it by using the KeyPairGenerator from java.security package
    private KeyPair generateKeyPair() throws NoSuchAlgorithmException {

        //Initialize with the algorithm that we are using in our case it is RSA
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance(RSA);
        keyPairGenerator.initialize(4096);
        KeyPair keyPair=keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    // Extract the public and private keys from the keypair
    public void init() throws NoSuchAlgorithmException {
        KeyPair keyPair=generateKeyPair();
        privateKey=keyPair.getPrivate();
        publicKey=keyPair.getPublic();

    }
    // Method for Encryption
    public String encrypt(String data) throws Exception {
        //Initialize public key
        init();
        //Initialize the Cipher object
        Cipher encryptCipher=Cipher.getInstance(RSA);
        encryptCipher.init(Cipher.ENCRYPT_MODE,publicKey);
        byte[] encryptedValue= encryptCipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    // Method for Decryption
    public String decrypt(String encryptedData) throws Exception {

        Cipher decryptCipher=Cipher.getInstance(RSA);
        decryptCipher.init(Cipher.DECRYPT_MODE,privateKey);
        byte[] decryptedValue= decryptCipher.doFinal(Base64.getMimeDecoder().decode(encryptedData));
        return new String(decryptedValue);
    }


}
