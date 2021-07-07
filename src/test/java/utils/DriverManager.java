package utils;
import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import com.microsoft.appcenter.appium.EnhancedIOSDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import com.microsoft.appcenter.appium.Factory;



public class DriverManager {



    private static ThreadLocal<AppiumDriver<MobileElement>> appiumDriverThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<EnhancedAndroidDriver<MobileElement>> enhancedAndroidDriverThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<EnhancedIOSDriver<MobileElement>> enhancedIOSDriverThreadLocal = new ThreadLocal<>();

    private static ThreadLocal<Boolean> isAndroid = new ThreadLocal<>();
    private static ThreadLocal<Boolean> label = new ThreadLocal<>();
    public static AppiumDriver<MobileElement> getDriver(){
        return appiumDriverThreadLocal.get();
    }
    public static boolean isAndroid(){
        return isAndroid.get();
    }

    public static void initDriver() throws MalformedURLException {

        AppiumDriver<MobileElement>  appdriver;

        DesiredCapabilities desiredCap  = new DesiredCapabilities();
//        for (Map.Entry<Object, Object> desiredCapability:desiredCapabilities.entrySet()) {
//            if(desiredCapability.getValue().toString().equalsIgnoreCase("true") ||
//                    desiredCapability.getValue().toString().equalsIgnoreCase("false")){
//                desiredCap.setCapability(desiredCapability.getKey().toString(),Boolean.parseBoolean(desiredCapability.getValue().toString()));
//            }
//            else{
//                desiredCap.setCapability(desiredCapability.getKey().toString(),desiredCapability.getValue().toString());
//            }
//
//        }

        desiredCap.setCapability("appType","ANDROID");
        desiredCap.setCapability("device","Google Pixel 3");
        desiredCap.setCapability("os_version","9.0");
        desiredCap.setCapability("project","First Java Project");
        desiredCap.setCapability("name","first_test");
        desiredCap.setCapability("autoDismissAlerts","TRUE");
        desiredCap.setCapability("deviceName","emulator-5554");
        desiredCap.setCapability("udid","emulator-5554");
        desiredCap.setCapability("clearSystemFiles","true");
        desiredCap.setCapability("fullReset","true");
        desiredCap.setCapability("autoGrantPermissions","true");
        desiredCap.setCapability("allowTestPackages","true");
//        desiredCap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        desiredCap.setCapability("app","C:\\Users\\Vishu\\IdeaProjects\\appiumappcenter\\app-debug.apk");

        if(System.getProperty("propertiesFile")!=null){
            if(System.getProperty("propertiesFile").equalsIgnoreCase("configAndroid.properties")){
                appdriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCap);
                isAndroid.set(true);
            }
            else if(System.getProperty("propertiesFile").equalsIgnoreCase("configIos.properties")){
                appdriver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCap);
                isAndroid.set(false);
            }
            else if(System.getProperty("propertiesFile").equalsIgnoreCase("configBrowserStackAndroid")){
                appdriver = new AndroidDriver<MobileElement>(new URL("http://hub.browserstack.com/wd/hub"), desiredCap);
                isAndroid.set(true);
            }
            else if(System.getProperty("propertiesFile").equalsIgnoreCase("configBrowserStackIos")){
                appdriver = new IOSDriver<MobileElement>(new URL("http://hub.browserstack.com/wd/hub"), desiredCap);
                isAndroid.set(false);
            }
            else{
                throw  new RuntimeException("Please add valid Properties file");
            }
        }
        else{
//            isAndroid.set(false);
            isAndroid.set(true);
//            appdriver = new AndroidDriver<MobileElement>(new URL("http://hub.browserstack.com/wd/hub"), desiredCap);
//            appdriver = new IOSDriver<MobileElement>(new URL("http://hub.browserstack.com/wd/hub"), desiredCap);
//            appdriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCap);
//            appdriver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
//            appdriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCap);

            EnhancedAndroidDriver<MobileElement> enhancedAndroidDriver = Factory.createAndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCap);
            enhancedAndroidDriverThreadLocal.set(enhancedAndroidDriver);
            appdriver = enhancedAndroidDriver;


        }
        appiumDriverThreadLocal.set(appdriver);

    }

    public static void setLabel(String label){
        if(isAndroid.get()){
            enhancedAndroidDriverThreadLocal.get().label(label);
        }else{
            enhancedIOSDriverThreadLocal.get().label(label);
        }
    }


    public static void tearDownDriver(){
        appiumDriverThreadLocal.get().quit();
        appiumDriverThreadLocal.remove();

        enhancedAndroidDriverThreadLocal.get().quit();
        enhancedAndroidDriverThreadLocal.remove();

//        enhancedIOSDriverThreadLocal.get().quit();
//        enhancedAndroidDriverThreadLocal.remove();
    }

}
