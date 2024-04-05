
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
class DES{
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        int j=100;
        long[] encTime= new long[j];
        long[] decTime= new long[j];

        //String we want to encrypt
        String  message="This is a confidential message.";
        byte[] myMessage =message.getBytes(); //string to byte array as DES works on bytes
        //If you want to use your own key
        // SecretKeyFactory MyKeyFactory = SecretKeyFactory.getInstance("DES");
        // String Password = "My Password";
        // byte[] mybyte =Password.getBytes();
        // DESKeySpec myMaterial = new DESKeySpec(mybyte);
        // SecretKey myDESKey = MyKeyFactory.generateSecret(myMaterial);
        KeyGenerator Mygenerator = KeyGenerator.getInstance("DES");
        SecretKey myDesKey = Mygenerator.generateKey();

        //initializing crypto algorithm
        Cipher myCipher = Cipher.getInstance("DES");

        for(int i =0; i<j;i++) {
            long startTime = System.nanoTime();
            //Generating Key


            //setting encryption mode
            myCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte[] myEncryptedBytes=myCipher.doFinal(myMessage);

            long estimatedTime = System.nanoTime() - startTime;
            System.out.println("DES encryption took " + estimatedTime * 1000/1e9 + " in miliseconds.");
            encTime[i]=estimatedTime;
            long startTime2 = System.nanoTime();
            encrypt_lib(message);
            long estimatedTime2 = System.nanoTime() - startTime2;
            System.out.println("Blowfish encryption took " + estimatedTime2 * 1000/1e9  + " in miliseconds.");
            decTime[i]=estimatedTime2;
            //print message in byte format
            //System.out.println(Arrays.toString(myEncryptedBytes));
            //System.out.println(Arrays.toString(myDecryptedBytes));

        }//end for
    }

    public static String encrypt_lib (String plaintext){
        try {
            String key = "AAFFAAFFBBCCDDBB";
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
}
