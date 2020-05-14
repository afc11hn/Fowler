package excercise;

class Rental {
    private final Movie movie;
    private final int daysRented;

    public Rental(final Movie movie, final int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    static double amountFor(final Rental rental, final double basePrice, final int maximumRentalDays, final double rentalRate) {
        final int daysRented = rental.getDaysRented();

        if (daysRented>maximumRentalDays) {
            final int rentalDuration = daysRented - maximumRentalDays;
            final double rentalPrice = (double) rentalDuration * rentalRate;

            return basePrice + rentalPrice;
        }

        return basePrice;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }


    double amountFor() {
        return switch (getMovie().getType()) {
            case REGULAR -> amountFor(this, 2, 2, 1.5);
            case NEW_RELEASE -> amountFor(this, 0, 0, 3);
            case CHILDRENS -> amountFor(this, 1.5, 3, 1.5);
        };
    }
}