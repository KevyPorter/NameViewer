package net.kevyporter.nameviewer;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.JTextField;


public class NameViewer {

	public void ViewNames(JTextField field){
		if(field.getText().equalsIgnoreCase("")){
			Utils.SetText("Enter a name!");return;
		}
		HashMap<Integer, String> map = new HashMap<>();
		String apiResponse = Utils.getAPI(field.getText());
		if (apiResponse.equalsIgnoreCase("")) {
			return;
		}
		if(apiResponse.replace('"', ' ').replace(" ", "").equalsIgnoreCase("{success:false,cause:Internal error}")){
			Utils.SetText("Internal Error.\nTry again in a few moments!");
		}
		if (apiResponse.replace('"', ' ').replace(" ", "").equalsIgnoreCase("{cause:Keythrottle!,throttle:true,success:false}"))
	    {
	      Utils.SetText("Please try again in a few moments!\nThe API key got used to much!");return;
	    }
	    if (apiResponse.replace('"', ' ').replace(" ", "").contains("player:null"))
	    {
	      Utils.SetText("Player '" + field.getText() + "' not found!");return;
	    }
		getName(apiResponse, map);
	}

	private void getName(String name, Map<Integer, String> map){
		name = name.replace("\",\"", ".");
		name = name.replace("{", "").replace("}", "").replace('"', ' ').replace(" ", "").replace("[", "").replace("]", "");
		String[] split = name.split(Pattern.quote(","));
		for(String s : split){
			if(s.startsWith("knownAliases")){
				map.put(Integer.valueOf(0), "Names: " + s.replace("knownAliases:", "").replace(".", ", ") + ".");
			}
		}
		Utils.SetText(Utils.MapToString(map));
	}

}
