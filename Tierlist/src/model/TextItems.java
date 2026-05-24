package model;

public class TextItems extends Items {

	private String content;
	
	public TextItems(String name, String text) {
		super(name, ItemsType.TEXT);
		this.content = text;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "TextItems [content=" + content + ", getName()=" + getName() + ", getType()=" + getType() + "]";
	}
}
