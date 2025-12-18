package fr.spirylics.kouign.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.spirylics.kouign.domain.chat.ChatServiceImpl;
import fr.spirylics.kouign.domain.chat.in.ChatService;
import fr.spirylics.kouign.domain.chat.in.ItineraryService;
import fr.spirylics.kouign.domain.chat.out.LlmChatClient;
import fr.spirylics.kouign.domain.itinerary.ItineraryServiceImpl;
import fr.spirylics.kouign.domain.model.ModelServiceImpl;
import fr.spirylics.kouign.domain.model.in.ModelService;
import fr.spirylics.kouign.domain.model.out.ModelRepository;
import fr.spirylics.kouign.domain.sugar.SugarServiceImpl;
import fr.spirylics.kouign.domain.sugar.in.SugarService;
import fr.spirylics.kouign.infrastructure.OpenAiLlmChatClient;
import fr.spirylics.kouign.infrastructure.repository.GeocodingRepository;
import fr.spirylics.kouign.infrastructure.repository.ItineraryRepository;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
@EnableConfigurationProperties(KouignProperties.class)
@ImportHttpServices(group = "osrm", types = {ItineraryRepository.class})
@ImportHttpServices(group = "nominatim", types = {GeocodingRepository.class})
public class ApplicationConfig {
    @Bean
    ModelService modelService(final ModelRepository modelRepository)
    {
        return new ModelServiceImpl(modelRepository);
    }

    @Bean
    OpenAiApi openAiApi(@Value("${spring.ai.openai.base-url}") final String baseUrl)
    {
        return OpenAiApi.builder().baseUrl(baseUrl).apiKey("apiKey").build();
    }

    @Bean
    ChatModel chatModel(final OpenAiApi openAiApi)
    {
        return OpenAiChatModel.builder().openAiApi(openAiApi).build();
    }

    @Bean
    ChatClient chatClient(final ChatModel chatModel)
    {
        return ChatClient.builder(chatModel).build();
    }

    @Bean
    LlmChatClient llmChatClient(final ChatClient chatClient)
    {
        return new OpenAiLlmChatClient(chatClient);
    }

    @Bean
    ChatService chatService(final LlmChatClient llmChatClient, final ModelService modelService)
    {
        return new ChatServiceImpl(llmChatClient, modelService);
    }

    @Bean
    ItineraryService itineraryService(final ItineraryRepository itineraryRepository,
                                      final GeocodingRepository geocodingRepository)
    {
        return new ItineraryServiceImpl(itineraryRepository, geocodingRepository);
    }

    @Bean
    SugarService sugarService(final KouignProperties kouignProperties)
    {
        return new SugarServiceImpl(kouignProperties.randomPause());
    }

    @Bean
    ObjectMapper objectMapper()
    {
        return new ObjectMapper();
    }

    @Bean
    RestClientHttpServiceGroupConfigurer restClientHttpServiceGroupConfigurer()
    {
        return groups -> {
            groups.filterByName("osrm")
                    .forEachClient((group, builder) -> builder.baseUrl("https://router.project-osrm.org")
                            .defaultHeader("Accept-Encoding", "identity").requestInterceptor(loggingInterceptor("OSRM"))
                            .build());

            groups.filterByName("nominatim")
                    .forEachClient((group, builder) -> builder.baseUrl("https://nominatim.openstreetmap.org")
                            .defaultHeader("User-Agent", "kouign-app")
                            .requestInterceptor(loggingInterceptor("Nominatim")).build());
        };
    }

    private ClientHttpRequestInterceptor loggingInterceptor(final String apiName)
    {
        final Logger logger = LoggerFactory.getLogger("fr.spirylics.kouign.http." + apiName);

        return (HttpRequest request, byte[] body, ClientHttpRequestExecution execution) -> {
            logger.info("=== {} Request ===", apiName);
            logger.info("URI: {}", request.getURI());
            logger.info("Method: {}", request.getMethod());
            logger.info("Headers: {}", request.getHeaders());

            if (body.length > 0) {
                logger.info("Body: {}", new String(body, StandardCharsets.UTF_8));
            }

            final ClientHttpResponse response = execution.execute(request, body);

            logger.info("=== {} Response ===", apiName);
            logger.info("Status: {}", response.getStatusCode());
            logger.info("Headers: {}", response.getHeaders());

            return response;
        };
    }
}
