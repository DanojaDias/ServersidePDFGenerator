/**
 * Created by danoja on 1/5/17.
 */
public class Column {
    private String name;
    private float width;

    public Column(String name, float width) {
        this.name = name;
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public float getWidth() { return width; }
}
