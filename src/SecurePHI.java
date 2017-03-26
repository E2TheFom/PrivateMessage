
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;



public class SecurePHI {

private static final String ALGORITHM = "AES/CBC/PKCS5Padding"; 

private final static char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

private static int[]  toInt   = new int[128];

static {
    for(int i=0; i< ALPHABET.length; i++){
        toInt[ALPHABET[i]]= i;
    }
}


private byte[] iv = new byte []{1,0,1,0,2,0,1,0,1,1,1,0,1,0,1,0};

private String base64Secretkey = "";


public SecurePHI() {
}

public SecurePHI(String sharedSecret) {
	
	base64Secretkey = SecurePHI.encode(sharedSecret.getBytes());
}




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

public String generate_new_iv() {
    SecureRandom ranGen = new SecureRandom();
    ranGen.setSeed(ranGen.generateSeed(20));
    
    ranGen.nextBytes(iv);
    return byteToHex(iv);
}


public String get_iv() {
    return byteToHex(iv);
}

public void set_iv(String p_iv) { 
	iv=hexStringToByteArray(p_iv);
}

public void set_sharedSecret(String sharedSecret) {
	
	base64Secretkey = SecurePHI.encode(sharedSecret.getBytes());
}

public String encrypt(final String valueEnc) { 

    String encryptedVal = null;
    
    IvParameterSpec ivspec = new IvParameterSpec(iv);

    try {
        final Key key = generateKeyFromString(base64Secretkey);		
        final Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key, ivspec);
        final byte[] encValue = c.doFinal(valueEnc.getBytes());
        encryptedVal = encode(encValue);
    } catch(Exception ex) {
        System.out.println("The Exception is=" + ex);
    }

    return encryptedVal;
}



public String decrypt(final String encryptedVal) {

    String decryptedValue = null;

    try {

        final Key key = generateKeyFromString(base64Secretkey);
        final Cipher c = Cipher.getInstance(ALGORITHM);
        IvParameterSpec ivspec = new IvParameterSpec(iv);
        c.init(Cipher.DECRYPT_MODE, key, ivspec);
        final byte[] decorVal = decode(encryptedVal);
        final byte[] decValue = c.doFinal(decorVal);
        decryptedValue = new String(decValue);
    } catch(Exception ex) {
        System.out.println("The Exception is=" + ex);
    }

    return decryptedValue;
}

private Key generateKeyFromString(final String secKey) throws Exception {
    final byte[] keyVal = decode(secKey);
    final Key key = new SecretKeySpec(keyVal, "AES");
    return key;
}

public byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                             + Character.digit(s.charAt(i+1), 16));
    }
    return data;
}

String byteToHex(final byte[] hash)
{
    Formatter formatter = new Formatter();
    for (byte b : hash)
    {
        formatter.format("%02x", b);
    }
    String result = formatter.toString();
    formatter.close();
    return result;
}





}

