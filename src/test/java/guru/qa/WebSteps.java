package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class WebSteps {

    @Step("Open main page")
    public void openMainPage(){
        Selenide.open("https://github.com/");
    }

    @Step("Search for repository {repo}")
    public void searchForRepository(String repo){
        $(".search-input-container").click();
        $("#query-builder-test").setValue(repo).pressEnter();
    }

    @Step("Click on repository link {repo}" )
    public void clickOnRepositoryLink(String repo){
        $(By.linkText(repo)).click();
    }

    @Step("Open Issues tab")
    public void openIssuesTab(){
        $("#issues-tab").click();
    }

    @Step("Check that isssue #{issue} is available")
    public void shouldSeeIssueWithNumber(int issue){
        $(withText("#" + issue)).should(Condition.exist);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot(){
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
