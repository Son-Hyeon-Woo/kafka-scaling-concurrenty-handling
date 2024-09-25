package kafkascalingconcurrentyhandling.domain;

import java.time.LocalDate;
import java.util.*;
import kafkascalingconcurrentyhandling.domain.*;
import kafkascalingconcurrentyhandling.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String productId;
    private String productName;
    private Integer qty;
    private Long customerId;
    private String address;

    public OrderPlaced(Order aggregate) {
        super(aggregate);
    }

    public OrderPlaced() {
        super();
    }
}
//>>> DDD / Domain Event
