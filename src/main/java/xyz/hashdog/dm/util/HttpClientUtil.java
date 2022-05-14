package xyz.hashdog.dm.util;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HttpClientUtil {
    public static String getStrFromUrl(String url, String encoding) {
        try {
            url = urlFormat(url);
            String result = "";
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();

            BufferedReader in;
            String line;
            for(in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding)); (line = in.readLine()) != null; result = result + line) {
            }

            in.close();
            return result;
        } catch (Exception var7) {
            return "";
        }
    }

    public static String getStrFromUrl(String url) {
        return getStrFromUrl(url, "UTF8");
    }
    public static String urlFormat(String url) {
        return url.replace(" ", "%20").replace("<", "%3C").replace(">", "%3E").replace("|", "%7C");
    }
}
