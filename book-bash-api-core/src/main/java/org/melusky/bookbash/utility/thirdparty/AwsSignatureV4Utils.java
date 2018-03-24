package org.melusky.bookbash.utility.thirdparty;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.codec.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class AwsSignatureV4Utils {

    public static byte[] computeHmacSHA256(byte[] key, String data) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException,
            UnsupportedEncodingException {
        String algorithm = "HmacSHA256";
        String charsetName = "UTF-8";

        Mac sha256_HMAC = Mac.getInstance(algorithm);
        SecretKeySpec secret_key = new SecretKeySpec(key, algorithm);
        sha256_HMAC.init(secret_key);

        return sha256_HMAC.doFinal(data.getBytes(charsetName));
    }

    public static byte[] computeHmacSHA256(String key, String data) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException,
            UnsupportedEncodingException {
        return computeHmacSHA256(key.getBytes(), data);
    }

    public static String getSignatureV4(String accessSecretKey, String date, String region, String regionService, String signing, String stringToSign)
            throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, UnsupportedEncodingException {

        byte[] dateKey = computeHmacSHA256(accessSecretKey, date);
        log.debug("dateKey: {}", Hex.encodeToString(dateKey));

        byte[] dateRegionKey = computeHmacSHA256(dateKey, region);
        log.debug("dateRegionKey: {}", Hex.encodeToString(dateRegionKey));

        byte[] dateRegionServiceKey = computeHmacSHA256(dateRegionKey, regionService);
        log.debug("dateRegionServiceKey: {}", Hex.encodeToString(dateRegionServiceKey));

        byte[] signingKey = computeHmacSHA256(dateRegionServiceKey, signing);
        log.debug("signingKey: {}", Hex.encodeToString(signingKey));

        byte[] signature = computeHmacSHA256(signingKey, stringToSign);
        log.debug("signature: {}", Hex.encodeToString(signature));

        return Hex.encodeToString(signature);
    }

}
