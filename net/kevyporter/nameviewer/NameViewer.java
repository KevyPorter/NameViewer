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
			Utils.Loading();
		}
		Utils.SetText(Utils.MapToString(map));
	}

}
