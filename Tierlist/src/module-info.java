module Tierlist {
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.net.http;
	requires com.fasterxml.jackson.databind;
	
	opens application to javafx.graphics, javafx.fxml;
}
