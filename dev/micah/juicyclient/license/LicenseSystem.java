package dev.micah.juicyclient.license;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class LicenseSystem {

    private static String stringUrl = "revoked for obvious reasons";

    private static String readTxTFile() {
        try {
            URL url = new URL(stringUrl);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                return inputLine;
            }
            in.close();
        } catch (Exception x) {

        }
        return "";
    }

    public static boolean isKeyValid() {
        String text = readTxTFile();
        if (text.equals("true") || text.isEmpty()) {
            return true;
        }
        if (text.equals("false")) {
            System.out.println("--------------------------------");
            System.out.println("Error: Your access to this client");
            System.out.println("has been removed by the owner!");
            System.out.println(" ");
            System.out.println("Reasons for this may consist of scamming");
            System.out.println("redistribution, or other things breaking the TOS.");
            System.out.println(" ");
            System.out.println("If you believe this is an error, please contact");
            System.out.println("Discord: Meecka#7099");
            System.out.println("--------------------------------");
            return false;
        }
        return true;
    }
	
}
