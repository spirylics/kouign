package fr.spirylics.kouign.config;

import fr.spirylics.kouign.domain.chat.ChatServiceImpl;
import fr.spirylics.kouign.domain.chat.in.ChatService;
import fr.spirylics.kouign.domain.chat.out.LlmChatClient;
import fr.spirylics.kouign.domain.model.ModelServiceImpl;
import fr.spirylics.kouign.domain.model.in.ModelService;
import fr.spirylics.kouign.domain.model.out.ModelRepository;
import fr.spirylics.kouign.infrastructure.OpenAiLlmChatClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ModelsProperties.class)
public class ApplicationConfig {
    @Bean
    ModelService modelService(final ModelRepository modelRepository) {
        return new ModelServiceImpl(modelRepository);
    }

     @Bean
     OpenAiApi openAiApi(@Value("${spring.ai.openai.base-url}") final String baseUrl) {
     return OpenAiApi.builder()
     .baseUrl(baseUrl)
     .apiKey("apiKey")
     .build();
     }

     @Bean
     ChatModel chatModel(final OpenAiApi openAiApi) {
     return OpenAiChatModel.builder().openAiApi(openAiApi).build();
     }

     @Bean
     ChatClient chatClient(final ChatModel chatModel) {
     return ChatClient.builder(chatModel).build();
     }

    @Bean
    LlmChatClient llmChatClient(final ChatClient chatClient) {
        return new OpenAiLlmChatClient(chatClient);
    }

    @Bean
    ChatService chatService(final LlmChatClient llmChatClient) {
        return new ChatServiceImpl(llmChatClient);
    }
}
