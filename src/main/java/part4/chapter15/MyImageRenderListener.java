/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */

package part4.chapter15;

import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfImageObject;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyImageRenderListener implements RenderListener {

    /** The new document to which we've added a border rectangle. */
    protected String path = "";

    /**
     * Creates a RenderListener that will look for images.
     */
    public MyImageRenderListener(String path) {
        this.path = path;
    }
    
    /**
     * @see RenderListener#beginTextBlock()
     */
    public void beginTextBlock() {
    }

    /**
     * @see RenderListener#endTextBlock()
     */
    public void endTextBlock() {
    }

    /**
     * @see RenderListener#renderImage(
     *     ImageRenderInfo)
     */
    public void renderImage(ImageRenderInfo renderInfo) {
        try {
            String filename;
            FileOutputStream os;
            PdfImageObject image = renderInfo.getImage();
            PdfName filter = (PdfName)image.get(PdfName.FILTER);
            if (PdfName.DCTDECODE.equals(filter)) {
                filename = String.format(path, renderInfo.getRef().getNumber(), "jpg");
                os = new FileOutputStream(filename);
                os.write(image.getStreamBytes());
                os.flush();
                os.close();
            }
            else if (PdfName.JPXDECODE.equals(filter)) {
                filename = String.format(path, renderInfo.getRef().getNumber(), "jp2");
                os = new FileOutputStream(filename);
                os.write(image.getStreamBytes());
                os.flush();
                os.close();
            }
            else if (PdfName.JBIG2DECODE.equals(filter)) {
            	// ignore: filter not supported.
            }
            else {
                BufferedImage awtimage = renderInfo.getImage().getBufferedImage();
                if (awtimage != null) {
                    filename = String.format(path, renderInfo.getRef().getNumber(), "png");
                    ImageIO.write(awtimage, "png", new FileOutputStream(filename));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see RenderListener#renderText(
     *     TextRenderInfo)
     */
    public void renderText(TextRenderInfo renderInfo) {
    }
}
