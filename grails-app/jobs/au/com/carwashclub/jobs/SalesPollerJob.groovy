package au.com.carwashclub.jobs

import org.quartz.JobExecutionContext
import au.com.carwashclub.domain.magento.SalesFlatOrderItem
import au.com.carwashclub.domain.magento.SalesFlatOrder
import org.quartz.Job

class SalesPollerJob implements Job {

    def voucherService;

    def grailsApplication;

    void execute(JobExecutionContext jobCtx) {
        log.info("Running ${jobCtx.jobDetail.key.name} " )

        voucherService = grailsApplication.mainContext.getBean("voucherService");

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
                voucherService.generateTokens();

            }

//            ((SalesFlatOrder)it).voucherEmailSent = 1;
//            ((SalesFlatOrder)it).save();
        }



     }
}