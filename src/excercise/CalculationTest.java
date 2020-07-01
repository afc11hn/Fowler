package excercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculationTest {
    @Test
    public void testNewReleaseRental() {
        Movie m1 = new Movie("movie1", Movie.Type.NEW_RELEASE);
        Rental r1 = new Rental(m1, 10);
        assertEquals(r1.amountFor(), 30.0);
    }

    @Test
    public void testChildrenRental() {
        Movie m2 = new Movie("movie2", Movie.Type.CHILDRENS);
        Rental r2 = new Rental(m2, 5);
        assertEquals(r2.amountFor(), 4.5);
    }
}
