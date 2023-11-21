 
package main;

import controller.FinalYearOptionsController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.StudentProfile;
import view.FinalYearOptionsRootPane;
import view.HomePagePane;

public class ApplicationLoader extends Application {

    private FinalYearOptionsRootPane view;
    private HomePagePane main;
    private Stage mainStage;

    @Override
    public void init() {
        // create view and model and pass their references to the controller
        view = new FinalYearOptionsRootPane();
        main = new HomePagePane();
        StudentProfile model = new StudentProfile();

        new FinalYearOptionsController(view, model);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // sets min width and height for the stage window
        stage.setMinWidth(530);
        stage.setMinHeight(500);
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.setTitle("Final Year Module Selection Tool");
        stage.getIcons().add(new Image("file:./icon.png"));

        mainStage = stage;

        stage.setScene(new Scene(main));

        main.getStartBtn().setOnAction(event -> {
            try {
                start1();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        stage.show();
    }

    public void start1() throws Exception {
        mainStage.hide();

        Stage actualViewStage = new Stage();

        actualViewStage.setScene(new Scene(view));

        actualViewStage.setTitle("Final Year Module Selection Tool");
        actualViewStage.getIcons().add(new Image("file:./icon.png"));
        actualViewStage.setMinWidth(530);
        actualViewStage.setMinHeight(500);
        actualViewStage.setMaximized(true);

        actualViewStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

