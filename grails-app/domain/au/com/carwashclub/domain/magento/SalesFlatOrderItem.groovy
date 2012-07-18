package au.com.carwashclub.domain.magento

class SalesFlatOrderItem {

    String productId
    String productType
    String productOptions
    String name
    Date createdAt
    Date updatedAt
    Short parentItemId

    SalesFlatOrder salesFlatOrder

    static mapping = {
        id column: "item_id"
        salesFlatOrder column: "order_id"
        version false
    }

    static constraints = {

    }

    public String toString() {
        return getName();
    }
}
