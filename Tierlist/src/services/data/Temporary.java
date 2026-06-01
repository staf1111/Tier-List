package services.data;

import java.util.List;

import application.widget.Tierlist_Preview;

public class Temporary {
	private static Temporary instance;
	
	private List<Tierlist_Preview> tempPreview;
	private TierList currentTierList_play, currentTierList_edit;

	public static void init()
	{
		if(instance == null) {
			instance = new Temporary();
		}
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
	
	
	public List<Tierlist_Preview> getTemp() {
		return tempPreview;
	}



	public void setTemp(List<Tierlist_Preview> temp) {
		this.tempPreview = temp;
	}



	public static Temporary getInstance()
	{
		if(instance == null) 
		{
			instance = new Temporary();
		}
		
		return instance;
	}
}
