package org.wso2.analytics.is.common.pdf;

import org.pdfbox.exceptions.COSVisitorException;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.pdmodel.PDPage;
import org.pdfbox.pdmodel.edit.PDPageContentStream;
import org.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PDFGenerator {
    /*
    *This method generates the PDF
    *@param pdf This is the object of PDFPageInfo class
    *@param table this is a object of Table class
    *@param header this is a object of Header class
    *@param outputStream this is the output stream
    */
    public void generatePDF(PDFPageInfo pdf, Table table, Header header, OutputStream outputStream) throws IOException, COSVisitorException {
        if(table.getContent()!=null && table.getContent().length !=0) {
            PDDocument pdfDoc = new PDDocument();
            PDPage pdPage = generatePage(pdf, pdfDoc);
            PDPage page = pdPage;
            PDPageContentStream contentStream = drawHeader(header, pdfDoc, page);
            drawTable(pdfDoc, contentStream, pdf, table, header);
            pdfDoc.save(outputStream);
            pdfDoc.close();
        }
    }

    private PDPageContentStream drawHeader(Header header, PDDocument pdfDoc, PDPage page ) throws IOException {
        PDPageContentStream contentStream = drawLogo(header, pdfDoc, page);
        if(header !=null) {
            if (header.getTitle() != null) {
                drawTitle(header, contentStream);
            }
            if (header.getHeaderInfo() != null) {
                drawHeaderInfo(header, contentStream);
            }
        }
        return contentStream;
    }

    private PDPageContentStream drawLogo(Header header, PDDocument pdfDoc, PDPage page) throws IOException {
        PDPageContentStream contentStream;
        try(InputStream inputStream = this.getClass().getResourceAsStream("/logo.jpg")){
            PDXObjectImage ximage = new PDJpeg(pdfDoc, inputStream);
            contentStream = new PDPageContentStream(pdfDoc, page);
            contentStream.drawImage(ximage, (header.getLogoCoordinates())[0], page.getMediaBox().getHeight() -
                    (header.getLogoCoordinates())[1], (header.getLogoSize())[0], (header.getLogoSize())[1]);
        }catch(NullPointerException ex){
            contentStream = new PDPageContentStream(pdfDoc, page);
        }
        return contentStream;
    }

    private void drawTitle(Header header, PDPageContentStream contentStream) throws IOException {
        contentStream.setFont(header.getTitleFont(), header.getTitleFontSize());
        contentStream.beginText();
        contentStream.moveTextPositionByAmount((header.getTitleCoordinates())[0],(header.getTitleCoordinates())[1]);
        contentStream.drawString(header.getTitle());
        contentStream.endText();
    }

    private void drawHeaderInfo(Header header, PDPageContentStream contentStream) throws IOException {
        contentStream.setFont(header.getHeaderInfoFont(), header.getHeaderInfoFontSize());
        float headerInfoHeight = header.getHeaderInfoFont().getFontBoundingBox().getHeight() / 1000 * header.getHeaderInfoFontSize();
        float nexty=header.getHeaderCoordinates()[1];
        for(int i=0;i < header.getHeaderInfo().length;i++) {
            contentStream.beginText();
            contentStream.moveTextPositionByAmount((header.getHeaderCoordinates())[0], nexty);
            nexty-=2*headerInfoHeight;
            contentStream.drawString(header.getHeaderInfo()[i]);
            contentStream.endText();
        }
    }

    private void drawTable(PDDocument pdfDoc, PDPageContentStream contentStream, PDFPageInfo pdf, Table table, Header header) throws IOException {
        float nextY = drawTableHeader(contentStream, table);
        drawTableBody(contentStream, pdfDoc, pdf, table, nextY, header);
    }

    private float drawTableHeader(PDPageContentStream contentStream ,Table table) throws IOException {
        //draw the content line
        return writeContentLine(contentStream,table.getColumnsNamesAsArray(), table.getMargin(), table.getTableTopY() - table.getRowHeight(), table, false, true);
    }

    private float writeContentLine(PDPageContentStream contentStream,String[] lineContent, float nextTextX, float nextTextY, Table table,
                                   boolean IsEven, boolean IsHeader) throws IOException {
        float yStartPerRow = nextTextY;
        float xStartPerRow = nextTextX;
        float lowestY = nextTextY;
        float yStart = nextTextY;
        //Calculate lowest y to draw the background color;
        for (int i = 0; i < table.getNumberOfColumns(); i++) {
            String text = lineContent[i];
            List<String> lines;
            if(IsHeader){
                lines = getLinesPerRow(text,table.getColumns().get(i).getWidth(), table, true);
            }
            else{
                lines =  getLinesPerRow(text,table.getColumns().get(i).getWidth(), table, false);
            }
            if(yStartPerRow < lowestY){
                lowestY = yStartPerRow;
            }
            yStartPerRow = nextTextY;
            for (int noOfLines = 0; noOfLines<lines.size(); noOfLines++){
                yStartPerRow -= table.getRowHeight();
            }
        }
        //draw background color
        if(IsHeader){
            contentStream.setNonStrokingColor(table.getTableHeaderBackgroundColor()[0], table.getTableHeaderBackgroundColor()[1], table.getTableHeaderBackgroundColor()[2]);
        }
        else{
            if (IsEven) {
                contentStream.setNonStrokingColor(table.getAlternativeRowColor()[0], table.getAlternativeRowColor()[1], table.getAlternativeRowColor()[2]);
            } else {
                contentStream.setNonStrokingColor(table.getTableBodyFillColor()[0], table.getTableBodyFillColor()[1], table.getTableBodyFillColor()[2]);
            }
        }
        //fill the Rectangle
        contentStream.fillRect(table.getMargin(), lowestY + table.getRowHeight() - table.getCellMargin(), table.getTableWidth(),  yStart - lowestY );
        contentStream.setNonStrokingColor(table.getTableFontColor()[0], table.getTableFontColor()[1], table.getTableFontColor()[2]);
        for (int i = 0; i < table.getNumberOfColumns(); i++) {
            String text = lineContent[i];
            List<String> lines;
            if(IsHeader) {
                lines = getLinesPerRow(text,table.getColumns().get(i).getWidth(), table, true);
                contentStream.setFont(table.getTableHeaderFont(), table.getTableHeaderFontSize());
            }
            else{
                lines =  getLinesPerRow(text,table.getColumns().get(i).getWidth(), table, false);
                contentStream.setFont(table.getTextFont(), table.getTextFontSize());
            }
            yStartPerRow = nextTextY;
            for (String line: lines)
            {
                contentStream.beginText();
                contentStream.moveTextPositionByAmount(nextTextX + table.getCellMargin(), yStartPerRow);
                contentStream.drawString(line != null ? line : "");
                contentStream.endText();
                yStartPerRow -= table.getRowHeight();
            }
            nextTextX += table.getColumns().get(i).getWidth();
        }
        return lowestY;
    }

    //If a line overflows in a row, This method returns a list that contains the lines
    private List<String> getLinesPerRow(String text, float width, Table table, boolean IsHeader) throws IOException {
        List<String> lines = new ArrayList<String>();
        int lastSpace = -1;
        //checks if line overflows and break them into pieces of column width
        while (text.length() > 0){
            int spaceIndex = text.indexOf(' ', lastSpace + 1);
            if (spaceIndex < 0) {
                spaceIndex = text.length();
            }
            String subString = text.substring(0, spaceIndex);
            float size;
            if(IsHeader) {
                size = table.getTableHeaderFontSize() * table.getTableHeaderFont().getStringWidth(subString) / 1000;
            }
            else {
                size = table.getTextFontSize() * table.getTextFont().getStringWidth(subString) / 1000;
            }
            if (size > width) {
                if (lastSpace < 0) {
                    lastSpace = spaceIndex;
                }
                subString = text.substring(0, lastSpace);
                float subStringSize;
                if(IsHeader) {
                    subStringSize =  table.getTableHeaderFontSize()*table.getTableHeaderFont().getStringWidth(subString) / 1000;
                }
                else {
                    subStringSize = table.getTextFontSize()*table.getTextFont().getStringWidth(subString) / 1000;
                }
                if(subStringSize > width) {
                    lastSpace = 0;
                    float stringWidth = 0;
                    //Checks width adding length of each character
                    while (stringWidth < width && subString.length() - 1 > lastSpace) {
                        lastSpace++;
                        if(IsHeader) {
                            stringWidth +=  table.getTableHeaderFont().getStringWidth(Character.toString(subString.charAt(lastSpace))) / 1000 * table.getTableHeaderFontSize();
                        }
                        else {
                            stringWidth += table.getTextFont().getStringWidth(Character.toString(subString.charAt(lastSpace))) / 1000 * table.getTextFontSize();
                        }
                    }
                    lastSpace -= 2;
                }
                subString = text.substring(0, lastSpace);
                lines.add(subString);
                text = text.substring(lastSpace).trim();
                lastSpace = -1;
            }
            else if (spaceIndex == text.length()) {
                lines.add(text);
                text = "";
            }
            else {
                lastSpace = spaceIndex;
            }
        }
        return lines;
    }

    private void drawTableBody(PDPageContentStream contentStream, PDDocument pdfDoc, PDFPageInfo pdf, Table table, float nextY, Header header) throws IOException {
        int pageNo = 0;
        for(int i = 0; i < table.getContent().length; i++) {
            if(nextY < table.getMargin()) {
                pageNo++;
                drawFooter(contentStream, header, pageNo);
                contentStream.close();
                PDPage page = generatePage(pdf, pdfDoc);
                nextY =table.getPageSize().getHeight() - table.getMargin() - table.getRowHeight();
                contentStream = new PDPageContentStream(pdfDoc, page);

            }
            if(i%2==0) {
               nextY = writeContentLine(contentStream, table.getContent()[i], table.getMargin(), nextY, table, true, false);
            }
            else{
                nextY = writeContentLine(contentStream, table.getContent()[i], table.getMargin(), nextY, table, false, false);
            }
        }
        pageNo++;
        drawFooter(contentStream, header, pageNo);
        contentStream.close();
    }

    private void drawFooter(PDPageContentStream contentStream, Header header, int pageNo) throws IOException {
        contentStream.beginText();
        if(header != null) {
            contentStream.moveTextPositionByAmount(header.getMargin(), 10);
            if (header.getTitle() != null) {
                contentStream.drawString(header.getTitle() + " - " + pageNo);
            } else {
                contentStream.drawString("" + pageNo);
            }
        }else {
            contentStream.moveTextPositionByAmount(40, 10);
            contentStream.drawString("" + pageNo);
        }
        contentStream.endText();
    }

    private PDPage generatePage(PDFPageInfo pdf,PDDocument pdfDoc) {
        PDPage page = new PDPage();
        page.setMediaBox(pdf.getPageSize());
        pdfDoc.addPage(page);
        return page;
    }
}
