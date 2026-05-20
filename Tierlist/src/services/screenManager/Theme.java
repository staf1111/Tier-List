package services.screenManager;

import java.util.EnumMap;
import java.util.Map;


public class Theme {
	private final Map<ThemeList, String> index = new EnumMap<>(ThemeList.class);

	public Theme() {
		index.put(ThemeList.LIGHT, "/application/styles/light.css");
		index.put(ThemeList.DARK, "/application/styles/dark.css");
	}
	
	public String getTheme(ThemeList th) {
        return index.get(th);
    }
}
