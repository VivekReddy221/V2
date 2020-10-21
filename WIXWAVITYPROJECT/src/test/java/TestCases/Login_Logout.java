package TestCases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import Generics.Generics;
import StepsBase.StepBase;
import excel.utils.ExcelReadPack;
import report.logs.Logs;

public class Login_Logout extends StepBase
{
	
	String testData[][],
	appCreationData[][],
	addControlsData[][],
	loginData[][],
	RecordsData[][];
	
	
	
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::TEST_CASE_1:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	//DESIGNED_BY_VIVEK_REDDY_M::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	//OCT_15_2020::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	
	@Test(priority = 1,groups = "Sanity",enabled=false)
	public void CreateAnAppWithTextControls() throws IOException, InterruptedException, AWTException
	{
		
		TestCaseName("WAVE-APPS-TC-01","Creating An App With Text Control","Sanity");
		
		loginData = new ExcelReadPack().testDataReaderInCellRange("Config",3,3,5);
		keyword("Login Into The Application","Keyword","login",loginData);
		keyword("Accepting The Coockie","Keyword","acceptCookie");
		
		testData = new ExcelReadPack().testDataReaderInCellRange("CreateAnAppWithTextNumber",3,3,4);
		keyword("Searching For Existing Apps and Delete If Any","Keyword","search_Delete_App",testData); 
		
		appCreationData = new ExcelReadPack().testDataReaderInCellRange("CreateAnAppWithTextNumber",3,2,5);
		keyword("Creating Sample App To Test All The Properties","Keyword","createAnApp",appCreationData);
		
		addControlsData = new ExcelReadPack().testDataReaderInRow_CellRange("CreateAnAppWithTextNumber",8,13,2,5);
		keyword("Adding Controls To An App","Keyword","addControls_setProprties",addControlsData); 
		
		keyword("Activating The App","keyword","activateApp");
		
		RecordsData = new ExcelReadPack().testDataReaderInRow_CellRange("CreateAnAppWithTextNumber",8,13,6,8);
		keyword("Adding Records To The Application","keyword","addRecords",RecordsData);
		
		keyword("Logging Out From The Application","Keyword","logout");
	}
	
	
	
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::TEST_CASE_2:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	//DESIGNED_BY_VIVEK_REDDY_M::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	//OCT_15_2020::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	
	@Test(priority = 2,groups = "Sanity",enabled=false)
	public void CreateAnAppWithNumbersControl() throws IOException, InterruptedException, AWTException
	{
		
		TestCaseName("WAVE-APPS-TC-02","Creating An App With Numbers Control","Sanity");
		
		loginData = new ExcelReadPack().testDataReaderInCellRange("Config",3,3,5);
		keyword("Login Into The Application","Keyword","login",loginData);
		keyword("Accepting The Coockie","Keyword","acceptCookie");
		
		testData = new ExcelReadPack().testDataReaderInCellRange("CreateAnAppWithNumbers",3,3,4);
		keyword("Searching For Existing Apps and Delete If Any","Keyword","search_Delete_App",testData); 
		
		appCreationData = new ExcelReadPack().testDataReaderInCellRange("CreateAnAppWithNumbers",3,2,5);
		keyword("Creating Sample App To Test All The Properties","Keyword","createAnApp",appCreationData);
		
		addControlsData = new ExcelReadPack().testDataReaderInRow_CellRange("CreateAnAppWithNumbers",8,12,2,5);
		keyword("Adding Controls To An App","Keyword","addControls_setProprties",addControlsData); 
		
		keyword("Activating The App","keyword","activateApp");
		
		RecordsData = new ExcelReadPack().testDataReaderInRow_CellRange("CreateAnAppWithNumbers",8,12,6,12);
		keyword("Adding Records To The Application","keyword","addRecords",RecordsData);
		
		keyword("Logging Out From The Application","Keyword","logout");
	}
	
	
		//:::::::::::::::::::::::::::::::::::::::::::::::::::::TEST_CASE_3:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		//DESIGNED_BY_VIVEK_REDDY_M::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		//OCT_15_2020::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		
		@Test(priority = 3,groups = "Sanity",enabled=false)
		public void CreateAnAppWithACategoryControls() throws IOException, InterruptedException, AWTException
		{
			
			TestCaseName("WAVE-APPS-TC-03","Creating An App With Category Control","Sanity");
			
			loginData = new ExcelReadPack().testDataReaderInCellRange("Config",3,3,5);
			keyword("Login Into The Application","Keyword","login",loginData);
			keyword("Accepting The Coockie","Keyword","acceptCookie");
			
			testData = new ExcelReadPack().testDataReaderInCellRange("CreateAnAppWithCategory",3,3,4);
			keyword("Searching For Existing Apps and Delete If Any","Keyword","search_Delete_App",testData); 
			
			appCreationData = new ExcelReadPack().testDataReaderInCellRange("CreateAnAppWithCategory",4,2,5);
			keyword("Creating Sample App To Test All The Properties","Keyword","createAnApp",appCreationData);
			
			addControlsData = new ExcelReadPack().testDataReaderInRow_CellRange("CreateAnAppWithCategory",8,10,2,6);
			keyword("Adding Controls To An App","Keyword","addControls_setProprties",addControlsData); 
			
			keyword("Activating The App","keyword","activateApp");
			
			RecordsData = new ExcelReadPack().testDataReaderInRow_CellRange("CreateAnAppWithCategory",8,10,7,8);
			keyword("Adding Records To The Application","keyword","addRecords",RecordsData); 
			
			keyword("Logging Out From The Application","Keyword","logout");
		}
		
		

		//:::::::::::::::::::::::::::::::::::::::::::::::::::::TEST_CASE_4:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		//DESIGNED_BY_VIVEK_REDDY_M::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		//OCT_18_2020::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		
		@Test(priority = 4,groups = "Sanity",enabled=false)
		public void CreateAnAppWithADependents() throws IOException, InterruptedException, AWTException
		{
			
			TestCaseName("WAVE-APPS-TC-04","Creating An App With Category Dependent Control","Sanity");
			
			loginData = new ExcelReadPack().testDataReaderInCellRange("Config",3,3,5);
			keyword("Login Into The Application","Keyword","login",loginData);
			keyword("Accepting The Coockie","Keyword","acceptCookie");
			
			testData = new ExcelReadPack().testDataReaderInCellRange("CreateAnAppWithDependents",3,3,4);
			keyword("Searching For Existing Apps and Delete If Any","Keyword","search_Delete_App",testData); 
			
			appCreationData = new ExcelReadPack().testDataReaderInCellRange("CreateAnAppWithDependents",4,2,5);
			keyword("Creating Sample App To Test All The Properties","Keyword","createAnApp",appCreationData);
			
			addControlsData = new ExcelReadPack().testDataReaderInRow_CellRange("CreateAnAppWithDependents",8,13,2,6);
			keyword("Adding Controls To An App","Keyword","addControls_setProprties",addControlsData); 
			
			keyword("Activating The App","keyword","activateApp");
			
			//RecordsData = new ExcelReadPack().testDataReaderInRow_CellRange("CreateAnAppWithDependents",8,10,7,8);
			//keyword("Adding Records To The Application","keyword","addRecords",RecordsData); 
			
			keyword("Logging Out From The Application","Keyword","logout");
		}
		
		
		
		//:::::::::::::::::::::::::::::::::::::::::::::::::::::TEST_CASE_5:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		//DESIGNED_BY_VIVEK_REDDY_M::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		//OCT_20_2020::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
				
				@Test(priority = 5,groups = "Sanity",enabled=false)
				public void CreateAnAppWithReference() throws IOException, InterruptedException, AWTException
				{
					
					TestCaseName("WAVE-APPS-TC-05","Creating An App With Category Reference Control","Sanity");
					
					loginData = new ExcelReadPack().testDataReaderInCellRange("Config",3,3,5);
					keyword("Login Into The Application","Keyword","login",loginData);
					keyword("Accepting The Coockie","Keyword","acceptCookie");
					
					testData = new ExcelReadPack().testDataReaderInCellRange("CreateAnAppWithReference",3,3,4);
					keyword("Searching For Existing Apps and Delete If Any","Keyword","search_Delete_App",testData); 
					
					appCreationData = new ExcelReadPack().testDataReaderInCellRange("CreateAnAppWithReference",4,2,5);
					keyword("Creating Sample App To Test All The Properties","Keyword","createAnApp",appCreationData);
					
					addControlsData = new ExcelReadPack().testDataReaderInRow_CellRange("CreateAnAppWithReference",8,17,2,6);
					keyword("Adding Controls To An App","Keyword","addControls_setProprties",addControlsData); 
					
					keyword("Activating The App","keyword","activateApp");
					
					//RecordsData = new ExcelReadPack().testDataReaderInRow_CellRange("CreateAnAppWithDependents",8,10,7,8);
					//keyword("Adding Records To The Application","keyword","addRecords",RecordsData); 
					
					keyword("Logging Out From The Application","Keyword","logout");
				}
				
				//:::::::::::::::::::::::::::::::::::::::::::::::::::::TEST_CASE_6:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
				//DESIGNED_BY_VIVEK_REDDY_M::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
				//OCT_20_2020::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
						
						@Test(priority = 5,groups = "Sanity",enabled=true)
						public void CreateAnAppWithProgressControl() throws IOException, InterruptedException, AWTException
						{
							
							TestCaseName("WAVE-APPS-TC-05","Creating An App With Category Reference Control","Sanity");
							
							loginData = new ExcelReadPack().testDataReaderInCellRange("Config",3,3,5);
							keyword("Login Into The Application","Keyword","login",loginData);
							keyword("Accepting The Coockie","Keyword","acceptCookie");
							
							testData = new ExcelReadPack().testDataReaderInCellRange("CreateAnAppWithProgress",3,3,4);
							keyword("Searching For Existing Apps and Delete If Any","Keyword","search_Delete_App",testData); 
							
							appCreationData = new ExcelReadPack().testDataReaderInCellRange("CreateAnAppWithProgress",4,2,5);
							keyword("Creating Sample App To Test All The Properties","Keyword","createAnApp",appCreationData);
							
							addControlsData = new ExcelReadPack().testDataReaderInRow_CellRange("CreateAnAppWithProgress",8,9,2,5);
							keyword("Adding Controls To An App","Keyword","addControls_setProprties",addControlsData); 
							
							keyword("Activating The App","keyword","activateApp");
							
							RecordsData = new ExcelReadPack().testDataReaderInRow_CellRange("CreateAnAppWithProgress",8,9,6,7);
							keyword("Adding Records To The Application","keyword","addRecords",RecordsData); 
							
							keyword("Logging Out From The Application","Keyword","logout");
						}
						
				
		
		

}
