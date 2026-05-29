package services.data;

import java.util.List;

import model.Items;
import model.Tier;

public class TierListManager { // class qui stocke la tierlist
	
	private List<Tier> tier;
	private List<Items> items;
	
	public TierListManager(){}

	public List<Tier> getTier() {
		return tier;
	}

	public void setTier(List<Tier> tier) {
		this.tier = tier;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}
}
