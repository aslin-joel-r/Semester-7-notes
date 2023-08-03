import java.util.Scanner;

public class CeaserCipher {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    
    public static String encrypt(String plainText, int shiftKey) {
        plainText = plainText.toLowerCase();
        StringBuilder cipherText = new StringBuilder();
        
        for (int i = 0; i < plainText.length(); i++) {
            char currentChar = plainText.charAt(i);
            
            if (Character.isLetter(currentChar)) {
                int charPosition = ALPHABET.indexOf(currentChar);
                int keyVal = (shiftKey + charPosition) % 26;
                char replaceVal = ALPHABET.charAt(keyVal);
                cipherText.append(replaceVal);
            } else {
                cipherText.append(currentChar);
            }
        }
        return cipherText.toString();
    }
    
    public static String decrypt(String cipherText, int shiftKey) {
        cipherText = cipherText.toLowerCase();
        StringBuilder plainText = new StringBuilder();
        
        for (int i = 0; i < cipherText.length(); i++) {
            char currentChar = cipherText.charAt(i);
            
            if (Character.isLetter(currentChar)) {
                int charPosition = ALPHABET.indexOf(currentChar);
                int keyVal = (charPosition - shiftKey) % 26;
                
                if (keyVal < 0) {
                    keyVal = ALPHABET.length() + keyVal;
                }
                
                char replaceVal = ALPHABET.charAt(keyVal);
                plainText.append(replaceVal);
            } else {
                plainText.append(currentChar);
            }
        }
        return plainText.toString();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Plain text for Encryption: ");
        String message = sc.nextLine();
        
        System.out.println("Enter the shift key: ");
        int shiftKey = sc.nextInt();
        
        String encryptedMessage = encrypt(message, shiftKey);
        System.out.println("Encrypted message: Cipher Text = " + encryptedMessage);
        
        String decryptedMessage = decrypt(encryptedMessage, shiftKey);
        System.out.println("Decrypted message: Plain Text = " + decryptedMessage);
        
        sc.close();
    }
}
