package com.opinioapp.sdk.delivery.auth;

import com.opinioapp.sdk.delivery.Credentials;
import com.opinioapp.sdk.delivery.utils.HttpAgent;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by lokesh on 3/11/15.
 *
 *
 * For signing Opinio Api Calls
 */
public class Signer {
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    private static final String UTF8_CHARSET = "UTF-8";

    public static String getSignedHeader(HttpAgent.HTTPMethod method, String host, String uri, Credentials credentials, String sortedQS)
            throws SignatureException {

        String stringToSign = method + "\n" +
                host + "\n" +
                uri + "\n" +
                credentials.getAccessKeyId() + "\n" +
                sortedQS + "\n" +
                "&SignatureVersion=1\n" +
                "&SignatureMethod=HmacSHA1";

        return "Opinio " + credentials.getAccessKeyId() + ":" + sign(stringToSign, credentials.getSecretKey());
    }

    private static String sign(String data, String key) throws java.security.SignatureException {
        String result;
        try {

            // Get an hmac_sha256 key from the raw key bytes.
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(UTF8_CHARSET), HMAC_SHA1_ALGORITHM);

            // Get an hmac_sha1 Mac instance and initialize with the signing key.
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);

            // Compute the hmac on input data bytes.
            byte[] rawHmac = mac.doFinal(data.getBytes(UTF8_CHARSET));

            // Base64-encode the hmac
            result = Base64.getEncoder().encodeToString(rawHmac);

        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }

    public static String canonicalize(SortedMap<String, String> sortedParamMap)
    {
        if (sortedParamMap.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, String> stringStringEntry : sortedParamMap.entrySet()) {
            builder.append("&");
            builder.append(percentEncodeRfc3986(stringStringEntry.getKey()));
            builder.append("=");
            builder.append(percentEncodeRfc3986(stringStringEntry.getValue()));
        }
        return builder.toString();
    }

    private static String percentEncodeRfc3986(Object o) {
        if (o == null) {
            return "";
        }

        String s = String.valueOf(o);
        String out;
        try {
            out = URLEncoder.encode(s, UTF8_CHARSET)
                    .replace("+", "%20")
                    .replace("*", "%2A")
                    .replace("%7E", "~");
        } catch (UnsupportedEncodingException e) {
            out = s;
        }
        return out;
    }
}
