import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class SecurePHI2 {

private static final String ALGORITHM = "AES/CBC/PKCS5Padding"; 

private final static char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

private static int[]  toInt   = new int[128];

static {
    for(int i=0; i< ALPHABET.length; i++){
        toInt[ALPHABET[i]]= i;
    }
}
private byte[] iv = new byte[16];


/**
 * Translates the specified byte array into Base64 string.
 *
 * @param buf the byte array (not null)
 * @return the translated Base64 string (not null)
 */
public static String encode(byte[] buf){
    int size = buf.length;
    char[] ar = new char[((size + 2) / 3) * 4];
    int a = 0;
    int i=0;
    while(i < size){
        byte b0 = buf[i++];
        byte b1 = (i < size) ? buf[i++] : 0;
        byte b2 = (i < size) ? buf[i++] : 0;

        int mask = 0x3F;
        ar[a++] = ALPHABET[(b0 >> 2) & mask];
        ar[a++] = ALPHABET[((b0 << 4) | ((b1 & 0xFF) >> 4)) & mask];
        ar[a++] = ALPHABET[((b1 << 2) | ((b2 & 0xFF) >> 6)) & mask];
        ar[a++] = ALPHABET[b2 & mask];
    }
    switch(size % 3){
        case 1: ar[--a]  = '=';
        case 2: ar[--a]  = '=';
    }
    return new String(ar);
}

/**
 * Translates the specified Base64 string into a byte array.
 *
 * @param s the Base64 string (not null)
 * @return the byte array (not null)
 */
public static byte[] decode(String s){
    int delta = s.endsWith( "==" ) ? 2 : s.endsWith( "=" ) ? 1 : 0;
    byte[] buffer = new byte[s.length()*3/4 - delta];
    int mask = 0xFF;
    int index = 0;
    for(int i=0; i< s.length(); i+=4){
        int c0 = toInt[s.charAt( i )];
        int c1 = toInt[s.charAt( i + 1)];
        buffer[index++]= (byte)(((c0 << 2) | (c1 >> 4)) & mask);
        if(index >= buffer.length){
            return buffer;
        }
        int c2 = toInt[s.charAt( i + 2)];
        buffer[index++]= (byte)(((c1 << 4) | (c2 >> 2)) & mask);
        if(index >= buffer.length){
            return buffer;
        }
        int c3 = toInt[s.charAt( i + 3 )];
        buffer[index++]= (byte)(((c2 << 6) | c3) & mask);
    }
    return buffer;
} 


public String encrypt(final String valueEnc,  String password) { 

	String secKey = encode(length16(password).getBytes());
	
    String encryptedVal = null;
    
    // build the initialization vector.  This example is all zeros, but it 
    // could be any value or generated using a random number generator.
    SecureRandom ranGen = new SecureRandom();
    ranGen.setSeed(ranGen.generateSeed(20));
//    iv = new byte[16];
    ranGen.nextBytes(iv);
    IvParameterSpec ivspec = new IvParameterSpec(iv);

    try {
        final Key key = generateKeyFromString(secKey);		
        final Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key, ivspec);
        final byte[] encValue = c.doFinal(valueEnc.getBytes());
//        encryptedVal = java.util.Base64.getEncoder().encodeToString(encValue);
        encryptedVal = encode(encValue);
    } catch(Exception ex) {
        System.out.println("The Exception is=" + ex);
    }

    return encryptedVal;
}


//The next method will decrypt the AES encrypted string (encryptedVal):
public String decrypt(final String encryptedVal, final String password) {
    String secretKey = encode(length16(password).getBytes());
    String decryptedValue = null;

    try {

        final Key key = generateKeyFromString(secretKey);
        final Cipher c = Cipher.getInstance(ALGORITHM);
        IvParameterSpec ivspec = new IvParameterSpec(iv);
        c.init(Cipher.DECRYPT_MODE, key, ivspec);
//        final byte[] decorVal = java.util.Base64.getDecoder().decode(encryptedVal);
        final byte[] decorVal = decode(encryptedVal);
        final byte[] decValue = c.doFinal(decorVal);
        decryptedValue = new String(decValue);
    } catch(Exception ex) {
        System.out.println("The Exception is=" + ex);
    }

    return decryptedValue;
}


//The secKey is a 128-bit key, which is encoded in the BASE64Encoder. The BASE64Decoder in the following method generates an appropriate 128-bit key
private Key generateKeyFromString(final String secKey) throws Exception {
    final byte[] keyVal = decode(secKey);
    final Key key = new SecretKeySpec(keyVal, "AES");
    return key;
}

private static String length16(String c) {
    int length = c.length();
    int spacesToAdd = 16 - length;
    String newString = c;
    for(int i = 0; i < spacesToAdd; i++)
        newString += "+";
    return newString;
}

public static void main(String[] args) {
	
	SecurePHI2 ed = new SecurePHI2();
//	String key = SecurePHI2.encode("Password++++++++".getBytes());
	String encryptedString = ed.encrypt("hello whos there?", "edward123");
	System.out.println(encryptedString);
	
	System.out.println(ed.decrypt(encryptedString, "edward123"));

}

public static void test1()
{
    String memberNumber = "AKD3782909033";
    String address  = "123 Broadway, New York, NY 10001 ";
    String rxNumber = "738299837640";
    String e_memberNumber = "";
    String e_address    = "";
    String e_rxNumber = "";
    
    SecurePHI2 ed = new SecurePHI2();
    String base64key = SecurePHI2.encode("M!78&93Hj;062z#+".getBytes());
    e_memberNumber = ed.encrypt(memberNumber, base64key);
    System.out.println("MemberNumber:" + memberNumber + " | " + e_memberNumber  + " | " + ed.decrypt(e_memberNumber, base64key));
    e_address = ed.encrypt(address, base64key);
    System.out.println("Address:" + address + " | " + e_address  + " | " + ed.decrypt(e_address, base64key));
    e_rxNumber = ed.encrypt(rxNumber, base64key);
    System.out.println("RxNumber:" + rxNumber + " | " + e_rxNumber  + " | " + ed.decrypt(e_rxNumber, base64key));

}

}

