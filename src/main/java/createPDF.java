import com.itextpdf.kernel.pdf.PdfReader;

import java.io.FileOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class createPDF {


    public static void main(String[] args) throws Exception
    {
        String dest = "results/tests/sample2.pdf";
        PdfWriter writer = new PdfWriter(dest);

        // Creating a PdfDocument
        PdfDocument pdfDoc = new PdfDocument(writer);

        // Adding a new page
        pdfDoc.addNewPage();

        //Create Paragraph
        String paragraph = "Hello World";
        Paragraph para = new Paragraph(paragraph);



        // Creating a Document
        Document document = new Document(pdfDoc);

        //Add objects to document
        document.add(para);

        // Closing the document
        document.close();
        System.out.println("PDF Created");

    }
}
