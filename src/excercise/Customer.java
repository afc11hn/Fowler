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

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Iterator<Rental> enumRentals = rentals.iterator();
        String result = String.format("excercise.Rental Record for %s\n\tTitle\t\tDays\tAmount\n", this.getName());

        while (enumRentals.hasNext()) {
            double thisAmount = 0;
            Rental each = (Rental) enumRentals.next();
            //determine amounts for each line
            thisAmount = amountFor(each);
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getType() == Movie.Type.NEW_RELEASE) && each.getDaysRented() > 1) {
                frequentRenterPoints++;
            }
            //show figures for this rental
            result += String.format("\t%s\t\t%d\t%s\n", each.getMovie().getTitle(), each.getDaysRented(), thisAmount);
            totalAmount += thisAmount;
        }
        //add footer lines
        result += String.format("Amount owed is %s\n", totalAmount);
        result += String.format("You earned %d frequent renter points", frequentRenterPoints);
        return result;
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
    