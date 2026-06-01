package model;

import services.data.TierList;

public class TierList_previewM {

	TierList tierlist;
	public TierList_previewM(TierList tierlist)
	{
		this.tierlist = tierlist;
	}
	
	public TierList getTierlist()
	{
		return this.tierlist;
	}
}
