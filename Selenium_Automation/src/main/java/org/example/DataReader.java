package org.example;

import com.opencsv.exceptions.CsvValidationException;
import com.opencsv.CSVReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DataReader {

        protected static final String env = System.getProperty("environment");
        protected  static  final String file = System.getProperty("PropertiesFile");

        public WebDriver driver;
        public JavascriptExecutor js;
        public WebDriverWait wait;
        static int noofRow;
        static String xlFilePath;


        //initial step - select domain
        @BeforeMethod
        public void setup() throws IOException {
            if(env.equals("pet")){
                xlFilePath = "C:\\Users\\User\\Documents\\Register.csv";
            }
            else if(env.equals("wapp")){
                xlFilePath = file;
            }

        }


        //read csv file data
        public static Object[][] getData()  throws IOException, CsvValidationException {

        String[] line;
        List<String[]> arrlist = new ArrayList<String[]>();
        CSVReader reader = new CSVReader(new FileReader(xlFilePath));

        while ((line = reader.readNext()) != null) {
            arrlist.add(line);
        }
        int index = 0;
        arrlist.remove(index);
        int rowCount = arrlist.size();
        int columnCount = arrlist.get(0).length;

        String Data[][] = new String [rowCount][columnCount];
        for(int i = 0 ; i < rowCount ; i++) {
            String[] eachRow = arrlist.get(i);
            for(int j = 0 ; j < columnCount ; j++) {
                Data[i][j] = eachRow[j];
            }
        }

        noofRow = rowCount -1;
        return Data;
    }

    //get URL_ID
    public static String url() throws CsvValidationException, IOException {
        //String url[] = new String[100];
        //for(int i = 0;i<=noofRow;i++){
           String url = (String) getData()[1][6];
       // }
        return url;
    }
   
    //get UI_Type
    public static String[] uitype() throws CsvValidationException, IOException {
        String uitype[] = new String[100];
        for(int i = 0;i<=noofRow;i++){
            uitype[i] = (String) getData()[i][1];
        }
        return uitype;
    }

    //get Action
    public static String[] action() throws CsvValidationException, IOException {
        String action[] = new String[100];
        for(int i = 0;i<=noofRow;i++){
            action[i] = (String) getData()[i][2];
        }
        return action;
    }

    //get Read Type
    public static String[] readType() throws CsvValidationException, IOException {
            String readtype[] = new String[100];
            for(int i = 0;i<=noofRow;i++){
                readtype[i] = (String) getData()[i][3];
            }
            return readtype;
    }

    //get Read Path
    public static String[] ReadPath() throws CsvValidationException, IOException {
        String readpath[] = new String[100];
        for(int i = 0;i<=noofRow;i++){
            readpath[i] = (String) getData()[i][4];
        }
        return readpath;
    }

    public static String[] Data() throws CsvValidationException, IOException {
        String data[] = new String[100];
        for(int i = 0;i<=noofRow;i++){
            data[i] = (String) getData()[i][5];
        }
        return data;
    }

    //set locator and value
    
    public static By locatorParser(String locator, String value,String data) {
        By loc = By.id(locator);
        if (locator.contains("id"))
            loc = By.id(value);

        else if (locator.contains("name"))
            loc = By.name(value);

        else if (locator.contains("xpath")){
            if(value.contains("//label[@for='']")){
               String updatevalue = "//label[@for='"+data+"']";
               loc = By.xpath(updatevalue);
            }
            else{
                loc = By.xpath(value);
            }
        }
        else if(locator.contains("linkText")){
            loc = By.linkText(value);
        }

        return loc;

    }


    //select UI Type and it function - click , sendkeys, select data
    
    @SuppressWarnings("unlikely-arg-type")
	public void actionMethod(String uitype,String action,String readtype,String value,String data){
    	
        if(uitype.equals("button")){
            driver.findElement(locatorParser(readtype,value,data)).click();
        }
        else if(uitype.equals("radio")){
            driver.findElement(locatorParser(readtype,value,data)).click();
        }
        else if(uitype.equals("text")){
            driver.findElement(locatorParser(readtype,value,data)).sendKeys(data);
        }
        else if(uitype.equals("calender")){
            js.executeScript ("document.getElementById('dobString').removeAttribute('readonly',0);");
            js.executeScript ("document.getElementById('dobString').setAttribute('value', '')");
            driver.findElement(locatorParser(readtype,value,data)).sendKeys("27/05/1989");

            js.executeScript ("document.getElementById('dob').removeAttribute('type',0);");
            js.executeScript ("document.getElementById('dob').setAttribute('value', '')");
            driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]")).click();
       }
       else if(uitype.equals("assertionEnabled")){
       	 assertionEnabled(readtype,value,data); 
       }
       else if(uitype.equals("assertionTextDisplayed")) {
       	assertionTextDisplayed(readtype,value,data);
       }
       else if(uitype.equals("assertionElementDisplayed")) {
    	   assertionElementDisplayed(readtype,value,data);
       }
       else if(uitype.equals("assertionSelected")) {
       	assertionElementSelected(readtype,value,data);
       }

   }
   public void assertionEnabled(String readtype,String value,String data) {
   	Assert.assertEquals(true,driver.findElement(locatorParser(readtype,value,data)).isEnabled());
   	
   }
   public void assertionTextDisplayed(String readtype,String value,String data) {
	
   	Assert.assertEquals(true,driver.findElement(locatorParser(readtype,value,data)).isDisplayed());
   	Assert.assertEquals(data, driver.findElement(locatorParser(readtype,value,data)).getText());
   	
   }
   public void assertionElementDisplayed(String readtype,String value,String data) {
		
	 Assert.assertEquals(true,driver.findElement(locatorParser(readtype,value,data)).isDisplayed());
	   
	   }
   public void assertionElementSelected(String readtype,String value,String data) {
   	Assert.assertEquals(true,driver.findElement( locatorParser(readtype,value,data)).isSelected());
   	
   }

    

}
