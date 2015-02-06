package net.kevyporter.nameviewer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Pattern;

import javax.swing.JTextField;


public class NameViewer {

	public void ViewNames(JTextField field){
		if(field.getText().equalsIgnoreCase("")){
			Utils.SetText("Enter a name!");return;
		}
		HashMap<Integer, String> map = new HashMap<>();
		String apiResponse = Utils.getUUID(field.getText());
		if (apiResponse.equalsIgnoreCase("")) {
			return;
		}
		getNames(apiResponse, map);
	}

	private void getNames(String pname, Map<Integer, String> map){
		String name = pname.replace("{", "").replace("}", "").replace('"', ' ').replace(" ", "").replace("[", "").replace("]", "");
		String id = "";
		String[] split = name.split(Pattern.quote(","));
		for(String s : split){
			if(s.startsWith("id")){
				id = Utils.getNames(s.replace("id:", ""));
			}
		}
		id = id.replace("{", "").replace("}", "").replaceAll(",", ".").replace('"', ' ').replace(" ", "").replace("[", "").replace("]", "").replace(".c", "-c").replace(".", ",");
		String[] split2 = id.split(Pattern.quote(","));
		int counter = 0;
		for(String s : split2){
			if(s.startsWith("name") && s.contains("changed")){
				String[] names = s.split(Pattern.quote("-"));
				for(String d : names){
					if(d.startsWith("name")){
						map.put(Integer.valueOf(counter), "Name: " + d.replace("name:", ""));
						counter++;
					}
					if(d.startsWith("changedToAt")){
						long unixSeconds = Long.parseLong(d.replace("changedToAt:", ""));
						Date date = new Date(unixSeconds);
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
						sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
						String formattedDate = sdf.format(date);
						map.put(Integer.valueOf(counter), "Changed on: " + formattedDate + " GMT+1" + "\n");
						counter++;
					}
				}
			}else
				if(s.startsWith("name") && !s.contains("changed")){
					map.put(Integer.valueOf(counter), "Original Name: " + s.replace("name:", "") + "\n");
					counter++;
				}
		}
		name = pname.replace("{", "").replace("}", "").replace('"', ' ').replace(" ", "").replace("[", "").replace("]", "");
		String[] currname = name.split(Pattern.quote(","));
		for(String n : currname){
			if(n.startsWith("name")){
				map.put(Integer.valueOf(counter), "Current Name: " + n.replace("name:", ""));
			}
		}
		Utils.SetText(Utils.MapToString(map));
	}

}
