package avancado;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class Calcular {

    private AndroidDriver driver;
    private DesiredCapabilities desiredCapabilities;
    private URL remoteUrl;
    private static String folderName = new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime());
    private Mass mass;
    private MobileElement btnOperation;

    // Funções ou Métodos de apoio
    public void print(String printName) throws IOException {
        File photo = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        System.out.println("folderName:" + folderName);
        FileUtils.copyFile(photo, new File("target/" + folderName + "/" + printName + ".png"));

    }

    @Before
    public void setUp() throws MalformedURLException {
        /*
        Valores previstos de flag:
        EL = Emulador Local
        EN = Emulador na Nuvem
        DL = Dispositivo Local
        DN = Dispositivo na Nuvem
         */

        String flag = "EN";

        desiredCapabilities = new DesiredCapabilities();

        switch (flag) {
            case "EL":
                desiredCapabilities.setCapability("platformName", "Android");
                desiredCapabilities.setCapability("platformVersion", "10.0+");
                desiredCapabilities.setCapability("deviceName", "emulator-5554");
                desiredCapabilities.setCapability("automationName", "uiautomator2");
                desiredCapabilities.setCapability("appPackage", "com.google.android.calculator");
                desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
                desiredCapabilities.setCapability("ensureWebviewsHavePages", true);

                remoteUrl = new URL("https://localhost:4723/wd/hub");
                break;

            case "EN":
                desiredCapabilities.setCapability("platformName", "Android");
                desiredCapabilities.setCapability("platformVersion", "9.0");
                desiredCapabilities.setCapability("browserName", "");
                desiredCapabilities.setCapability("appiumVersion", "1.19.2");
                desiredCapabilities.setCapability("deviceName", "Samsung Galaxy S9 FHD GoogleAPI Emulator");
                desiredCapabilities.setCapability("deviceOrientation", "portrait");
                desiredCapabilities.setCapability("app", "storage:filename=Calculator_v7.8 (271241277)_apkpure.com (1).apk");
                desiredCapabilities.setCapability("appPackage", "com.google.android.calculator");
                desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
                desiredCapabilities.setCapability("SAUCE_USERNAME", "eddy.silva32");
                desiredCapabilities.setCapability("AUCE_ACCESS_KEY", "7620ca87-09dd-41f5-86c2-1ff3492caf03");
                desiredCapabilities.setCapability("ensureWebviewsHavePages", true);

                remoteUrl = new URL("https://eddy.silva32:7620ca87-09dd-41f5-86c2-1ff3492caf03@ondemand.us-west-1.saucelabs.com:443/wd/hub");
                break;

            case "DL":
                //TODO: a programar
                break;

            case "DN":
                //TODO: a programar
                break;
        }

    }

    @After
    public void tearDown() {
        driver.quit();
    }


    @Given("^that I open the google calculator on my smartphone$")
    public void thatIOpenTheGoogleCalculatorOnMySmartphone() {
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @When("^I select \"([^\"]*)\" plus \"([^\"]*)\" and press the equal button$")
    public void iSelectPlusAndPressTheEqualButton(String num1, String num2) throws IOException {
        print("Add Two Positive Integer numbers - Step 1 - Opened the Calculator");
        MobileElement btnA = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_" + num1);
        btnA.click();
        print("Add Two Positive Integer numbers - Step 2 - Clicked on Button " + num1);
        MobileElement btnSoma = (MobileElement) driver.findElementByAccessibilityId("plus");
        btnSoma.click();
        print("Add Two Positive Integer numbers - Step 3 - Clicked on the Plus Button");
        MobileElement btnB = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_" + num2);
        btnB.click();
        print("Add Two Positive Integer numbers - Step 4 - Clicked on Button " + num2);
        MobileElement btnIgual = (MobileElement) driver.findElementByAccessibilityId("equals");
        btnIgual.click();
        print("Add Two Positive Integer numbers - Step 5 - Clicked on the Equal button");
    }

    @Then("^it displays the result as \"([^\"]*)\"$")
    public void itDisplaysTheResultAs(String expectedResult) {
        MobileElement lblCurrentResult = (MobileElement) driver.findElementById("com.google.android.calculator:id/result_final");
        assertEquals(expectedResult, lblCurrentResult.getText());
    }

    // Given with Mass
    @Given("^that I use the mass \"([^\"]*)\" to test the calculator App$")
    public void thatIUseTheMassToTestTheCalculatorApp(String massName) throws MalformedURLException {
        // Settings to execution of emulator from the clound
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9.0");
        desiredCapabilities.setCapability("browserName", "");
        desiredCapabilities.setCapability("appiumVersion", "1.19.2");
        desiredCapabilities.setCapability("deviceName", mass.deviceName);
        desiredCapabilities.setCapability("deviceOrientation", "portrait");
        desiredCapabilities.setCapability("app", "storage:filename=Calculator_v7.8 (271241277)_apkpure.com (1).apk");
        desiredCapabilities.setCapability("appPackage", "com.google.android.calculator");
        desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
        desiredCapabilities.setCapability("SAUCE_USERNAME", "eddy.silva32");
        desiredCapabilities.setCapability("AUCE_ACCESS_KEY", "7620ca87-09dd-41f5-86c2-1ff3492caf03");
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);

        remoteUrl = new URL("https://eddy.silva32:7620ca87-09dd-41f5-86c2-1ff3492caf03@ondemand.us-west-1.saucelabs.com:443/wd/hub");

        // Open the app
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @When("^I perform the operation with two numbers$")
    public void iPerformTheOperationWithTwoNumbers() throws IOException {
        print("Add Two Positive Integer numbers - Step 1 - Opened the Calculator");
        MobileElement btnA = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_" + mass.num1);
        btnA.click();
        print("Add Two Positive Integer numbers - Step 2 - Clicked on Button " + mass.num1);

        // Select the math operation
        // Example with a button for each of the 4 operations
        /*
        switch (mass.operator){
            case "+":
                MobileElement btnPlus = (MobileElement) driver.findElementByAccessibilityId("plus");
                btnPlus.click();
                break;
            case "-":
                MobileElement btnMinus = (MobileElement) driver.findElementByAccessibilityId("minus");
                btnMinus.click();
                break;
            case "*":
                MobileElement btnMultiply = (MobileElement) driver.findElementByAccessibilityId("multiply");
                btnMultiply.click();
                break;
            case "/":
                MobileElement btnDivide = (MobileElement) driver.findElementByAccessibilityId("divide");
                btnDivide.click();
                break;
        }
        */

        //Summarized example - the symbol is read and transformed into the operation
        switch (mass.operator){
            case "+":
                btnOperation = (MobileElement) driver.findElementByAccessibilityId("plus");
                break;
            case "-":
                btnOperation = (MobileElement) driver.findElementByAccessibilityId("minus");
                break;
            case "*":
                btnOperation = (MobileElement) driver.findElementByAccessibilityId("multiply");
                break;
            case "/":
                btnOperation = (MobileElement) driver.findElementByAccessibilityId("divide");
                break;
        }
            btnOperation.click();

        // It would be much simpler if the mass already had the operation instead of the symbol
            /*
            btnOperacao = (MobileElement) driver.findElementByAccessibilityId(massa.operador);
            btnOperacao.click();
            */

            print("Add Two Positive Integer numbers - Step 3 - Clicked on the Plus Button");
            MobileElement btnB = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_" + mass.num2);
            btnB.click();
            print("Add Two Positive Integer numbers - Step 4 - Clicked on Button " + mass.num2);
            MobileElement btnIgual = (MobileElement) driver.findElementByAccessibilityId("equals");
            btnIgual.click();
            print("Add Two Positive Integer numbers - Step 5 - Clicked on the Equal button");


        }

        @Then("^it compare the current result with the expected$")
        public void itCompareTheCurrentResultWithTheExpected() {
            if(mass.operator.equals("/") && mass.num2.equals("0")) {
                // TODO map the error message of the divide
            }
            else {
                MobileElement lblCurrentResult = (MobileElement) driver.findElementById("com.google.android.calculator:id/result_final");
                assertEquals(mass.expectedResult, lblCurrentResult.getText());
            }
        }
    }

