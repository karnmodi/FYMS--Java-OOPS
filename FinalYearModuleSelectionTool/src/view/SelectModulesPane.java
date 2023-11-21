package view;


import model.Module;

import java.util.*;

import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
/**
 * The SelectModulesPane class represents a JavaFX GridPane for selecting and managing academic modules.
 * It includes ListView components for displaying selected and unselected modules for different blocks,
 * as well as buttons for adding, removing, resetting, and submitting module selections.
 * The current credit is also displayed in a TextField.
 *
 * @author Karan Falgun Modi
 * @version 1.0
 */
public class SelectModulesPane extends GridPane {

    // ListView components for selected and unselected modules
    private ListView<Module> SMBK1, SMBK2, SMBK3, USMBK4;

    // TextField for displaying current credit
    private TextField txtCurrentCrds;

    // Buttons for interaction
    private Button btnAdd, btnRemove, btnReset, btnSubmit;

    // Labels for different module categories
    private Label lblSMBK1, lblSMBK2, lblSMBK3, lblUSMBK4, lblCurrentCredit, lblBlk34;

    /**
     * Constructs a SelectModulesPane with all the necessary components and layouts.
     * Initializes ListViews, Labels, TextField, and Buttons for module selection and management.
     */

	public SelectModulesPane() {

		this.setVgap(20);
		this.setHgap(20);

		SMBK1 = new ListView<>();
		SMBK2 = new ListView<>();
		SMBK3 = new ListView<>();
		USMBK4 = new ListView<>();
		txtCurrentCrds = new TextField();
		txtCurrentCrds.setMaxWidth(50);
		txtCurrentCrds.getStyleClass().add("uneditable-text-field");
		txtCurrentCrds.setEditable(false);

		lblSMBK1 = new Label("Selected Block 1 Modules: ");
		lblSMBK2 = new Label("Selected Block 2 Modules: ");
		lblSMBK3 = new Label("Selected Block 3/4 Modules: ");
		lblUSMBK4 = new Label("Unselected Block 3/4 Modules: ");
		lblCurrentCredit = new Label("Current Credit: ");
		lblBlk34 = new Label("Block 3/4: ");
		btnAdd = new Button("Add");
		btnRemove = new Button("Remove");
		btnReset = new Button("Reset");
		btnSubmit = new Button("Submit");

		Insets TopLeftmargins = new Insets(20, 0, 0, 20);
		Insets TopRightmargins = new Insets(20, 20, 0, 0);
		Insets BottomLeftmargins = new Insets(0, 0, 20, 20);
		Insets BottomRightmargins = new Insets(0, 20, 20, 0);
		Insets BottomMargins = new Insets(0, 0, 20, 0);

		VBox smbk1 = new VBox(10);
		smbk1.getChildren().add(lblSMBK1);
		smbk1.getChildren().add(SMBK1);

		VBox smbk2 = new VBox(10);
		smbk2.getChildren().add(lblSMBK2);
		smbk2.getChildren().add(SMBK2);

		VBox smbk3 = new VBox(10);
		smbk3.getChildren().add(lblSMBK3);
		smbk3.getChildren().add(SMBK3);

		VBox usmbk4 = new VBox(10);
		usmbk4.getChildren().add(lblUSMBK4);
		usmbk4.getChildren().add(USMBK4);
		HBox BtnLbl = new HBox(10);
		BtnLbl.getChildren().addAll(lblBlk34, btnAdd, btnRemove);
		usmbk4.getChildren().add(BtnLbl);
		GridPane.setHalignment(usmbk4, HPos.CENTER);

		HBox credits = new HBox(10);
		credits.getChildren().addAll(lblCurrentCredit, txtCurrentCrds);

		GridPane.setHalignment(credits, HPos.CENTER);

		HBox btns = new HBox(10);
		btns.getChildren().add(btnReset);
		btns.getChildren().add(btnSubmit);


		smbk1.setPrefWidth(150);
		smbk1.setPrefHeight(100);

		smbk2.setPrefWidth(150);
		smbk2.setPrefHeight(100);

		smbk3.setPrefWidth(150);
		smbk3.setPrefHeight(100);

		usmbk4.setPrefWidth(150);
		usmbk4.setPrefHeight(100);

		setHgrow(smbk1, Priority.ALWAYS);
		setHgrow(smbk2, Priority.ALWAYS);
		setHgrow(smbk3, Priority.ALWAYS);
		setHgrow(usmbk4, Priority.ALWAYS);

		setVgrow(smbk1, Priority.ALWAYS);
		setVgrow(smbk2, Priority.ALWAYS);
		setVgrow(smbk3, Priority.ALWAYS);
		setVgrow(usmbk4, Priority.ALWAYS);

		this.add(smbk1, 0, 0);
		this.add(smbk2, 0, 1);
		this.add(smbk3, 1, 1);
		this.add(usmbk4, 1, 0);
		this.add(credits, 1, 2);
		this.add(btns, 1, 3);

		setMargin(smbk1, TopLeftmargins);
		setMargin(smbk3, BottomRightmargins);
		setMargin(smbk2, BottomLeftmargins);
		setMargin(usmbk4, TopRightmargins);
		setMargin(btns, BottomMargins);

	}
	
	public ListView<Module> getSMBK1(){
		return SMBK1;
	}

	public ListView<Module> getSMBK2(){
		return SMBK2;
	}
	
	public ListView<Module> getSMBK3(){
		return SMBK3;
	}

	public ListView<Module> getUSMBK4(){
		return USMBK4;
	}
	
	public void setSMBK1(Collection<Module> collection) {
		ObservableList<Module> observableList = FXCollections.observableArrayList(collection);
	    SMBK1.setItems(observableList);
	}


	public void setSMBK2(Collection<Module> collection) {
		ObservableList<Module> observableList = FXCollections.observableArrayList(collection);
	    SMBK2.setItems(observableList);
	}

	
	public void setSMBK3(Collection<Module> collection) {
		ObservableList<Module> observableList = FXCollections.observableArrayList(collection);
	    SMBK3.setItems(observableList);
	}

	
	public void setUSMBK4(Collection<Module> collection) {
		ObservableList<Module> observableList = FXCollections.observableArrayList(collection);
	    USMBK4.setItems(observableList);
	}
	
	public void settxtCurrentCrds(Integer a) {
		txtCurrentCrds.setText(Integer.toString(a));
	}
	
	public void addAddButtonHandler(EventHandler<ActionEvent> handler) {
		btnAdd.setOnAction(handler);
	}

	public void addRemoveButtonHandler(EventHandler<ActionEvent> handler) {
		btnRemove.setOnAction(handler);
	}
	
	public void addResetListViewsHandler(EventHandler<ActionEvent> handler) {
		btnReset.setOnAction(handler);
	}

	public void addSubmitHandler(EventHandler<ActionEvent> handler) {
		btnSubmit.setOnAction(handler);
	}
	
	


}
