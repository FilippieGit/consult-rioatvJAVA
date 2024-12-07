package Controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.io.File;

public class DecComparecimento_DAO {

    public static void imprimir() throws Exception {

        Document doc = null;
        OutputStream os = null;

        // Entrada de dados
        String nomePaciente = JOptionPane.showInputDialog(null, "Nome do(a) paciente:", "Declaração de Comparecimento", -1);
        if (nomePaciente == null) return;
        
        String dataConsulta = JOptionPane.showInputDialog(null, "Data da consulta (ex.: 03/12/2024):", "Declaração de Comparecimento", -1);
        if (dataConsulta == null) return;

        String horarioConsulta = JOptionPane.showInputDialog(null, "Período da consulta (ex.: 10h às 12h):", "Declaração de Comparecimento", -1);
        if (horarioConsulta == null) return;

        String finalidade = JOptionPane.showInputDialog(null, "Finalidade da declaração:", "Declaração de Comparecimento", -1);
        if (finalidade == null) return;

        String localData = JOptionPane.showInputDialog(null, "Local e Data (ex.: São Paulo, 03/12/2024):", "Declaração de Comparecimento", -1);
        if (localData == null) return;

        try {
            // Configuração do documento
            doc = new Document(PageSize.A4, 40, 40, 25, 50);
            os = new FileOutputStream("DecComparecimento.pdf");
            PdfWriter writer = PdfWriter.getInstance(doc, os);
            doc.open();

            // Fontes
            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 16, Font.NORMAL);
            Font textoFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            Font subtituloFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

            // Logo
            String caminhoImagem = "images/dentistabg.png"; // Substitua pelo caminho da sua imagem
            Image logo = Image.getInstance(caminhoImagem);
            logo.scaleAbsolute(PageSize.A4.getWidth(), PageSize.A4.getHeight());
            logo.setAlignment(15);
            doc.add(logo);
            
            // Assinatura (ajustando a posição fixa)
            PdfContentByte cb = writer.getDirectContent();

            // Cabeçalho
            Paragraph cabecalho = new Paragraph("Dra. Geisy de Almeida", tituloFont);
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            doc.add(cabecalho);
            
            // Subtítulo
            Paragraph subtitulo = new Paragraph("Cirurgiã Dentista\nCROSP 00.000\n", subtituloFont);
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            doc.add(subtitulo);

            // Título
            Paragraph titulo = new Paragraph("DECLARAÇÃO DE COMPARECIMENTO\n\n", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingBefore(165);
            doc.add(titulo);

            // Corpo do texto
            Paragraph corpo = new Paragraph(
                "Declaro, para fins de " + finalidade + ", que o(a) Sr(a). " + nomePaciente +
                " esteve em consulta odontológica no dia " + dataConsulta + ".\ndurante o período das " +
                horarioConsulta + ".\n\n", textoFont);
            corpo.setAlignment(Element.ALIGN_JUSTIFIED);
            corpo.setSpacingAfter(20);
            doc.add(corpo);

            // Local e data
            Paragraph localDataPar = new Paragraph(localData + "\n\n", textoFont);
            localDataPar.setAlignment(Element.ALIGN_CENTER);
            localDataPar.setSpacingBefore(50);
            doc.add(localDataPar);

            // Assinatura
            Paragraph assinatura = new Paragraph("___________________________________________\ncarimbo e assinatura", subtituloFont);
            assinatura.setAlignment(Element.ALIGN_CENTER);
            assinatura.setSpacingBefore(80);
            doc.add(assinatura);
            
            // Endereço
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Paragraph("Travessa dos Cartões de Natalm 31 - Parque Vitória - São Paulo - SP", subtituloFont), 297.5f, 50, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Paragraph("Fones: 2240-5572 / 2243-4513 - contato@graficaestudio-e.com.br", subtituloFont), 297.5f, 40, 0);
        } finally {
            if (doc != null) {
                doc.close();
            }
            if (os != null) {
                os.close();
            }
        }

        // Abrir o PDF gerado
        Desktop.getDesktop().open(new File("DecComparecimento.pdf"));
    }
}
