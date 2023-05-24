package dat.backend.model.entities;

import java.util.Objects;

public class ItemList {
    private int itemListId;
    private String description;
    private int price;
    private int orderId;
    private int productVariantId;
    private int quantity;

    public ItemList(String description, int price, int orderId, int productVariantId, int quantity) {
        this.description = description;
        this.price = price;
        this.orderId = orderId;
        this.productVariantId = productVariantId;
        this.quantity = quantity;
    }

    public ItemList(int itemListId, String description, int price, int orderId, int productVariantId, int quantity) { // Might need to be deleted.
        this.itemListId = itemListId;
        this.description = description;
        this.price = price;
        this.orderId = orderId;
        this.productVariantId = productVariantId;
        this.quantity = quantity;
    }


    public int getItemListId() {
        return itemListId;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductVariantId() {
        return productVariantId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemList itemList = (ItemList) o;
        return getItemListId() == itemList.getItemListId() && getPrice() == itemList.getPrice() && getOrderId() == itemList.getOrderId() && getProductVariantId() == itemList.getProductVariantId() && getQuantity() == itemList.getQuantity() && Objects.equals(getDescription(), itemList.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemListId(), getDescription(), getPrice(), getOrderId(), getProductVariantId(), getQuantity());
    }

    @Override
    public String toString() {
        return "ItemList{" +
                "itemListId=" + itemListId +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", orderId=" + orderId +
                ", productVariantId=" + productVariantId +
                ", quantity=" + quantity +
                '}';
    }
}
