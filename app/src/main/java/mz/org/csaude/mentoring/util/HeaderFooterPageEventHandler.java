package mz.org.csaude.mentoring.util;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;

public class HeaderFooterPageEventHandler implements IEventHandler {

    protected PdfFormXObject placeholder;
    protected float x = 420; // Adjusted for landscape
    protected float y = 20; // Adjusted for landscape
    protected float side = 20;
    protected float descent = 3;

    public HeaderFooterPageEventHandler(PdfFormXObject placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public void handleEvent(Event event) {
        try {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdfDoc = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            int pageNumber = pdfDoc.getPageNumber(page);
            PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

            pdfCanvas.beginText();
            pdfCanvas.setFontAndSize(PdfFontFactory.createFont(), 12);
            pdfCanvas.moveText(x, y);
            pdfCanvas.showText(String.format("Página %d", pageNumber));
            pdfCanvas.endText();
            pdfCanvas.release();

            PdfCanvas total = new PdfCanvas(placeholder, pdfDoc);
            total.beginText();
            total.setFontAndSize(PdfFontFactory.createFont(), 12);
            total.moveText(side, descent);
            total.showText(String.format("%d", pdfDoc.getNumberOfPages()));
            total.endText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

