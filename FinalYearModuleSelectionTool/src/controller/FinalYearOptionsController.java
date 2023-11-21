package controller;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import view.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseButton;
import javafx.stage.DirectoryChooser;
import model.Block;
import model.Course;
import model.Module;
import model.Name;
import model.StudentProfile;

public class FinalYearOptionsController {

	// fields to be used throughout class
	private FinalYearOptionsRootPane view;
	private StudentProfile model;

	private CreateStudentProfilePane cspp;
	private SelectModulesPane smp;
	private ResearveModulePane rmp;
	private OverviewSelectionPane osp;
	private FinalYearOptionsMenuBar mstmb;
	private ObservableList<Module> module = FXCollections.observableArrayList();

	public FinalYearOptionsController(FinalYearOptionsRootPane view, StudentProfile model) {
		// initialise view and model fields
		this.view = view;
		this.model = new StudentProfile();

		cspp = view.getCreateStudentProfilePane();

		smp = view.getSelectModulesPane();

		osp = view.getOverviewStudentPane();

		rmp = view.getReserveModulePane();

		mstmb = view.getModuleSelectionToolMenuBar();



		// add courses to combobox in create student profile pane using the

		// buildModulesAndCourses helper method below
		cspp.addCourseDataToComboBox(buildModulesAndCourses());

		this.attachEventHandlers();

	}

	public void showErrorPopup(String errorMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(errorMessage);
		alert.showAndWait();
	}

	public void showSuccessfulPopup(String SuccessMessage) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("SuccessFull!!");
		alert.setHeaderText("Congratulations!!");
		alert.setContentText(SuccessMessage);
		alert.showAndWait();
	}

	private void UpdateCredit() {
		int Credits = 0;

		for (Module module : smp.getSMBK1().getItems()) {
			Credits += module.getModuleCredits();
		}

		for (Module module : smp.getSMBK2().getItems()) {
			Credits += module.getModuleCredits();
		}

		for (Module module : smp.getSMBK3().getItems()) {
			Credits += module.getModuleCredits();
		}

		smp.settxtCurrentCrds(Credits);

	}

	// helper method - used to attach event handlers
	private void attachEventHandlers() {
		// attach an event handler to the create student profile pane
		cspp.addCreateStudentProfileHandler(new CreateStudentProfileHandler());

		smp.addResetListViewsHandler(new SMPResetListViewsHandler());
		smp.addSubmitHandler(new SMPSubmitbtnHandler());
		smp.addAddButtonHandler(new SMPAddButtonHandler());
		smp.addRemoveButtonHandler(new SMPRemoveButtonHandler());

		rmp.addAddButtonHandler(new RMPAddButtonHandler());
		rmp.addRemoveButtonHandler(new RMPRemoveButtonHandler());
		rmp.addConfirmButtonHandler(new RMPConfirmButtonHandler());

		osp.addResetHandler(new OSPResetHandler());
		osp.addSubmitHandler(new OSPSubmitHandler());

		mstmb.addSaveHandler(new MSTMBSaveHandler());
		mstmb.addLoadHandler(new MSTMBLoadHandler());
		mstmb.addAboutHandler(new MSTMBAboutHandler());

//		hpp.addStartHandler(new START());
//		hpp.addStart1Handler(new START1());

		// attach an event handler to the menu bar that closes the application
		mstmb.addExitHandler(e -> System.exit(0));
	}


	// event handler (currently empty), which can be used for creating a profile
	private class CreateStudentProfileHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Name name = cspp.getStudentName();
			String PNumber = cspp.getStudentPnumber();
			String email = cspp.getStudentEmail();
			Course selectedCourse = cspp.getSelectedCourse();
			LocalDate date = cspp.getStudentDate();

			model.setStudentName(name);
			model.setStudentPnumber(PNumber);
			model.setStudentEmail(email);
			model.setStudentCourse(selectedCourse);
			model.setSubmissionDate(date);

			if (selectedCourse != null) {
				module.clear();
				module.addAll(selectedCourse.getAllModulesOnCourse());

				ObservableList<Module> smbk1List = FXCollections.observableArrayList();
				module.stream().filter(module -> module.getRunPlan() == Block.BLOCK_1).forEach(smbk1List::add);
				smp.setSMBK1(smbk1List);

				ObservableList<Module> smbk2List = FXCollections.observableArrayList();
				module.stream().filter(module -> module.getRunPlan() == Block.BLOCK_2).forEach(smbk2List::add);
				smp.setSMBK2(smbk2List);

				ObservableList<Module> smbk3List = FXCollections.observableArrayList();
				module.stream().filter(module -> module.getRunPlan() == Block.BLOCK_3_4 && module.isMandatory())
						.forEach(smbk3List::add);
				smp.setSMBK3(smbk3List);

				ObservableList<Module> usmbk4List = FXCollections.observableArrayList();
				module.stream().filter(module -> module.getRunPlan() == Block.BLOCK_3_4 && !module.isMandatory())
						.forEach(usmbk4List::add);
				smp.setUSMBK4(usmbk4List);
			}

			UpdateCredit();
			osp.setStudent(model);

			if (cspp.getStudentPnumber().length() <= 1) {
				cspp.getStudentPnumberTextField().requestFocus();
				showErrorPopup("Add PNumber!!");

			}
			else if (!cspp.getStudentPnumber().toUpperCase().startsWith("P")) {
				cspp.getStudentPnumberTextField().requestFocus();
				showErrorPopup("Enter Valid Pnumber which starts from 'P'!!");
				
			}

			else if (cspp.getStudentName().getFirstName().isEmpty()) {
				cspp.getStudentFirstNameTextField().requestFocus();
				showErrorPopup("Add First Name!!");
			}

			else if (cspp.getStudentName().getFamilyName().isEmpty()) {
				cspp.getStudentLastNameTextField().requestFocus();
				showErrorPopup("Add Family Name!!");
			}
			else if (!cspp.getStudentEmail().toLowerCase().endsWith(".com")) {
				cspp.getStudentEmailTextField().requestFocus();
				showErrorPopup("Please enter valid Email.");
			}

			else {
				String personalDetails = osp.toStringPD();
				osp.setPersonalDetailsText(personalDetails);
				view.changeTab(1);
			}
		}
	}

	private class SMPAddButtonHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			if (smp.getSMBK3().getItems().size() < 2) {
				Collection<Module> selectedItems = smp.getUSMBK4().getSelectionModel().getSelectedItems();
				smp.getSMBK3().getItems().addAll(selectedItems);
				smp.getUSMBK4().getItems().removeAll(selectedItems);
				UpdateCredit();
			} else {
				showErrorPopup("One Module can be added as an Optional module.");
			}
		}
	}

	private class SMPRemoveButtonHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Collection<Module> removeList = smp.getSMBK3().getSelectionModel().getSelectedItems();

			removeList.forEach(module -> {
				if (module.isMandatory()) {
					showErrorPopup("Cannot remove a mandatory module.");
				} else {
					smp.getSMBK3().getItems().remove(module);
					smp.getUSMBK4().getItems().add(module);
					UpdateCredit();
				}
			});
		}
	}

	private class SMPResetListViewsHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			ObservableList<Module> smbk3List = FXCollections.observableArrayList();

			module.stream().filter(module -> module.getRunPlan() == Block.BLOCK_3_4 && module.isMandatory())
					.forEach(smbk3List::add);

			smp.setSMBK3(smbk3List);
			UpdateCredit();

			ObservableList<Module> usmbk4List = FXCollections.observableArrayList();

			module.stream().filter(module -> module.getRunPlan() == Block.BLOCK_3_4 && !module.isMandatory())
					.forEach(usmbk4List::add);

			smp.setUSMBK4(usmbk4List);
		}
	}

	private class SMPSubmitbtnHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			if (smp.getSMBK3().getItems().size() == 1) {
				showErrorPopup("Kindly Select one Optional Module.");
			} else {
				Collection<Module> allSMBK3List = smp.getUSMBK4().getItems();
				rmp.setUSMB3(allSMBK3List);

				Collection<Module> allSelectedModules = new ArrayList<>();
				allSelectedModules.addAll(smp.getSMBK1().getItems());
				allSelectedModules.addAll(smp.getSMBK2().getItems());
				allSelectedModules.addAll(smp.getSMBK3().getItems());

				StringBuilder moduleDetails = new StringBuilder();
				moduleDetails.append("Selected Modules:\n===============\n\n");

				int[] i = { 1 };
				allSelectedModules.forEach(m -> moduleDetails.append(i[0]++ + m.actualToString()).append("\n"));

				osp.setSelectedModules(moduleDetails.toString());
				view.changeTab(2);
				
				ObservableList<Module> smbk1List = FXCollections.observableArrayList();

				module.stream().filter(module -> module.getRunPlan() == Block.BLOCK_1).forEach(smbk1List::add);

				smp.setSMBK1(smbk1List);
			}
		}
	}

	private class RMPAddButtonHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {

			if (rmp.getRSMB3().getItems().size() < 1) {
				Collection<Module> SelectedItem = rmp.getUSMB3().getSelectionModel().getSelectedItems();
				rmp.getRSMB3().getItems().addAll(SelectedItem);
				rmp.getUSMB3().getItems().removeAll(SelectedItem);

			}

			else {
				showErrorPopup("One Module can be added as a Reserve module.");
			}
		}
	}

	private class RMPRemoveButtonHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

			Collection<Module> RemoveList = rmp.getRSMB3().getSelectionModel().getSelectedItems();

			for (Module module : RemoveList) {

				rmp.getRSMB3().getItems().remove(module);
				rmp.getUSMB3().getItems().add(module);

			}
		}
	}

	private class RMPConfirmButtonHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {

			if (rmp.getRSMB3().getItems().size() == 0) {
				showErrorPopup("Kindly Reserve one Module.");
			} else {
				Collection<Module> AllReservedModules = rmp.getRSMB3().getItems();

				StringBuilder rmdetails = new StringBuilder();

				rmdetails.append("Reserved Modules:\n===============\n\n");
				int i = 1;
				for (Module m : AllReservedModules) {
					rmdetails.append(i).append(m.actualToString()).append("\n");
					i++;
				}
				osp.setReservedModules(rmdetails.toString());

				view.changeTab(3);
			}
		}
	}

	private class OSPResetHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {

			cspp.Reset();

			ObservableList<Module> smbk1List = FXCollections.observableArrayList();
			for (Module module : module) {
				if (module.getRunPlan() == Block.BLOCK_1) {
					smbk1List.add(module);
				}
			}
			smp.setSMBK1(smbk1List);

			ObservableList<Module> smbk3List = FXCollections.observableArrayList();

			for (Module module : module) {
				if (module.getRunPlan() == Block.BLOCK_3_4 && module.isMandatory()) {
					smbk3List.add(module);
				}
			}
			smp.setSMBK3(smbk3List);
			UpdateCredit();

			ObservableList<Module> usmbk4List = FXCollections.observableArrayList();
			for (Module module : module) {
				if (module.getRunPlan() == Block.BLOCK_3_4 && !module.isMandatory()) {
					usmbk4List.add(module);
				}
			}

			smp.setUSMBK4(usmbk4List);

			Collection<Module> AllSMBK3List = smp.getUSMBK4().getItems();
			rmp.setUSMB3(AllSMBK3List);
			Collection<Module> emptyList = new ArrayList<>();
			rmp.setRSMB3(emptyList);

			osp.Reset();
			view.changeTab(0);

		}
	}

	private class OSPSubmitHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			if (cspp.getStudentName().getFirstName().isEmpty()) {
				showErrorPopup("Kindly Add all Student Details!!");
				view.changeTab(0);
			} else if (smp.getSMBK3().getItems().size() <= 1) {
				showErrorPopup("Kindly Select Any Optional Module.");
				view.changeTab(1);
			} else if (rmp.getRSMB3().getItems().size() <= 0) {
				showErrorPopup("Kindly Select Any Reserve Module.");
				view.changeTab(2);
			} else {
				try {
					DirectoryChooser directoryChooser = new DirectoryChooser();
					File selectedDirectory = directoryChooser.showDialog(null);

					if (selectedDirectory != null) {
						File fileToSave = new File(selectedDirectory, cspp.getStudentName().getFullName() + ".dat");
						PrintWriter out = new PrintWriter(fileToSave);

						out.println("Student Personal Details\n" + osp.getPersonalDetails().getText() + "\n"
								+ osp.getSelectedModules().getText() + "\n" + osp.getReservedModules().getText()
								+ "\n");

						out.close();
					}
				} catch (IOException e1) {
					showErrorPopup("Error while saving data to a file.");
				}

				try {
					PrintWriter out = new PrintWriter(new File(cspp.getStudentName().getFullName() + ".dat"));

					out.println("Student Personal Details\n\n" + osp.getPersonalDetails().getText() + "\n\n"
							+ osp.getSelectedModules().getText() + "\n\n" + osp.getReservedModules().getText());
					showSuccessfulPopup(
							"File has been saved!!\n e.g. Student Full Name : Karan Modi, \nFile Name would be : Karan Modi.dat");
					out.close();
				} catch (IOException e) {
					showErrorPopup("Error while saving data to a file.");
				}
			}
		}
	}

	private class MSTMBSaveHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

			if (cspp.getStudentName().getFirstName().isEmpty()) {
				showErrorPopup("Kindly Add all Student Details!!");
				view.changeTab(0);
			}

			else if (smp.getSMBK3().getItems().size() <= 1) {
				showErrorPopup("Kindly Select Any Optional Module.");
				view.changeTab(1);
			}

			else if (rmp.getRSMB3().getItems().size() <= 0) {
				showErrorPopup("Kindly Select Any Reserve Module.");
				view.changeTab(2);
			} else {
				try {
					DirectoryChooser directoryChooser = new DirectoryChooser();
					File selectedDirectory = directoryChooser.showDialog(null);

					if (selectedDirectory != null) {
						File fileToSave = new File(selectedDirectory, cspp.getStudentName().getFullName() + ".dat");
						PrintWriter out = new PrintWriter(fileToSave);

						out.println("Student Personal Details\n" + osp.getPersonalDetails().getText() + "\n"
								+ osp.getSelectedModules().getText() + "\n" + osp.getReservedModules().getText()
								+ "\n");

						showSuccessfulPopup("File Saved to " + fileToSave.getAbsolutePath());

						out.close();
					}
				} catch (IOException e1) {
					showErrorPopup("Error while saving data to a file.");

				}
				try {
					PrintWriter out = new PrintWriter(new File(cspp.getStudentName().getFullName() + ".dat"));

					out.println("Student Personal Details\n\n" + osp.getPersonalDetails().getText() + "\n\n"
							+ osp.getSelectedModules().getText() + "\n\n" + osp.getReservedModules().getText());
					out.close();
				} catch (IOException e1) {
					showErrorPopup("Error while saving data to a file.");
				}
			}

		}
	}

	private class MSTMBLoadHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

			LoadDataPane loadDataPane = new LoadDataPane();
			Tab loadDataTab = new Tab("Load Data", loadDataPane);

			view.getTabPane().getTabs().add(loadDataTab);

			view.getTabPane().getSelectionModel().select(loadDataTab);

			loadDataPane.getLoadUsers().setOnMouseClicked(event -> {
				if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
					String selectedFileName = loadDataPane.getLoadUsers().getSelectionModel().getSelectedItem();
					if (selectedFileName != null) {
						File selectedFile = new File(System.getProperty("user.dir"), selectedFileName);
						loadDataPane.displayFileContent(selectedFile);
					}
				}
			});

			loadDataPane.getBtnGetUserData().setOnAction(event -> {
				String selectedFileName = loadDataPane.getLoadUsers().getSelectionModel().getSelectedItem();
				if (selectedFileName != null) {
					File selectedFile = new File(System.getProperty("user.dir"), selectedFileName);
					loadDataPane.displayFileContent(selectedFile);
				}
			});

			view.getTabPane().getTabs().add(loadDataTab);
			view.getTabPane().getSelectionModel().select(loadDataTab);

			loadDataPane.updateFileList();

		}
	}

	private class MSTMBAboutHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Alert aboutDialog = new Alert(AlertType.INFORMATION);
			aboutDialog.setTitle("About Final Year Module Selection Tool");
			aboutDialog.setHeaderText("Version 1.0");
			aboutDialog.setContentText("Developed by Karan Falgun Modi.\nP2761604\nCopyright © 2023");

			aboutDialog.showAndWait();
		}
	}

	private Course[] buildModulesAndCourses() {
		Module ctec3701 = new Module("CTEC3701", "Software Development: Methods & Standards", 30, true, Block.BLOCK_1);
		Module arti3706 = new Module("ARTI3706", "Agent-Based Modelling and Parallel Computing", 30, true,
				Block.BLOCK_1);

		Module arti3708 = new Module("ARTI3708", "Introduction to classical artificial intelligence", 30, true,
				Block.BLOCK_2);
		Module ctec3702 = new Module("CTEC3702", "Big Data and Machine Learning", 30, true, Block.BLOCK_2);
		Module ctec3703 = new Module("CTEC3703", "Mobile App Development and Big Data", 30, true, Block.BLOCK_2);

		Module ctec3451 = new Module("CTEC3451", "Development Project", 30, true, Block.BLOCK_3_4);

		Module ctec3704 = new Module("CTEC3704", "Functional Programming", 30, false, Block.BLOCK_3_4);
		Module ctec3705 = new Module("CTEC3705", "Advanced Web Development", 30, false, Block.BLOCK_3_4);

		Module imat3711 = new Module("IMAT3711", "Privacy and Data Protection", 30, false, Block.BLOCK_3_4);
		Module imat3722 = new Module("IMAT3722", "Fuzzy Logic and Inference Systems", 30, false, Block.BLOCK_3_4);

		Module ctec3706 = new Module("CTEC3706", "Embedded Systems and IoT", 30, false, Block.BLOCK_3_4);

		Module arti3702 = new Module("Arti3702", "Big Data and Machine Learning", 30, false, Block.BLOCK_3_4);

		Course compSci = new Course("Computer Science");
		compSci.addModule(ctec3701);
		compSci.addModule(ctec3702);
		compSci.addModule(ctec3451);
		compSci.addModule(ctec3704);
		compSci.addModule(ctec3705);
		compSci.addModule(imat3711);
		compSci.addModule(imat3722);

		Course softEng = new Course("Software Engineering");
		softEng.addModule(ctec3701);
		softEng.addModule(ctec3703);
		softEng.addModule(ctec3451);
		softEng.addModule(ctec3704);
		softEng.addModule(ctec3705);
		softEng.addModule(ctec3706);

		Course artIntl = new Course("Artificial Intelligence");
		artIntl.addModule(arti3708);
		artIntl.addModule(arti3706);
		artIntl.addModule(ctec3451);
		artIntl.addModule(ctec3704);
		artIntl.addModule(ctec3705);
		artIntl.addModule(arti3702);

		Course[] courses = new Course[3];
		courses[0] = compSci;
		courses[1] = softEng;
		courses[2] = artIntl;

		return courses;
	}

}
