package au.com.carwashclub.controllers.magento

import org.springframework.dao.DataIntegrityViolationException
import au.com.carwashclub.domain.magento.SalesFlatOrderItem

class SalesFlatOrderItemController {

    static allowedMethods = [save: "GET", update: "GET", delete: "GET"]

    def scaffold = SalesFlatOrderItem;




}
