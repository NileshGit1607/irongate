package com.irongate.web;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class IRCTCDataPuller {

    private static final String URL = "https://www.cleartrip.com/trains/list?page=";

    public static void main(String[] args) {
        try {
            List<Train> trains = Lists.newArrayList();
            for (int page = 1; page <= 5; page++) {
                String pageHTMLContent = getPage(URL, page);
                trains.addAll(findTrains(pageHTMLContent));
            }
            System.out.println(trains.size());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getPage(String url, int page) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        HttpsURLConnection connection = getConnection(new URL("https://www.cleartrip.com/trains/list?page=" + page));
        String data = getContent(connection);
        return data;
    }

    public static HttpsURLConnection getConnection(URL url) throws KeyManagementException, NoSuchAlgorithmException, IOException {
        SSLContext ctx = SSLContext.getInstance("TLS");

        ctx.init(null, new TrustManager[]{new InvalidCertificateTrustManager()}, null);

        SSLContext.setDefault(ctx);

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        // connection.setRequestProperty("Authorization", "Basic " + authEncoded);
        connection.setHostnameVerifier(new InvalidCertificateHostVerifier());

        return connection;
    }

    private static String getContent(HttpsURLConnection con) {
        StringBuilder builder = new StringBuilder();
        if (con != null) {

            try {

                BufferedReader br =
                        new BufferedReader(
                                new InputStreamReader(con.getInputStream()));

                String input;

                while ((input = br.readLine()) != null) {
                    builder.append(input);
                }
                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return builder.toString();
    }

    private static List<Train> findTrains(String data) {
        List<Train> trains = Lists.newArrayList();
        String _start = "class=\"results\">";
        data = StringUtils.deleteWhitespace(data);
        data = StringUtils.substringAfter(data, _start);
        data = StringUtils.substringAfter(data, "</tr>");
        while (StringUtils.substringBetween(data, "<tr>", "</tr>") != null) {
            String trainInfo = StringUtils.substringBetween(data, "<tr>", "</tr>");
            Train train = new Train();
            String[] tds = StringUtils.substringsBetween(trainInfo, "<td>", "</td>");
            train.setNumber(tds[0]);
            train.setName(StringUtils.substringBetween(tds[1], ">", "<"));
            train.setSource(StringUtils.substringBetween(tds[2], ">", "<"));
            train.setDestination(StringUtils.substringBetween(tds[3], ">", "<"));
            System.out.println(train);
            data = StringUtils.substringAfter(data, "</tr>");
            trains.add(train);
        }
        return trains;
    }

    private static List<Station> getStations(String train) {
        List<Station> stations = Lists.newArrayList();
        return stations;
    }

}
