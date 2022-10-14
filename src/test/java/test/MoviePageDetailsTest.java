package test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MoviePageDetailsTest extends BaseTest {

	@DataProvider
	public Object[][] movieDetails() {
		return new Object[][] { { "Brahmastra Part One: Shiva" }, { "K.G.F: Chapter 1" },
				{ "Pushpa: The Rise - Part 1" } };
	}

	/**
	 * A common method to get the details of release date and country origin and
	 * compare them
	 * 
	 * @param movieName
	 */
	@Test(dataProvider = "movieDetails")
	public void validateTheMovieDetailsTest(String movieName) {
		List<String> detailsImdb = imdbhomepage.getMovieDetailsFromImdb(movieName);
		List<String> detailsWikipedia = wikipediahomepage.getMovieDetailsFromWikipedia(movieName);
		
		 System.out.println(detailsImdb);
		 System.out.println(detailsWikipedia);
		
		Assert.assertEquals(detailsImdb, detailsWikipedia);

	}

}
