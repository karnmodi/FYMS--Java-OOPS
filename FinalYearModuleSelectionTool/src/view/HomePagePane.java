package view;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.scene.image.Image;

public class HomePagePane extends GridPane {

	private Button btnStart;

	public HomePagePane() {
		// Set background image for the welcome tab
		BackgroundImage backgroundImage = new BackgroundImage(new Image("file:./bg.png", 0, 0, true, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(100, 100, true, true, true, true));

		this.setBackground(new Background(backgroundImage));

		this.setPadding(new Insets(20));
		this.setHgap(10);
		this.setVgap(10);

		Label lblTitle = new Label("Final Year Module Selection Software");
		lblTitle.setFont(Font.font("Verdana", 30));
		lblTitle.setTextFill(Color.web("#ffcc00"));
		lblTitle.setStyle("-fx-font-weight: bold;");

		Label lblDescription = new Label(
				"          A Software is fully made for the Final year Module Selection for all the students.\nIt can be used to select one Optional Module from the module list which are not compulsory.\n                              And All the stored details can be found in Load Pane.");
		lblDescription.setFont(Font.font("Verdana", 20));
		lblDescription.setTextFill(Color.WHITE);
		lblDescription.setStyle("-fx-font-weight: bold;");

		Label lblPD = new Label("Karan Falgun Modi\n        P2761604\n      © Copyright.");
		lblPD.setFont(Font.font("Verdana", 13));
		lblPD.setTextFill(Color.WHITE);

		// Add bouncing scale animation
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), lblTitle);
		scaleTransition.setFromX(1);
		scaleTransition.setFromY(1);
		scaleTransition.setToX(1.1);
		scaleTransition.setToY(1.1);
		scaleTransition.setAutoReverse(true);
		scaleTransition.setCycleCount(Timeline.INDEFINITE);
		scaleTransition.play();

		btnStart = new Button("Start");

		btnStart.setStyle("-fx-background-color: #3498db; " + "-fx-text-fill: white; " + "-fx-font-size: 20px; "
				+ "-fx-padding: 15px 30px; " + "-fx-border-radius: 8px; " + "-fx-background-radius: 8px;"
				+ "-fx-border-color: #2980b9; " + "-fx-border-width: 2px;");

		btnStart.setOnMouseEntered(event -> setCursor(javafx.scene.Cursor.HAND));
		btnStart.setOnMouseExited(event -> setCursor(javafx.scene.Cursor.DEFAULT));

		btnStart.setOnMouseEntered(event -> {
			Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(btnStart.scaleXProperty(), 1)),
					new KeyFrame(Duration.millis(100), new KeyValue(btnStart.scaleXProperty(), 1.2)),
					new KeyFrame(Duration.millis(200), new KeyValue(btnStart.scaleXProperty(), 1)));
			timeline.play();

			FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), btnStart);
			fadeTransition.setFromValue(0.0);
			fadeTransition.setToValue(1.0);
			fadeTransition.play();
		});

		this.add(lblTitle, 1, 0, 2, 1);
		this.add(lblDescription, 1, 0, 2, 1);
		this.add(lblPD, 1, 0, 2, 1);
		this.add(btnStart, 1, 0);

		GridPane.setVgrow(lblTitle, Priority.ALWAYS);
		GridPane.setHgrow(lblTitle, Priority.ALWAYS);
		GridPane.setVgrow(btnStart, Priority.ALWAYS);
		GridPane.setHgrow(btnStart, Priority.ALWAYS);
		Insets setM = new Insets(50);
		Insets settop = new Insets(150, 0, 0, 0);
		Insets settop1 = new Insets(300, 0, 0, 0);
		GridPane.setMargin(btnStart, setM);
		GridPane.setMargin(lblTitle, settop);
		GridPane.setMargin(lblDescription, settop1);
		setHalignment(btnStart, javafx.geometry.HPos.CENTER);
		setValignment(btnStart, javafx.geometry.VPos.BOTTOM);
		setHalignment(lblTitle, javafx.geometry.HPos.CENTER);
		setValignment(lblTitle, javafx.geometry.VPos.TOP);
		setHalignment(lblDescription, javafx.geometry.HPos.CENTER);
		setValignment(lblDescription, javafx.geometry.VPos.TOP);
		setHalignment(lblPD, javafx.geometry.HPos.RIGHT);
		setValignment(lblPD, javafx.geometry.VPos.BOTTOM);
	}

	public void addStartHandler(EventHandler<ActionEvent> handler) {
		btnStart.setOnAction(handler);
	}

	public Button getStartBtn() {
		return btnStart;
	}

}
