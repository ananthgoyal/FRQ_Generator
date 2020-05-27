package _2018;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class prob_1 {
    static ArrayList<Integer>  kVals = new ArrayList();
    static ArrayList compData = new ArrayList();

    public static void main(String[] args) throws Exception {
        //Add Font
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        //Create Paragraph
        for(int j = 0; j < 20; j++) {
            double[] dataSet = solve();


            String[] stringSet = new String[dataSet.length];
            Paragraph[] paraNum = new Paragraph[dataSet.length];

            int i = 0;
            for (Object item : kVals) {

                dataSet[0] = kVals.get(i);
                generateDoc(dataSet, stringSet, paraNum, j);
                i++;
            }
            kVals.clear();
        }
        //Version Notice
    }

    public static void generateDoc(double[] dataSet, String[] stringSet, Paragraph[] paraNum, int num) throws IOException
    {
        PdfReader reader = new PdfReader("input/tests/input_whited.pdf");
        PdfDocument srcDoc = new PdfDocument(reader);
        PdfWriter writer = new PdfWriter("results/_2018/prob_1/output" + num + ".pdf");
        PdfDocument pdfDoc = new PdfDocument(writer);
        //copy contents
        srcDoc.copyPagesTo(1, srcDoc.getNumberOfPages(), pdfDoc);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        int[][] positionData = {{223, 500}, {300, 493}, {238, 435}, {385, 435}, {325, 515}};

        for (int i = 0; i < dataSet.length; i++) {
            stringSet[i] = /*linebreakSolve(i) + */ "" + dataSet[i];
            if (i != 4)
                paraNum[i] = new Paragraph(rmvDec(stringSet[i])).setFont(font).setFixedPosition(positionData[i][0], positionData[i][1], 100);
            else
                paraNum[i] = new Paragraph(rmvDec(stringSet[i])).setFont(font).setFixedPosition(positionData[i][0], positionData[i][1], 100).setFontSize(8);

        }

        Paragraph notice = new Paragraph(("Version # ") + num).setFont(font).setFontSize(20).setFixedPosition(270,710,200);
        // Creating a Document
        Document document = new Document(pdfDoc);
        //Add objects to document
        for(int i = 0; i < dataSet.length; i++){
            document.add(paraNum[i]);
        }
        document.add(notice);
        // Closing the document
        document.close();
        System.out.println("PDF Generated");

    }

    public static double integrate(int k, int fracDen, int exp){
            double integral = 0;
            for(double i = 1; i <= 300; i += 1){
                integral += (calculate(i, k, fracDen, exp) * i);
            }
            return integral;

    }

    public static double calculate(double x, int k, int fracDen, int exp){
        return (k * (Math.pow(x/100, 3)) * Math.pow((1 - x/fracDen), exp));
    }

//Calculate
    public static double[] solve(){
        int k = 44;
        int fracDen = 300;
        double exitRate = 0.7;
        int initPop = 20;
        int exp = 7;
        int outTest = 0;

        //limits
        int kLim = 99;
        int fracDenLim = 500;
        double exitRateLim = 0.9;
        int initPopLim =  99;
        int expLim =  7;

        Random rand = new Random();

        initPop = rand.nextInt(kLim + 1);
        exitRate = rand.nextInt(10);
        exitRate/=10;

        exp = rand.nextInt((expLim - exp) + exp);
        fracDen = rand.nextInt((fracDenLim - fracDen) + fracDen);
        double testVal = 0.0;
        for(int i = 0; i < 100; i++){
            testVal = (integrate(i, fracDen, exp))/100;
            if(("" + testVal).contains(".00")){
               kVals.add(i);
           }
        }
        return new double[] {k, fracDen, exitRate, initPop, exp};
    }
    //markup
    public static String rmvDec(String string){
        if (string.contains(".0")){
            return string.replace(".0", "");
        }
        else
            return string;
    }

    public static String linebreakSolve(int num){
        String rV = "";
        if(num==0){
            rV+="\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
        }
        return rV;
    }
}
