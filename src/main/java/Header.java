import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 * Created by danoja on 1/5/17.
 */
public class Header extends PDF {
    //Logo Attributes
    private String logoPath;
    private float[] logoCoordinates;
    private float[] logoSize;

    //Theme Image Attributes
    private String themeImagePath;
    private float[] themeImageCoordinates;
    private float[] themeImageSize;

    //Title Attributes
    private String title;
    private PDFont titleFont;
    private float titleFontSize;
    private float[] titleCoordinates;

    //HeaderInfo Attributes
    private String[] headerInfo;
    private PDFont headerInfoFont;
    private float headerInfoFontSize;
    private float[] headerCoordinates;


    public String getLogoPath() {
        return logoPath;
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

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
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

    public void setThemeImagePath(String themeImagePath) {
        this.themeImagePath = themeImagePath;
    }


    public void setThemeImageCordinates(float[] themeImageCoordinates) { this.themeImageCoordinates = themeImageCoordinates; }

    public void setThemeImageSize(float[] themeImageSize) {
        this.themeImageSize = themeImageSize;
    }

    public String getThemeImagePath() {
        return themeImagePath;
    }

    public float[] getThemeImageCoordinates() {
        return themeImageCoordinates;
    }

    public float[] getThemeImageSize() {
        return themeImageSize;
    }
}
