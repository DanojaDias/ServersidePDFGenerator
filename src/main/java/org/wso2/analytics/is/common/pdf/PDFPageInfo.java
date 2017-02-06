package org.wso2.analytics.is.common.pdf;
import org.pdfbox.pdmodel.common.PDRectangle;

class PDFPageInfo {

    protected float margin = DefaultConstants.DEFAULT_MARGIN;
    protected PDRectangle pageSize = DefaultConstants.DEFAULT_PAGE_SIZE;

    void setMargin(float margin) {
        this.margin = margin;
    }

    void setPageSize(PDRectangle pageSize) {
        this.pageSize = pageSize;
    }

    float getMargin() { return margin; }

    PDRectangle getPageSize() { return pageSize; }
}
