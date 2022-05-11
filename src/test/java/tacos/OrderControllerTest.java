package tacos;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tacos.data.IngredientRepository;
import tacos.data.OrderRepository;
import tacos.data.TacoRepository;
import tacos.model.Ingredient;
import tacos.model.Order;
import tacos.model.Taco;
import tacos.web.OrderController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IngredientRepository ingredientRepository;

    @MockBean
    TacoRepository tacoRepository;

    @MockBean
    OrderRepository orderRepository;

    private List<Ingredient> ingredients;

    private Order order;

    @Before
    public void setup() {
        order = new Order();

        Taco design = new Taco();
        design.setName("Test taco");
        design.setIngredients(
                Arrays.asList(
                        new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                        new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                        new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE)
                )
        );
        order.setDeliveryName("Test User");
        order.setDeliveryCity("Amsterdam");
        order.setDeliveryStreet("Test street");
        order.setDeliveryProvince("Test");
        order.setZip("1122AB");
        order.setNumber("4556253823729533");
        order.setExpiration("12/20");
        order.setCvv("123");
        order.setPlacedAt(new Date());
        order.addDesign(design);

    }

    @Test
    public void testShowOrderForm() throws Exception {
        mockMvc.perform(get("/orders/current").sessionAttr("order", order))
                .andExpect(status().isOk())
                .andExpect(view().name("orderForm"));
    }

    @Test
    public void processOrder() throws Exception {
        when(orderRepository.save(order))
                .thenReturn(order);

        mockMvc.perform(post("/orders").sessionAttr("order", order)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().stringValues("Location", "/"));
    }
}
