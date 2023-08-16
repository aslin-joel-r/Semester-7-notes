import java.util.Scanner;

public class PlayfairCipher {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter keyword: ");
        String key = in.nextLine();
        System.out.print("Enter message to encrypt: ");
        String msg = in.nextLine();
        PFEncryption pfEncryption = new PFEncryption();
        pfEncryption.makeArray(key);
        msg = pfEncryption.manageMessage(msg);
        pfEncryption.doPlayFair(msg, "Encrypt");
        String en = pfEncryption.getEncrypted();
        System.out.println("Encrypting... \nThe encrypted text is: " + en);
        System.out.println("=============================");
        pfEncryption.doPlayFair(en, "Decrypt");
        System.out.print("\nDecrypting... \nThe decrypted text is: " + pfEncryption.getDecrypted());
    }
}

class PFEncryption {
    private char[][] alphabets = new char[5][5];
    private char[] uniqueChar = new char[26];
    private String encrypted = "";
    private String decrypted = "";
    private String ch = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

    void makeArray(String keyword) {
        keyword = keyword.toUpperCase().replace("J", "I");
        int val = 0;
        int uniqueLen;

        for (int i = 0; i < keyword.length(); i++) {
            uniqueLen = 0;
            if (keyword.charAt(i) != ' ') {
                for (int k = 0; k < uniqueChar.length; k++) {
                    if (uniqueChar[k] == '\0') {
                        break;
                    }
                    uniqueLen++;
                }
                boolean present = false;
                for (int j = 0; j < uniqueLen; j++) {
                    if (keyword.charAt(i) == uniqueChar[j]) {
                        present = true;
                        break;
                    }
                }
                if (!present) {
                    uniqueChar[val] = keyword.charAt(i);
                    val++;
                }
            }
            ch = ch.replaceAll(Character.toString(keyword.charAt(i)), "");
        }

        val = 0;
        for (int i = 0; i < ch.length(); i++) {
            if (ch.charAt(i) != ' ') {
                uniqueChar[val] = ch.charAt(i);
                val++;
            }
        }

        val = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                alphabets[i][j] = uniqueChar[val];
                val++;
                System.out.print(alphabets[i][j] + "\t");
            }
            System.out.println();
        }
    }

    String manageMessage(String msg) {
        
int val=0;
int len=msg.length()-2;
String newTxt="";
String intermediate="";
while (len>=0)
{
intermediate=msg.substring(val, val+2);
if (intermediate.charAt(0)==intermediate.charAt(1))
{
newTxt=intermediate.charAt(0) + "x" + intermediate.charAt(1);
msg=msg.replaceFirst(intermediate, newTxt);
len++;
}
len-=2;
val+=2;
}
if (msg.length()%2!=0)
{
msg=msg+'x';
}
return msg.toUpperCase().replaceAll("J","I").replaceAll(" ","");

    }

    void doPlayFair(String msg, String tag) {
        int val=0;
while (val<msg.length())
{
searchAndEncryptOrDecrypt(msg.substring(val,val+2),tag);
val+=2;
}
    }

    
void searchAndEncryptOrDecrypt(String doubblyCh, String tag)
{
char ch1=doubblyCh.charAt(0);
char ch2=doubblyCh.charAt(1);
int row1=0, col1=0, row2=0, col2=0;
for (int i=0; i<5; i++)
{
for (int j=0; j<5; j++)
{
if (alphabets[i][j]==ch1)
{
row1=i;
col1=j;
}
else if (alphabets[i][j]==ch2)
{
row2=i;
col2=j;
} } }
if (tag=="Encrypt")
encrypt(row1, col1, row2, col2);
else if(tag=="Decrypt")
decrypt(row1, col1, row2, col2);
}
void encrypt(int row1, int col1, int row2, int col2)
{
if (row1==row2)
{
col1=col1+1;
col2=col2+1;
if (col1>4)
col1=0;
if (col2>4)
col2=0;
encrypted+=(Character.toString(alphabets[row1][col1])+
Character.toString(alphabets[row1][col2]));
}
else if(col1==col2)
{
row1=row1+1;
row2=row2+1;
if (row1>4)
row1=0;
if (row2>4)
row2=0;
encrypted+=(Character.toString(alphabets[row1][col1])+
Character.toString(alphabets[row2][col1]));
}
else
{ encrypted+=(Character.toString(alphabets[row1][col2])+
Character.toString(alphabets[row2][col1]));
}}
void decrypt(int row1, int col1, int row2, int col2)
{
if (row1==row2)
{
col1=col1-1;
col2=col2-1;
if (col1<0)
col1=4;
if (col2<0)
col2=4;
decrypted+=(Character.toString(alphabets[row1][col1])+
Character.toString(alphabets[row1][col2]));
}
else if(col1==col2)
{
row1=row1-1;
row2=row2-1;
if (row1<0)
row1=4;
if (row2<0)
row2=4;
decrypted+=(Character.toString(alphabets[row1][col1])+
Character.toString(alphabets[row2][col1]));
}
else
{
decrypted+=(Character.toString(alphabets[row1][col2])+
Character.toString(alphabets[row2][col1]));
}}
String getEncrypted( )
{
return encrypted;
}
String getDecrypted( )
{
return decrypted;
}
}
