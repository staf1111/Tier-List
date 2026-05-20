package services.screenManager;


import java.util.EnumMap;
import java.util.Map;

class Modal {
	
	private final Map<ModalType, String> index = new EnumMap<>(ModalType.class);
	
	Modal()
	{
		//screen
		index.put(ModalType.API_POPUP, "/fxml/modal/");
		index.put(ModalType.SETTINGS, "/fxml/modal/");

	}

	public String getPath(ModalType type) {
        return index.get(type);
    }
	
}
