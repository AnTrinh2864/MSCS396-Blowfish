//MSCS 396
//Project Blowfish: Time comparison
//This is used so that we can compare our own Blowfish implementation
// with the library provided Blowfish and DES

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

class DES2{
    String key = "AAFFAAFFBBCCDDBB";
    byte[] skey = new byte[1000];
    String skeyString;
    static byte[] raw;
    String inputMessage,encryptedData,decryptedMessage;
    public DES2() {
        try {
            inputMessage= JOptionPane.showInputDialog(null,"Enter message to encrypt");
            //inputMessage = "plaintex";
            byte[] ibyte = inputMessage.getBytes();
            long startTime = System.nanoTime();
            generateSymmetricKey();
            byte[] ebyte=encrypt(raw, ibyte);
            long end = System.nanoTime();
            System.out.println("DES Encryption took: " + (end - startTime)*1000/1e9 + " ms" );
            String encryptedData = new String(ebyte);
            System.out.println("DES Encrypted message "+encryptedData);
            //JOptionPane.showMessageDialog(null,"Encrypted Data "+"\n"+encryptedData);

            startTime = System.nanoTime();
            String bf = encrypt_lib(inputMessage);
            long estimatedTime = System.nanoTime() - startTime;
            System.out.println("Blowfish encryption takes: " + estimatedTime*1000/1e9 + " ms");
            System.out.println("Blowfish encrypted Message: " + bf);

            startTime = System.nanoTime();
            byte[] dbyte= decrypt(raw,ebyte);
            estimatedTime = System.nanoTime() - startTime;
            String decryptedMessage = new String(dbyte);
            System.out.println("Decrypted message "+decryptedMessage);
            //JOptionPane.showMessageDialog(null,"Decrypted Data "+"\n"+decryptedMessage);
            System.out.println("decryption took " + estimatedTime * 1000/1e9  + " in miliseconds.");

            startTime = System.nanoTime();
            String de_bf = decrypt_lib(bf);
            estimatedTime = System.nanoTime() - startTime;
            System.out.println("Blowfish decryption takes: " + estimatedTime*1000/1e9 + " ms");
            System.out.println("Blowfish decrypted Message: " + de_bf);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    void generateSymmetricKey() {
        try {
            Random r = new Random();
            int num = r.nextInt(10000);
            String knum = String.valueOf(num);
            byte[] knumb = knum.getBytes();
            skey=getRawKey(knumb);
            skeyString = new String(skey);
            System.out.println("DES Symmetric key = "+skeyString);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("DES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
        kgen.init(56, sr);
        SecretKey skey = kgen.generateKey();
        raw = skey.getEncoded();
        return raw;
    }
    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }
    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    public String encrypt_lib (String plaintext){
        try {
            byte[] keyData = (key).getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
            byte[] hasil = cipher.doFinal(plaintext.getBytes());
            return new String(Base64.getEncoder().encode(hasil));
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String decrypt_lib(String cipherText) {
        try{
            byte[] keyData = (key).getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
            byte[] hasil = cipher.doFinal(Base64.getDecoder().decode(cipherText));
            return new String(hasil);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String args[]) {
        for (int i = 0; i <1; i++) {
            DES2 des = new DES2();
        }
    }
}
