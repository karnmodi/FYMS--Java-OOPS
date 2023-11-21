package view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

public class LoadDataPane extends GridPane {

	private ListView<String> LoadUsers;
	private TextArea UserData;
	private Button BtngetUserData;

	public LoadDataPane() {

		LoadUsers = new ListView<String>();
		UserData = new TextArea();
		BtngetUserData = new Button("get User Data");

		UserData.setEditable(false);
		Insets Margin = new Insets(20);

		HBox CD = new HBox(20);
		CD.getChildren().addAll(LoadUsers, createCenteredButton(BtngetUserData), UserData);

		setMargin(CD, Margin);

		LoadUsers.setMaxWidth(360);
		BtngetUserData.setMaxWidth(100);
		UserData.setMaxWidth(1000);

		LoadUsers.setPrefWidth(360);
		BtngetUserData.setPrefWidth(100);
		UserData.setPrefWidth(1000);

		LoadUsers.setMinWidth(130);
		BtngetUserData.setMinWidth(100);
		UserData.setMinWidth(130);

		setHgrow(CD, Priority.ALWAYS);

		setVgrow(CD, Priority.ALWAYS);

		this.add(CD, 0, 0);

	}

	private StackPane createCenteredButton(Button button) {
		StackPane stackPane = new StackPane(button);
		stackPane.setAlignment(Pos.CENTER);
		return stackPane;
	}

	public ListView<String> getLoadUsers() {
		return LoadUsers;
	}

	public TextArea getUserData() {
		return UserData;
	}

	public Button getBtnGetUserData() {
		return BtngetUserData;
	}

	public void updateFileList() {
		File projectDirectory = new File(System.getProperty("user.dir"));
		File[] files = projectDirectory.listFiles((dir, name) -> name.endsWith(".dat"));

		if (files != null) {
			List<String> fileNames = Arrays.stream(files).map(File::getName).toList();
			LoadUsers.getItems().addAll(fileNames);
		}
	}

	public void displayFileContent(File file) {
		try {
			String content = Files.readString(file.toPath());
			UserData.setText(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}