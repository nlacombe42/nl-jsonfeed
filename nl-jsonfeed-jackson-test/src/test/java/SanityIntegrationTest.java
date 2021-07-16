import net.nlacombe.jsonfeed.test.Main;
import org.junit.jupiter.api.Test;

public class SanityIntegrationTest {

    @Test
    public void main_does_not_throw_exception() {
        Main.main(new String[]{});
    }

}
