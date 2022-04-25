import java.util.ArrayList;
import java.util.List;


public class Statement {
    private String customerName;
    private List<Rental> rentals = new ArrayList<Rental>();
	private double totalAmount;
	private int frequentRenterPoints;

    public Statement(String customerName) {
        this.customerName = customerName;
    }

    public String generate() {
        initialize();
        String statementText = header();
        for (Rental rental : rentals) {
            double thisAmount = 0;

            // determine amount for each line
            switch (rental.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (rental.getDaysRented() > 2)
                        thisAmount += (rental.getDaysRented() - 2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += rental.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (rental.getDaysRented() > 3)
                        thisAmount += (rental.getDaysRented() - 3) * 1.5;
                    break;
            }

            // add frequent renter points
            frequentRenterPoints ++;
            // add bonus for a two day new release rental
            if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
                    rental.getDaysRented() > 1) frequentRenterPoints ++;

            //show figures for this rental
            statementText += "\t" + rental.getMovie().getTitle()+ "\t" +
                    String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }
        //add footer lines
        statementText += "You owed " + String.valueOf(totalAmount) + "\n";
        statementText += "You earned " + String.valueOf(frequentRenterPoints) +
                " frequent renter points\n";
        return statementText;
    }

	private String header() {
		return String.format("Rental Record for %s \n", customerName);
	}

	private void initialize() {
		totalAmount = 0;
        frequentRenterPoints = 0;
	}

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return customerName;
    }

	public double getTotal() {
		return totalAmount;
	}

	public int getFrequentRenterPoints() {
		return frequentRenterPoints;
	}
}
