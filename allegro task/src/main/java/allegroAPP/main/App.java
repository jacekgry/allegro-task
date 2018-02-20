package allegroAPP.main;

import java.util.Locale;

import allegroAPP.utils.FxmlUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class App extends Application {

	public static void main(String[] args) {
		launch(args);		
	}

	@Override
	public void start(Stage stage) throws Exception {
		Locale.setDefault(new Locale("en", "EN"));
		Pane mainAnchorPane = FxmlUtils.fxmlLoader("/fxml/Main.fxml");
		Scene scene = new Scene(mainAnchorPane);

		stage.setHeight(820.0);
		stage.setWidth(1200.0);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.setTitle("Task");
		stage.show();		
	}



}
