package fr.spirylics.kouign.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public record ChatCompletionRequest(
        String model,
        List<Message> messages,
        @Nullable Double temperature,
        @JsonProperty("max_tokens") @Nullable Integer maxTokens,
        @JsonProperty("max_completion_tokens") @Nullable Integer maxCompletionTokens,
        @Nullable Boolean stream,
        @JsonProperty("frequency_penalty") @Nullable Double frequencyPenalty,
        @JsonProperty("presence_penalty") @Nullable Double presencePenalty,
        @Nullable List<String> stop,
        @JsonProperty("top_p") @Nullable Double topP,
        @Nullable Integer n,
        @JsonProperty("logit_bias") @Nullable Map<String, Integer> logitBias,
        @Nullable String user
) {
    public record Message(
            String role,
            String content,
            @Nullable String name
    ) {}


    public Prompt toPrompt() {
        var aiMessages = messages.stream()
                .<org.springframework.ai.chat.messages.Message>map(msg -> switch (msg.role.toLowerCase(Locale.ROOT)) {
                    case "system" -> new SystemMessage(msg.content);
                    case "assistant" -> new AssistantMessage(msg.content);
                    case "user" -> new UserMessage(msg.content);
                    default -> throw new IllegalArgumentException("Unknown role: " + msg.role);
                })
                .toList();

        var optionsBuilder = ChatOptions.builder();

        optionsBuilder.model(model);
        if (temperature != null) optionsBuilder.temperature(temperature);
        if (maxTokens != null) optionsBuilder.maxTokens(maxTokens);
        if (topP != null) optionsBuilder.topP(topP);
        if (frequencyPenalty != null) optionsBuilder.frequencyPenalty(frequencyPenalty);
        if (presencePenalty != null) optionsBuilder.presencePenalty(presencePenalty);
        if (stop != null) optionsBuilder.stopSequences(stop);

        return new Prompt(aiMessages, optionsBuilder.build());
    }
}