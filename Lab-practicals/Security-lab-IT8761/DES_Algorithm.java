

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class DES_Algorithm {
    private static Cipher encryptionCipher;
    private static Cipher decryptionCipher;

    public static void initSecretKey(SecretKey key) throws Exception {
        encryptionCipher = Cipher.getInstance("DES");
        decryptionCipher = Cipher.getInstance("DES");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
        decryptionCipher.init(Cipher.DECRYPT_MODE, key);
    }

    public static String encrypt(String str) throws Exception {
        byte[] messageBytes = str.getBytes(StandardCharsets.UTF_8);
        byte[] encodedBytes = encryptionCipher.doFinal(messageBytes);
        return new String(Base64.getEncoder().encode(encodedBytes));
    }

    public static String decrypt(String str) throws Exception {
        byte[] decodedBytes = Base64.getDecoder().decode(str);
        byte[] messageBytes = decryptionCipher.doFinal(decodedBytes);
        return new String(messageBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] argv) {
        try {
            SecretKey key = KeyGenerator.getInstance("DES").generateKey();
            initSecretKey(key);

            Scanner sc = new Scanner(System.in);
            System.out.println("\nDES Algorithm\n");
            System.out.println("Enter the message: (A-Z, a-z, 0-9)");
            String message = sc.nextLine();
            String encryptedString = "";
            String decryptedString = "";

            try {
                encryptedString = encrypt(message);
                // Reset the decryption cipher
                initSecretKey(key);
                decryptedString = decrypt(encryptedString);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println();
            System.out.println("CipherText: " + encryptedString);
            System.out.println("PlainText: " + decryptedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
