package au.com.carwashclub.magento

import org.springframework.dao.DataIntegrityViolationException

class SalesFlatOrderController {

    static allowedMethods = [save: "GET", update: "GET", delete: "GET"]

    def scaffold = true;
}
