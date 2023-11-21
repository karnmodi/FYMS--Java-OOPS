package view;

import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Module;

public class ResearveModulePane extends GridPane{

	private ListView<Module> USMB3,RSMB3;
	private Label lblUSMB3, lblRSMB3, lblLabel;
	private Button btnAdd, btnRemove, btnConfirm;
	
	
	public ResearveModulePane() {
		
		this.setVgap(20);
		this.setHgap(20);
		
		USMB3 = new ListView<>();
		RSMB3 = new ListView<>();
		lblUSMB3 = new Label("Unselected Block 3/4 Modules : ");
		lblRSMB3 = new Label("Reserved Block 3/4 Modules : ");
		lblLabel= new Label("Reserve one Optional Modules : ");
		btnAdd = new Button("Add");
		btnRemove = new Button("Remove");
		btnConfirm = new Button("Confirm");
		
		 Insets TLmargins = new Insets(20,0,0,20);
		 Insets TRmargins= new Insets(20,20,0,0);
		 Insets margins= new Insets(0,20,20,20);
		
		VBox usmb3 = new VBox(10);
        usmb3.getChildren().add(lblUSMB3);
        usmb3.getChildren().add(USMB3);
        
        VBox rsmb3 = new VBox(10);
        rsmb3.getChildren().add(lblRSMB3);
        rsmb3.getChildren().add(RSMB3);
		
        HBox lblBtn = new HBox(10);
        lblBtn.getChildren().addAll(lblLabel, btnAdd, btnRemove, btnConfirm);

        GridPane.setHalignment(lblBtn, HPos.CENTER);
       
        USMB3.setPrefSize(200, 600);
        RSMB3.setPrefSize(200, 600);
        
        
        setHgrow(usmb3, Priority.ALWAYS);
        setHgrow(rsmb3, Priority.ALWAYS);

        setVgrow(usmb3, Priority.ALWAYS);
        setVgrow(rsmb3, Priority.ALWAYS);

        
		this.add(usmb3, 0, 0);
		this.add(rsmb3, 1, 0);
		this.add(lblBtn, 1, 1,2,2);
		
		setMargin(usmb3, TLmargins);
		setMargin(rsmb3, TRmargins);
		setMargin(lblBtn, margins);
		
		
	}
	

	public ListView<Module> getUSMB3() {
		return USMB3;
	}
	
	public ListView<Module> getRSMB3() {
		return RSMB3;
	}
	
	public void setUSMB3(Collection<Module> collection) {
		ObservableList<Module> observableList = FXCollections.observableArrayList(collection);
	    USMB3.setItems(observableList);
	}

	public void setRSMB3(Collection<Module> collection) {
		ObservableList<Module> observableList = FXCollections.observableArrayList(collection);
		RSMB3.setItems(observableList);
	}
	
	public void Reset(Collection<Module> collection) {
		ObservableList<Module> observableList = FXCollections.observableArrayList(collection);
	    USMB3.setItems(observableList);
	    
	    ObservableList<Module> observableList1 = FXCollections.observableArrayList(collection);
		RSMB3.setItems(observableList1);
	    
	}
	
	public void addAddButtonHandler(EventHandler<ActionEvent> handler) {
		btnAdd.setOnAction(handler);
	}

	public void addRemoveButtonHandler(EventHandler<ActionEvent> handler) {
		btnRemove.setOnAction(handler);
	}

	public void addConfirmButtonHandler(EventHandler<ActionEvent> handler) {
		btnConfirm.setOnAction(handler);
	}
	
}
