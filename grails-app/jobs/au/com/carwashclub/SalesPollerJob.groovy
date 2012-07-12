package au.com.carwashclub

import org.quartz.Job
import org.quartz.JobExecutionContext
import au.com.carwashclub.magento.SalesFlatOrder
import org.apache.log4j.Logger



class SalesPollerJob implements Job{

     static Logger LOG =  org.apache.log4j.Logger.getLogger("au.com.carwashclub.SalesPollerJob");

     void execute(JobExecutionContext jobCtx) {

        // Say Hello to the World and display the date/time
        log.info("Hello from - ${jobCtx.jobDetail.key.name} }" )
         LOG.info("Hello2 from - ${jobCtx.jobDetail.key.name} }" )

        def result = SalesFlatOrder.findAll {
            voucherSent == null
        }

        log.error result.size();

        result.each {
            log.info "generate vouchers for " + ((SalesFlatOrder)it).customerFirstname + " " + ((SalesFlatOrder)it).customerLastname
            def items =  ((SalesFlatOrder)it).saleFlatOrderItems;
            items.each {
                println "generate voucher for " + it.productId + " " + it.productType
            }

//            ((SalesFlatOrder)it).voucherEmailSent = 1;
//            ((SalesFlatOrder)it).save();
        }



     }
}
