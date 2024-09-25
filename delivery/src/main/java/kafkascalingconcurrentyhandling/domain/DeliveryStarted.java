package kafkascalingconcurrentyhandling.domain;

import java.time.LocalDate;
import java.util.*;
import kafkascalingconcurrentyhandling.domain.*;
import kafkascalingconcurrentyhandling.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class DeliveryStarted extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long customerId;
    private String productId;
    private String productName;
    private Integer qty;
    private String address;
    private String status;

    public DeliveryStarted(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryStarted() {
        super();
    }
}
//>>> DDD / Domain Event
