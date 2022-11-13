package eu.skyphantom.skypvp.utils;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class GeoUtils {
    private static GeoUtils instance;
    public JsonObject object;
    public String server = "";

    public GeoUtils(String ip) {
        instance = this;
        this.server = "http://ip-api.com/json/" + ip + "?fields=status,message,continent,continentCode,country,countryCode,region,regionName,city,district,zip,lat,lon,timezone,currency,isp,org,as,asname,reverse,mobile,proxy,query";
        this.object = (new JsonParser()).parse(this.websitedata(this.server)).getAsJsonObject();
    }

    public static GeoUtils getInstance() {
        return instance;
    }

    public String getAS() {
        return this.getObjectString("as");
    }

    public String getASNAME() {
        return this.getObjectString("asname");
    }

    public String getCITY() {
        return this.getObjectString("city");
    }

    public String getCONTINENT() {
        return this.getObjectString("continent");
    }

    public String getCONTINENTCODE() {
        return this.getObjectString("continentCode");
    }

    public String getCOUNTRY() {
        return this.getObjectString("country");
    }

    public String getCOUNTRYCODE() {
        return this.getObjectString("countryCode");
    }

    public String getDISTRICT() {
        return this.getObjectString("district");
    }

    public String getISP() {
        return this.getObjectString("isp");
    }

    public String getORG() {
        return this.getObjectString("org");
    }

    public String getQUERY() {
        return this.getObjectString("query");
    }

    public String getREGIONNAME() {
        return this.getObjectString("regionName");
    }

    public String getREVERSE() {
        return this.getObjectString("reverse");
    }

    public String getTIMEZONE() {
        return this.getObjectString("timezone");
    }

    public String getPROXY() {
        return this.getObjectString("proxy");
    }

    public boolean isSuccess() {
        return this.getObjectString("status").equals("success");
    }

    public boolean isRight(String obj) {
        return !obj.isEmpty() && !obj.contains("Unknown");
    }

    public String getObjectString(String obj) {
        try {
            return this.object.get(obj).getAsString();
        } catch (Exception var3) {
            return "Pinging...";
        }
    }

    public String websitedata(String website) {
        try {
            URL url = new URL(website);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String content = this.format((List<String>) bufferedReader.lines().collect(Collectors.toList()));
            bufferedReader.close();
            return content;
        } catch (Exception var5) {
            return null;
        }
    }

    private String format(List<String> arrayList) {
        String out = "";

        String entry;
        for (Iterator<String> var3 = arrayList.iterator(); var3.hasNext(); out = out + entry + "\n") {
            entry = var3.next();
        }

        return out;
    }
}
