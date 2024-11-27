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

public class Receituario_DAO {

    public static void imprimir() throws Exception {

        Document doc = null;
        OutputStream os = null;
        String a = JOptionPane.showInputDialog(null, "Motivo da declaração de comparecimento:", "Criar declaração de comparecimento de paciente", -1);
        String b = JOptionPane.showInputDialog(null, "Nome do paciente:", "Criar declaração de comparecimento de paciente", -1);
        String c = JOptionPane.showInputDialog(null, "Data da consulta:", "Criar declaração de comparecimento de paciente", -1);
        String d = JOptionPane.showInputDialog(null, "Período do dia", "Criar declaração de comparecimento de paciente", -1);
        String f = JOptionPane.showInputDialog(null, "Local da declaração:", "Criar declaração de comparecimento de paciente", -1);
        LocalDate data = java.time.LocalDate.now();
        String g = data.format(DateTimeFormatter.ISO_DATE);

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
            Paragraph par = new Paragraph("Declaro para fins de" + a + "\nque o(a) Sr.(a). " + b + "\n\n");
            doc.add(par);
            Paragraph par2 = new Paragraph("Nome: " + a + "\nCRM: " + b + "\n\n");
            doc.add(par2);
            Paragraph par3 = new Paragraph("RECEITUÁRIO\n\n");
            doc.add(par3);
            Paragraph par4 = new Paragraph("Paciente: " + c + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            doc.add(par4);
            Paragraph par5 = new Paragraph(f);
            doc.add(par5);

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
