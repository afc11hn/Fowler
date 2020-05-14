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
        // bonus for a two day new release rental
        long bonus = rentals.stream()
                .filter(r -> r.getMovie().getType() == Movie.Type.NEW_RELEASE)
                .filter(r -> r.getDaysRented() > 1)
                .count();
        return rentals.size() + (int) bonus;
    }

    public String statement() {
        double totalAmount = 0;
        String format = String.format("excercise.Rental Record for %s\n\tTitle\t\tDays\tAmount\n", this.getName());
        StringBuilder result = new StringBuilder(format);

        for (Rental rental: rentals) {
            double thisAmount = amountFor(rental);
            format = String.format("\t%s\t\t%d\t%s\n", rental.getMovie().getTitle(), rental.getDaysRented(), thisAmount);
            result.append(format);
            totalAmount += thisAmount;
        }
        //add footer lines
        result.append(String.format("Amount owed is %s\n", totalAmount));
        result.append(String.format("You earned %d frequent renter points", frequentRenterPoints()));
        return result.toString();
    }

    private double amountFor(final Rental rental) {
        double thisAmount = 0;
        switch (rental.getMovie().getType()) {
            case REGULAR:
                thisAmount += 2;
                if (rental.getDaysRented() > 2) {
                    thisAmount += (rental.getDaysRented() - 2) * 1.5;
                }
                break;
            case NEW_RELEASE:
                thisAmount += rental.getDaysRented() * 3;
                break;
            case CHILDRENS:
                thisAmount += 1.5;
                if (rental.getDaysRented() > 3) {
                    thisAmount += (rental.getDaysRented() - 3) * 1.5;
                }
                break;
        }
        return thisAmount;
    }

}
    