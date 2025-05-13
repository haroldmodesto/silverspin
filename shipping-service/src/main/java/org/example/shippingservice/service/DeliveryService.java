package org.example.shippingservice.service;

import jakarta.validation.constraints.NotEmpty;
import org.example.shippingservice.dto.DeliveryDto;
import org.example.shippingservice.entity.Delivery;
import org.example.shippingservice.entity.ShippingStatus;
import org.example.shippingservice.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public DeliveryDto getDeliveryInfoForOrder(@NotEmpty Long orderId) {
        Optional<Delivery> persisted = deliveryRepository.findByOrderId(orderId);
        if (persisted.isPresent()) {
            Delivery delivery = persisted.get();
            return new DeliveryDto(
                    delivery.getId(),
                    delivery.getOrderId(),
                    delivery.getTrackingNumber(),
                    delivery.getStatus());
        }

        return this.save(orderId);
    }

    public DeliveryDto save(@NotEmpty Long orderId) {
        Delivery newDelivery = new Delivery();
        newDelivery.setOrderId(orderId);
        newDelivery.setStatus(ShippingStatus.IN_TRANSIT.name());
        newDelivery.setTrackingNumber(generateTrackingNumber());

        Delivery processed = deliveryRepository.save(newDelivery);

        return new DeliveryDto(
                processed.getId(),
                processed.getOrderId(),
                processed.getTrackingNumber(),
                processed.getStatus());
    }

    private String generateTrackingNumber() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }
}
