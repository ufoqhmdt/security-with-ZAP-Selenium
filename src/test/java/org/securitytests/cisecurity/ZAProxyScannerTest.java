package org.securitytests.cisecurity;


import edu.umass.cs.benchlab.har.HarEntry;
import edu.umass.cs.benchlab.har.HarRequest;
import edu.umass.cs.benchlab.har.HarResponse;
import net.continuumsecurity.proxy.HarUtils;
import net.continuumsecurity.proxy.ProxyException;
import net.continuumsecurity.proxy.ScanningProxy;
import net.continuumsecurity.proxy.Spider;
import net.continuumsecurity.proxy.ZAProxyScanner;

import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.securitytests.cisecurity.drivers.ProxyFactory;
import org.securitytests.cisecurity.StringUtils;
import org.zaproxy.clientapi.core.Alert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.thoughtworks.selenium.SeleneseTestCase.assertEquals;
import static org.junit.Assert.assertTrue;


public class ZAProxyScannerTest {
    static WebDriver driver;
    //static ZAProxyScanner zaproxy;
   
    static String HOST = Config.getProxyHost();//"127.0.0.1";
    static int PORT = Config.getProxyPort();
    static String CHROME = "src/test/resources/chromedriver.exe";
    String scannerIds = null;
   // static String BASEURL = "http://localhost:9090/";
    static String BASEURL = Config.getBaseUrlForMicroSite();
    //static String BASEURL = Config.getBaseUrlForEshop();
    static List<Alert> alerts = new ArrayList<Alert>();
    
    Logger log = Logger.getLogger(ZAProxyScannerTest.class);
    static ScanningProxy scanner;
    static Spider spider;

    
    @BeforeClass
    public static void configure() throws Exception {
        //zaproxy = new ZAProxyScanner(HOST, PORT, "");
    	scanner = ProxyFactory.getScanningProxy();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, scanner.getSeleniumProxy());

        System.setProperty("webdriver.chrome.driver", CHROME);
        driver=new ChromeDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        
        
        spider = ProxyFactory.getSpider();
     
    }

    @Test
    public void test_axss() throws Exception {
    	
    	scanner.clear();
        alerts.clear();
        this.testMakeRequest();
        this.enablePolicy(Constants.XSSPOLICY);
        //this.enablePolicy(Constants.SQLINJPOLICY);
        spider.spider(BASEURL);
        this.runScanner(BASEURL);
        alerts=scanner.getAlerts();
        String details=ScanningUtil.getAlertDetails(alerts);
        Date timestamp=new Date();
        
        FileUtils.writeFile("output/alerts_"+timestamp.getTime()+".txt",details);
     
        System.out.println(scanner.getAlertsCount());
        scanner.disableAllScanners();
    }
    public void runScanner(String BASEURL) throws Exception {
    	System.out.println("Scanning: "+BASEURL);
    	scanner.scan(BASEURL);
        int complete = 0;
        while (complete < 100) {
            complete = scanner.getScanStatus();
            System.out.println("Scan is " + complete + "% complete.");
            Thread.sleep(1000);
        }
    }
    
    //@Test
    public void test_Eshop() throws Exception {
    	scanner.clear();
        alerts.clear();
        this.testMakeRequestForViewAllProducts();
        this.enablePolicy(Constants.XSSPOLICY);
        //this.enablePolicy(Constants.SQLINJPOLICY);
        spider.spider(BASEURL);
        this.runScanner(BASEURL);
        alerts=scanner.getAlerts();
        System.out.println(scanner.getAlertsCount());
    }
  
    /*@Test
    public void test_sql_inj() throws Exception {
        automatedScanningSteps.createNewScanSession();
        automatedScanningSteps.navigateApp("navigate");
        automatedScanningSteps.enablePolicy(Constants.SQLINJPOLICY);
        automatedScanningSteps.runScanner();
        automatedScanningSteps.removeFalsePositives(falsePositives);
    }*/
    public void enablePolicy(String policyName) throws Exception {
       String str=StringUtils.lowcaseLetter(policyName);
       if(str.equals("directory-browsing"))
       {
    	   scannerIds = "0";
       }
       else if(str.equals("cross-site-scripting"))
       {
    	   scannerIds = "40012,40014,40016,40017";
       }
       else if(str.equals("sql-injection"))
       {
    	   scannerIds = "40018";
       }
       else if(str.equals("path-traversal"))
       {
    	   scannerIds = "6";
       }
       else if(str.equals("remote-file-inclusion"))
       {
    	   scannerIds = "7";
       }
       else if(str.equals("server-side-include"))
       {
    	   scannerIds = "40009";
       }
       else if(str.equals("script-active-scan-rules"))
       {
    	   scannerIds = "50000";
       }
       else if(str.equals("server-side-code-injection"))
       {
    	   scannerIds = "90019";
       }
       else if(str.equals("external-redirect"))
       {
    	   scannerIds = "30000";
       }
       else if(str.equals("crlf-injection"))
       {
    	   scannerIds = "40003";
       }

        if (scannerIds == null) throw new Exception("No matching policy found for: " + policyName);
        scanner.setEnableScanners(scannerIds, true);
    }
    @AfterClass
    public static void tearDown() throws Exception {
        driver.close();
        driver.quit();
    }

    @Before
    public void setup() throws ProxyException {
    	scanner.clear();
        driver.manage().deleteAllCookies();
    }

    //@Test
    public void testGetHistory() throws ProxyException {
        driver.get(BASEURL);
        List<HarEntry> history = scanner.getHistory();
        assertTrue(history.size() > 1); //should redirect to login
        //Assert.assertEquals(history.get(0).getResponse().getStatus(), 302);
    }
    
    
    public void testMakeRequestForViewAllProducts() throws IOException {
        // driver.get(BASEURL + "task/search?q=test&search=Search");
     	 driver.get(BASEURL + "product");
         HarRequest origRequest = scanner.getHistory().get(0).getRequest();
         HarResponse origResponse = scanner.getHistory().get(0).getResponse();
         List<HarEntry> responses = scanner.makeRequest(origRequest, true);
         HarResponse manualResponse = responses.get(0).getResponse();

         Assert.assertEquals(origResponse.getBodySize(), manualResponse.getBodySize());
         //System.out.println(manualResponse.getContent().getText());
         Assert.assertEquals(origResponse.getContent().getText(), manualResponse.getContent().getText());
         
     }
   // @Test
    public void testMakeRequest() throws IOException {
       // driver.get(BASEURL + "task/search?q=test&search=Search");
    	driver.get(BASEURL + "index.php?s=Excel");
    	/*System.out.println("Making request on: "+BASEURL + "index.php?s=Excel");
        HarRequest origRequest = scanner.getHistory().get(0).getRequest();
        HarResponse origResponse = scanner.getHistory().get(0).getResponse();
 
        List<HarEntry> responses = scanner.makeRequest(origRequest, true);
        HarResponse manualResponse = responses.get(0).getResponse();

        Assert.assertEquals(origResponse.getBodySize(), manualResponse.getBodySize());
        //System.out.println(manualResponse.getContent().getText());
        Assert.assertEquals(origResponse.getContent().getText(), manualResponse.getContent().getText());
        */
    }
   // @Test
    
    public void testScanUrl() throws IOException, InterruptedException {
    	driver.get(BASEURL);
    	
    	scanner.setEnablePassiveScan(true);
    	
    	scanner.scan(BASEURL);
    	
    	int complete = 0;
        while (complete < 100) {
            complete = scanner.getScanStatus();
            System.out.println("Scan is " + complete + "% complete.");
            Thread.sleep(1000);
        }
    	
    }
    //@Test
    public void testCookiesWithMakeRequest() throws IOException {
        System.out.println("Opening login page");
        openLoginPage();
        System.out.println("Logging on");

        login("bob", "password");        //sets a session ID cookie

        String sessionID = driver.manage().getCookieNamed("JSESSIONID").getValue();
        assert sessionID.length() > 4;
        System.out.println("getting history");
        List<HarEntry> history = scanner.getHistory();
        System.out.println("clearing history");
        scanner.clear();
        System.out.println("cleared");
        HarRequest copy = history.get(history.size() - 1).getRequest(); //The last request will contain a session ID
        copy = HarUtils.changeCookieValue(copy, "JSESSIONID", "nothing");

        List<HarEntry> responses = scanner.makeRequest(copy, true);
        //The changed session ID
        extracted(responses);
    }

	private void extracted(List<HarEntry> responses) {
		assertEquals("nothing", responses.get(0).getRequest().getCookies().getCookies().get(0).getValue());
	}


    private Map<String, List<Alert>> getAlertsByHost(List<Alert> alerts) {

        Map<String, List<Alert>> alertsByHost = new HashMap<String, List<Alert>>();
        for (Alert alert : alerts) {
            URL url = null;
            try {
                url = new URL(alert.getUrl());
                String host = url.getHost();
                if (alertsByHost.get(host) == null) {
                    alertsByHost.put(host, new ArrayList<Alert>());
                }
                alertsByHost.get(host).add(alert);
            } catch (MalformedURLException e) {
                System.err.println("Skipping malformed URL: "+alert.getUrl());
                e.printStackTrace();
            }
        }
        return alertsByHost;
    }


    public void openLoginPage() {
        driver.get(BASEURL + "user/login");
    }

    public void login(String user, String pass) {
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(user);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(pass);
        driver.findElement(By.name("_action_login")).click();
    }

}
