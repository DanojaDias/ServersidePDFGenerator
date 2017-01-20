package org.wso2.analytics.is.common.pdf;
import org.pdfbox.pdmodel.PDPage;
import org.pdfbox.pdmodel.common.PDRectangle;

public class PDFPageInfo {

    protected float margin = 40;
    protected PDRectangle pageSize = PDPage.PAGE_SIZE_LETTER;

    public void setMargin(float margin) {
        this.margin = margin;
    }

    public void setPageSize(PDRectangle pageSize) {
        this.pageSize = pageSize;
    }

    public float getMargin() { return margin; }

    public PDRectangle getPageSize() { return pageSize; }
}
