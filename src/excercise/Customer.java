package excercise;

import java.lang.*;
import java.util.*;

class Customer {
    private final String name;
    private final List<Rental> rentals = new ArrayList<>();

    public Customer(final String customerName) {
        name = customerName;
    }

    public void addRental(final Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public int frequentRenterPoints() {
        return rentals.stream()
                .mapToInt(Rental::frequentRenterPoints)
                .sum();
    }

    public String statement() {
        double totalAmount = 0;
        String format = String.format("excercise.Rental Record for %s\n\tTitle\t\tDays\tAmount\n", this.getName());
        StringBuilder result = new StringBuilder(format);

        for (Rental rental : rentals) {
            double thisAmount = rental.amountFor();
            format = String.format("\t%s\t\t%d\t%s\n", rental.getMovie().getTitle(), rental.getDaysRented(), thisAmount);
            result.append(format);
            totalAmount += thisAmount;
        }
        //add footer lines
        result.append(String.format("Amount owed is %s\n", totalAmount));
        result.append(String.format("You earned %d frequent renter points", frequentRenterPoints()));
        return result.toString();
    }

}
    