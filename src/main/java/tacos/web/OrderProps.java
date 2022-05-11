package tacos.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;

@Component
@ConfigurationProperties(prefix="taco.orders")
@Data
public class OrderProps {
    @Max(value=10, message = "max 10")
    private int pageSize = 20;
}
