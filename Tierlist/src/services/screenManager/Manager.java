package services.screenManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.data.Settings;

public class Manager {
	private static Manager instance; // on crée une instance pour le manager
    private static Stage stage; 

	private static final Page PAGE = new Page(); //initialisation de la class Page
	private static final Theme THEME = new Theme();
	
	private static Parent root;
	
	private static final PageType DEFAULT = PageType.HOME;
	
	private static final int WIDTH_PREF = 1000;	
	private static final int HEIGHT_PREF = 650;
	
	private PageType current; // type de la page en cours
	private ThemeList currentT;
	
	public static void init(Stage primaryStage) { // on initalise le root
        if (instance == null) {
            instance = new Manager(primaryStage);
        }
    }
	
	private Manager(Stage pr) //constructeur de la page
	{
		stage = pr;
		setPage(DEFAULT);
	}
	
	public void setPage(PageType PT) // changement de page
	{	
		try {
            String PATH = PAGE.getPath(PT); // recherche de la page
            if (PATH == null) {
                throw new IllegalArgumentException("Page inconnue: " + PT); // la page voulu n'existe pas
            }
            
            root = FXMLLoader.load(Manager.class.getResource(PATH)); // on itilisalise le rot
    	
            if(root != null) //on initialise le thème de la page
    		{
    			for (ThemeList t : ThemeList.values())
    			{
    				if(t.name() == Settings.getInstance().getTheme().toString()) //on prend le theme qui est enregistrer dans les paramètres
    				{
    					setTheme(t);
    					break;
    				}
    			}
    		}
  
            stage.setResizable(false); //on empèche le redimensionnement de la page
        	
            if (stage.getScene() == null) { // si null cela veut dire que la scene n'existe pas

            	Scene scene = new Scene(root, WIDTH_PREF, HEIGHT_PREF);

                stage.setScene(scene);
            } else { // on change de scene
                stage.getScene().setRoot(root);
            }

            if (instance != null) {
                instance.current = PT;
            }
            
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public void setTheme(ThemeList th) //fonction pour changer de theme
	{
		this.currentT = th;
		
		root.getStylesheets().clear();
		root.getStylesheets().setAll(getClass().getResource(THEME.getTheme(th)).toExternalForm());
	}
	
	
	public static Manager getInstance() {
        return instance;
    }
}
