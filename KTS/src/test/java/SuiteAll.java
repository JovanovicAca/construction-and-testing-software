import com.example.KTS.repository.MenuItem.MenuItemRepoTest;
import com.example.KTS.service.Ingredient.IngredientServiceUnitTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.TestPropertySource;
import com.example.KTS.repository.IngredientRepositoryTest;

@RunWith(Suite.class)
@TestPropertySource("classpath:test.properties")
@Suite.SuiteClasses({IngredientRepositoryTest.class, IngredientServiceUnitTest.class, MenuItemRepoTest.class})
public class SuiteAll {


}