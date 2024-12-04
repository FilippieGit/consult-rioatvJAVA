
package Controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import java.awt.Desktop;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import com.itextpdf.text.pdf.PdfWriter;

public class Atestado_DAO {
    public static void imprimir() throws Exception {
        Document doc = null;
        OutputStream os = null;

        String a = JOptionPane.showInputDialog(null, "Nome completo:", "Criar atestado de paciente", -1);
        String b = JOptionPane.showInputDialog(null, "Nº de carteira identidade:", "Criar atestado de paciente", -1);
        String c = JOptionPane.showInputDialog(null, "Data do atendimento:", "Criar atestado de paciente", -1);
        String d = JOptionPane.showInputDialog(null, "Data inicial de afastamento:", "Criar atestado de paciente", -1);
        String e = JOptionPane.showInputDialog(null, "Data final do afastamento:", "Criar atestado de paciente", -1);
        String f = JOptionPane.showInputDialog(null, "Motivos do afastamento:", "Criar atestado de paciente", -1);
        String g = JOptionPane.showInputDialog(null, "Local da consulta:", "Criar atestado de paciente", -1);
        LocalDate data = java.time.LocalDate.now();
        String h = data.format(DateTimeFormatter.ISO_DATE);

        try {
            // Cria o documento tamanho A4
            doc = new Document(PageSize.A4, 45, 45, 40, 40);

            // Cria a stream de saída
            os = new FileOutputStream("Atestado.pdf");

            // Associa a stream de saída ao documento
            PdfWriter writer = PdfWriter.getInstance(doc, os);

            // Abre o documento
            doc.open();
            
            PdfContentByte cb = writer.getDirectContent();

            // Adiciona a imagem como fundo
            String verdeImg = "src/images/verde.png";
            Image imagem = Image.getInstance(verdeImg);
            imagem.setAbsolutePosition(20, 20);
            imagem.scaleAbsolute(PageSize.A4.getWidth()-40, PageSize.A4.getHeight()-40);

            PdfContentByte canvas = writer.getDirectContentUnder();
            canvas.addImage(imagem);

            // Adiciona os textos por cima da imagem
            Font f1 = new Font(Font.FontFamily.UNDEFINED, 28);
            Font f2 = new Font(Font.FontFamily.UNDEFINED, 16);
            Font f3 = new Font(Font.FontFamily.UNDEFINED, 14);
            Font f4 = new Font(Font.FontFamily.UNDEFINED, 20);
            
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase("ATESTADO MÉDICO", f1), 297.5f, 714, 0);
            
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase("INFORMAÇÕES RELEVANTES:", f3), 297.5f, 390, 0);
            
            Paragraph par = new Paragraph(" ");
            doc.add(par);

            Paragraph par2 = new Paragraph("Atesto para os devidos fins que o Sr. (a) " + a +
                    " portador da Carteira de identidade Nº " + b +
                    " esteve sob cuidados médicos no dia " + c +
                    " e deverá se afastar de suas atividades pelo período de " + d +
                    " até " + e + " por motivos de " + f + ".", f2);
            par2.setAlignment(Element.ALIGN_LEFT);
            par2.setSpacingBefore(150);
            doc.add(par2);
            
            ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("Local: " + g, f4), 50, 208, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase("Data: " + h, f4), 465, 208, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase("_____________________________", f4), 297.5f, 130, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase("Carimbo e Assinatura", f4), 297.5f, 100, 0);

        } finally {
            if (doc != null) {
                // Fechamento do documento
                doc.close();
            }
            if (os != null) {
                // Fechamento da stream de saída
                os.close();
            }
        }
        Desktop.getDesktop().open(new File("Atestado.pdf"));
    }
}
