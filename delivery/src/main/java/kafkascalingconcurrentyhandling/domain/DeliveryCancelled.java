package kafkascalingconcurrentyhandling.domain;

import java.time.LocalDate;
import java.util.*;
import kafkascalingconcurrentyhandling.domain.*;
import kafkascalingconcurrentyhandling.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class DeliveryCancelled extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long customerId;
    private String productId;
    private String productName;
    private Integer qty;
    private String address;
    private String status;

    public DeliveryCancelled(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryCancelled() {
        super();
    }
}
//>>> DDD / Domain Event
