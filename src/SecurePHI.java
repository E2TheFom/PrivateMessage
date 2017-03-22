
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;


//Created by oleg.fominykh@worldinfolinks.com using the JCA for PHI/PII data encryption. 2016.
public class SecurePHI {

private static final String ALGORITHM = "AES/CBC/PKCS5Padding"; 

private final static char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

private static int[]  toInt   = new int[128];

static {
    for(int i=0; i< ALPHABET.length; i++){
        toInt[ALPHABET[i]]= i;
    }
}

// set the initialization vector to a random number at class load
private byte[] iv = new byte []{1,0,1,0,2,0,1,0,1,1,1,0,1,0,1,0};

private String base64Secretkey = "";


public SecurePHI() {
}

public SecurePHI(String sharedSecret) {
	//create a secret key from the shared secret
	//System.out.println("sharedSecret=" + sharedSecret);
	base64Secretkey = SecurePHI.encode(sharedSecret.getBytes());
}



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

public String generate_new_iv() {
    SecureRandom ranGen = new SecureRandom();
    ranGen.setSeed(ranGen.generateSeed(20));
    //iv = new byte[16];
    ranGen.nextBytes(iv);
    return byteToHex(iv);
}


public String get_iv() {
    return byteToHex(iv);
}

public void set_iv(String p_iv) { // input string is in hex-format
	iv=hexStringToByteArray(p_iv);
}

public void set_sharedSecret(String sharedSecret) {
	//create a secret key from the shared secret
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


//The next method will decrypt the AES encrypted string (encryptedVal):
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

//The secKey is a 128-bit key, which is encoded in the BASE64Encoder. The BASE64Decoder in the following method generates an appropriate 128-bit key
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

public static void main(String[] args) {
	
	// sample variables and values
	String memberNumber = "A123B456C789";
	String e_memberNumber = ""; // encrypted value
	String firstName = "Kevin";
	String e_firstName = ""; // encrypted value
	String lastName = "Starling";
	String e_lastName = ""; // encrypted value
	String address	= "123 Broadway, New York, NY 10001";
	String e_address	= ""; // encrypted value
	String rxNumber = "123456789012,123456789012,123456789012,123456789012,123456789012,123456789012,123456789012,123456789012,123456789012,123456789012";
	String e_rxNumber = ""; // encrypted value
	//
	
	// required variables
	String cbciv = ""; // initialization vector
	String sharedSecret = "thu!h7KU-3aqumEk"; // 16 characters (sample shared secret)
	boolean renew_iv = false; // set to true if a new initialization vector is to be generated for every transmission
	//
	
	SecurePHI ed = new SecurePHI(sharedSecret);
	//generate a new initialization vector for the CBC mode 
	if (renew_iv) {
		cbciv=ed.generate_new_iv();
	} else {
		cbciv=ed.get_iv();
	}
	System.out.println("Initialization Vector=" + cbciv);

	// encrypt the values and append the prefix "enc-"
	e_memberNumber = "enc-" + ed.encrypt(memberNumber);
	e_address = "enc-" + ed.encrypt(address);
	e_rxNumber = "enc-" + ed.encrypt(rxNumber);
	e_firstName = "enc-" + ed.encrypt(firstName);
	e_lastName = "enc-" + ed.encrypt(lastName);

	// at this point the encryption process is fully completed.
	// send the encrypted variables and the "cbciv" (CBC initialization vector) to the recipient
	
	ed.iv=null;
	// Start the decryption steps
	ed.iv=ed.hexStringToByteArray(cbciv); // convert the initialization vector from Hex string to byte array

	// decrypt the value and print
	System.out.println("MemberNumber:" + memberNumber + " | " + e_memberNumber  + " | " + ed.decrypt(e_memberNumber.startsWith("enc-") ? e_memberNumber.substring(4) : e_memberNumber));
	// decrypt the value and print
	System.out.println("Address:" + address + " | " + e_address  + " | " + ed.decrypt(e_address.startsWith("enc-") ? e_address.substring(4) : e_address));
	// decrypt the value and print
	System.out.println("RxNumber:" + rxNumber + " | " + e_rxNumber  + " | " + ed.decrypt(e_rxNumber.startsWith("enc-") ? e_rxNumber.substring(4) : e_rxNumber));
	// decrypt the value and print
	System.out.println("FirstName:" + firstName + " | " + e_firstName  + " | " + ed.decrypt(e_firstName.startsWith("enc-") ? e_firstName.substring(4) : e_firstName));
	// decrypt the value and print
	System.out.println("LastName:" + lastName + " | " + e_lastName  + " | " + ed.decrypt(e_lastName.startsWith("enc-") ? e_lastName.substring(4) : e_lastName));


}



}

