package org.wso2.analytics.is.common.pdf;

/**
 * Created by danoja on 2/6/17.
 */
public class Footer {
    private String FooterContent ;
    private float[] FooterCoordinates = DefaultConstants.DEFAULT_FOOTER_COORDINATES;

    String getFooterContent() {
        return FooterContent;
    }

    float[] getFooterCoordinates() {
        return FooterCoordinates;
    }

    void setFooterContent(String footerContent) {
        FooterContent = footerContent;
    }

    void setFooterCoordinates(float[] footerCoordinates) {
        FooterCoordinates = footerCoordinates;
    }
}

