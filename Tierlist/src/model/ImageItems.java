package model;

public class ImageItems extends Items {

	private String path;
	private String year = "";
	
	public ImageItems(String name, String path, String year)
	{
		this(name,path);
		this.year = year;
	}
	
	public ImageItems(String name, String path) {
		super(name, ItemsType.IMAGE);
		this.path = path;
	}
	public String getPath() {
		return path;
	}

	@Override
	public String toString() {
		return "ImageItems [path=" + path + ", year=" + year + ", getName()=" + getName() + ", getType()=" + getType() + "]";
	}

}
