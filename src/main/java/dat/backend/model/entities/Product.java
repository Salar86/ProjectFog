package dat.backend.model.entities;

import java.util.Objects;

public class Product {

    private int productId;
    private String productDescription;
    private double unit;
    private double pricePerUnit;

    public Product(int productId, String productDescription, double unit, double pricePerUnit) {
        this.productId = productId;
        this.productDescription = productDescription;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getUnit() {
        return unit;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setUnit(double unit) {
        this.unit = unit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getProductId() == product.getProductId() && Double.compare(product.getUnit(), getUnit()) == 0 && Double.compare(product.getPricePerUnit(), getPricePerUnit()) == 0 && Objects.equals(getProductDescription(), product.getProductDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getProductDescription(), getUnit(), getPricePerUnit());
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productDescription='" + productDescription + '\'' +
                ", unit=" + unit +
                ", pricePerUnit=" + pricePerUnit +
                '}';
    }
}
