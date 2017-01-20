package org.wso2.analytics.is.common.pdf;
import org.pdfbox.pdmodel.font.PDFont;
import org.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;


public class Header extends PDFPageInfo {
    //Logo Attributes
    private float[] logoCoordinates = {40, 60};;
    private float[] logoSize = {140, 55};

    //Title Attributes
    private String title;
    private PDFont titleFont = PDType1Font.HELVETICA_BOLD;
    private float titleFontSize = 15;
    private float[] titleCoordinates = {171, 678};

    //HeaderInfo Attributes
    private String[] headerInfo;
    private PDFont headerInfoFont = PDType1Font.HELVETICA_BOLD;
    private float headerInfoFontSize = 12;
    private float[] headerCoordinates = {40, 650};

    public Header() throws IOException {
    }

    public float[] getLogoCoordinates() {
        return logoCoordinates;
    }

    public float[] getLogoSize() {
        return logoSize;
    }

    public PDFont getTitleFont() {
        return titleFont;
    }

    public float getTitleFontSize() {
        return titleFontSize;
    }

    public String getTitle() {
        return title;
    }

    public float[] getTitleCoordinates() {
        return titleCoordinates;
    }

    private float getTitleHeight() throws IOException {
        return titleFont.getFontBoundingBox().getHeight() / 1000 * titleFontSize;
    }

    private float getTitleWidth() throws IOException {
        return titleFont.getStringWidth(title) / 1000 * titleFontSize;
    }

    private float getHeaderInfoHeight() throws IOException {
        return headerInfoFont.getFontBoundingBox().getHeight() / 1000 * headerInfoFontSize;
    }

    public void setLogoCoordinates(float[] logoCoordinates) {
        this.logoCoordinates = logoCoordinates;
    }

    public void setLogoSize(float[] logoSize) {
        this.logoSize = logoSize;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitleFont(PDFont titleFont) {
        this.titleFont = titleFont;
    }

    public void setTitleFontSize(float titleFontSize) {
        this.titleFontSize = titleFontSize;
    }

    public void setTitleCoordinates(float[] titleCoordinates) {
        this.titleCoordinates = titleCoordinates;
    }

    public PDFont getHeaderInfoFont() {
        return headerInfoFont;
    }

    public float getHeaderInfoFontSize() {
        return headerInfoFontSize;
    }

    public String[] getHeaderInfo() {
        return headerInfo;
    }

    public float[] getHeaderCoordinates() {
        return headerCoordinates;
    }

    public void setHeaderInfo(String[] headerInfo) {
        this.headerInfo = headerInfo;
    }

    public void setHeaderInfoFont(PDFont headerInfoFont) {
        this.headerInfoFont = headerInfoFont;
    }

    public void setHeaderInfoFontSize(float headerInfoFontSize) {
        this.headerInfoFontSize = headerInfoFontSize;
    }

    public void setHeaderCoordinates(float[] headerCoordinates) {
        this.headerCoordinates = headerCoordinates;
    }
}
