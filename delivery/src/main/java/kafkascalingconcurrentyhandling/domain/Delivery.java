package kafkascalingconcurrentyhandling.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import kafkascalingconcurrentyhandling.DeliveryApplication;
import kafkascalingconcurrentyhandling.domain.DeliveryCancelled;
import kafkascalingconcurrentyhandling.domain.DeliveryModified;
import kafkascalingconcurrentyhandling.domain.DeliveryStarted;
import lombok.Data;

@Entity
@Table(name = "Delivery_table")
@Data
//<<< DDD / Aggregate Root
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Long customerId;

    private String productId;

    private String productName;

    private Integer qty;

    private String address;

    private String status;

    @PostPersist
    public void onPostPersist() {
        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        DeliveryModified deliveryModified = new DeliveryModified(this);
        deliveryModified.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {
        DeliveryCancelled deliveryCancelled = new DeliveryCancelled(this);
        deliveryCancelled.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    //<<< Clean Arch / Port Method
    public static void modifyDelivery(OrderModified orderModified) {
        //implement business logic here:

        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        DeliveryModified deliveryModified = new DeliveryModified(delivery);
        deliveryModified.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderModified.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            DeliveryModified deliveryModified = new DeliveryModified(delivery);
            deliveryModified.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void cancelDelivery(OrderCancelled orderCancelled) {
        //implement business logic here:

        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        DeliveryCancelled deliveryCancelled = new DeliveryCancelled(delivery);
        deliveryCancelled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderCancelled.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            DeliveryCancelled deliveryCancelled = new DeliveryCancelled(delivery);
            deliveryCancelled.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
