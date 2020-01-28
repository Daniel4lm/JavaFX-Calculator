package calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Calculator extends Application{

	public static void main(String[] args) {
				
		launch(args);
	}

	@Override
	public void start(Stage glavniPrikaz) throws Exception {		
		Parent glavni = FXMLLoader.load(getClass().getResource("fxcalculator.fxml"));
        glavniPrikaz.setTitle("Kalkulator ver. 0.4");
        glavniPrikaz.setResizable(false);
        glavniPrikaz.setScene(new Scene(glavni));
        glavniPrikaz.show();
	}

}
