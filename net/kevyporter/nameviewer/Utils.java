package net.kevyporter.nameviewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Utils {

	protected static String getAPI(String player){
		String api = "";
		try{
			URL u = new URL("https://api.hypixel.net/player?key=026105d9-80c3-424a-b84e-4043d3e8dd56&name=" + player);
			HttpsURLConnection con = (HttpsURLConnection)u.openConnection();
		      con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.111 Safari/537.36");
		      
		      BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		      String input;
		      while ((input = br.readLine()) != null)
		      {
		        api = api + input;
		      }
		      br.close();
		    }
		    catch (IOException e)
		    {
		      SetText("API is either down, or key was overused, sorry ;-;\nRetry in a minute or two :)");
		    }

		return api;
	}
	
	protected static String MapToString(Map<Integer, String> map)
	  {
	    String t = "";
	    for (int i = 0; i <= map.size(); i++) {
	      t = t + (String)map.get(Integer.valueOf(i)) + "\n";
	    }
	    t = t.replace("null\n", "");
	    return t;
	  }
	
	protected static void SetText(String input)
	  {
	    Main.text.setText(null);
	    Main.text.setText(input);
	  }
	
	protected static void Loading()
	  {
	    Main.text.setText(null);
	    Main.text.setText("Loading...");
	  }
	
}
