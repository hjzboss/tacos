package tacos;

import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class RestfulTest {

    @Autowired
    TestRestTemplate rest;

    @LocalServerPort
    private int port;

    private static HtmlUnitDriver browser;

    @BeforeClass
    public static void setup() {
        browser = new HtmlUnitDriver();
        browser.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeBrowser() {
        browser.quit();
    }

    @Test
    public void test(){
        Taco get = rest.getForObject(getUrl(),
                Taco.class);
        assertEquals(get.toString(),
                "Taco(id=1, name=asdad, createdAt=Wed Nov 04 15:29:21 CST 2020, " +
                        "ingredients=[Ingredient(id=COTO, name=Corn Tortilla, type=WRAP), " +
                        "Ingredient(id=CHED, name=Cheddar, type=CHEESE)])");
    }

    private String getUrl() {
        return "http://localhost:" + port + "/design/1";
    }
}
