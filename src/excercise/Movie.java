package excercise;

public class Movie {

    enum Type {
        REGULAR(0),
        NEW_RELEASE(1),
        CHILDRENS(2);


        private final int value;
        Type(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }


    }

    private final String title;
    private Type type;

    public Movie(final String title, final Movie.Type type) {
        this.title = title;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(final Movie.Type type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

}