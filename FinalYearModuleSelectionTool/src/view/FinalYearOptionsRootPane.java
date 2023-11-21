package view;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;



public class FinalYearOptionsRootPane extends BorderPane {

	private CreateStudentProfilePane cspp;
	private SelectModulesPane smp;
	private ResearveModulePane rmp;
	private OverviewSelectionPane  osp;
	private FinalYearOptionsMenuBar mstmb;
	private LoadDataPane ldp;
	private TabPane tp;
	
	public FinalYearOptionsRootPane() {
		//create tab pane and disable tabs from being closed
		tp = new TabPane();
		tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		//create panes
		cspp = new CreateStudentProfilePane();
		
		//create tabs with panes added
		Tab t1 = new Tab("Create Profile", cspp );

		smp = new SelectModulesPane();
		Tab t2 = new Tab("Select Modules", smp);
		
		rmp = new ResearveModulePane();
		Tab t3 = new Tab("Reserve Module", rmp);
		
		osp = new OverviewSelectionPane();
		Tab t4 = new Tab("Overview Selection", osp);
		
		//add tabs to tab pane
		tp.getTabs().addAll(t1,t2,t3,t4);
		
		
		//create menu bar
		mstmb = new FinalYearOptionsMenuBar();
		
		//add menu bar and tab pane to this root pane
		this.setTop(mstmb);
		this.setCenter(tp);
		
     
    }


	//methods allowing sub-containers to be accessed by the controller.
	public CreateStudentProfilePane getCreateStudentProfilePane() {
		return cspp;
	}

	public SelectModulesPane getSelectModulesPane() {
		return smp;
	}

	public ResearveModulePane getReserveModulePane() {
		return rmp;
	}

	public OverviewSelectionPane getOverviewStudentPane() {
		return osp;
	}

	
	public FinalYearOptionsMenuBar getModuleSelectionToolMenuBar() {
		return mstmb;
	}


	
	//method to allow the controller to change tabs
	public void changeTab(int index) {
		tp.getSelectionModel().select(index);
	}
	
	public TabPane getTabPane() {
	    return tp;
	}

	public LoadDataPane getLoadDataPane() {
		return ldp;
	}

	

}
