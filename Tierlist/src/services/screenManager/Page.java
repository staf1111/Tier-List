package services.screenManager;


import java.util.EnumMap;
import java.util.Map;

class Page {
	
	private final Map<PageType, String> index = new EnumMap<>(PageType.class);
	
	Page()
	{
		//screen
		index.put(PageType.HOME, "/application/views/home.fxml");
		index.put(PageType.CREATETIERLIST, "/application/views/createTierList.fxml");
		index.put(PageType.PLAY, "/application/views/play.fxml");
		index.put(PageType.MYTIERLIST, "/application/views/mesTiersLists.fxml");
	}

	public String getPath(PageType type) {
        return index.get(type);
    }
	
}
