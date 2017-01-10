import org.apache.pdfbox.pdmodel.common.PDRectangle;

/**
 * Created by danoja on 1/5/17.
 */
public class PDF {

    protected float margin;
    protected PDRectangle pageSize;

    public void setMargin(float margin) {
        this.margin = margin;
    }

    public void setPageSize(PDRectangle pageSize) {
        this.pageSize = pageSize;
    }

    public float getMargin() { return margin; }

    public PDRectangle getPageSize() { return pageSize; }
}
