package au.com.carwashclub.jobs

import org.quartz.JobExecutionContext
import au.com.carwashclub.domain.magento.SalesFlatOrderItem
import au.com.carwashclub.domain.magento.SalesFlatOrder
import org.quartz.Job

class SalesPollerJob implements Job {

    def orderProcessingService;
    def emailVouchersService;

    def grailsApplication;

    void execute(JobExecutionContext jobCtx) {
        log.info("Running ${jobCtx.jobDetail.key.name} " )

        orderProcessingService = grailsApplication.mainContext.getBean("orderProcessingService");
        emailVouchersService = grailsApplication.mainContext.getBean("emailVouchersService");

        def result = SalesFlatOrder.findAll("from SalesFlatOrder as sf where sf.orderProcessed is null")

        result.each {
            SalesFlatOrder s = ((SalesFlatOrder)it);
            log.info("About to process Sales order: " + s.id +
                    " Customer: " + s.customerFirstname + " " + s.customerLastname) +
                    " Created Date: " + s.createdAt.toLocaleString();
            orderProcessingService.processSalesOrder(s);
        }

        result = SalesFlatOrder.findAll("from SalesFlatOrder as sf where sf.orderProcessed is not null and sf.orderSent is null");
        log.info("Pdf Generation queue size: " + result.size())
        result.each {
              emailVouchersService.send(it)
//            pdfGeneratorService.pdfForSaleOrder(it)
//            pdfGeneratorService.pdfForSalesOrderAsFile(it, "voucher" +it.id+".pdf")
        }



     }
}