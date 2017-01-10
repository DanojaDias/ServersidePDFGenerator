package org.wso2.analytics.is.common.pdf;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDFSample {

    //PDF configuration
    private static final PDRectangle PAGE_SIZE = PDPage.PAGE_SIZE_A3;
    private static final float MARGIN = 40;

    //PDF Logo Image Configuration
    private static final String LOGO_PATH = "/home/danoja/Pictures/is-analytics-header-logo.png";
    private static final float [] LOGO_COORDINATES ={MARGIN, 60};
    private static final float [] LOGO_SIZE ={140, 55};

    //PDF Theme image Configuration
    private static final String THEME_IMAGE_PATH = "/home/danoja/Pictures/Orange.png";
    private static final float [] THEME_IMAGE_COORDINATES ={PAGE_SIZE.getWidth()- 76, 91};
    private static final float [] THEME_IMAGE_SIZE ={75, 90};

    //PDF Title Configurations
    private static final String TITLE = "ABNORMAL LONG SESSION ALERTS";
    private static final float TITLE_FONT_SIZE = 15;
    private static final PDFont TITLE_FONT = PDType1Font.HELVETICA_BOLD;
    private static float titleHeight = TITLE_FONT.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * TITLE_FONT_SIZE;
    private static final float [] TITLE_COORDINATES ={(PAGE_SIZE.getWidth() - getTitleWidth()) / 2, PAGE_SIZE.getHeight() - LOGO_COORDINATES[1] - 3*titleHeight};

    //PDF HeaderInfo Configurations
    private static final String[] HEADER_INFO = {"Starting Date : 11/24/2016, 12:06:05 PM","Ending Date  : 12/23/2016, 12:06:05 PM","Total Records : 924"};
    private static final float HEADER_FONT_SIZE = 12;
    private static final PDFont HEADER_FONT = PDType1Font.HELVETICA_BOLD;
    private static float headerInfoHeight = HEADER_FONT.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * HEADER_FONT_SIZE;
    private static final float [] HEADER_COORDINATES ={MARGIN, PAGE_SIZE.getHeight() - LOGO_COORDINATES[1] - 3*titleHeight - 2*headerInfoHeight};


    // Table configuration
    private static final float CELL_MARGIN = 4;
    private static final float ROW_HEIGHT = 15 + CELL_MARGIN;
    private static final float TABLE_TOPY= PAGE_SIZE.getHeight() - LOGO_COORDINATES[1] - 3*titleHeight - 2*headerInfoHeight*(HEADER_INFO.length+1);
    private static final PDFont TABLE_FONT = PDType1Font.HELVETICA;
    private static final float TABLE_FONT_SIZE = 10;
    private static final PDFont TABLE_HEADER_FONT = PDType1Font.HELVETICA_BOLD;
    private static final float TABLE_HEADER_FONT_SIZE = 11;
    private static final int[] TABLE_HEADER_BACKGROUND_COLOR = {201, 202, 197};
    private static final int[] TABLE_FONT_COLOR ={0, 0, 0} ;
    private static final int[] TABLE_LINE_COLOR = {255,255,255};
    private static final float TABLE_LINE_WIDTH = 1.5f;
    private static final int[] ALTERNATIVE_ROW_COLOR = {240, 236, 224};
    private static final int[] TABLE_BODY_FILL_COLOR = {255,255,255};


    public static void main(String[] args) throws IOException, COSVisitorException {
        new PDFGenerator().generatePDF(createPDF(), createContent(), createHeader());
    }

    private static PDF createPDF() {
        PDF pdf = new PDF();
        pdf.setMargin(MARGIN);
        pdf.setPageSize(PAGE_SIZE);
        return pdf;
    }

    private static float getTitleWidth() {
        float titleWidth = 0;
        try {
            titleWidth = TITLE_FONT.getStringWidth(TITLE) / 1000 * TITLE_FONT_SIZE;
        }
        catch(IOException ex){
            //Error on getting Tittle Width
        }
        return titleWidth;
    }
    private static Table createContent() {
        // Total size of columns must not be greater than table width.
        List<Column> columns = new ArrayList<Column>();
        columns.add(new Column("FirstName", 90 + TABLE_LINE_WIDTH));
        columns.add(new Column("LastName", 90  + TABLE_LINE_WIDTH));
        columns.add(new Column("Email", 100  + TABLE_LINE_WIDTH));
        columns.add(new Column("ZipCode", 90  + TABLE_LINE_WIDTH));
        columns.add(new Column("MailOptIn123456789 World12345", 65  + TABLE_LINE_WIDTH));
        columns.add(new Column("Code", 60  + TABLE_LINE_WIDTH));
        columns.add(new Column("Branch", 60  + TABLE_LINE_WIDTH));
        columns.add(new Column("Product", 90  + TABLE_LINE_WIDTH));
        columns.add(new Column("Date 12345 123456", 100 + TABLE_LINE_WIDTH));


        String[][] content = {
                { "1", "LastName 123456789", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am" },
                { "2", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD12345", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "3", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am" },
                { "4", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName5", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am" },
                { "FirstName6", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName7", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName8", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName9", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName10", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName11", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName12", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName13", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName14", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName15", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName16", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName17", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName18", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName19", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName20", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName21", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName22", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName23", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName24", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName25", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName26", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName27", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName28", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName29", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName30", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName31", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName32", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName33", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName34", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName35", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName36", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName37", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName38", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName39", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName40", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName41", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName42", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName43", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName44", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName45", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName46", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName47", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName48", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName49", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName50", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName51", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName52", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName53", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName54", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName55", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName56", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName57", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName58", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName59", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName60", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName61", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName62", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName63", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName64", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName65", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName66", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName67", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName68", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName69", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName70", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName71", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName72", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName73", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName74", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName75", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName76", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName78", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName79", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName80", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName81", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName82", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName83", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName84", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName85", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName86", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName87", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName88", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName89", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName90", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName91", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName92", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName93", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName94", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName95", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName96", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName97", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName98", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName99", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName100", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName101", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName102", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"},
                { "FirstName103", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am"}
        };


        Table table = new Table();
        table.setCellMargin(CELL_MARGIN);
        table.setColumns(columns);
        table.setContent(content);
        table.setRowHeight(ROW_HEIGHT);
        table.setMargin(MARGIN);
        table.setPageSize(PAGE_SIZE);
        table.setTextFont(TABLE_FONT);
        table.setFontSize(TABLE_FONT_SIZE);
        table.setTableTopY(TABLE_TOPY);
        table.setTableHeaderFont(TABLE_HEADER_FONT);
        table.setTableHeaderFontSize(TABLE_HEADER_FONT_SIZE);
        table.setTableFontColor(TABLE_FONT_COLOR);
        table.setTableHeaderBackgroundColor(TABLE_HEADER_BACKGROUND_COLOR);
        table.setTableLineColor(TABLE_LINE_COLOR);
        table.setTableLineWidth(TABLE_LINE_WIDTH);
        table.setAlternativeRowColor(ALTERNATIVE_ROW_COLOR);
        table.setTableBodyFillColor(TABLE_BODY_FILL_COLOR);
        return table;
    }

    private static Header createHeader() {
        Header header = new Header();
        header.setLogoPath(LOGO_PATH);
        header.setLogoCoordinates(LOGO_COORDINATES);
        header.setLogoSize(LOGO_SIZE);
        header.setThemeImagePath(THEME_IMAGE_PATH);
        header.setThemeImageCordinates(THEME_IMAGE_COORDINATES);
        header.setThemeImageSize(THEME_IMAGE_SIZE);
        header.setTitle(TITLE);
        header.setTitleFont(TITLE_FONT);
        header.setTitleFontSize(TITLE_FONT_SIZE);
        header.setTitleCoordinates(TITLE_COORDINATES);
        header.setHeaderInfo(HEADER_INFO);
        header.setHeaderInfoFont(HEADER_FONT);
        header.setHeaderInfoFontSize(HEADER_FONT_SIZE);
        header.setHeaderCoordinates(HEADER_COORDINATES);
        return header;
    }
}
