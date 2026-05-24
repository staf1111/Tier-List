package model;

public class Items {
	private String name;
	private ItemsType type;
	
	public Items(String name, ItemsType type)
	{
		this.name = name;
		this.type = type;
	}


	public String getName() {
		return name;
	}


	public ItemsType getType() {
		return type;
	}


	@Override
	public String toString() {
		return "Items [name=" + name + ", type=" + type + "]";
	}
}
