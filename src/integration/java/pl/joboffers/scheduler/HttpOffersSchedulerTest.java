package pl.joboffers.scheduler;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import pl.joboffers.BaseIntegrationTest;
import pl.joboffers.JobOffersApplication;
import pl.joboffers.domain.offer.OfferFetchable;

import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = JobOffersApplication.class, properties = "scheduling.enabled=true")
public class HttpOffersSchedulerTest extends BaseIntegrationTest {

    @SpyBean
    OfferFetchable offerFetchable;

    @Test
    public void should_run_http_client_offers_fetching_exactly_given_times() {
        await()
                .atMost(Duration.ofSeconds(2))
                .untilAsserted(() -> verify(offerFetchable, times(2)).fetchOffers());
    }
}
