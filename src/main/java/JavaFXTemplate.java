import javafx.util.Duration;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXTemplate extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
        primaryStage.setTitle("Baccarat Game!!!");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/fxml1.fxml"));
        Scene s1 = new Scene(root, 800,500);
        s1.getStylesheets().add("/CSS/style1.css");
    // s1.getRoot().setStyle("-fx-font-family: 'Verdana';" + "-fx-focus-color: transparent;" + "-fx-background-color: #2F4F4F;");
        primaryStage.setScene(s1);
        primaryStage.show();
		// Thread t = new Thread(()-> {A_IDS_A_15solver ids = new A_IDS_A_15solver();});
		// t.start();
	}
}
