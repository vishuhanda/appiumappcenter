package pagesTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import utils.DriverManager;

public class DemoPageTest {

    @AndroidFindBy(id ="com.example.demoapp:id/button_first")
    private MobileElement updateBtn;

//    @iOSXCUITFindBy(accessibility = "Use Testdroid Cloud")
    @AndroidFindBy(xpath="//*[@resource-id='com.bitbar.testdroid:id/radio2']")
    private MobileElement selectRadioBtn;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]")
    private MobileElement nativeLanguagePopUp;

    @AndroidFindBy(id = "com.bitbar.testdroid:id/editText1")
    private MobileElement nameTextBox;

    @AndroidFindBy(id = "com.bitbar.testdroid:id/button1")
    private MobileElement answerBtn;

    @AndroidFindBy(id="com.bitbar.testdroid:id/textView1")
    private MobileElement wrongAnswerTxtBox;


    public DemoPageTest(){
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()),this);
    }

    public void clickBtn(){
        updateBtn.click();
    }

    public void selectRadioBtn(){
        selectRadioBtn.click();
    }

    public void enterName(){
        nameTextBox.sendKeys("hello vishu");
    }

}
