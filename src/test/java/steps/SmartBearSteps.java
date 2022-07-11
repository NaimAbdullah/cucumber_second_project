package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.SmartBearPage;
import utils.Driver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class SmartBearSteps {

    WebDriver driver;
    SmartBearPage smartBearPage;
    List<String> info;

    @Before
    public void setup(){
        driver = Driver.getDriver();
        smartBearPage = new SmartBearPage();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        info = new ArrayList<>();
        info.add("John Doe");
        info.add("FamilyAlbum");
        info.add("2");
        info.add(formatter.format(date));
        info.add("1234 west Ave");
        info.add("Chicago");
        info.add("Illinois");
        info.add("60422");
        info.add("Visa");
        info.add("1234123412341234");
        info.add("01/25");
    }

    String name = "John Doe", street = "1234 west Ave", city = "Chicago", state = "Illinois", zip = "60422", card = "1234123412341234", expiration = "01/25";

    @Given("user is on {string}")
    public void userIsOn(String url) {
        driver.get(url);
    }

    @When("user enters username as {string}")
    public void userEntersUsernameAs(String username) {
        smartBearPage.usernameInputBox.sendKeys(username);
    }

    @And("user enters password as {string}")
    public void userEntersPasswordAs(String password) {
        smartBearPage.passwordInputBox.sendKeys(password);
    }

    @And("user clicks on Login button")
    public void userClicksOnLoginButton() {
        smartBearPage.loginButton.click();
    }

    @Then("user should see {string} Message")
    public void userShouldSeeMessage(String text) {
        Assert.assertEquals(text, smartBearPage.errorMessage.getText());
    }

    @Then("user should be routed to {string}")
    public void userShouldBeRoutedTo(String url) {
        Assert.assertEquals(url, driver.getCurrentUrl());
    }

    @And("validate below menu items are displayed")
    public void validateBelowMenuItemsAreDisplayed(DataTable dataTable) {
        for (int i = 0; i < dataTable.asList().size(); i++) {
            Assert.assertEquals(dataTable.asList().get(i), smartBearPage.webOrderItems.get(i).getText());
        }
    }

    @When("user clicks on {string} button")
    public void userClicksOnButton(String buttonText) {
        switch (buttonText){
            case "Check All":
                smartBearPage.checkAllButton.click();
                break;
            case "Uncheck All":
                smartBearPage.uncheckAllButton.click();
                break;
            case "Delete Selected":
                smartBearPage.deleteSelectedButton.click();
                break;
            case "Process":
                smartBearPage.processButton.click();
                break;
            default:
                throw new RuntimeException("Invalid button text in feature file!!");
        }
    }

    @Then("all rows should be checked")
    public void allRowsShouldBeChecked() {
        for (int i = 0; i < smartBearPage.checkboxes.size(); i++) {
            Assert.assertTrue(smartBearPage.checkboxes.get(i).isSelected());
        }
    }

    @Then("all rows should be unchecked")
    public void allRowsShouldBeUnchecked() {
        for (int i = 0; i < smartBearPage.checkboxes.size(); i++) {
            Assert.assertFalse(smartBearPage.checkboxes.get(i).isSelected());
        }
    }

    @When("user clicks on {string} menu item")
    public void userClicksOnMenuItem(String buttonText) {
        switch (buttonText){
            case "Order":
                smartBearPage.webOrderItems.get(2).click();
                break;
            case "View all orders":
                smartBearPage.webOrderItems.get(0).click();
                break;
            default:
                throw new RuntimeException("Invalid button text in feature file!!");
        }
    }

    @And("user selects {string} as product")
    public void userSelectsAsProduct(String buttonText) {
        Select select = new Select(smartBearPage.productDropdown);
        select.selectByVisibleText(buttonText);
    }

    @And("user enters {int} as quantity")
    public void userEntersAsQuantity(int num) {
        smartBearPage.quantityInputBox.sendKeys(Keys.COMMAND + "a");
        smartBearPage.quantityInputBox.sendKeys(String.valueOf(num));
    }

    @And("user enters all address information")
    public void userEntersAllAddressInformation() {
        smartBearPage.customerNameInputBox.sendKeys(name);
        smartBearPage.streetInputBox.sendKeys(street);
        smartBearPage.cityInputBox.sendKeys(city);
        smartBearPage.stateInputBox.sendKeys(state);
        smartBearPage.zipInputBox.sendKeys(zip);
    }

    @And("user enters all payment information")
    public void userEntersAllPaymentInformation() {
        smartBearPage.cardType.get(0).click();
        smartBearPage.cardNumber.sendKeys(card);
        smartBearPage.expireDate.sendKeys(expiration);
    }

    @Then("user should see their order displayed in the List of All Orders table")
    public void userShouldSeeTheirOrderDisplayedInTheListOfAllOrdersTable() {
        Assert.assertTrue(smartBearPage.currentOrder.isDisplayed());
    }

    @And("validate all information entered displayed correct with the order")
    public void validateAllInformationEnteredDisplayedCorrectWithTheOrder() {
        for (int i = 1; i < smartBearPage.currentOrderInfo.size()-1; i++) {
            Assert.assertEquals(info.get(i-1), smartBearPage.currentOrderInfo.get(i).getText());
        }
    }

    @Then("validate all orders are deleted from the {string}")
    public void validateAllOrdersAreDeletedFromTheListOfAllOrders(String table) {
        if ("Delete".equals(table)) {
            try {
                Assert.assertFalse(smartBearPage.wholeTable.isDisplayed());
            } catch (NoSuchElementException e) {
                Assert.assertTrue(true);
            }
        }
    }

    @And("validate user sees {string} Message")
    public void validateUserSeesMessage(String text) {
        Assert.assertEquals(text, smartBearPage.orderMessage.getText());
    }
}
