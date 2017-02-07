package org.wso2.analytics.is.common.pdf;

import org.pdfbox.exceptions.COSVisitorException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PDFLauncher {
    public static void generatePDF(String[] columns, String[][] rows, int[] columnSizes, String title, String[] headerInfo) throws IOException, COSVisitorException {

        File file = new File("repository/deployment/server/jaggeryapps/portal/controllers/apis/pdfSample.pdf");
        boolean fileCreated = false;
        if (!file.exists()) {
            fileCreated = file.createNewFile();
        }
        if(fileCreated) {
            try(OutputStream os = new FileOutputStream(file)) {
                PDFGenerator pdfGenerator = new PDFGenerator();
                pdfGenerator.generatePDF(createPDF(), createTable(columns, rows, columnSizes), createHeader(title, headerInfo), createFooter(title), os);
            }
        }
    }

    private static PDFPageInfo createPDF() {
        return new PDFPageInfo();
    }

    private static Table createTable(String[] columns, String[][] rows, int[] columnSizes) {

        List<Column> tableColumn = new ArrayList<>();
        for(int i = 0; i < columns.length; i++) {
            tableColumn.add(new Column(columns[i], columnSizes[i]));
        }
        Table table = new Table();
        table.setColumns(tableColumn);
        table.setContent(rows);
        return table;
    }

    private static Header createHeader(String title, String[] headerInfo) throws IOException {

        Header header = new Header();
        header.setTitle(title);
        header.setHeaderInfo(headerInfo);
        float titleCoordinateX = (DefaultConstants.DEFAULT_PAGE_SIZE.getWidth() -
                (DefaultConstants.DEFAULT_TITLE_FONT.getStringWidth(title) / 1000 * DefaultConstants.DEFAULT_TITLE_FONT_SIZE )) / 2;
        float [] titleCoordinate = {titleCoordinateX, DefaultConstants.DEFAULT_TITLE_COORDINATES[1]};
        header.setTitleCoordinates(titleCoordinate);
        return header;
    }

    private static Footer createFooter(String title) throws IOException {

        Footer footer = new Footer();
        footer.setFooterContent(title);
        return footer;
    }
}

