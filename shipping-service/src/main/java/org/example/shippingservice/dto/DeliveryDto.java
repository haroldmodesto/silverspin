package org.example.shippingservice.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link org.example.shippingservice.entity.Delivery}
 */
public class DeliveryDto implements Serializable {
    private final Long id;
    @jakarta.validation.constraints.NotNull(message = "Required")
    @jakarta.validation.constraints.Positive(message = "Invalid order id")
    private final Long orderId;
    @jakarta.validation.constraints.NotEmpty(message = "Required")
    private final String trackingNumber;
    @jakarta.validation.constraints.NotBlank(message = "Required")
    private final String status;

    public DeliveryDto(Long id, Long orderId, String trackingNumber, String status) {
        this.id = id;
        this.orderId = orderId;
        this.trackingNumber = trackingNumber;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryDto entity = (DeliveryDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.orderId, entity.orderId) &&
                Objects.equals(this.trackingNumber, entity.trackingNumber) &&
                Objects.equals(this.status, entity.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, trackingNumber, status);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "orderId = " + orderId + ", " +
                "trackingNumber = " + trackingNumber + ", " +
                "status = " + status + ")";
    }
}