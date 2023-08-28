package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class StepsTest {

    private static final String REPOSITORY ="eroshenkoam/allure-example";
    private static final int ISSUE = 80;

    @Test
    @DisplayName("stepsTest")
    public void stepsTest(){

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open main page", () ->{
            Selenide.open("https://github.com/");
//            attachment("Source", webdriver().driver().source());
        });

        step("Search for repository " + REPOSITORY, () ->{
            $(".search-input-container").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });

        step("Click on repository link " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });

        step("Open Issues tab", () -> {
            $("#issues-tab").click();
        });

        step("Check that isssue #" + ISSUE + " is available", () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    public void testAnnotatedStep(){

        SelenideLogger.addListener("allure", new AllureSelenide());

        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }

    @Test
    public void stepsTestWithAttachents(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open main page", () ->{
            Selenide.open("https://github.com/");
            attachment("Source", webdriver().driver().source());
        });

    }

    @Test
    public void TestAnnotatedStepWithAttachments(){

        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.takeScreenshot();
    }
}
