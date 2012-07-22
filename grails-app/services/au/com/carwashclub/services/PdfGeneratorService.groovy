package au.com.carwashclub.services

import au.com.carwashclub.domain.magento.SalesFlatOrder
import grails.plugin.rendering.RenderingService
import grails.plugin.rendering.pdf.PdfRenderingService
import grails.plugin.rendering.image.JpegRenderingService

class PdfGeneratorService {

    def PdfRenderingService pdfRenderingService

    def pdfForSaleOrder(SalesFlatOrder sale) {

        // Render to a file
//        new File("voucher.pdf").withOutputStream { outputStream ->
//            pdfRenderingService.render(template: '/pdfVouchers')
//        }

        def bytes = pdfRenderingService.render(template: '/pdfVouchers', model: [sale: sale])

        new File("voucher1.pdf").append(bytes);

    }
}
