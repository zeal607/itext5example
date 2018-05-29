/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */
package part4.chapter15;

import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

import java.io.PrintWriter;

public class MyTextRenderListener implements RenderListener {

	/** The print writer to which the information will be written. */
    protected PrintWriter out;

    /**
     * Creates a RenderListener that will look for text.
     */
    public MyTextRenderListener(PrintWriter out) {
        this.out = out;
    }
    
    /**
     * @see RenderListener#beginTextBlock()
     */
    public void beginTextBlock() {
        out.print("<");
    }

    /**
     * @see RenderListener#endTextBlock()
     */
    public void endTextBlock() {
        out.println(">");
    }

    /**
     * @see RenderListener#renderImage(
     *     ImageRenderInfo)
     */
    public void renderImage(ImageRenderInfo renderInfo) {
    }

    /**
     * @see RenderListener#renderText(
     *     TextRenderInfo)
     */
    public void renderText(TextRenderInfo renderInfo) {
        out.print("<");
        out.print(renderInfo.getText());
        out.print(">");
    }
}
