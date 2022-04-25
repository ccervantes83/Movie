package VideoStore;
public class NewReleaseMovie extends Movie {
	public NewReleaseMovie(String title) {
	super(title);
	}

	double determineAmount(int daysRented) {
		return daysRented * 3;		
	}	
	int determineFrequentRenterPoints(int daysRented) {
		return (daysRented > 1) ? 2 : 1; 
	}
}
