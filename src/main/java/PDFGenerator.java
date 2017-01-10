import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danoja on 1/5/17.
 */
public class PDFGenerator {

    private PDDocument doc = null;
    private PDF pdf;
    private PDPage page;
    private PDPageContentStream contentStream;
    public void generatePDF(PDF pdf, Table table, Header header) throws IOException, COSVisitorException {
        this.pdf=pdf;
        try {
            doc = new PDDocument();
            page = generatePage();
            drawHeader(header);
            drawTable(table);
            doc.save("sample.pdf");
        } finally {
            if (doc != null) {
                doc.close();
            }
        }
    }

    private void drawHeader(Header header) throws IOException {
        drawLogo(header);
        drawThemeImage(header);
        drawTitle(header);
        drawHeaderInfo(header);
    }

    private void drawLogo(Header header) throws IOException {
        BufferedImage awtImage = ImageIO.read(new File(header.getLogoPath()));
        PDPixelMap ximage = new PDPixelMap(doc, awtImage);
        contentStream = new PDPageContentStream(doc, page);
        contentStream.drawXObject(ximage, (header.getLogoCoordinates())[0],  page.getMediaBox().getHeight() - (header.getLogoCoordinates())[1], (header.getLogoSize())[0], (header.getLogoSize())[1]);
    }


    private void drawThemeImage(Header header) throws IOException {
        BufferedImage awtImage = ImageIO.read(new File(header.getThemeImagePath()));
        PDPixelMap ximage = new PDPixelMap(doc, awtImage);
        contentStream.drawXObject(ximage, (header.getThemeImageCoordinates())[0],  page.getMediaBox().getHeight() - (header.getThemeImageCoordinates())[1], (header.getThemeImageSize())[0], (header.getThemeImageSize())[1]);
    }

    private void drawTitle(Header header) throws IOException {
        contentStream.setFont(header.getTitleFont(), header.getTitleFontSize());
        contentStream.beginText();
        contentStream.moveTextPositionByAmount((header.getTitleCoordinates())[0],(header.getTitleCoordinates())[1]);
        contentStream.drawString(header.getTitle());
        contentStream.endText();
    }

    private void drawHeaderInfo(Header header) throws IOException {
        contentStream.setFont(header.getHeaderInfoFont(), header.getHeaderInfoFontSize());
        float headerInfoHeight = header.getHeaderInfoFont().getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * header.getHeaderInfoFontSize();
        float nexty=header.getHeaderCoordinates()[1];
        for(int i=0;i < header.getHeaderInfo().length;i++) {
            contentStream.beginText();
            contentStream.moveTextPositionByAmount((header.getHeaderCoordinates())[0], nexty);
            nexty-=2*headerInfoHeight;
            contentStream.drawString(header.getHeaderInfo()[i]);
            contentStream.endText();
        }
    }

    private void drawTable(Table table) throws IOException {
        float nextY = drawTableHeader(table);
        drawTableBody(table, nextY);
    }

    private float drawTableHeader(Table table) throws IOException {
        //draw the content line
        return writeContentLine(table.getColumnsNamesAsArray(), table.getMargin(), table.getTableTopY() - table.getRowHeight(), table, false, true);
    }

    private float writeContentLine(String[] lineContent, float nextTextX, float nextTextY, Table table, boolean IsEven, boolean IsHeader) throws IOException {
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
        //draw vertical lines per Row
        contentStream.setStrokingColor(table.getTableLineColor()[0], table.getTableLineColor()[1], table.getTableLineColor()[2]);
        contentStream.setLineWidth(table.getTableLineWidth());
        for (int i = 0; i < table.getNumberOfColumns(); i++) {
            contentStream.drawLine(xStartPerRow, nextTextY + table.getRowHeight(), xStartPerRow, lowestY - table.getCellMargin() + table.getRowHeight());
            xStartPerRow += table.getColumns().get(i).getWidth();
        }
        //draw last vertical line per Row
        contentStream.drawLine(xStartPerRow, nextTextY + table.getRowHeight(), xStartPerRow, lowestY - table.getCellMargin()+ table.getRowHeight());
        //draw the bottom line of the row
        contentStream.drawLine(table.getMargin(), lowestY - table.getCellMargin() + table.getRowHeight(), table.getMargin() + table.getTableWidth(), lowestY - table.getCellMargin() + table.getRowHeight());
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

    private void drawTableBody(Table table, float nextY) throws IOException {
        for(int i = 0; i < table.getContent().length; i++) {
            if(nextY < table.getMargin()) {
                contentStream.close();
                page = generatePage();
                nextY =table.getPageSize().getHeight() - table.getMargin() - table.getRowHeight();
                contentStream = new PDPageContentStream(doc, page);
            }
            if(i%2==0) {
               nextY = writeContentLine(table.getContent()[i], table.getMargin(), nextY, table, true, false);
            }
            else{
                nextY = writeContentLine(table.getContent()[i], table.getMargin(), nextY, table, false, false);
            }
        }
        contentStream.close();
    }

    private PDPage generatePage() {
        page = new PDPage();
        page.setMediaBox(pdf.getPageSize());
        doc.addPage(page);
        return page;
    }
}
