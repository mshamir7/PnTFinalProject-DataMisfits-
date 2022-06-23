package apps.cargurus;

import base.CommonAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.cargurus.HomePage;
import pages.cargurus.SearchResultPage;
import pages.cargurus.ShoppingForAUsedCarPage;
import utility.ConnectDB;
import utility.ExcelReader;
import utility.Utility;

import java.util.List;

public class FilterSearchResultsUsedCars extends CommonAPI {

    //@Test
    public void searchUsedCars() {
        HomePage homePage = new HomePage(getDriver());
        ShoppingForAUsedCarPage shoppingForAUsedCarPage = new ShoppingForAUsedCarPage(getDriver());
        homePage.clickHeaderBuyBtn();
        shoppingForAUsedCarPage.selectAllMakesDropDownList("Acura");
        shoppingForAUsedCarPage.selectAllModelsDropDownList("ILX");
        shoppingForAUsedCarPage.typeMinPriceTextBox("20000");
        shoppingForAUsedCarPage.typeMaxPriceTextBox("35000");
        shoppingForAUsedCarPage.typeZipCode("11375");
        shoppingForAUsedCarPage.selectRadius("50 mi");
        shoppingForAUsedCarPage.clickSearchBtn();
        String actualSearchTitleText = getDriver().findElement(By.xpath("//body/main[@id='main']/div[@id='cargurus-listing-search']/div[1]/div[1]/div[1]/h1[1]")).getText();
        Assert.assertEquals("Used Acura ILX for Sale in Forest Hills, NY", actualSearchTitleText);
    }

    //Update Zipcode From SEARCH RESULTS PAGE TC022

   // @Test
    public void updateZipCodeFromSearchResultsPage() {
        HomePage homePage = new HomePage(getDriver());
        SearchResultPage searchResultPage = new SearchResultPage(getDriver());
        ShoppingForAUsedCarPage shoppingForAUsedCarPage = new ShoppingForAUsedCarPage(getDriver());
        homePage.clickHeaderBuyBtn();
        shoppingForAUsedCarPage.selectAllMakesDropDownList("Acura");
        shoppingForAUsedCarPage.selectAllModelsDropDownList("ILX");
        shoppingForAUsedCarPage.typeMinPriceTextBox("20000");
        shoppingForAUsedCarPage.typeMaxPriceTextBox("35000");
        shoppingForAUsedCarPage.typeZipCode("11375");
        shoppingForAUsedCarPage.selectRadius("50 mi");
        shoppingForAUsedCarPage.clickSearchBtn();
        searchResultPage.clearZipCodeTextBox();
        searchResultPage.typeAndUpdateZipCode("11565");
        searchResultPage.clickUpdateZip();
        String actualUpdateSearchTitleText = getDriver().findElement(By.xpath("//body/main[@id='main']/div[@id='cargurus-listing-search']/div[1]/div[1]/div[1]/h1[1]")).getText();
        // Assert.assertEquals("Used Acura ILX for Sale in Malverne, NY", actualUpdateSearchTitleText);
    }


    //ERROR MESSAGE DISPLAYED USING INVALID ZIPCODE TC023

   // @Test
    public void invalidZipCodeErrorMessage() {
        HomePage homePage = new HomePage(getDriver());
        SearchResultPage searchResultPage = new SearchResultPage(getDriver());
        ShoppingForAUsedCarPage shoppingForAUsedCarPage = new ShoppingForAUsedCarPage(getDriver());
        homePage.clickHeaderBuyBtn();
        shoppingForAUsedCarPage.selectAllMakesDropDownList("Acura");
        shoppingForAUsedCarPage.selectAllModelsDropDownList("ILX");
        shoppingForAUsedCarPage.typeMinPriceTextBox("20000");
        shoppingForAUsedCarPage.typeMaxPriceTextBox("35000");
        shoppingForAUsedCarPage.typeZipCode("11375");
        shoppingForAUsedCarPage.selectRadius("50 mi");
        shoppingForAUsedCarPage.clickSearchBtn();
        searchResultPage.clearZipCodeTextBox();
        searchResultPage.typeAndUpdateZipCode("00000");
        searchResultPage.clickUpdateZip();
        // Assert.assertTrue(searchResultPage.assertErrorMessageBanner());
    }


    //FILTER SEARCH BY BODY STYLE FROM SEARCH RESULTS PAGE TC024

   // @Test
    public void filterByBodyStyleSearchResultsPage() {
        HomePage homePage = new HomePage(getDriver());
        SearchResultPage searchResultPage = new SearchResultPage(getDriver());
        ShoppingForAUsedCarPage shoppingForAUsedCarPage = new ShoppingForAUsedCarPage(getDriver());
        homePage.clickHeaderBuyBtn();
        shoppingForAUsedCarPage.selectAllMakesDropDownList("Acura");
        shoppingForAUsedCarPage.selectAllModelsDropDownList("ILX");
        shoppingForAUsedCarPage.typeMinPriceTextBox("20000");
        shoppingForAUsedCarPage.typeMaxPriceTextBox("35000");
        shoppingForAUsedCarPage.typeZipCode("11375");
        shoppingForAUsedCarPage.selectRadius("50 mi");
        shoppingForAUsedCarPage.clickSearchBtn();
        waitFor(2);
        searchResultPage.clickBodyStyleTab();
        searchResultPage.selectBodyStyleDropDownList("Sedan");
        searchResultPage.clearZipCodeTextBox();
        searchResultPage.typeAndEnterZip("11565");
        String actualText = getDriver().findElement(By.xpath("//em[contains(text(),'Sedans')]")).getText();
        Assert.assertEquals("Sedans", actualText);
        String actualText1 = getDriver().findElement(By.xpath("//em[contains(text(),'Malverne, NY')]")).getText();
        Assert.assertEquals("Malverne, NY", actualText1);
    }

    //FILTER SEARCH BY PRICE FROM SEARCH RESULTS PAGE TC025

  //  @Test
    public void filterByPriceSearchResultsPage() {
        HomePage homePage = new HomePage(getDriver());
        SearchResultPage searchResultPage = new SearchResultPage(getDriver());
        ShoppingForAUsedCarPage shoppingForAUsedCarPage = new ShoppingForAUsedCarPage(getDriver());
        homePage.clickHeaderBuyBtn();
        shoppingForAUsedCarPage.selectAllMakesDropDownList("Acura");
        shoppingForAUsedCarPage.selectAllModelsDropDownList("ILX");
        shoppingForAUsedCarPage.typeMinPriceTextBox("20000");
        shoppingForAUsedCarPage.typeMaxPriceTextBox("35000");
        shoppingForAUsedCarPage.typeZipCode("11375");
        shoppingForAUsedCarPage.selectRadius("50 mi");
        shoppingForAUsedCarPage.clickSearchBtn();
        searchResultPage.clickByPriceTab();
        searchResultPage.clearMinPriceTextBox();
        searchResultPage.typeMinPrice("15000");
        searchResultPage.clearMaxPriceTextBox();
        searchResultPage.typeMaxPrice("25000");
        searchResultPage.clearZipCodeTextBox();
        searchResultPage.typeAndEnterZipCode("11565");
        // searchResultPage.clickSearchButton();
        String actual = getDriver().findElement(By.xpath("//div[contains(text(),'Price Search')]")).getText();
        Assert.assertEquals("Price Search", actual);
    }


    //NAVIGATE TO REQUEST INFO DIALOG BOX TC026
   // @Test
    public void requestInfoDialogueBox() {
        HomePage homePage = new HomePage(getDriver());
        SearchResultPage searchResultPage = new SearchResultPage(getDriver());
        ShoppingForAUsedCarPage shoppingForAUsedCarPage = new ShoppingForAUsedCarPage(getDriver());
        homePage.clickHeaderBuyBtn();
        shoppingForAUsedCarPage.selectAllMakesDropDownList("Acura");
        shoppingForAUsedCarPage.selectAllModelsDropDownList("ILX");
        shoppingForAUsedCarPage.typeMinPriceTextBox("20000");
        shoppingForAUsedCarPage.typeMaxPriceTextBox("35000");
        shoppingForAUsedCarPage.typeZipCode("11375");
        shoppingForAUsedCarPage.selectRadius("50 mi");
        shoppingForAUsedCarPage.clickSearchBtn();
        searchResultPage.clickRequestInfoBtn();
        searchResultPage.typeRequestInfoFirstName("John");
        searchResultPage.typeRequestInfoLastName("Smith");
        searchResultPage.typeRequestInfoPostalCode("11565");
        searchResultPage.typeRequestInfoEmailAddress("abcd1234@gmail.com");
        Assert.assertTrue(getDriver().findElement(By.xpath("//span[contains(text(),'Send')]")).isDisplayed());
    }

    @Test
    public void searchMultipleItemsUsingExcelSpreadSheet() throws UnhandledAlertException {
        HomePage homePage = new HomePage(getDriver());
        SearchResultPage searchResultPage = new SearchResultPage(getDriver());
        ShoppingForAUsedCarPage shoppingForAUsedCarPage = new ShoppingForAUsedCarPage(getDriver());
        homePage.clickHeaderBuyBtn();
        shoppingForAUsedCarPage.selectAllMakesDropDownList("Acura");
        shoppingForAUsedCarPage.selectAllModelsDropDownList("ILX");
        shoppingForAUsedCarPage.typeMinPriceTextBox("20000");
        shoppingForAUsedCarPage.typeMaxPriceTextBox("35000");
        shoppingForAUsedCarPage.typeZipCode("11375");
        shoppingForAUsedCarPage.selectRadius("50 mi");
        shoppingForAUsedCarPage.clickSearchBtn();
        searchResultPage.clearUpdateZip();

        ExcelReader excelReader = new ExcelReader(Utility.currentDir + "/Search.xlsx");
        List<String> items = excelReader.getEntireColumnForGivenHeader("Sheet1", "Zipcodes");
        for (String item : items) {
            waitFor(2);
            searchResultPage.typeAndEnterUpdatedZip(item);
            waitFor(3);
            searchResultPage.clearUpdateZip();
            waitFor(2);
        }
        Assert.assertEquals("Used Acura ILX for Sale in Jackson Heights, NY - CarGurus", getDriver().getTitle());
    }

    @Test
    public void searchMultipleItemsUsingMySQLDB() throws UnhandledAlertException {
        HomePage homePage = new HomePage(getDriver());
        SearchResultPage searchResultPage = new SearchResultPage(getDriver());
        ShoppingForAUsedCarPage shoppingForAUsedCarPage = new ShoppingForAUsedCarPage(getDriver());
        homePage.clickHeaderBuyBtn();
        shoppingForAUsedCarPage.selectAllMakesDropDownList("Acura");
        shoppingForAUsedCarPage.selectAllModelsDropDownList("ILX");
        shoppingForAUsedCarPage.typeMinPriceTextBox("20000");
        shoppingForAUsedCarPage.typeMaxPriceTextBox("35000");
        shoppingForAUsedCarPage.typeZipCode("11375");
        shoppingForAUsedCarPage.selectRadius("50 mi");
        shoppingForAUsedCarPage.clickSearchBtn();
        searchResultPage.clearUpdateZip();

        ConnectDB cdb = new ConnectDB();
        cdb.connectToMySql();
        List<String> zipcodes = cdb.directDatabaseQueryExecute("Select * from locations", "zipcodes");
        for (String zipcode : zipcodes) {
            waitFor(2);
            searchResultPage.typeAndEnterUpdatedZip(zipcode);
            waitFor(2);
            searchResultPage.clearUpdateZip();
            waitFor(1);
        }
        Assert.assertEquals("Used Acura ILX for Sale in Jamaica, NY - CarGurus", getDriver().getTitle());
    }
}

