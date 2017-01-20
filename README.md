# PDFBox Table Generator

![Image of a sample pdf](https://github.com/DanojaDias/ServersidePDFGenerator/blob/master/Sample.png)

##Usage
First, A java Class is need to be created giving all the configurations.

A default table can be generted using the following code.

```java
package org.wso2.analytics.is.common.pdf;
import org.pdfbox.exceptions.COSVisitorException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PDFSample {
     public static void main(String[] args) throws IOException, COSVisitorException {
         File file = new File("pdfSample.pdf");
         if (!file.exists()) {
             file.createNewFile();
         }
         try(OutputStream os = new FileOutputStream(file)) {
             PDFGenerator pdfGenerator = new PDFGenerator();
             pdfGenerator.generatePDF(createPDF(), createContent(), null, os);
         }
     }
     private static PDFPageInfo createPDF() {
         PDFPageInfo pdf = new PDFPageInfo();
         return pdf;
     }
     private static Table createContent() {
         // Total size of columns must not be greater than table width.
         List<Column> columns = new ArrayList<Column>();
         columns.add(new Column("TimeStamp", 150 ));
         columns.add(new Column("Tenant Domain", 100 ));
         columns.add(new Column("User", 100 ));
         columns.add(new Column("Duration (ms)", 100 ));
         columns.add(new Column("Avg. Duration (ms)", 100 ));

         String[][] content = {
                 {"31/05/2013 07:15 am", "carbon.super", "User1", "319626.00", "26497.00"},
                 {"31/05/2013 07:15 am", "carbon.super", "User1", "319626.00", "26497.00"},
                 {"31/05/2013 07:15 am", "carbon.super", "User1", "319626.00", "26497.00"},
                 {"31/05/2013 07:15 am", "carbon.super", "User1", "319626.00", "26497.00"},
                 {"31/05/2013 07:15 am", "carbon.super", "User1", "319626.00", "26497.00"}
         };

         Table table = new Table();
         table.setColumns(columns);
         table.setContent(content);
         return table;
     }
 }
 ```
 
##Configure the PDF Template

Configurable Components are shown in the following image
 ![Image of the configurable Components of the pdf](https://github.com/DanojaDias/ServersidePDFGenerator/blob/master/ConfigurableComponenets.jpg)

### 1. [Logo Image](#logo-image-configurations)
### 2. [Title](#title-configurations)
### 3. [HeaderInfo](#header-info-configurations)
### 4. [Table](#table-configurations)
### 5. [Table Header](#table-header-configurations)
### 6. [Table Body](#table-body-configurations)
### 7. [PDF Page](#PDF-page-configurations)

###Logo Image Configurations

####setLogoCoordinates

 ```java
 public void setLogoCoordinates(float[] logoCoordinates)
```
Changes the logo coordinates. This takes a float array that has the x, y coodinates of the logo.

####setLogoSize

```java
public void setLogoSize(float[] logoSize)
```
Changes the logo coordinates. This takes a float array that has the x, y coodinates of the logo.


###Title Configurations

####setTitle

```java
public void setTitle(String title)
```
Set the title.

####setTitleFont

```java
public void setTitleFont(PDFont titleFont)
```
Set the title font.

####setTitleFontSize

```java
public void setTitleFontSize(float titleFontSize)
```
Set the title font size.

####setTitleCoordinates

```java
public void setTitleCoordinates(float[] titleCoordinates)
```
Set the title Coordinates. This takes a size 2 array which takes the x, y coodinates

###Header Info Configurations

####setHeaderInfo

```java
public void setHeaderInfo(String[] headerInfo)
```
Set the header information that needs to be at the top of the page. This is a String
array that contains the Strings per each line

####setHeaderInfoFont

```java
public void setHeaderInfoFont(PDFont headerInfoFont)
```
Set the header information font

####setHeaderInfoFontSize

```java
public void setHeaderInfoFontSize(float headerInfoFontSize)
```
Set the header information font size

####setHeaderCoordinates

```java
public void setHeaderCoordinates(float[] headerCoordinates)
```
Set the header coordinates. This is a float array that gives the x, y coodinates of the
beggining of the header info.

###Table Configurations

####setTableTopY

```java
public void setTableTopY(float tableTopY)
```
Changes the table top Coordinate

###Table Header Configurations

####setTableHeaderFont
```java
public void setTableHeaderFont(PDFont tableHeaderFont)
```
Changes the text font of the table header.

####setTableHeaderFontSize
```java
public void setTableHeaderFontSize(float tableHeaderFontSize)
```
Changes the text font size of the table header.

####setTableHeaderBackgroundColor
```java
public void setTableHeaderBackgroundColor(int[] tableHeaderBackgroundColor)
```
Changes the background colour of the table header. This takes a integer array of
size 3 which contains the RGB value of the color

###Table Body Configurations

####setTextFont
```java
public void setTextFont(PDFont textFont)
```
Changes the font of the Table body.

####setTableFontColor
```java
public void setTableFontColor(int[] tableFontColor)
```
Changes the font colour of the table body font. This takes a integer array of
size 3 which contains the RGB value of the color

####setFontSize
```java
public void setFontSize(float fontSize)
```
Changes the font size of the Table body font.

####setTableBodyFillColor
```java
public void setTableBodyFillColor(int[] tableBodyFillColor)
```
Changes the body colour of the table body. This takes a Integer array of
size 3 which contains the RGB value of the color

####setAlternativeRowColor
```java
public void setAlternativeRowColor(int[] alternativeRowColor)
```
Changes the body colour of the table body alternative rows. This takes a Integer array of
size 3 which contains the RGB value of the color

####setRowHeight
```java
public void setRowHeight(float rowHeight)
```
Changes the row Height of the table.

####setCellMargin
```java
public void setCellMargin(float cellMargin)
```
Changes the size of the cell Margin of the table.

###PDF Page Configurations

####setPageSize

```java
public void setPageSize(PDRectangle pageSize)
```
Set the page Size

####setMargin

```java
public void setMargin(float margin)
```
Set the Margin of the page
 
