package StepsBase;

import java.awt.AWTException;
import java.io.IOException;

import Generics.Generics;
import excel.utils.StringClsUtil;
import report.logs.Logs;
import specifics.SpecificType;
import xpath.hub.XpathHub;

public class StepBase 
{
	protected static Logs log;
	String Location,Location1;
	Generics gen;
	
	String status;
	
	SpecificType spec;
	String TestData1[][]={ {"testData","NO"}};
	
	public void TestCaseName(String testCaseId,String TestDesc,String testingType)
	{
		log = new Logs();
		log._INFO("**********************************************************-S-T-A-R-T-****************************************************************************************");
		log._INFO("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
		log._INFO(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		log._INFO("::::::::::::::::::::::::::::::::::"+testCaseId+"-"+TestDesc+"::::::::::::::::::::::::::::::::::::::::::::::::::::");
		log._INFO(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		log._INFO("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
		log.CreateTestCase(testCaseId, TestDesc,testingType);
	}

	public void Step(String Des,String action) throws InterruptedException, IOException, AWTException
	{
		MainLooper(Des,action,"YES","NO","NO","NO","click",TestData1);
	}
	public void Step(String Des,String action,String elementName) throws InterruptedException, IOException, AWTException
	{
		MainLooper(Des,action,elementName,"NO","NO","NO","click",TestData1);
	}
	public void Step(String Des,String action,String elementName,String TestData) throws InterruptedException, IOException, AWTException
	{
		MainLooper(Des,action,elementName,"NO","NO","NO",TestData,TestData1);
	}

	public void Step(String Des,String action,String elementName,String elementName1,String TestData) throws InterruptedException, IOException, AWTException
	{
		MainLooper(Des,action,elementName,elementName1,"NO","NO",TestData,TestData1);
	}
	
	public void keyword(String Des, String keyword, String KeywordName,String TestData[][]) throws InterruptedException, IOException, AWTException
	{
		MainLooper(Des,"NO","NO","NO",keyword,KeywordName,"NO",TestData);
	}

	public void keyword(String Des, String keyword, String KeywordName) throws InterruptedException, IOException, AWTException
	{
		MainLooper(Des,"NO","NO","NO",keyword,KeywordName,"NO",TestData1);
	}
	void MainLooper(String Des,String action,String elementName,String elementName1,String keyword, String KeywordName,String TestData,String testData[][]) throws InterruptedException, IOException, AWTException
	{
		log._INFO(":::::====>> "+Des);
		gen = new Generics();
		spec = new SpecificType();

		if(keyword=="NO")
		{
			if(elementName1!="YES")
			{
			if(action!="dragAndDrop")
			{
			if(new XpathHub().xpathGetter(elementName).contains("[$]"))
			{
				Location = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter(elementName),TestData);
			}
			else
			{
				Location = new XpathHub().xpathGetter(elementName);
			}
			}
			else
			{
				Location = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter(elementName),TestData);
				Location1 = new XpathHub().xpathGetter(elementName1);
			}
			}
			else
			{
				Location = elementName;
			}
		}
		else
		{
			log._INFO("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
			log._INFO("**************************************"+"Keyword"+"-"+KeywordName+" Execution Started**********************************");
			log._INFO(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			log.createKeyword(KeywordName);
		} 
		
		if(action!=null && elementName!=null && testData!=null && keyword=="NO" )
		{

			if(elementName.contains(";//")||elementName.contains(";(//"))
			{
				String data[] =  new StringClsUtil().SplitData(elementName);
				elementName = data[0];
				Location = data[1];
			} 
			
			log._INFO("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
			
			switch(action)
			{
			case "input": log.EXTENT_INFO("-- "+Des+"["+elementName+"]"+" With Test Data "+"["+TestData+"]");
						 gen.input(elementName, Location, TestData); 
						 break;
						 
			case "click": log.EXTENT_INFO("-- "+Des+"["+elementName+"]");
						 gen.click(elementName, Location);
						 break;
						 
			case "verify": log.EXTENT_INFO("-- "+Des+"["+elementName+"]");
						  gen.verify(elementName, Location);
			 			 break;
			 			 
			case "select": gen.select(elementName,Location, TestData);
						 break;
						 
			case "dragAndDrop": log.EXTENT_INFO("-- "+"Dragging An Element ["+elementName+"] To "+ "["+elementName1+"]");
								gen.dropAndDrop(elementName, Location, Location1);
						 break;
						 
			case "attachTheImage": log.EXTENT_INFO("-- "+Des+" From The Location"+ System.getProperty("user.dir")+"\\"+TestData);
								gen.attachTheImage(TestData);
						break;
						
			case "softAssert" : gen.softAssert(Location, TestData);
						break;
			case "JSinput": log.EXTENT_INFO("-- "+Des+"["+elementName+"]"+" With Test Data "+"["+TestData+"]");
						gen.JSinput(elementName, Location, TestData);
						break;
			case "copyPaste": log.EXTENT_INFO("-- "+Des);
						gen.copyPaste(TestData);
						break;
			default: {
					log._ERROR("Given Action Is In Out Of Scope");
					 log.EXTENT_FAIL("Given Action Is In Out Of Scope");
					 }
			}
			
		}
		else
		{
			log.EXTENT_INFO("|"+Des+"|");
			switch(KeywordName)
			{
			case "login": spec.login(testData);
						 break;
			case "acceptCookie":spec.acceptCookie();
						 break;
			case "logout": spec.logout();
						 break; 
			case "createAnApp":spec.createAnApp(testData);
						 break;
			case "addControls_setProprties":spec.addControls_setProprties(testData);
						 break;
			case "activateApp":spec.activateApp();
						 break;
			case "addRecords": spec.addRecords(testData);
						 break;
			case "search_Delete_App": spec.search_Delete_App(testData);
						 break;
			case "dataValidationInLISTpage": spec.dataValidationInLISTpage(testData);
						 break;
			case "dataValidationInLINE_EDITpage": spec.dataValidationInLINE_EDITpage(testData);
						 break;
			case "dataValidationNORMALEDITpage" : spec.dataValidationNORMALEDITpage(testData);
						 break;
			case "verifyExistingRecords" : spec.verifyExistingRecords(testData);
						 break;  
			default: { log._ERROR("Given Specific Keyword Is In Out Of Scope");
						log.EXTENT_FAIL("Given Specific Keyword Is In Out Of Scope"); } 
			}
		}
			log._INFO("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

}
