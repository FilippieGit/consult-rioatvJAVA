package Controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import java.awt.Desktop;
import java.io.File;
import javax.swing.JOptionPane;

public class Receituario_DAO {

    public static void imprimir() throws Exception {

        Document doc = null;
        OutputStream os = null;

        // Entrada de dados
        String nomeMedico = JOptionPane.showInputDialog(null, "Nome do(a) médico(a):", "Receituário", -1);
        String crm = JOptionPane.showInputDialog(null, "CRM do(a) médico(a):", "Receituário", -1);
        String nomePaciente = JOptionPane.showInputDialog(null, "Nome do(a) paciente:", "Receituário", -1);
        String localData = JOptionPane.showInputDialog(null, "Local e data (ex.: São Paulo, 03 de dezembro de 2024):", "Receituário", -1);

        try {
            // Configuração do documento
            doc = new Document(PageSize.A4, 40, 40, 18, 0);
            os = new FileOutputStream("Receituario.pdf");
            PdfWriter writer = PdfWriter.getInstance(doc, os);
            doc.open();

            // Desenho da barra lateral
            PdfContentByte canvas = writer.getDirectContent();
            BaseColor barraCor = new BaseColor(34, 139, 34); // Cor
            canvas.setColorFill(barraCor);
            canvas.rectangle(0, 0, 20, 1200); // x, y, largura, altura
            canvas.fill();
            
            // Assinatura (ajustando a posição fixa)
            PdfContentByte cb = writer.getDirectContent();

            // Fontes
            Font tituloFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Font textoFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
            
            

            // Config imagem

            // Cabeçalho com imagem e informações do médico
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);

            // Adiciona a imagem
            String caminhoImagem = "images/simbolo.png";
            Image imagem = Image.getInstance(caminhoImagem);

            // Define a largura e altura da imagem
            imagem.scaleAbsolute(100, 100);

            // Coloca a imagem no PDF
            PdfPCell imagemCell = new PdfPCell(imagem);
            imagemCell.setBorder(0);
            imagemCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(imagemCell);
            
            

            // Adiciona informações do médico
            PdfPCell infoMedicoCell = new PdfPCell(new Paragraph(""));
            infoMedicoCell.setBorder(0);            
            table.addCell(infoMedicoCell);
            doc.add(table);

            
            ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("Nome: " + nomeMedico, textoFont), 200, 780, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("CRM: " + crm, textoFont), 200, 760, 0);
            


            // Título central
            Paragraph titulo = new Paragraph("Receituário", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            doc.add(titulo);

            // Informações do paciente
            Paragraph pacienteInfo = new Paragraph("Paciente: " + nomePaciente, textoFont);
            doc.add(pacienteInfo);

            
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(localData, textoFont), 200, 90, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase("_________________________", textoFont), 420, 90, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase("Ass.", textoFont), 420, 70, 0);

        } finally {
            if (doc != null) {
                doc.close();
            }
            if (os != null) {
                os.close();
            }
        }

        // Abrir o PDF gerado
        Desktop.getDesktop().open(new File("Receituario.pdf"));
    }
}
