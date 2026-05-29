package model;

public class ImageItems implements Items{
	private String name;
	
	private String path;
	
	private String year = "";
	
	public ImageItems(String name, String path, String year)
	{
		this(name,path);
		this.year = year;
	}
	
	public ImageItems(String name, String path) {
		this.name = name;
		this.path = path;
	}
	
	@Override
	public String getContent() {
		return path;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	@Override
	public String toString() {
		return "ImageItems [path=" + path + ", year=" + year + ", getName()=" + getName() + "]";
	}
}
