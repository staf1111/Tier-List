package services.data;

import java.util.List;

import application.widget.Tier;
import model.ItemsM;

public class TierListManager { // class qui stocke la tierlist
	
	private List<Tier> tier;
	private List<ItemsM> items;
	
	public TierListManager(){}

	public List<Tier> getTier() {
		return tier;
	}

	public void setTier(List<Tier> tier) {
		this.tier = tier;
	}

	public List<ItemsM> getItems() {
		return items;
	}

	public void setItems(List<ItemsM> items) {
		this.items = items;
	}
}
