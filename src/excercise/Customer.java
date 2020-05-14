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

        for (Rental rental : rentals) {
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
        return switch (rental.getMovie().getType()) {
            case REGULAR -> amountFor(rental, 2, 2, 1.5);
            case NEW_RELEASE -> amountFor(rental, 0, 0, 3);
            case CHILDRENS -> amountFor(rental, 1.5, 3, 1.5);
        };
    }

    private static double amountFor(final Rental rental, final double basePrice, final int maximumRentalDays, final double rentalRate) {
        final int daysRented = rental.getDaysRented();

        if (daysRented>maximumRentalDays) {
            final int rentalDuration = daysRented - maximumRentalDays;
            final double rentalPrice = (double) rentalDuration * rentalRate;

            return basePrice + rentalPrice;
        }

        return basePrice;
    }
}
    