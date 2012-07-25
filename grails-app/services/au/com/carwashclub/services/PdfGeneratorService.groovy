package au.com.carwashclub.services

import au.com.carwashclub.domain.magento.SalesFlatOrder
import grails.plugin.rendering.pdf.PdfRenderingService
import org.codehaus.groovy.grails.commons.ApplicationHolder
import au.com.carwashclub.domain.Voucher

class PdfGeneratorService {

    static byte[] logoImage;

    def PdfRenderingService pdfRenderingService

    def pdfForSaleOrder(SalesFlatOrder sale) {
        if(logoImage == null){
            logoImage = new File(ApplicationHolder.application.parentContext.servletContext.getRealPath("/images/cwc_logo.png")).bytes
        }

        log.info("Generating PDF");

        List<Voucher> vouchers  = Voucher.executeQuery("select v from Voucher as v, SalesFlatOrder as s, SalesFlatOrderItem as si where s.id = :saleId and si.salesFlatOrder = s and v.salesItem = si",[saleId: sale.id])
        log.info("Vouchers " + vouchers.size())

        ByteArrayOutputStream bytes = pdfRenderingService.render(template: '/pdfVouchers', model: [imageBytes:logoImage, vouchers: vouchers])

        OutputStream outputStream = new FileOutputStream (sale.id + "-voucher.pdf")

        bytes.writeTo(outputStream);
        bytes.close();

        outputStream.close();

        log.info("PDF Generation Completed");

    }
}
