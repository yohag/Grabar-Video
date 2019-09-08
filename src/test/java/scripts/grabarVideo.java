package scripts;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Tools.tools;

public class grabarVideo {

    private static WebDriver driver;
    //private static evidenciaVideo screenRecorder;
    private static tools herramienta;

    @BeforeClass
    public static void setUp() throws IOException, AWTException {	
    	herramienta = new tools();
    	herramienta.init();
        // create driver
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

        // maximize screen
        driver.manage().window().maximize();
    }

    @Before
    public void beforeTest() throws IOException {
        //screenRecorder.start();
    	herramienta.iniciarGrabacion();
    }

    @Test
    public void testScreenRecorder() throws InterruptedException {
    	herramienta.getDriver().get("https://google.com/");
        //driver.get("https://google.com/");
       Thread.sleep(5000);    
    }

    @After
    public void afterTest() throws IOException {
        //screenRecorder.stop();
    	herramienta.finalizarGrabacion();
        //List<File> createdMovieFiles = screenRecorder.getCreatedMovieFiles();
    	List<File> createdMovieFiles =  herramienta.obtenerGrabaciones();
        for(File movie : createdMovieFiles){
            System.out.println("New movie created: " + movie.getAbsolutePath());
        }
    }

    @AfterClass
    public static void cleanUp(){
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

}