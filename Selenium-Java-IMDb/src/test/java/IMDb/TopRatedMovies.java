package IMDb;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class TopRatedMovies {

	@Test
	public static  void TopRatedMoviesStartSaving() throws InterruptedException  {

		//System SetProperty On Windows
        //File ChromeDriverFileOnWindows = new File("/Users/reyhaneh/Downloads/chromedriver");
		//System.setProperty("webdriver.chrome.driver", ChromeDriverFileOnWindows.getAbsolutePath());

		//System SetProperty On MAC
		File ChromeDriverFileOnMac = new File("/Users/reyhaneh/Downloads/chromedriver");
		System.setProperty("webdriver.chrome.driver", ChromeDriverFileOnMac.getAbsolutePath());

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		driver.get("https://www.imdb.com");
		driver.manage().window().maximize();

		//open Sign in page
		driver.findElement(By.xpath("//*[@id=\"imdbHeader\"]/div[2]/div[5]/a/div")).click();

		//Sign in with IMDb
		driver.findElement(By.xpath("//*[@id=\"signin-options\"]/div/div[1]/a[1]")).click();
		driver.findElement(By.name("email")).sendKeys("r.janikeh@yahoo.com");
		driver.findElement(By.name("password")).sendKeys("12345678@aA");
		driver.findElement(By.id("signInSubmit")).submit();


		//open menu page
		driver.findElement(By.xpath("//label[@id='imdbHeader-navDrawerOpen--desktop']//*[local-name()='svg']//*[name()='path' and contains(@d,'M4 18h16c.')]")).click();

		//open Top Rated Movies page
		driver.findElement(By.xpath("//*[@id=\"imdbHeader\"]/div[2]/aside/div/div[2]/div/div[1]/span/div/div/ul/a[3]")).click();


		//check actual Title & expected Title and print
		String baseUrl="https://www.imdb.com/chart/top/?ref_=nv_mv_250";
		String expectedTitle="IMDb Top 250 - IMDb";
		driver.get(baseUrl);
		String actualTitle=driver.getTitle();
		if(actualTitle.equals(expectedTitle)){
			System.out.println("Title Matched");
		}
		else{
			System.out.println("Title didn't match");
		}


		//Get Title and print
		System.out.println("Page title is : "+driver.getTitle());
		WebElement title = driver.findElement(By.tagName("title"));
		System.out.println(title.getText());


		// get all the options in the Sort by   and print
		WebElement dropdownElementMulti = driver.findElement(By.xpath("//select[@id='lister-sort-by-options']"));
		Select dropdownMulti = new Select(dropdownElementMulti);
		List<WebElement> allElementsMulti = dropdownMulti.getOptions();
		System.out.println("Values present in the 'Sort by' Dropdown:");
		for (WebElement elementMulti : allElementsMulti) {
			// iterate over each element and print the text
			System.out.println(elementMulti.getText());
		}


		// chose Your Rating on 9 for first film
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/span/div/div/div[3]/table/tbody/tr[1]/td[4]/div/div[2]/div[3]")).click();
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/span/div/div/div[3]/table/tbody/tr[1]/td[4]/div/div[1]/div/ol/li[9]")).click();
		Thread.sleep(4000);

		// chose Your Rating on delete for first film
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/span/div/div/div[3]/table/tbody/tr[1]/td[4]/div/div[2]/div[4]")).click();
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/span/div/div/div[3]/table/tbody/tr[1]/td[4]/div/div[1]/div/span")).click();
		
		Thread.sleep(3000);
		//Click to add to watchlist
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/span/div/div/div[3]/table/tbody/tr[1]/td[5]/div/div")).click();


		//print about the 'You Have Seen' section
		String YouHaveSeen = driver.findElement(By.xpath("//*[@id=\"sidebar\"]/div[3]/span/div[2]/div[2]")).getText();
		System.out.println("The 'You Have Seen' section:"); 
		{	
			System.out.println(YouHaveSeen);
		}

		Thread.sleep(4000);
		//Open watchlist section
		driver.findElement(By.xpath("//*[@id=\"imdbHeader\"]/div[2]/div[4]/a/div")).click();

		//print about the 'watchlist' section
		String watchlist = driver.findElement(By.xpath("//*[@id=\"center-1-react\"]/div/div[2]/div[1]/div[2]/div")).getText();
		System.out.println("The 'watchlist' section:"); 
		{	
			System.out.println(watchlist);
		}
		
		Thread.sleep(3000);
		driver.close();
		driver.quit();
	}
}
