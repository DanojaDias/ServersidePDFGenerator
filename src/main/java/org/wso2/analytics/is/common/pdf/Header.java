package org.wso2.analytics.is.common.pdf;
import org.pdfbox.pdmodel.font.PDFont;

class Header extends PDFPageInfo {
    //Logo Attributes
    private float[] logoCoordinates = DefaultConstants.DEFAULT_LOGO_COORDINATES;
    private float[] logoSize = DefaultConstants.DEFAULT_LOGO_SIZE;

    //Title Attributes
    private String title;
    private PDFont titleFont = DefaultConstants.DEFAULT_TITLE_FONT;
    private float titleFontSize = DefaultConstants.DEFAULT_TITLE_FONT_SIZE;
    private float[] titleCoordinates = {DefaultConstants.DEFAULT_TITLE_COORDINATES[0], DefaultConstants.DEFAULT_TITLE_COORDINATES[1]};

    //HeaderInfo Attributes
    private String[] headerInfo;
    private PDFont headerInfoFont = DefaultConstants.DEFAULT_HEADER_INFO_FONT;
    private float headerInfoFontSize = DefaultConstants.DEFAULT_HEADER_INFO_FONT_SIZE;
    private float[] headerCoordinates = DefaultConstants.DEFAULT_HEADER_COORDINATES;

    float[] getLogoCoordinates() {
        return logoCoordinates;
    }

    float[] getLogoSize() {
        return logoSize;
    }

    PDFont getTitleFont() {
        return titleFont;
    }

    float getTitleFontSize() {
        return titleFontSize;
    }

    String getTitle() {
        return title;
    }

    float[] getTitleCoordinates() {
        return titleCoordinates;
    }

    void setLogoCoordinates(float[] logoCoordinates) {
        this.logoCoordinates = logoCoordinates;
    }

    void setLogoSize(float[] logoSize) {
        this.logoSize = logoSize;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void setTitleFont(PDFont titleFont) {
        this.titleFont = titleFont;
    }

    void setTitleFontSize(float titleFontSize) {
        this.titleFontSize = titleFontSize;
    }

    void setTitleCoordinates(float[] titleCoordinates) {
        this.titleCoordinates = titleCoordinates;
    }

    PDFont getHeaderInfoFont() {
        return headerInfoFont;
    }

    float getHeaderInfoFontSize() {
        return headerInfoFontSize;
    }

    String[] getHeaderInfo() {
        return headerInfo;
    }

    float[] getHeaderCoordinates() {
        return headerCoordinates;
    }

    void setHeaderInfo(String[] headerInfo) {
        this.headerInfo = headerInfo;
    }

    void setHeaderInfoFont(PDFont headerInfoFont) {
        this.headerInfoFont = headerInfoFont;
    }

    void setHeaderInfoFontSize(float headerInfoFontSize) {
        this.headerInfoFontSize = headerInfoFontSize;
    }

    void setHeaderCoordinates(float[] headerCoordinates) {
        this.headerCoordinates = headerCoordinates;
    }
}
