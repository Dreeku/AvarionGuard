package net.avarioncode.anticrash.api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public final class PCDetection {
    public String status;
    public String node;
    public String ip;
    public String asn;
    public String provider;
    public String country;
    public String isocode;
    public String proxy;
    public String type;
    public String port;
    public String last_seen_human;
    public String last_seen_unix;
    public String query_time;
    public String message;
    public String error;
    private final String api_key;
    private String api_url = "http://proxycheck.io/v2/";
    private int api_timeout = 5000;
    private int useVpn = 0;
    public String tag;

    public PCDetection(String key) {
        this.api_key = key;
    }

    public void setUseVpn(boolean var) {
        if (var) {
            this.useVpn = 1;
        } else {
            this.useVpn = 0;
        }

    }

    public void set_api_timeout(int timeout) {
        this.api_timeout = timeout;
    }

    public void useSSL() {
        this.api_url = this.api_url.replace("http://", "https://");
    }

    public void parseResults(String Ip) throws IOException, ParseException {
        String query_url = this.get_query_url(Ip);
        String query_result = this.query(query_url, this.api_timeout, "ProxyCheck-IO Java-Library");
        JSONParser parser = new JSONParser();
        JSONObject main = (JSONObject) parser.parse(query_result);
        JSONObject sub = (JSONObject) main.get(Ip);
        this.status = (String) main.get("status");
        this.node = (String) main.get("node");
        this.ip = Ip;
        this.asn = (String) sub.get("asn");
        this.provider = (String) sub.get("provider");
        this.country = (String) sub.get("country");
        this.isocode = (String) sub.get("isocode");
        this.proxy = (String) sub.get("proxy");
        this.type = (String) sub.get("type");
        this.port = (String) sub.get("port");
        this.last_seen_human = (String) sub.get("last seen human");
        this.last_seen_unix = (String) sub.get("last seen unix");
        this.query_time = (String) main.get("query time");
        this.message = (String) main.get("message");
        this.error = (String) sub.get("error");
    }

    public String get_query_url(String ip) {
        int usePort = 0;
        int useTime = 0;
        int useSeen = 0;
        int useInf = 0;
        int useNode = 0;
        int useAsn = 0;
        int useDays = 7;
        return this.api_url + ip + "?key=" + this.api_key + "&vpn=" + this.useVpn + "&asn=" + useAsn + "&node=" + useNode + "&time=" + useTime + "&inf=" + useInf + "&port=" + usePort + "&seen=" + useSeen + "&days=" + useDays + "&tag=" + this.tag;
    }

    public String query(String url, int timeout, String userAgent) throws IOException {
        StringBuilder response = new StringBuilder();
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        connection.setConnectTimeout(timeout);
        connection.setRequestProperty("User-Agent", userAgent);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        Throwable var8 = null;

        try {
            while ((url = in.readLine()) != null) {
                response.append(url);
            }
        } catch (Throwable var17) {
            var8 = var17;
            throw var17;
        } finally {
            if (var8 != null) {
                try {
                    in.close();
                } catch (Throwable var16) {
                    var8.addSuppressed(var16);
                }
            } else {
                in.close();
            }

        }

        return response.toString();
    }
}
