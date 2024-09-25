package kafkascalingconcurrentyhandling.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import kafkascalingconcurrentyhandling.OrderApplication;
import kafkascalingconcurrentyhandling.domain.OrderCancelled;
import kafkascalingconcurrentyhandling.domain.OrderModified;
import kafkascalingconcurrentyhandling.domain.OrderPlaced;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
//<<< DDD / Aggregate Root
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productId;

    private String productName;

    private Integer qty;

    private Long customerId;

    private String address;

    @PostPersist
    public void onPostPersist() {
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        OrderModified orderModified = new OrderModified(this);
        orderModified.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {}

    @PreRemove
    public void onPreRemove() {
        OrderCancelled orderCancelled = new OrderCancelled(this);
        orderCancelled.publishAfterCommit();
    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    public void order() {
        //implement business logic here:

    }

    public void modifyAddress() {
        //implement business logic here:

        OrderModified orderModified = new OrderModified(this);
        orderModified.publishAfterCommit();
    }

    public void orderCancel() {
        //implement business logic here:

        OrderCancelled orderCancelled = new OrderCancelled(this);
        orderCancelled.publishAfterCommit();
    }
}
//>>> DDD / Aggregate Root
