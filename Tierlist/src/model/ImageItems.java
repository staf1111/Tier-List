package model;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ImageItems implements ItemsM{
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String path;
	
	private String year = "";
		
	private byte[] imageData;
	
	public ImageItems(String name, String path, String year)
	{
		this(name,path);
		this.year = year;
	}
	
	public ImageItems(String name, String path) {
		this.name = name;
		this.path = path;
		
		if("Image local".equals(name))
		{
			try {
				imageData = Files.readAllBytes(Path.of(new URI(path).getPath()));

			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public String getContent() {
		return path;
	}
	@Override
	public String getName() {
		return name;
	}
	
	public byte[] getImageData() {
		return imageData;
	}

	@Override
	public String toString() {
		return "ImageItems [path=" + path + ", year=" + year + ", getName()=" + getName() + "]";
	}
}
