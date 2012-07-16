package au.com.carwashclub

import org.quartz.Job
import org.quartz.JobExecutionContext
import au.com.carwashclub.magento.SalesFlatOrder
import au.com.carwashclub.magento.SalesFlatOrderItem

class SalesPollerJob implements Job{

     void execute(JobExecutionContext jobCtx) {


        log.info("Running ${jobCtx.jobDetail.key.name} " )

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
                String[] tokenPin = GenerateVoucher.getInstance().generateTokenAndPin();

            }

//            ((SalesFlatOrder)it).voucherEmailSent = 1;
//            ((SalesFlatOrder)it).save();
        }



     }
}
