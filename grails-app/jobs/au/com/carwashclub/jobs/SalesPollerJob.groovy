package au.com.carwashclub.jobs

import org.quartz.JobExecutionContext
import au.com.carwashclub.domain.magento.SalesFlatOrderItem
import au.com.carwashclub.domain.magento.SalesFlatOrder
import org.quartz.Job

class SalesPollerJob implements Job {

    def orderProcessingService;

    def grailsApplication;

    void execute(JobExecutionContext jobCtx) {
        log.info("Running ${jobCtx.jobDetail.key.name} " )

        orderProcessingService = grailsApplication.mainContext.getBean("orderProcessingService");

        def result = SalesFlatOrder.findAll {
//           orderProcessed  == null
        }

        result.each {
            SalesFlatOrder s = ((SalesFlatOrder)it);
            log.info("About to process Sales order: " + s.id +
                    " Customer: " + s.customerFirstname + " " + s.customerLastname) +
                    " Created Date: " + s.createdAt.toLocaleString();
            orderProcessingService.processSalesOrder(s);
        }



     }
}