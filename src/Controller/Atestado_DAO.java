
package Controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class Atestado_DAO {
    
    public static void imprimir() throws Exception{
        
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

            //cria o documento tamanho A4, margens de 2,54cm
            doc = new Document(PageSize.A4, 72, 72, 72, 72);

            //cria a stream de saída
            os = new FileOutputStream("Relatorio1.pdf");

            //associa a stream de saída ao
            PdfWriter.getInstance(doc, os);

            //abre o documento
            doc.open();

            //adiciona o texto ao PDF
            Paragraph par = new Paragraph("ATESTADO MÉDICO \n\n");
            doc.add(par);
            Paragraph par2 = new Paragraph("Atesto para os devidos fins que o Sr. (a) " + a + " portador da Carteira de identidade(RG) Nº " + b + " esteve sob cuidados médicos no dia " + c + " e deverá se afastar das suas atividades laborativas pelo período de " + d + " até " + e + " por motivos de " + f + "\n\n");
            doc.add(par2);
            Paragraph par3 = new Paragraph("Local: " + g + "\nData: "+ h);
            doc.add(par3);

        } finally {

            if (doc != null) {

                //fechamento do documento
                doc.close();
            }

            if (os != null) {
                //fechamento da stream de saída
                os.close();
            }
        }
        Desktop.getDesktop().open(new File("Relatorio1.pdf"));
    }

}
