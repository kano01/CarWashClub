package au.com.carwashclub.services

import au.com.carwashclub.domain.magento.SalesFlatOrder

class EmailVouchersService {

    def pdfGeneratorService

    def mailService

    def send(SalesFlatOrder sale) {

        ByteArrayOutputStream stream = pdfGeneratorService.pdfForSaleOrder(sale)

        try{
            sendMail {
                multipart true
                to sale.customer.email
                subject "Carwash Club Vouchers"
                body "Here are you vouchers"
                attachBytes "vouchers.pdf", "application/pdf", stream.toByteArray()
            }
        }

        catch(Exception ex){
            log.error("Error sending email for sale: " +sale.id , ex)
            return;
        } finally {
            stream.close();
        }

        log.info("Sale: " + sale.id + " email sent.")

        sale.setOrderSent(new Date());
        sale.save(flush: true)

        log.info("Sale: " + sale.id + " marked as order sent.")

    }
}
