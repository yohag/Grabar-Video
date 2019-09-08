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

import Tools.tools;
import pageFactory.pageIndex;

public class extranjeria {
	private static tools herramienta;
	
	  @BeforeClass
	    public static void setUp() throws IOException, AWTException {	
	    	herramienta = new tools();
	    	herramienta.init();
	    }
	
    @Before
    public void beforeTest() throws IOException {
    	herramienta.iniciarGrabacion();
    }

    @After
    public void afterTest() throws IOException {
    	herramienta.finalizarGrabacion();
    	
    	List<File> createdMovieFiles =  herramienta.obtenerGrabaciones();
        for(File movie : createdMovieFiles){
        	System.out.println("New movie created: " + movie.getAbsolutePath());
        }
    }
    
	 @Test
    public void testCambiarIdioma() throws InterruptedException {
		WebDriver driver = herramienta.getDriver();
		driver.get("https://www.extranjeria.gob.cl/");
		pageIndex index = new pageIndex(driver);
		index.click(index.extranjeria_btnKreyol);
		Thread.sleep(3000);
	 } 

	@AfterClass
	public static void cleanUp(){
		
		WebDriver driver = herramienta.getDriver();
	    if (driver != null) {
	        driver.close();
	        driver.quit();
	    }
	}

}
