package org.wso2.analytics.is.common.pdf;

import org.pdfbox.pdmodel.PDPage;
import org.pdfbox.pdmodel.common.PDRectangle;
import org.pdfbox.pdmodel.font.PDFont;
import org.pdfbox.pdmodel.font.PDType1Font;


class DefaultConstants {

    //Constants used in Header
    static final float[] DEFAULT_LOGO_COORDINATES = {40, 60};

    static final float[] DEFAULT_LOGO_SIZE = {140, 55};


    //Title Attributes
    static final PDFont DEFAULT_TITLE_FONT = PDType1Font.HELVETICA_BOLD;

    static final  float DEFAULT_TITLE_FONT_SIZE = 15;

    static final float[] DEFAULT_TITLE_COORDINATES = {171, 678};


    //HeaderInfo Attributes
    static final PDFont DEFAULT_HEADER_INFO_FONT = PDType1Font.HELVETICA_BOLD;

    static final float DEFAULT_HEADER_INFO_FONT_SIZE = 12;

    static final float[] DEFAULT_HEADER_COORDINATES = {40, 650};


    //Constants used in table
    static final PDFont DEFAULT_TEXT_FONT = PDType1Font.HELVETICA;

    static final float DEFAULT_FONT_SIZE = 7;

    static final float DEFAULT_CELL_MARGIN = 4;

    static final float DEFAULT_ROW_HEIGHT = 10 + DEFAULT_CELL_MARGIN;

    static final float DEFAULT_TABLE_TOPY = 570f;

    static final PDFont DEFAULT_TABLE_HEADER_FONT = PDType1Font.HELVETICA_BOLD;

    static final float DEFAULT_TABLE_HEADER_FONT_SIZE = 8;

    static final int [] DEFAULT_TABLE_HEADER_BACKGROUND_COLOR = {201, 202, 197};

    static final int[] DEFAULT_TABLE_FONT_COLOR = {0, 0, 0};

    static final int[] DEFAULT_ALTERNATIVE_ROW_COLOR = {240, 236, 224};

    static final int[] DEFAULT_TABLE_BODY_FILL_COLOR = {255,255,255};


    //Constant used in PDFPageInfo
    static final float DEFAULT_MARGIN = 40;

    static final PDRectangle DEFAULT_PAGESIZE = PDPage.PAGE_SIZE_LETTER;

}
