package specifics;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;

import Generics.Generics;
import StepsBase.StepBase;
import excel.utils.StringClsUtil;
import report.logs.Logs;
import xpath.hub.XpathHub;



public class SpecificType extends StepBase
{
	String dataSplit[];
	String xpathConversion,xpathConversion1;
	
    static Logs log = new Logs();
    
    static int j;
    
    static String testData[][],
	appCreationData[][],
	addControlsData[][],
	loginData[][],
	RecordsData[][];
    
    static String Subcontrol[],MainControl[],ControlLabels[];
	
	public String DateSet()
	{
		long mills = System.currentTimeMillis();
		Date date = new Date(mills);
		
		return new excel.utils.StringClsUtil().Convert_Customized_Format(date);
	}
	
	public void search_Delete_App(String data[][]) throws InterruptedException, IOException, AWTException
	{
		Step("Clicking On Home Menu Button","click","homeMenu");
		
		Step("Clicking On Apps","click","apps");
		
		Step("Searching For An app is exist already or not","input","appSearch",data[0][0]);
		
		List<WebElement> elements = new Generics().elementCount(new excel.utils.StringClsUtil().ReplaceString(new XpathHub().xpathGetter("appsCount"),data[0][0]),"apps");
		
		int count = elements.size();
		

		for(int i = 0;i<count;i++)
		{
			log.LoopIndex("Deleting The Apps","Deleting The App"+(i+1));
			
			String	xpath = new excel.utils.StringClsUtil().ReplaceString1(new XpathHub().xpathGetter("appSelect"),data[0][0]);
			
			xpathConversion = new StringClsUtil().ReplaceString(xpath,Integer.toString(1));
			
			Step("Clicking On Three Dot Menu App To Delete","click","appSelect;"+xpathConversion,"YES","NO");
			
			/*if(i+1==count)
			{
				Step("Opening An Application","click","openApp");
			}
			else
			{*/
			Step("Clicking On Delete Option","click","deleteApp");
			Step("Accepting The App Deletion pop Up","click","deleteAppAccept");
			new Generics().Sleep(8000);
			//}
		}
		
	}
	public void verifyExistingRecords(String data[][]) throws IOException, InterruptedException, AWTException
	{
		/*List<WebElement> elements = new Generics().elementCount(new XpathHub().xpathGetter("countRecordsInList")," Records Found");
		
		int size = elements.size();
		
		if(size<2)
		{
			addRecords(data);
			dataValidationInLISTpage(data);
			dataValidationInLINE_EDITpage(data);
			dataValidationNORMALEDITpage(data);
		}
		else
		{		
			deleteAllRecords();
			new Generics().Sleep(8000);
			addRecords(data);
			dataValidationInLISTpage(data);
			dataValidationInLINE_EDITpage(data);
			dataValidationNORMALEDITpage(data); 
		}*/
	}
	public void login(String data[][]) throws InterruptedException, IOException, AWTException
	{
		Step("Verfiying The Login Card","verify","loginCard");
		//Step("Verfiying The Wavity Logo","verify","logo");

		Step("Entering The Data In UserName","input","userName",data[0][0]);
		Step("Clicking The Next Button","click","next");
		
		Step("Verfiying The Login Card","verify","loginCard");
		//Step("Verfiying The Wavity Logo","verify","logo");

		Step("Entering The Data In PassWord","input","passWord",data[0][1]);
		
		//Step("Verfiying Forgot Password Link","verify","forgotPassword");
		Step("Verfiying Back Button","verify","back");
		
		Step("Clicking The Element Login","click","continue");
	}
	
	public void acceptCookie() throws IOException, InterruptedException, AWTException
	{
		new Generics().specificWait(new XpathHub().xpathGetter("acceptCookie"),2000);
		Step("Clicking On The Coockie","click","acceptCookie");
	}
	public void logout() throws InterruptedException, IOException, AWTException
	{
		
		Step("Clicking On User Account","click","userAccount");
		Step("Clickng On Logout","click","logout");
		
		new Generics().assertClose();

	}
	
	public void createAnApp(String data[][]) throws InterruptedException, IOException, AWTException
	{
		appCreationData = data;
		
		Step("Verifying W-Image On The HomePage","verify","wImage1");
		Step("Clicking on W-Image On The HomePage","click","wImage1");
		
		Step("Clicking On Apps","click","apps");
		
		Step("Clicking On Create New App Link","click","createNewApp");
		Step("Clicking On Create Button","click","createButton");
		Step("Verify App Name text","verify","appName");
		//Step("Clicking On Choose Icon","click","chooseIcon");
		//new Generics().Sleep(4000);
		
		//new Generics().attachTheImage(data[0][0]);
		
		Step("Entering App Name","input","enterAppName",data[0][1]);
		Step("Entering App Description","input","appDescription",data[0][2]);
		Step("Clicking on App Type","select","selectAppType",data[0][3]);
		Step("Clicking On Create Button","click","clickCreateApp");
	}
	
	public void addControls_setProprties(String data[][]) throws InterruptedException, IOException, AWTException
	{
		addControlsData = data;
		
		
		
		for(int i=0;i<data.length;i++)
		{
			log.LoopIndex("Adding The Main Control"+(i+1)+": "+data[i][0],"Adding Sub-Control"+": "+data[i][1]);
		Step("Clicking On MainControl On the App Designer","click","mainControl",data[i][0]);
		//new Generics().Sleep(1000);
	//	Step("Verifying Sub Control is Avalibale or not","verify","subControl",data[i][1]);
		Step("Dragging The Sub Control To App Controls Dropable Area","dragAndDrop","subControl","controlsDrop",data[i][1]);
		//new Generics().Sleep(1000);
		xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("controlLabel"),Integer.toString(i+1));
		Step("Entering App Control Label Name","input","controlLabel;"+xpathConversion,"YES",data[i][2]);
		//new Generics().Sleep(2000);
		
		if(data[i][1].contains("Dependent") || data[i][1].contains("Reference"))
		{
			
			log.LoopIndex("Adding Control Objects for The Sub Control","Sub-Control"+": "+data[i][1]);
			j=i;
			addControlObjectsForDependents(data[i][1],data[i][4]);
			addControlProperties(data[i][3],data[i][1]);
		}
		else
		{
		if(data[i][0].contains("Category"))
		{
			log.LoopIndex("Adding Control Objects for The Sub Control","Sub-Control"+": "+data[i][1]);
			j =i;
			addControlObjects(data[i][1],data[i][4]);
			addControlProperties(data[i][3],data[i][1]);
		}
		else
		{
		
		addControlProperties(data[i][3],data[i][1]);
		}
		
		}
		}
		
		Step("Saving An Application","click","saveApp");
		new Generics().Sleep(5000);
	}
	
	public void addControlObjectsForDependents(String subControlName,String data) throws InterruptedException, IOException, AWTException
	{
		String[] controlOb = new StringClsUtil().SplitData(data);
		j++;
		
		for(int i=0;i<controlOb.length;i++)
		{
			String[] controlOb1 = new StringClsUtil().SplitData1(controlOb[i]);
			
			if(subControlName.contains("Reference"))
			{
				
				xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("DependentOb"),Integer.toString(j));
				xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,controlOb1[0]);
				
				Step("Clicking The Dropdown","click",xpathConversion,"YES","NO");
				
				xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("refObjSearch"),Integer.toString(j));
				xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,controlOb1[0]);
	 
				
				Step("Searching The Value to select","input","refObjSearch;"+xpathConversion,"YES",controlOb1[1]);
				
				xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("refObj"),Integer.toString(j));
				xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,controlOb1[0]);
				xpathConversion = new StringClsUtil().ReplaceString2(xpathConversion,controlOb1[1]);
				
				Step("Clicking The Option in"+controlOb1[0]+" control","click","selectObject;"+xpathConversion,"YES","NO");
				
			}
			else if(subControlName.contains("Dependent Multi Values"))
			{
				if(controlOb1[0].contains("Depends On") ||controlOb1[0].contains("Match"))
				{
					new Generics().Sleep(10000);
					xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("DependentOb1"),Integer.toString(j));
					xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,controlOb1[0]);
					
					Step("Selecting The Object from dropdown","select","DependentOb1;"+xpathConversion,"YES",controlOb1[1]);
					
				}
				else
				{
					new Generics().Sleep(10000);
					xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("clickLabelObject"),Integer.toString(j));
					xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,controlOb1[0]);
					
					Step("Clicking On the Dropdown Label","click","clickLabelObject;"+xpathConversion,"YES","NO");
					
					xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("DependentOb"),Integer.toString(j));
					xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,controlOb1[0]);
					
					Step("Clicking The Dropdown","click",xpathConversion,"YES","NO");
					
					xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("refObjSearch"),Integer.toString(j));
					xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,controlOb1[0]);
		 
					
					Step("Searching The Value to select","input","refObjSearch;"+xpathConversion,"YES",controlOb1[1]);
					
					xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("refObj"),Integer.toString(j));
					xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,controlOb1[0]);
					xpathConversion = new StringClsUtil().ReplaceString2(xpathConversion,controlOb1[1]);
					
					Step("Clicking The Option in"+controlOb1[0]+" control","click","selectObject;"+xpathConversion,"YES","NO");
				}
			}
			else
			{
			if(controlOb1[0].contains("Application"))
			{
				
				xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("DependentOb"),Integer.toString(j));
				xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,controlOb1[0]);
				
				Step("Clicking The Dropdown","click",xpathConversion,"YES","NO");
				
				xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("refObjSearch"),Integer.toString(j));
				xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,controlOb1[0]);
	 
				
				Step("Searching The Value to select","input","refObjSearch;"+xpathConversion,"YES",controlOb1[1]);
				
				xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("refObj"),Integer.toString(j));
				xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,controlOb1[0]);
				xpathConversion = new StringClsUtil().ReplaceString2(xpathConversion,controlOb1[1]);
				
				Step("Clicking The Option in"+controlOb1[0]+" control","click","selectObject;"+xpathConversion,"YES","NO");
				
			}
			else
			{
				xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("DependentOb1"),Integer.toString(j));
				xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,controlOb1[0]);
				
				Step("Selecting The Object from dropdown","select","DependentOb1;"+xpathConversion,"YES",controlOb1[1]);
			}
			
			}
		}
		
		
	}

	public void addControlObjects(String subControlName,String data) throws InterruptedException, IOException, AWTException
	{

		if(subControlName.contains("Dropdown")||subControlName.contains("Linear")||subControlName.contains("Segment"))
		{
			j++;
			int b=0; 
			
			String[] controlOb = new StringClsUtil().SplitData(data);
			
			for(int i=0;i<controlOb.length;i++)
			{
				String[] controlOb1 = new StringClsUtil().SplitData1(controlOb[i]);
				
				xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("selectControlObj"),Integer.toString(j));
				xpathConversion1 = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("AddAnOptionInCategory"),Integer.toString(j));
				
				if(controlOb1[0].contains("Value"))
				{

					if(i!=0)
					{
						Step("Clicking On Add Option In Category Controls In App Designer","click","[AddAnOptionInCategory];"+xpathConversion1,"YES","NO");
					}
					xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,controlOb1[0]); 
					xpathConversion = new StringClsUtil().ReplaceString2(xpathConversion,Integer.toString(i+2)); 
					Step("Selecting The "+controlOb1[0] +" Control Object","input","[selectControlObj:"+controlOb1[0]+"];"+xpathConversion,"YES",controlOb1[1]);
					
				}
				else
				{
					if(controlOb[1]!=null)
					{
					xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,controlOb1[0]); 
					xpathConversion = new StringClsUtil().ReplaceString2(xpathConversion,Integer.toString(2+b)); 
					Step("Selecting The "+controlOb1[0] +" Control Object","input","[selectControlObj:"+controlOb1[0]+"];"+xpathConversion,"YES",controlOb1[1]);
					}
					b=b+1;
				}
			}
		}
	}
	
	//To Add Control properties
	public void addControlProperties(String ControlsProperties,String subControl) throws IOException, InterruptedException, AWTException
	{
		String propertySet[] = null;
		
		if(ControlsProperties!=null) 
		{
			
		propertySet=  new StringClsUtil().SplitData(ControlsProperties);
		
		for(int i =0;i<propertySet.length;i++)
		{
			log.InnerLoopIndex("Adding The Property: "+propertySet[i],"Property: "+propertySet[i]);
			
		if(propertySet[i]!=null)
		{
			
			if(subControl.contains("Two Fields"))
			{
				String[] propSpit = new StringClsUtil().SplitData(propertySet[i]);
				
			for(int m=0;m<propSpit.length;m++)
			{
					if(propSpit[m]!=null)
					{
						selectPropertyType(propSpit[m],(m+1));
					}
			}
		    }
			
			else if(subControl.contains("Three Fields"))
			{
				String[] propSpit = new StringClsUtil().SplitData(propertySet[i]);
				
				for(int m=0;m<propSpit.length;m++)
				{
					String[] propSpit1 = new StringClsUtil().SplitData1(propSpit[m]);
					
						if(propSpit1[m]!=null)
						{
							
							selectPropertyType(propSpit[m],(m+1));
							
						}
				}
			}
			else
			{
				
				selectPropertyType(propertySet[i],1);
			}
		}
	}
	}
	}
	
	public void selectPropertyType(String propertyType,int looper) throws InterruptedException, IOException, AWTException
	{
		String 
		
		clickRelated="|Required|Deprecated|Read only|Allow data import|Hide|One time entry|Script|Font color:|Allow negative|Populate on create|option while record entry:"
					+ "System date as prefix|System date as suffix| ",
		selectable="|Input field type:|Font size:|Font weight:|Font style:|Editor:|Number type:|Scale:|Prefix separator:|Suffix separator:|Dateformat:|Selection type:"
					+"Timezone:|Min selection:|Max selection:|",
		inputable="|Help message:|Default value:|Max length:|Field placeholder:|Placeholder|Prefix:|Suffix:|";
		
		String prop[] = new StringClsUtil().SplitData1(propertyType);
		
		
		if(clickRelated.contains(prop[0].trim()))
		{
			xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("selectProperty"),propertyType); 
			xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,Integer.toString(looper)); 
			Step("Selecting The "+propertyType +"property","click","[selectProperty:"+propertyType+"];"+xpathConversion,"YES","NO");
		}
		
		else if(inputable.contains(prop[0].trim()))
		{
			//String[] input = new StringClsUtil().SplitData1(inputable) ;
			
			xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("selectProperty"),prop[0]); 
			xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,Integer.toString(looper)); 
			Step("Giving The Data In The "+prop[0] +"property","input","[selectProperty:"+prop[0]+"];"+xpathConversion,"YES",prop[1]);
		}
		else if(selectable.contains(prop[0].trim()))
		{
			//String[] selection = new StringClsUtil().SplitData1(selectable) ;
			
			xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("selectProperty"),prop[0]); 
			xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,Integer.toString(looper)); 
			Step("Selecting The Property Data In The "+prop[0] +"property","select","[selectProperty:"+prop[0]+"];"+xpathConversion,"YES",prop[1]);
			
		}
		else
		{
			log._ERROR("Property Not Found: "+prop[0]);
		}
		
	}
	public void activateApp() throws InterruptedException, IOException, AWTException
	{
		Step("Verifying App Inactivation Message Available or Not","verify","notActivation");
		new Generics().Sleep(5000);
		
		Step("Clicking On ThreeDot Menu To Activate The app","click","threeDotMenu");
		Step("Clicking on Active Button","click","activeApp");
		new Generics().Sleep(2000);
		Step("Activating The App","click","activateApp");
		new Generics().Sleep(5000);
	}

	public void addRecords(String data[][]) throws InterruptedException, IOException, AWTException
	{
		
		MainControl = new String[addControlsData.length];
		Subcontrol = new String[addControlsData.length];
		ControlLabels = new String[addControlsData.length];
		
		for(int i=0;i<addControlsData.length;i++)
		{
			MainControl[i] = addControlsData[i][0];
			Subcontrol[i]= addControlsData[i][1];
			ControlLabels[i]= addControlsData[i][2];
		}
		
		
		int endLooper = 0;
		
		for(int i=0;i<data[0].length;i++)
		{
			if(endLooper<data[0].length)	
			{
			log.LoopIndex("Adding The Records", "Data Insertion In The Record"+(i+1));
			
			if(appCreationData[0][3].contains("Company"))
			{
			new Generics().specificWait(new XpathHub().xpathGetter("addRecord"),30);
			Step("Clicking On New Button In App Records List page","click","addRecord");
			}
			else
			{
				new Generics().specificWait(new XpathHub().xpathGetter("addRecord1"),30);
				Step("Clicking On New Button In App Records List page","click","addRecord1");
			}
			
		
		for(int j=0;j<MainControl.length;j++)
		{
			
			if(data[j][i]!=null)
			{
		
			switch(MainControl[j])
			{
				case "Text": addDataToText(Subcontrol[j],ControlLabels[j],data[j][i]); break;
				
				case "Numbers" : addDataToNumbers(Subcontrol[j],ControlLabels[j],data[j][i]); break;
				
				case "Category": addDataToCategory(Subcontrol[j],ControlLabels[j],data[j][i]); break;
				
				case "Progress": addDataToProgress(Subcontrol[j],ControlLabels[j],data[j][i]);break;
			}
			
			}
			
		}
		Step("Saving The Record","click","recordSave");
		endLooper = endLooper +1 ;
		}
		
		new Generics().Sleep(4000);
	}
	}
	public void addDataToProgress(String subControl,String ControlLabel,String data) throws IOException, InterruptedException
	{
		switch(subControl)
		{
		case "Single Slider": 	xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("CurrentSliderValue"),ControlLabel);
								String get = new Generics().getData(xpathConversion);
								get = get.replace("%","");
								int i = Integer.parseInt(get);
								i = Integer.parseInt(data)-i;
							  
								xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("SliderStart"),ControlLabel);
								new Generics().dropAndDropByOffeset("SliderStart", xpathConversion,i,0);
								break;
		}
	}
	public void addDataToText(String subControl,String ControlLabel,String data) throws InterruptedException, IOException, AWTException
	{
		 switch(subControl)
			{
				case "Plain": 
				{
					xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("AddDataToText_Numebrs_Control"),ControlLabel);
					xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,Integer.toString(1));
					Step("Inputting The Data in "+subControl+" control","input","AddDataToText_Numebrs_Control;"+xpathConversion,"YES",data);
				}
				break;
				case "Two Fields": 
				{
					String[] twoF = new StringClsUtil().SplitData(data);
					
					for(int x=0;x<twoF.length;x++)
					{
						xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("AddDataToText_Numebrs_Control"),ControlLabel);
						xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,Integer.toString(x+1));
						Step("Inputting The Data in "+subControl+" control","input","AddDataToText_Numebrs_Control;"+xpathConversion,"YES",twoF[x]);
					
					}
				}
				break;
				case "Three Fields": 
				{
					String[] threeF = new StringClsUtil().SplitData(data);
					
					for(int x=0;x<threeF.length;x++)
					{
						xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("AddDataToText_Numebrs_Control"),ControlLabel);
						xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,Integer.toString(x+1));
						Step("Inputting The Data in "+subControl+" control","input","AddDataToText_Numebrs_Control;"+xpathConversion,"YES",threeF[x]);
					}
				}
				break;
				case "Text Block": 
				{
					xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("AddDataToText_Numebrs_Control"),ControlLabel);
					xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,Integer.toString(1));
					Step("Clicking On The Text Block","click",xpathConversion,"YES","NO");
					new Generics().copyPaste(data);
				}
				break;
			}
	}
	
	public void addDataToNumbers(String subControl,String ControlLabel,String data) throws InterruptedException, IOException, AWTException
	{
		 switch(subControl)
			{
				case "Number": 
				{
					xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("AddDataToText_Numebrs_Control"),ControlLabel);
					xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,Integer.toString(1));
					Step("Inputting The Data in "+subControl+" control","input","AddDataToText_Numebrs_Control;"+xpathConversion,"YES",data);
				}
				break;
				case "Financial": 
				{
					
						xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("AddDataToText_Numebrs_Control"),ControlLabel);
						xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,Integer.toString(1));
						Step("Inputting The Data in "+subControl+" control","input","AddDataToText_Numebrs_Control;"+xpathConversion,"YES",data);
				}
				break;
			}
	}
	
	public void addDataToCategory(String subControl,String ControlLabel,String data) throws InterruptedException, IOException, AWTException
	{
		 switch(subControl)
			{
				case "Dropdown": 
				{
					String[] dropSpit = new StringClsUtil().SplitData(data);
					
					for(int i=0;i<dropSpit.length;i++)
					{
						
						
						if(dropSpit[i].contains("Other"))
						{
							String[] dropSpit1 = new StringClsUtil().SplitData1(dropSpit[i]);
							OtherSelection(ControlLabel,dropSpit1[0],dropSpit1[1]);
						}
						else
						{
					xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("LabelInspect"),ControlLabel);
					Step("Clicking The Dropdown","click",xpathConversion,"YES","NO");
					
					xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("AddDataToDropDown"),ControlLabel);
					Step("Clicking The Dropdown","click","AddDataToDropDown;"+xpathConversion,"YES","NO");
					
					Step("Searching The Value to select","input","dropDownSearch",dropSpit[i]);
					
					xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("AddDataToDropDown1"),dropSpit[i]);
					Step("Clicking The Option in"+ControlLabel+" "+subControl+" control","click","AddDataToDropDown1;"+xpathConversion,"YES","NO");
						}
					
					}
				}
				break;
				case "Linear": 
				{
					String[] dropSpit = new StringClsUtil().SplitData(data);
					
					for(int i=0;i<dropSpit.length;i++)
					{
						
						if(dropSpit[i].contains("Other"))
						{
							String[] dropSpit1 = new StringClsUtil().SplitData1(dropSpit[i]);
							OtherSelection(ControlLabel,dropSpit1[0],dropSpit1[1]);
						}
						else
						{
					
						xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("OtherDropdown_LinearDataClick"),ControlLabel);
						xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,dropSpit[i]);
						Step("Clikcing On The Option for the control:"+ControlLabel,"click","OtherDropdown_LinearDataClick;"+xpathConversion,"YES",dropSpit[i]);
						}
					}
				}
				break;
				case "Segment": 
				{
					String[] dropSpit = new StringClsUtil().SplitData(data);
					
					for(int i=0;i<dropSpit.length;i++)
					{
						
						if(dropSpit[i].contains("Other"))
						{
							String[] dropSpit1 = new StringClsUtil().SplitData1(dropSpit[i]);
							OtherSelection(ControlLabel,dropSpit1[0],dropSpit1[1]);
						}
						else
						{
					
						xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("SegementClick"),ControlLabel);
						xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,dropSpit[i]);
						Step("Clicking on the option for "+subControl+" control","click","AddDataToText_Numebrs_Control;"+xpathConversion,"YES",dropSpit[i]);
						}
					}
				}
				break;
			}
	}
	
	
	public void dropDownSelect(String ControlLabel,String data) throws InterruptedException, IOException, AWTException
	{
		xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("LabelInspect"),ControlLabel);
		Step("Clicking The Dropdown","click",xpathConversion,"YES","NO");
		
		xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("AddDataToDropDown"),ControlLabel);
		Step("Clicking The Dropdown","click",xpathConversion,"YES","NO");
		
		Step("Searching The Value to select","input","dropDownSearch",data);
		
		xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("AddDataToDropDown1"),data);
		Step("Clicking The Option in"+ControlLabel+" control","click","AddDataToDropDown1;"+xpathConversion,"YES","NO");
	}
	
	public void OtherSelection(String ControlLabel,String ControlLabel1,String data) throws IOException, InterruptedException, AWTException
	{
		xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("LabelInspect"),ControlLabel);
		Step("Clicking The Dropdown","click",xpathConversion,"YES","NO");
		
		xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("OtherDropdown_LinearDataClick"),ControlLabel);
		xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,ControlLabel1);
		Step("Clicking The Dropdown","click",xpathConversion,"YES","NO");
		
		xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("OtherDropdownDataInput"),ControlLabel);
		xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,ControlLabel1);
		Step("Inputting In other value in Dropdown","input","OtherDropdownDataInput;"+xpathConversion,"YES",data);
	}
	public void dataValidationInLISTpage(String data[][]) throws IOException
	{
		List<WebElement> elements = new Generics().elementCount(new XpathHub().xpathGetter("countRecordsInList")," Records Found");
		
		int size = elements.size();
		
		for(int i=0,x=size;i<size;i++,x--)
		{
			
			List<WebElement> fields = new Generics().elementCount(new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("verifyRecordInList"),Integer.toString(i+1))," Fields Found");
			int fsize = fields.size();
			log._INFO("================================================================================================================");
			log._INFO("Verifying The Data In The Record"+(i+1)+" In The Records List page");
			log._INFO("================================================================================================================");
			
			for(int j=1,y=0;j<fsize-1;j++,y++)
			{
				log._INFO("Verifying The Field"+j+"In The Record"+(i+1)+" In The Records List page");
				xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("dataValidatetListAtElement"),Integer.toString(i+1));
				String xpath = new StringClsUtil().ReplaceString1(xpathConversion,Integer.toString(j+1));
				new Generics().softAssert(xpath, data[x-1][y]);
				/*Boolean result = new Generics().dataValidation(xpath, data[x-1][y]);
				assertTrue(result);*/
			}
		}
	}
	public void dataValidationInLINE_EDITpage(String data[][]) throws IOException, InterruptedException, AWTException
	{
		List<WebElement> elements = new Generics().elementCount(new XpathHub().xpathGetter("countRecordsInList")," Records Found");
		
		int size = elements.size();
		
		for(int i=0,x=size;i<size;i++,x--)
		{
			xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("inlineEditLink"),Integer.toString(i+1));
			Step("Clicking On App Record Inline Edit link For Record"+(i+1),"click",xpathConversion,"YES","NO");
			
			List<WebElement> fields = new Generics().elementCount(new XpathHub().xpathGetter("inlinefieldsCount")," Fields Found");
			int fsize = fields.size();
			
			log._INFO("================================================================================================================");
			log._INFO("Verifying The Data In The Record"+(i+1)+" In The Records Inline Edit Page");
			log._INFO("================================================================================================================");
			
			for(int j=0,y=0;j<fsize;j++,y++)
			{
				log._INFO("Verifying The Field"+(j+1)+"In The Record"+(i+1)+" In The Records Inline Edit page");
				xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("dataValidatetInlineAtElement"),Integer.toString(j+1));
				new Generics().softAssert(xpathConversion, data[x-1][y]);
				/*boolean result = new Generics().dataValidation(xpathConversion, data[x-1][y]);
				assertTrue(result); */
			}
		Step("Clicking On Back Button In Inline Edit page","click","backFromInlineEditToListPage");
		}
		
	}
	public void dataValidationNORMALEDITpage(String data[][]) throws IOException, InterruptedException, AWTException
	{
        List<WebElement> elements = new Generics().elementCount(new XpathHub().xpathGetter("countRecordsInList")," Records Found");
		
		int size = elements.size();
		
		for(int i=0,x=size;i<size;i++,x--)
		{
			xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("inlineEditLink"),Integer.toString(i+1));
			Step("Clicking On App Record Inline Edit link For Record"+(i+1),"click",xpathConversion,"YES","NO");
			Step("Clciking On Edit In Inline Edit page","click","editFromInlinePage");
			
			List<WebElement> fields = new Generics().elementCount(new XpathHub().xpathGetter("editPageElementCount")," Fields Found");
			int fsize = fields.size();
			
			log._INFO("================================================================================================================");
			log._INFO("Verifying The Data In The Record"+(i+1)+" In The Records Normal Edit Page");
			log._INFO("================================================================================================================");
			
			for(int j=0,y=0;j<fsize;j++,y++)
			{
				log._INFO("Verifying The Field"+(j+1)+"In The Record"+(i+1)+" In The Records Inline Edit page");
				xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("dataValidateInRecordEditPage"),Integer.toString(j+1));
				new Generics().softAssert(xpathConversion, data[x-1][y]);
				
				/*boolean result = new Generics().dataValidation1(xpathConversion, data[x-1][y]);
				assertTrue(result);*/
			}
			
		Step("Clicking On Cancel Button In Normal Edit Page","click","cancelInEditPage");
		Step("Clicking On Back Button In Inline Edit page","click","backFromInlineEditToListPage");
		
		}
	}
	
	public void InlineUpdate(String data[][]) throws IOException, InterruptedException, AWTException
	{
		List<WebElement> elements = new Generics().elementCount(new XpathHub().xpathGetter("countRecordsInList")," Records Found");
		
		int size = elements.size();
		
		for(int i=0,x=size;i<size;i++,x--)
		{
			xpathConversion = new excel.utils.StringClsUtil().ReplaceString(new XpathHub().xpathGetter("inlineEditLink"),Integer.toString(i+1));
			Step("Clicking On App Record Inline Edit link For Record"+(i+1),"click",xpathConversion,"YES","NO");
			
			List<WebElement> fields = new Generics().elementCount(new XpathHub().xpathGetter("inlinefieldsCount")," Fields Found");
			int fsize = fields.size();
			
			log._INFO("================================================================================================================");
			log._INFO("Updating The Data In The Record"+(i+1)+" In The Records Inline Edit Page");
			log._INFO("================================================================================================================");
			
			for(int j=0,y=0;j<fsize;j++,y++)
			{
				log._INFO("Verifying The Field"+(j+1)+"In The Record"+(i+1)+" In The Records Inline Edit page");
				xpathConversion = new excel.utils.StringClsUtil().ReplaceString(new XpathHub().xpathGetter("dataValidatetInlineAtElement"),Integer.toString(j+1));
				Step("Clicking On The Control To Enter The Data","click",xpathConversion,"YES","NO");
				Step("Entering The Data..","input",xpathConversion,"YES","Vivek");
				
				xpathConversion = new excel.utils.StringClsUtil().ReplaceString(new XpathHub().xpathGetter("inlineSave"),Integer.toString(j+1));
				Step("Saving The Control In InLine-Edit","click",xpathConversion,"YES","NO");
				
				new Generics().Sleep(4000);
			}
		Step("Clicking On Back Button In Inline Edit page","click","backFromInlineEditToListPage");
		}
	}
	
	public void normalUpdate(String data[][])
	{
	}
	
	public void deleteAllRecords() throws InterruptedException, IOException, AWTException
	{
		Step("Selecting All The Records To Delete","click","selectAllRecordsToDelete");
		Step("Clicking On Delete","click","recDelete");
		Step("Accepting The Records Deletion Conformation Message","click","confmDelete");
	}
	
	
}
