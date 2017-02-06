package org.wso2.analytics.is.common.pdf;
import org.pdfbox.pdmodel.font.PDFont;

import java.util.List;


class Table extends PDFPageInfo {


    private PDFont textFont = DefaultConstants.DEFAULT_TEXT_FONT;
    private float fontSize = DefaultConstants.DEFAULT_FONT_SIZE;
    private float cellMargin = DefaultConstants.DEFAULT_CELL_MARGIN;
    private float rowHeight = DefaultConstants.DEFAULT_ROW_HEIGHT;
    private float tableTopY = DefaultConstants.DEFAULT_TABLE_TOP_Y;
    private List<Column> columns;
    private String[][] content;

    private PDFont tableHeaderFont = DefaultConstants.DEFAULT_TABLE_HEADER_FONT;
    private float tableHeaderFontSize = DefaultConstants.DEFAULT_TABLE_HEADER_FONT_SIZE;
    private int [] tableHeaderBackgroundColor = DefaultConstants.DEFAULT_TABLE_HEADER_BACKGROUND_COLOR;
    private int[] tableFontColor = DefaultConstants.DEFAULT_TABLE_FONT_COLOR;
    private int[] alternativeRowColor = DefaultConstants.DEFAULT_ALTERNATIVE_ROW_COLOR;
    private int[] tableBodyFillColor = DefaultConstants.DEFAULT_TABLE_BODY_FILL_COLOR;

    float getTableWidth() {
        float tableWidth = 0f;
        for (Column column : columns) {
            tableWidth += column.getWidth();
        }
        return tableWidth;
    }

    float getTableTopY() {
        return tableTopY;
    }

    String[] getColumnsNamesAsArray() {
        String[] columnNames = new String[getNumberOfColumns()];
        for (int i = 0; i < getNumberOfColumns(); i++) {
            columnNames[i] = columns.get(i).getName();
        }
        return columnNames;
    }

    List<Column> getColumns() {
        return columns;
    }

    Integer getNumberOfColumns() {
        return this.getColumns().size();
    }

    void setCellMargin(float cellMargin) {
        this.cellMargin = cellMargin;
    }

    void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    void setContent(String[][] content) {
        this.content = content;
    }

    void setRowHeight(float rowHeight) {
        this.rowHeight = rowHeight;
    }

    void setTextFont(PDFont textFont) {
        this.textFont = textFont;
    }

    void setFontSize(float fontSize) {
        this.fontSize = fontSize;
    }

    void setTableTopY(float tableTopY) {
        this.tableTopY = tableTopY;
    }

    float getCellMargin() {
        return cellMargin;
    }

    float getRowHeight() {
        return rowHeight;
    }

    PDFont getTextFont() {
        return textFont;
    }

    float getTextFontSize() {
        return fontSize;
    }

    String[][] getContent() { return content; }

    void setTableHeaderFont(PDFont tableHeaderFont) {
        this.tableHeaderFont = tableHeaderFont;
    }

    void setTableHeaderFontSize(float tableHeaderFontSize) {
        this.tableHeaderFontSize = tableHeaderFontSize;
    }

    float getTableHeaderFontSize() {
        return tableHeaderFontSize;
    }

    PDFont getTableHeaderFont() {
        return tableHeaderFont;
    }

    void setTableHeaderBackgroundColor(int[] tableHeaderBackgroundColor) { this.tableHeaderBackgroundColor = tableHeaderBackgroundColor; }

    int[] getTableHeaderBackgroundColor() { return tableHeaderBackgroundColor; }

    void setTableFontColor(int[] tableFontColor) { this.tableFontColor = tableFontColor; }

    int[] getTableFontColor() { return tableFontColor; }

    void setAlternativeRowColor(int[] alternativeRowColor) { this.alternativeRowColor = alternativeRowColor; }

    int[] getAlternativeRowColor() { return alternativeRowColor; }

    void setTableBodyFillColor(int[] tableBodyFillColor) { this.tableBodyFillColor = tableBodyFillColor; }

    int[] getTableBodyFillColor() { return tableBodyFillColor; }
}
