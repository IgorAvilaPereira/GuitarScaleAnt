/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apresentacao;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/**
 *
 * @author iapereira
 */
public class ComponentPrinter implements Printable, ActionListener {

    Component componentToPrint;

    public ComponentPrinter(Component comp) {
        componentToPrint = comp;
    }

    /* Quando mandamos uma página ser impressa pelo PrinterJob,
 * ele vai executando continuamente este método até que o retorno
 * seja a constante NO_SUCH_PAGE
     */
    public int print(Graphics g, PageFormat pf, int page)
            throws PrinterException {


        /* Teremos apenas uma página, que começa em zero, por isso
	 * quando a página for maior que zero retornamos NO_SUCH_PAGE
         */
        if (page > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g.create();

        /* As coordenadas (0, 0) costumam ficar fora da área de impressão,
	 * portanto traduzimos as coordenadas (X, Y) de acordo com o PageFormat
         */
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        // Carrega a largura e a altura da página com base no PageFormat
        double pageWidth = pf.getImageableWidth();
        double pageHeight = pf.getImageableHeight();

        // Carrega a largura e a altura do componente a ser impresso
        double compWidth = (double) componentToPrint.getWidth();
        double compHeight = (double) componentToPrint.getHeight();

        /* Redimensiona o graphics se a largura do componente for
	 * maior que a largura da página 
         */
        if (compWidth >= pageWidth) {
            double scale = pageWidth / compWidth;
            g2d.scale(scale, scale);
            compHeight *= scale;
        }

        /* Redimensiona o graphics se a altura do componente for
	 * maior que a altura da página 
         */
        if (compHeight >= pageHeight) {
            double scale = pageHeight / compHeight;
            g2d.scale(scale, scale);
        }

        /* Now print the window and its visible contents */
        componentToPrint.printAll(g2d);

        g2d.dispose();

        return PAGE_EXISTS;
    }

    public void actionPerformed(ActionEvent e) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
                /* The job did not successfully complete */
            }
        }
    
    }
}
