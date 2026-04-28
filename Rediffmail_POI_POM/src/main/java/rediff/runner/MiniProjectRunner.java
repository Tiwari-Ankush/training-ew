
package rediff.runner;

import rediff.base.DriverFactory;
import rediff.pages.LoginLandingPage;
import rediff.pages.RegistrationPage;
import rediff.utils.ExcelUtil;

public class MiniProjectRunner {

    public static void main(String[] args) {

        String url = "https://mail.rediff.com/cgi-bin/login.cgi";

        // Excel path
        String excelPath = System.getProperty("user.dir")
                + "/src/test/resources/testdata/rediffData.xlsx";

        ExcelUtil excel = new ExcelUtil(excelPath, "Sheet1");

        try {
            DriverFactory.initDriver();
            DriverFactory.driver.get(url);

            LoginLandingPage landing = new LoginLandingPage(DriverFactory.driver);
            RegistrationPage reg = new RegistrationPage(DriverFactory.driver);

            // Loop through Excel rows (start from 1 because row 0 is headers)
            int rows = excel.getRowCount();

            for (int r = 1; r <= rows; r++) {
                String fullName = excel.getCellData(r, 0);
                String rediffId = excel.getCellData(r, 1);
                String password = excel.getCellData(r, 2);
                String day = excel.getCellData(r, 3);
                String month = excel.getCellData(r, 4);
                String year = excel.getCellData(r, 5);
                String country = excel.getCellData(r, 6);

                System.out.println("\n=== Running for Excel Row: " + r + " ===");

                landing.clickCreateAccount();

                reg.enterFullName(fullName);
                reg.enterRediffId(rediffId);
                reg.clickCheckAvailability();
                reg.selectFirstSuggestionIfAvailable();

                reg.enterPassword(password);
                reg.enterRePassword(password);

                reg.clickAltEmailCheckbox();
                reg.setDOB(day, month, year);

                reg.printAllCountries();
                reg.selectCountry(country);

                System.out.println("[INFO] Flow completed till country selection.");
                System.out.println("[INFO] Stopping before final submit/captcha.");
                break; // remove this if you want to repeat for all rows (site may block repeated automation)
            }

        } finally {
            excel.close();
            DriverFactory.quitDriver();
            System.out.println("Browser closed successfully.");
        }
    }
}