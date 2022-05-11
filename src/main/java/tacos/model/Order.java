package tacos.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import tacos.security.User;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Taco_Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date placedAt;

    @NotBlank(message="Name is required")
    private String deliveryName;

    @NotBlank(message="Street is required")
    private String deliveryStreet;

    @NotBlank(message="City is required")
    private String deliveryCity;

    @NotBlank(message="Province is required")
    private String deliveryProvince;

    @NotBlank(message="Zip code is required")
    private String zip;

    @CreditCardNumber(message="Not a valid credit card number")
    private String number;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String expiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String cvv;

    @ManyToMany(targetEntity = Taco.class)
    private final List<Taco> tacos = new ArrayList<>();

    @ManyToOne
    private User user;

    public void addDesign(Taco design){
        this.tacos.add(design);
    }

    @PrePersist
    public void setPlacedAt() {
        this.placedAt = new Date();
    }

}
