package driver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidDriverHandler extends DriverHandler{
		
	private static Logger log = LoggerFactory.getLogger(AndroidDriverHandler.class);
	private AndroidDriver<WebElement> driver = null;
	
	@Override
	 public AndroidDriver<WebElement> startMobile(String platform, String platformVersion, String deviceName, String appDir, String automationName, String url) throws MalformedURLException{
	    	
	    	File appApk = new File(appDir);
	    	log.info(String.format("====get application package: %s ====",appDir));
	        //iOS app file: https://applitools.bintray.com/Examples/eyes-ios-hello-world.zip
	    	
	    	DesiredCapabilities cap = new DesiredCapabilities();
	        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
	        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
	        cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
	        cap.setCapability(MobileCapabilityType.APP, appApk.getAbsolutePath());
	        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
	       
	        cap.setCapability(MobileCapabilityType.NO_RESET, true);	
			cap.setCapability("sessionOverride", true);	
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,"60");
			cap.setCapability("resetKeyboard", false);
			cap.setCapability("noSign", true);
	        
	        URL appiumUrl = new URL(url);
	        
	        driver = new AndroidDriver<>(appiumUrl, cap);
	        return driver;
	    }
   
	@Override
    public void closeDriver() {
        if (driver != null) {
        	log.info(String.format("====close application ===="));
            driver.closeApp();
            
            log.info(String.format("====close Android driver ===="));
        	driver.quit();
        }
    }
}