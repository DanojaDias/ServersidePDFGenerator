package org.wso2.analytics.is.common.pdf.internal;

import org.apache.felix.scr.annotations.Component;
import org.osgi.service.component.ComponentContext;

@Component(immediate = true, metatype = true, label = "Service Implementation", description = "The implementation for the Service")
public class PdfTableGeneratorComponent{

    protected void activate(ComponentContext context) {
        System.out.println("hello");
    }

    protected void deactivate(ComponentContext context) {
        System.out.println("bye");
    }
}