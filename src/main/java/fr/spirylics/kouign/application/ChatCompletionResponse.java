package fr.spirylics.kouign.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.client.ChatClientResponse;

@Builder
public record ChatCompletionResponse(String id, String object, long created, String model, List<Choice> choices,
                                     Usage usage) {

    public static ChatCompletionResponse from(ChatClientResponse clientResponse)
    {
        var chatResponse = clientResponse.chatResponse();
        if (chatResponse == null) {
            throw new IllegalArgumentException("chatResponse must not be null");
        }

        var result = chatResponse.getResult();
        if (result == null) {
            throw new IllegalArgumentException("result must not be null");
        }
        if (result.getMetadata().getFinishReason() == null) {
            throw new IllegalArgumentException("finish reason must not be null");
        }


        var message = new Message("assistant", String.valueOf(result.getOutput().getText()));

        var choice = new Choice(0, message, null, result.getMetadata().getFinishReason());

        var metadata = chatResponse.getMetadata();
        var usage = mapUsage(metadata.getUsage());

        return ChatCompletionResponse.builder().id(generateId()).object("chat.completion")
                .created(Instant.now().getEpochSecond()).model(metadata.getModel()).choices(List.of(choice))
                .usage(usage).build();
    }

    private static Usage mapUsage(org.springframework.ai.chat.metadata.Usage aiUsage)
    {
        return new Usage(Math.toIntExact(aiUsage.getPromptTokens()), Math.toIntExact(aiUsage.getCompletionTokens()),
                Math.toIntExact(aiUsage.getTotalTokens()));
    }

    private static String generateId()
    {
        return "chatcmpl-" + UUID.randomUUID().toString().replace("-", "").substring(0, 29);
    }

    public record Choice(int index, Message message, @Nullable Object logprobs,
                         @JsonProperty("finish_reason") String finishReason) {
    }

    public record Message(String role, String content) {
    }

    public record Usage(@JsonProperty("prompt_tokens") int promptTokens,
                        @JsonProperty("completion_tokens") int completionTokens,
                        @JsonProperty("total_tokens") int totalTokens) {
    }
}
