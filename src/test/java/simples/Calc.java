package simples;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class Calc {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        /*
        Valores previstos de flag:
        EL = Emulador Local
        EN = Emulador na Nuvem
        DL = Dispositivo Local
        DN = Dispositivo na nuvem
         */

        String flag = "EL";

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9.0");
        desiredCapabilities.setCapability("browserName", "");
        desiredCapabilities.setCapability("appiumVersion", "1.20.1");
        desiredCapabilities.setCapability("deviceName", "Samsung Galaxy S9 FHD GoogleAPI Emulator");
        desiredCapabilities.setCapability("deviceOrientation", "portrait");
        desiredCapabilities.setCapability("app", "storage:filename=Calculator_v7.8 (271241277)_apkpure.com (1).apk");
        desiredCapabilities.setCapability("appPackage", "com.google.android.calculator");
        desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
        desiredCapabilities.setCapability("SAUCE_USERNAME", "eddy.silva32");
        desiredCapabilities.setCapability("AUCE_ACCESS_KEY", "7620ca87-09dd-41f5-86c2-1ff3492caf03");
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);

        URL remoteUrl = new URL("https://eddy.silva32:7620ca87-09dd-41f5-86c2-1ff3492caf03@ondemand.us-west-1.saucelabs.com:443/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void somarDoisNumeros() {
        String expectedResult = "5";

        MobileElement btn2 = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_2");
        btn2.click();
        MobileElement btnPlus = (MobileElement) driver.findElementByAccessibilityId("plus");
        btnPlus.click();
        MobileElement btn3 = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_3");
        btn3.click();
        MobileElement btnEquals = (MobileElement) driver.findElementByAccessibilityId("equals");
        btnEquals.click();
        MobileElement lblCurrentResult = (MobileElement) driver.findElementById("com.google.android.calculator:id/result_final");
        assertEquals(expectedResult, lblCurrentResult.getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
