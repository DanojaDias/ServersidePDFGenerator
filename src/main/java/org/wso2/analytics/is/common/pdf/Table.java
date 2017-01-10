package org.wso2.analytics.is.common.pdf;
import org.apache.pdfbox.pdmodel.font.PDFont;

import java.util.List;

/**
 * Created by danoja on 1/5/17.
 */
public class Table extends PDF {

    private float rowHeight;
    private PDFont textFont;
    private float fontSize;
    private float cellMargin;
    private float tableTopY;
    private List<Column> columns;
    private String[][] content;
    private PDFont tableHeaderFont;
    private float tableHeaderFontSize;
    private int [] tableHeaderBackgroundColor;
    private int[] tableFontColor;
    private int[] tableLineColor;
    private float tableLineWidth;
    private int[] alternativeRowColor;
    private int[] tableBodyFillColor;

    public float getTableWidth() {
        float tableWidth = 0f;

        for (Column column : columns) {
            tableWidth += column.getWidth();
        }
        return tableWidth;
    }


    public float getTableTopY() {
        return tableTopY;
    }

    public String[] getColumnsNamesAsArray() {
        String[] columnNames = new String[getNumberOfColumns()];
        for (int i = 0; i < getNumberOfColumns(); i++) {
            columnNames[i] = columns.get(i).getName();
        }
        return columnNames;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public Integer getNumberOfColumns() {
        return this.getColumns().size();
    }

    public void setCellMargin(float cellMargin) {
        this.cellMargin = cellMargin;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public void setContent(String[][] content) {
        this.content = content;
    }

    public void setRowHeight(float rowHeight) {
        this.rowHeight = rowHeight;
    }

    public void setTextFont(PDFont textFont) {
        this.textFont = textFont;
    }

    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
    }

    public void setTableTopY(float tableTopY) {
        this.tableTopY = tableTopY;
    }

    public float getCellMargin() {
        return cellMargin;
    }

    public float getRowHeight() {
        return rowHeight;
    }

    public PDFont getTextFont() {
        return textFont;
    }

    public float getTextFontSize() {
        return fontSize;
    }

    public String[][] getContent() { return content; }

    public void setTableHeaderFont(PDFont tableHeaderFont) {
        this.tableHeaderFont = tableHeaderFont;
    }

    public void setTableHeaderFontSize(float tableHeaderFontSize) {
        this.tableHeaderFontSize = tableHeaderFontSize;
    }

    public float getTableHeaderFontSize() {
        return tableHeaderFontSize;
    }

    public PDFont getTableHeaderFont() {
        return tableHeaderFont;
    }

    public void setTableHeaderBackgroundColor(int[] tableHeaderBackgroundColor) { this.tableHeaderBackgroundColor = tableHeaderBackgroundColor; }

    public int[] getTableHeaderBackgroundColor() { return tableHeaderBackgroundColor; }

    public void setTableFontColor(int[] tableFontColor) { this.tableFontColor = tableFontColor; }

    public int[] getTableFontColor() { return tableFontColor; }

    public void setTableLineColor(int[] tableLineColor) { this.tableLineColor = tableLineColor; }

    public void setTableLineWidth(float tableLineWidth) { this.tableLineWidth = tableLineWidth; }

    public int[] getTableLineColor() { return tableLineColor; }

    public float getTableLineWidth() { return tableLineWidth; }

    public void setAlternativeRowColor(int[] alternativeRowColor) { this.alternativeRowColor = alternativeRowColor; }

    public int[] getAlternativeRowColor() { return alternativeRowColor; }

    public void setTableBodyFillColor(int[] tableBodyFillColor) { this.tableBodyFillColor = tableBodyFillColor; }

    public int[] getTableBodyFillColor() { return tableBodyFillColor; }
}
