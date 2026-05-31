package services.data;

import services.screenManager.ThemeList;

public class Settings {
	private static Settings instance;

	private String RAWG_apiKey = "";
	private String TMDB_apiKey = "";
	
	private int ApiRate = 5; // nombre limite de résultat lors de la requette
	private int MaxApiRate = 20; //nombre maximum de résultat par requette
	
	private ThemeList theme = ThemeList.LIGHT;
	
	private TierList currentTierList_play, currentTierList_edit;
	
	public static void init()
	{
		if(instance == null)
		{
			instance = new Settings();
		}
	}
	
	private Settings()
	{
		RAWG_apiKey = "69cc1f05ae3c40daab0d0889eb46acaf";
		TMDB_apiKey = "581cb5fcdd5f9a93344b6d275fbb65d1";
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

	
	public TierList getCurrentTierList_play() {
		return currentTierList_play;
	}

	public void setCurrentTierList_play(TierList currentTierList_play) {
		this.currentTierList_play = currentTierList_play;
	}

	public TierList getCurrentTierList_edit() {
		return currentTierList_edit;
	}

	public void setCurrentTierList_edit(TierList currentTierList_edit) {
		this.currentTierList_edit = currentTierList_edit;
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
