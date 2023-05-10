package dat.backend.model.entities;

import java.util.Objects;

public class Order {
    private int orderId;
    private double length;
    private double width;
    private double price;
    private String material;
    private String status;
    private int userId;

    public Order(int orderId, double length, double width, double price, String material, String status) {
        this.orderId = orderId;
        this.length = length;
        this.width = width;
        this.price = price;
        this.material = material;
        this.status = status;
    }

    public Order(int orderId, double length, double width, double price, String material, String status, int userId) {
        this.orderId = orderId;
        this.length = length;
        this.width = width;
        this.price = price;
        this.material = material;
        this.status = status;
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getPrice() {
        return price;
    }

    public String getMaterial() {
        return material;
    }

    public String getStatus() {
        return status;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getOrderId() == order.getOrderId() && Double.compare(order.getLength(), getLength()) == 0 && Double.compare(order.getWidth(), getWidth()) == 0 && Double.compare(order.getPrice(), getPrice()) == 0 && Objects.equals(getMaterial(), order.getMaterial()) && Objects.equals(getStatus(), order.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getLength(), getWidth(), getPrice(), getMaterial(), getStatus());
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", length=" + length +
                ", width=" + width +
                ", price=" + price +
                ", material='" + material + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
