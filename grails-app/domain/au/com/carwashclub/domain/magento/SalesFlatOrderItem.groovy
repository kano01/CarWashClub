package au.com.carwashclub.domain.magento

class SalesFlatOrderItem {

    String productId
    String productType
    String productOptions
    String name
    Date createdAt
    Date updatedAt
    Short parentItemId
    Double qtyOrdered
    Double price
    Double priceInclTax
    Date lineProcessed


    SalesFlatOrder salesFlatOrder

    static mapping = {
        id column: "item_id"
        salesFlatOrder column: "order_id"
        version false
    }

    static constraints = {
        lineProcessed(nullable: true)
        parentItemId(nullable: true)
    }

    public String toString() {
        return getName();
    }
}
