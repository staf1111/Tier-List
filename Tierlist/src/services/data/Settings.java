package services.data;

import services.screenManager.*;

public class Settings {
	private static Settings instance;

	private String RAWG_apiKey = "";
	private String TMDB_apiKey = "";
	
	private int ApiRate = 5; // nombre limite de résultats lors de la requette
	private int MaxApiRate = 20; //nombre maximum de résultats par requette
	
	private ThemeList theme = ThemeList.LIGHT;
	
	public static void init()
	{
		if(instance == null)
		{
			instance = new Settings();
		}
	}
	
	private Settings()
	{
		RAWG_apiKey = "1234";
		TMDB_apiKey = "1234";
	}

	public String getRAWG_apiKey() {
		return RAWG_apiKey;
	}


	public void setRAWG_apiKey(String rAWG_apiKey) {
		RAWG_apiKey = rAWG_apiKey;
	}


	public String getTMDB_apiKey() {
		return TMDB_apiKey;
	}


	public void setTMDB_apiKey(String tMDB_apiKey) {
		TMDB_apiKey = tMDB_apiKey;
	}


	public ThemeList getTheme() {
		return theme;
	}
	
	public int getApiRate() {
		return ApiRate;
	}

	public void setApiRate(int apiRate) {
		ApiRate = apiRate;
	}

	public static Settings getInstance()
	{
		if(instance == null)
		{
			init();
			return instance;
		}
		return instance;
	}
}
