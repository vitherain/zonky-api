package cz.herain.zonky;

import cz.herain.zonky.main.RestTemplateConfig;
import cz.herain.zonky.main.ZonkyRestConsumingApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Vít on 26. 10. 2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ZonkyRestConsumingApplication.class, RestTemplateConfig.class})
public abstract class ZonkyRestConsumingApplicationTest {
}
