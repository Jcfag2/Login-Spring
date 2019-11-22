package fr.gtm.hellomvc;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Repository;


public class Digest {
	
public static String Sha256(String pw) throws NoSuchAlgorithmException {
	String aEncoder = pw;
	MessageDigest digest = MessageDigest.getInstance("SHA-256");
	byte[] encodedhash = digest.digest(aEncoder.getBytes(StandardCharsets.UTF_8));
	
	String hex = bytesToHex(encodedhash);
	return hex;
}

private static String bytesToHex(byte[] hash) {
    StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < hash.length; i++) {
    String hex = Integer.toHexString(0xff & hash[i]);
    if(hex.length() == 1) hexString.append('0');
        hexString.append(hex);
    }
    return hexString.toString();
}

}
