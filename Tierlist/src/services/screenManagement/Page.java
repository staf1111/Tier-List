package services.screenManagement;


import java.util.EnumMap;
import java.util.Map;

class Page {
	
	private final Map<PageType, String> index = new EnumMap<>(PageType.class);
	
	Page()
	{
		//screen
		index.put(PageType.HOME, "/fxml/page/home.fxml");
		index.put(PageType.CREATETIERLIST, "/fxml/page/createTierList.fxml");
		index.put(PageType.PLAY, "/fxml/page/play.fxml");
	}

	public String getPath(PageType type) {
        return index.get(type);
    }
	
}
