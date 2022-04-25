import junit.framework.*;

public class VideoStoreTest extends TestCase
{
    private Statement statement;
    public VideoStoreTest(String name) {
        super (name);
    }

    protected void setUp ()  {
        statement = new Statement ("Customer");
    }

    public void testSingleNewReleaseStatementTotals () {
        statement.addRental (new Rental (new Movie ("The Cell", Movie.NEW_RELEASE), 3));
        statement.generate();
        assertEquals(9.0, statement.getTotal());
        assertEquals(2, statement.getFrequentRenterPoints());
    }

    public void testDualNewReleaseStatementTotals () {
        statement.addRental (new Rental (new Movie ("The Cell", Movie.NEW_RELEASE), 3));
        statement.addRental (new Rental (new Movie ("The Tigger Movie", Movie.NEW_RELEASE), 3));
        statement.generate();
        assertEquals(18.0, statement.getTotal());
        assertEquals(4, statement.getFrequentRenterPoints());
    }

    public void testSingleChildrensStatementTotals () {
        statement.addRental (new Rental (new Movie ("The Tigger Movie", Movie.CHILDRENS), 3));
        statement.generate();
        assertEquals(1.5, statement.getTotal());
        assertEquals(1, statement.getFrequentRenterPoints());
    }

    public void testMultipleRegularStatementTotals () {
        statement.addRental (new Rental (new Movie ("Plan 9 from Outer Space", Movie.REGULAR), 1));
        statement.addRental (new Rental (new Movie ("8 1/2", Movie.REGULAR), 2));
        statement.addRental (new Rental (new Movie ("Eraserhead", Movie.REGULAR), 3));
        statement.generate();
        assertEquals(7.5, statement.getTotal());
        assertEquals(3, statement.getFrequentRenterPoints());  
    }
    
    public void testMultipleRegularStatementFormat () {
        statement.addRental (new Rental (new Movie ("Plan 9 from Outer Space", Movie.REGULAR), 1));
        statement.addRental (new Rental (new Movie ("8 1/2", Movie.REGULAR), 2));
        statement.addRental (new Rental (new Movie ("Eraserhead", Movie.REGULAR), 3));

        assertEquals ("Rental Record for Customer\n"
        		+ "\tPlan 9 from Outer Space\t2.0\n"
        		+ "\t8 1/2\t2.0\n"
        		+ "\tEraserhead\t3.5\n"
        		+ "You owed 7.5\n"
        		+ "You earned 3 frequent renter points\n", 
        		statement.generate ());
    }
}

