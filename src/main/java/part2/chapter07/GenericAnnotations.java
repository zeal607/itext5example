/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */

package part2.chapter07;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.FileOutputStream;
import java.io.IOException;

public class GenericAnnotations extends PdfPageEventHelper {

    /** The resulting PDF. */
    public static final String RESULT
        = "results/part2/chapter07/generic_annotations.pdf";
    /** Possible icons. */
    public static final String[] ICONS = {
        "Comment", "Key", "Note", "Help", "NewParagraph", "Paragraph", "Insert"
    };

    /**
     * Main method.
     * @param    args    no arguments needed
     * @throws DocumentException 
     * @throws IOException
     */
    public static void main(String[] args)
        throws IOException, DocumentException {
    	// step 1
        Document document = new Document();
        // step 2
        PdfWriter writer
            = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        writer.setPageEvent(new GenericAnnotations());
        // step 3
        document.open();
        // step 4
        Paragraph p = new Paragraph();
        Chunk chunk;
        Chunk tab = new Chunk(new VerticalPositionMark());
        for (int i = 0; i < ICONS.length; i++) {
            chunk = new Chunk(ICONS[i]);
            chunk.setGenericTag(ICONS[i]);
            p.add(chunk);
            p.add(tab);
        }
        document.add(p);
        // step 5
        document.close();
    }

    /**
     * @see PdfPageEventHelper#onGenericTag(
     *      PdfWriter,
     *      Document,
     *      Rectangle, String)
     */
    @Override
    public void onGenericTag(PdfWriter writer,
        Document document, Rectangle rect, String text) {
        PdfAnnotation annotation = new PdfAnnotation(writer,
            new Rectangle(
                rect.getRight() + 10, rect.getBottom(),
                rect.getRight() + 30, rect.getTop()));
        annotation.setTitle("Text annotation");
        annotation.put(PdfName.SUBTYPE, PdfName.TEXT);
        annotation.put(PdfName.OPEN, PdfBoolean.PDFFALSE);
        annotation.put(PdfName.CONTENTS,
            new PdfString(String.format("Icon: %s", text)));
        annotation.put(PdfName.NAME, new PdfName(text));
        writer.addAnnotation(annotation);
    }
    
}
