package view;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Course;
import model.StudentProfile;

public class OverviewSelectionPane extends GridPane {

    private String txtName;
    private String txtPNumber;
    private String txtEmail;
    private LocalDate txtDate;
    private Course txtCourse;
    private TextArea PersonalDetails;
    private TextArea SelectedModulesDetails;
    private TextArea ReservedModulesDetails;
    private Label lblPersonalDetails;
    private Label lblSelectedModulesDetails;
    private Label lblReservedModulesDetails;
    private Button btnSaveOverview;
    private Button btnReset;

    

    public OverviewSelectionPane() {
       
    	
        lblPersonalDetails = new Label("Student Personal Details :");
        PersonalDetails = new TextArea();
        PersonalDetails.setEditable(false);
        PersonalDetails.setMouseTransparent(true); 

        lblSelectedModulesDetails = new Label("Selected Modules :");
        SelectedModulesDetails = new TextArea();
        SelectedModulesDetails.setEditable(false);
        
        lblReservedModulesDetails  = new Label("Reserved Modules :");
        ReservedModulesDetails = new TextArea();
        ReservedModulesDetails.setEditable(false);
        
        btnSaveOverview = new Button("Save Overview");
        btnReset = new Button("Reset");
        GridPane.setHalignment(btnSaveOverview, HPos.CENTER);
        GridPane.setHalignment(btnReset, HPos.LEFT);
        
        Insets margins = new Insets(20);
        
        PersonalDetails.setPrefSize(700, 280);
        SelectedModulesDetails.setPrefSize(700, 300);
        ReservedModulesDetails.setPrefSize(700, 300);
        
        PersonalDetails.setFont(Font.font(15));
        ReservedModulesDetails.setFont(Font.font(15));
        SelectedModulesDetails.setFont(Font.font(15));
        
        
        
        VBox pd = new VBox(10);
        pd.getChildren().addAll(lblPersonalDetails, PersonalDetails );

        VBox scd = new VBox(10);
        scd.getChildren().addAll(lblSelectedModulesDetails, SelectedModulesDetails );

        VBox rcd = new VBox(10);
        rcd.getChildren().addAll(lblReservedModulesDetails, ReservedModulesDetails );
        
        setHgrow(pd, Priority.ALWAYS);
        
        setHgrow(scd, Priority.ALWAYS);
        setVgrow(scd, Priority.ALWAYS);
        
        setHgrow(rcd, Priority.ALWAYS);
        setVgrow(rcd, Priority.ALWAYS);
        
        this.add(pd, 0, 0, 2, 1);
        
        this.add(scd, 0, 1);
        this.add(rcd, 1, 1);
        this.add(btnSaveOverview, 0, 2,2,1);
        this.add(btnReset, 0, 2,2,1);
        
        setMargin(pd,margins);
        setMargin(scd,margins);
        setMargin(rcd,margins);
        setMargin(btnSaveOverview,margins);
        setMargin(btnReset,margins);
        
        
    }
    
    public TextArea getPersonalDetails() {
    	return PersonalDetails;
    }
    public TextArea getSelectedModules() {
    	return SelectedModulesDetails;
    }
    public TextArea getReservedModules() {
    	return ReservedModulesDetails;
    }

    public void setStudent(StudentProfile student) {
    	this.txtName = student.getStudentName();
    	this.txtPNumber = student.getStudentPnumber();
    	this.txtEmail = student.getStudentEmail();
    	this.txtDate = student.getSubmissionDate();
    	this.txtCourse = student.getStudentCourse();
    }
    
    
    public String toStringPD() {
    	return "Name : " + txtName + "\nP Number : " + txtPNumber + "\nEmail : " + txtEmail + "\nDate : " + txtDate + "\nCourse : " + txtCourse; 
    	}
    
    public void Reset() {
    	PersonalDetails.setText("");
    	SelectedModulesDetails.setText("");
    	ReservedModulesDetails.setText("");
    }
    
    public void setPersonalDetailsText(String details) {
        PersonalDetails.setText(details);
    }
    
    public void setSelectedModules(String SMDetails) {
    	SelectedModulesDetails.setText(SMDetails);
    }
    public void setReservedModules(String RMDetails) {
    	ReservedModulesDetails.setText(RMDetails);
    }
    
    public void addResetHandler(EventHandler<ActionEvent> handler) {
    	btnReset.setOnAction(handler);
    }

    public void addSubmitHandler(EventHandler<ActionEvent> handler) {
		btnSaveOverview.setOnAction(handler);
	}
    
}

