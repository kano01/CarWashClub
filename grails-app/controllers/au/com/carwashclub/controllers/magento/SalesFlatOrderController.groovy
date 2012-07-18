package au.com.carwashclub.controllers.magento

import au.com.carwashclub.domain.magento.SalesFlatOrder

class SalesFlatOrderController {

    static allowedMethods = [save: "GET", update: "GET", delete: "GET"]

    def scaffold = SalesFlatOrder;
}
