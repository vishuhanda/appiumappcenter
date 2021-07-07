package Tests;

import com.microsoft.appcenter.appium.Factory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
//import org.testng.annotations.Test;
import pages.DemoPageTest;
import utils.DriverManager;
import utils.PropertyManager;
import java.net.MalformedURLException;


public class DemoTest {


    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    @Test
    public void firstTest() throws MalformedURLException, InterruptedException {

        PropertyManager.initProperties("");
        DriverManager.initDriver();

        DemoPageTest demoPage = new DemoPageTest();
        demoPage.clickBtn();


        System.out.println("First test");

        Thread.sleep(5000);
        DriverManager.setLabel("Ending app test 1");
        DriverManager.setLabel("Ending app test 2");
        DriverManager.setLabel("Ending app test 3");
        DriverManager.setLabel("Ending app test 4");
        DriverManager.tearDownDriver();

    }

    @Test
    public void secondTest() throws MalformedURLException {

        PropertyManager.initProperties("configAndroid.properties");
        DriverManager.initDriver();

        System.out.println("Second test");


        DriverManager.tearDownDriver();

    }





}
