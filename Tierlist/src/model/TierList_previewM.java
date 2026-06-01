package model;

import services.data.TierList;

public class TierList_previewM {

	TierList tierlist;
	String path;
	public TierList_previewM(TierList tierlist, String path)
	{
		this.tierlist = tierlist;
		this.path = path;
	}
	
	public TierList getTierlist()
	{
		return this.tierlist;
	}
	
	public String getPath()
	{
		return path;
	}
}
