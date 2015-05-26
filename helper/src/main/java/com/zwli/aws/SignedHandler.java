package com.zwli.aws;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;

public class SignedHandler {

    private static final String TIMESTAMP = "Timestamp";

    private static final String AWS_ACCESS_KEY_ID = "AWSAccessKeyId";

    private static final String UTF8 = "UTF-8";

    private static final String REQUEST_URI = "/";

    private static final String REQUEST_METHOD = "GET";

    private String endpoint; // must be lowercase

    private String awsAccessKeyId;

    private String awsSecretKey;

    private String requestMethod;

    public SignedHandler(String endpoint, String awsAccessKeyId, String awsSecretKey) {
        this(endpoint, awsAccessKeyId, awsSecretKey, REQUEST_METHOD);
    }

    public SignedHandler(String endpoint, String awsAccessKeyId, String awsSecretKey, String requestMethod) {
        super();
        this.endpoint = endpoint;
        this.awsAccessKeyId = awsAccessKeyId;
        this.awsSecretKey = awsSecretKey;
        this.requestMethod = requestMethod;
    }

    public String sign(Map<String, String> params) throws SignatureException {
        if (null == params || params.isEmpty()) {
            return "";
        }
        params.put(AWS_ACCESS_KEY_ID, awsAccessKeyId);
        params.put(TIMESTAMP, timestamp());

        SortedMap<String, String> sortedParamMap = new TreeMap<String, String>(params);
        String canonicalQueryStr = canonicalize(sortedParamMap);

        String enterStr = "\n";
        String stringToSign = requestMethod + enterStr + endpoint + enterStr + REQUEST_URI + enterStr + canonicalQueryStr;

        String signature = sign(stringToSign);
        signature = encodeRfc3986(signature);

        String signedUrl = "https://" + endpoint + REQUEST_URI + "?" + canonicalQueryStr + "&Signature=" + signature;
        return signedUrl;
    }

    private String sign(String stringToSign) throws SignatureException {
        String signature = Signature.calculateRFC2104HMAC(stringToSign, awsSecretKey);
        return signature;
    }

    private String timestamp() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String timestamp = sdf.format(cal.getTime());
        return timestamp;
    }

    private String canonicalize(SortedMap<String, String> sortedParamMap) {
        if (sortedParamMap.isEmpty()) {
            return "";
        }
        StringBuilder strBuilder = new StringBuilder();
        Iterator<Map.Entry<String, String>> it = sortedParamMap.entrySet().iterator();
        Map.Entry<String, String> kvPair = null;
        while (it.hasNext()) {
            kvPair = it.next();
            strBuilder.append(encodeRfc3986(kvPair.getKey()));
            strBuilder.append("=");
            strBuilder.append(encodeRfc3986(kvPair.getValue()));
            if (it.hasNext()) {
                strBuilder.append("&");
            }
        }
        return strBuilder.toString();
    }

    private String encodeRfc3986(String s) {
        String toReturn = null;
        try {
            toReturn = URLEncoder.encode(s, UTF8).replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
        } catch (UnsupportedEncodingException e) {
            toReturn = s;
        }
        return toReturn;
    }

    public static void main(String[] args) {
        String endpoint = "ec2.us-west-2.amazonaws.com";
        String awsAccessKeyId = "AKIAI2QAEIGH6ZJB7QYA";
        String awsSecretKey = "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";

        Map<String, String> params = new HashMap<String, String>();
        params.put("SignatureMethod", "HmacSHA256");
        params.put("SignatureVersion", "2");
        params.put("Version", "2011-05-15");

        // params = signDescribeSecurityGroups(params);
        // params = signDescribeRegions(params);

        try {
            SignedHandler helper = new SignedHandler(endpoint, awsAccessKeyId, awsSecretKey);
            String url = helper.sign(params);
            System.err.println(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // private static Map<String, String> signDescribeRegions(Map<String, String> params) {
    // params.put("Action", "DescribeRegions");
    // return params;
    // }
    //
    // private static Map<String, String> signDescribeSecurityGroups(Map<String, String> params) {
    // params.put("Action", "DescribeSecurityGroups");
    // return params;
    // }
}
