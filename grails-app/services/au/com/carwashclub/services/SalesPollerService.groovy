package au.com.carwashclub.services

import au.com.carwashclub.GenerateVoucher
import au.com.carwashclub.domain.magento.SalesFlatOrder
import au.com.carwashclub.domain.magento.SalesFlatOrderItem
import org.quartz.Job
import org.quartz.JobExecutionContext

class SalesPollerService implements Job {

    def voucherService;

    def grailsApplication;

    void execute(JobExecutionContext jobCtx) {
        log.info("Running ${jobCtx.jobDetail.key.name} " )

        voucherService = grailsApplication.mainContext.getBean("voucherService");
        voucherService.generateTokens();

        def result = SalesFlatOrder.findAll {
            voucherSent == null
        }

        result.each {
            SalesFlatOrder s = ((SalesFlatOrder)it);
            log.info("Generate vouchers for ID: " + s.id +
                    " Customer: " + s.customerFirstname + " " + s.customerLastname) +
                    " Created Date: " + s.createdAt.toLocaleString();
            def items =  s.saleFlatOrderItems;
            items.each {
                SalesFlatOrderItem sf =  ((SalesFlatOrderItem)it);
                log.info("Processing line item ID: " + sf.id + " Product:"  + sf.name + " Product Type: " + sf.productId);


            }

//            ((SalesFlatOrder)it).voucherEmailSent = 1;
//            ((SalesFlatOrder)it).save();
        }



     }
}
