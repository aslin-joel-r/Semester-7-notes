import java.util.Scanner;
public class VigenereCipher {
public static char[][] generateAndGetVigenereTable(){
char[][] table = new char[26][26];
int offset = 0;
for(int i=0; i<26; i++){
for(int j=0; j<26; j++){
table[i][j] = (char) ((j+offset)%26 +65);
}
offset++;
}
return table;
}
public static String encrypt(char[] plainText, char[] key){
StringBuilder cipherText = new StringBuilder();
char[][] table = generateAndGetVigenereTable();
for(int i=0; i<plainText.length; i++){
int row = plainText[i] - 65;
int col = key[i%key.length] - 65;
cipherText.append(table[row][col]);
}
return cipherText.toString();
}
public static String decrypt(char[] cipherText, char[] key){
StringBuilder plainText = new StringBuilder();
char[][] table = generateAndGetVigenereTable();
for(int i=0; i<cipherText.length; i++){
int row = key[i%key.length] - 65;
int col = 0;
for(int j=0; j<table[0].length; j++){
if(table[row][j] == cipherText[i]){
col = j;
break;
}
}
plainText.append((char)(col + 65));
}
return plainText.toString();
}
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.println("\nVigenereCipher\n");
System.out.println("Enter the message (Uppercase, without spaces):");
String message = sc.next().toUpperCase();
System.out.println("Enter the key: (A-Z)");
String key = sc.next().toUpperCase();
System.out.println();
String cipherText = encrypt(message.toCharArray(), key.toCharArray());
System.out.println("Cipher Text: " + cipherText);
String plainText = decrypt(cipherText.toCharArray(), key.toCharArray());
System.out.println("Plain Text: " + plainText);
}
}
