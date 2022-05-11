package tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.data.IngredientRepository;
import tacos.model.Ingredient;
import tacos.security.User;
import tacos.security.UserRepository;

@Configuration
@Profile("!prod")
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repository, UserRepository userRepository,
                                        PasswordEncoder passwordEncoder) {

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                repository.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
                repository.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
                repository.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
                repository.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
                repository.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
                repository.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
                repository.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
                repository.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
                repository.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
                repository.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));

                userRepository.save(new User("test", passwordEncoder.encode("test"),
                        "Rob Jot", "123 North Street", "Amsterdam", "TX",
                        "76227", "123-123-1234"));
            }
        };
    }
}
