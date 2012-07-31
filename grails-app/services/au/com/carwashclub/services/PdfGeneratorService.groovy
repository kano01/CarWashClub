package au.com.carwashclub.services

import au.com.carwashclub.domain.magento.SalesFlatOrder
import grails.plugin.rendering.pdf.PdfRenderingService
import org.codehaus.groovy.grails.commons.ApplicationHolder
import au.com.carwashclub.domain.Voucher

class PdfGeneratorService {

    static byte[] logoImage;

    def PdfRenderingService pdfRenderingService

    ByteArrayOutputStream pdfForSaleOrder(SalesFlatOrder sale) {
        if(logoImage == null){
            logoImage = new File(ApplicationHolder.application.parentContext.servletContext.getRealPath("/images/cwc_logo.png")).bytes
        }

        List<Voucher> vouchers  = Voucher.executeQuery("select v from Voucher as v, SalesFlatOrder as s, SalesFlatOrderItem as si where s.id = :saleId and si.salesFlatOrder = s and v.salesItem = si",[saleId: sale.id])
        log.info("Generating PDF for Sale: " + sale.id + " with " + vouchers.size() + " vouchers")

        return pdfRenderingService.render(template: '/pdfVouchers', model: [imageBytes:logoImage, vouchers: vouchers])

    }

    File pdfForSalesOrderAsFile(SalesFlatOrder sale, String fileUri) {
        ByteArrayOutputStream byteStream;
        OutputStream outputStream;

        try {
            outputStream = new FileOutputStream (fileUri);

            byteStream = pdfForSaleOrder(sale);
            byteStream.writeTo(outputStream);

        }finally {
            if(byteStream)
               byteStream.close();

            if(outputStream)
            outputStream.close();
        }
    }

}
