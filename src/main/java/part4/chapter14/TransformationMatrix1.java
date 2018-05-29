/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */
package part4.chapter14;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class TransformationMatrix1 {

    /** The resulting PDF. */
    public static final String RESULT = "results/part4/chapter14/transformation_matrix1.pdf";
    /** A PDF with the iText logo that will be transformed. */
    public static final String RESOURCE = "resources/pdfs/logo.pdf";
    
    /**
     * Creates a PDF document.
     * @param filename the path to the new PDF document
     * @throws DocumentException 
     * @throws IOException
     */
    public void createPdf(String filename) throws IOException, DocumentException {
    	// step 1
        Rectangle rect = new Rectangle(-595, -842, 595, 842);
        Document document = new Document(rect);
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4
        // draw the coordinate system
        PdfContentByte canvas = writer.getDirectContent();
        canvas.moveTo(-595, 0);
        canvas.lineTo(595, 0);
        canvas.moveTo(0, -842);
        canvas.lineTo(0, 842);
        canvas.stroke();
        // Read the PDF containing the logo
        PdfReader reader = new PdfReader(RESOURCE);
        PdfTemplate template = writer.getImportedPage(reader, 1);
        // add it at different positions using different transformations
        canvas.saveState();
        canvas.addTemplate(template, 0, 0);
        canvas.concatCTM(0.5f, 0, 0, 0.5f, -595, 0);
        canvas.addTemplate(template, 0, 0);
        canvas.concatCTM(1, 0, 0, 1, 595, 595);
        canvas.addTemplate(template, 0, 0);
        canvas.restoreState();

        canvas.saveState();
        canvas.concatCTM(1, 0, 0.4f, 1, -750, -650);
        canvas.addTemplate(template, 0, 0);
        canvas.restoreState();
        
        canvas.saveState();
        canvas.concatCTM(0, -1, -1, 0, 650, 0);
        canvas.addTemplate(template, 0, 0);
        canvas.concatCTM(0.2f, 0, 0, 0.5f, 0, 300);
        canvas.addTemplate(template, 0, 0);
        canvas.restoreState();
        // step 5
        document.close();
    }
    
    /**
     * Main method.
     *
     * @param    args    no arguments needed
     * @throws DocumentException 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, DocumentException {
        new TransformationMatrix1().createPdf(RESULT);
    }
}
