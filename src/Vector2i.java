public class Vector2i {

    private int x;
    private int y;

    Vector2i() {
        x = 0;
        y = 0;
    }

    Vector2i(int pX, int pY) {
        x = pX;
        y = pY;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Vector2i add(Vector2i vect) {
        return new Vector2i(this.getX() + vect.getX(), this.getY() + vect.getY());
    }

    public Vector2i sub(Vector2i vect) {
        return new Vector2i(this.getX() - vect.getX(), this.getY() - vect.getY());
    }

    public void negate() {
        this.x = -x;
        this.y = -y;
    }
}
