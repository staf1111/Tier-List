package model;

import java.io.Serializable;

public interface ItemsM extends Serializable{ 
	public String getName();
	
	public String getContent();
	
	public void setContent(String content);
}