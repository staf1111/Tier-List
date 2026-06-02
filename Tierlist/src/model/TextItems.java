package model;

public class TextItems implements ItemsM {
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String content;
	
	public TextItems(String name, String text) {
		this.name = name;
		this.content = text;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getContent() {
		return content;
	}
	@Override
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "TextItems [content=" + content + ", getName()=" + getName() + "]";
	}
}
