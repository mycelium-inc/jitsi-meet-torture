package org.jitsi.meet.test.base;

import javax.net.ssl.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyCeliumBackendService
{

    public boolean finishParty()
    {

        try{
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[] { new TrustAll509TrustManager() }, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier( new HostnameVerifier(){
                public boolean verify(String string, SSLSession ssls) {
                    return true;
                }
            });

        System.out.println(CustomCache._backendUrl);
        URL obj = new URL(CustomCache._backendUrl + "/api/bot-army/finish");
		HttpURLConnection con = (HttpURLConnection)obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Content-Type", "application/json");

		// For POST only - START
		String jsonInputString = "{\"partyName\":\"" + CustomCache._roomName + "\"}";
	    con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(jsonInputString.getBytes("utf-8"));
		os.flush();
		os.close();

	    System.out.println("POST Body :: " + jsonInputString);
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
            System.out.println(response.toString());
		} else {
			System.out.println("POST request not worked");
        }
    }
    catch(Exception ex){
        System.out.println(ex);
        return false;
    }
        return true;
    }
}