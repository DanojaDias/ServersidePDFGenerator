package org.wso2.analytics.is.common.pdf;

import org.pdfbox.exceptions.COSVisitorException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PDFGenerateLauncher {
    public static void generatePDF(String[] columns, String[][] rows, int[] columnSizes, String title, String[] headerInfo) throws IOException, COSVisitorException {
        File file = new File("repository/deployment/server/jaggeryapps/portal/controllers/apis/pdfSample.pdf");
        boolean fileCreated = false;
        if (!file.exists()) {
            fileCreated = file.createNewFile();
        }
        if(fileCreated) {
            try(OutputStream os = new FileOutputStream(file)) {
                PDFGenerator pdfGenerator = new PDFGenerator();
                pdfGenerator.generatePDF(createPDF(), createTable(columns, rows, columnSizes), createHeader(title, headerInfo), os);
            }
        }
    }

    private static PDFPageInfo createPDF() {
        PDFPageInfo pdf = new PDFPageInfo();
        return pdf;
    }

    private static Table createTable(String[] columns, String[][] rows, int[] columnSizes) {
        // Total size of columns must not be greater than table width.
        List<Column> tableColumn = new ArrayList<Column>();
        for(int i = 0; i < columns.length; i++) {
            tableColumn.add(new Column(columns[i], columnSizes[i]));
        }
        String[][] content = rows;
        Table table = new Table();
        table.setColumns(tableColumn);
        table.setContent(content);
        return table;
    }

    private static Header createHeader(String title, String[] headerInfo) throws IOException {
        Header header = new Header();
        header.setTitle(title);
        header.setHeaderInfo(headerInfo);
        return header;
    }
}

