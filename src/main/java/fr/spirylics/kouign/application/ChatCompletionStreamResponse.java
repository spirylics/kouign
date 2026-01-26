package fr.spirylics.kouign.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionChunk;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.client.ChatClientResponse;

@Builder
public record ChatCompletionStreamResponse(String id, String object, long created, String model, List<Choice> choices,
                                           @Nullable Usage usage) {

    public static ChatCompletionStreamResponse from(ChatCompletionChunk clientResponse, String id)
    {
        var chatResponse = clientResponse.chatResponse();
        if (chatResponse == null) {
            throw new IllegalArgumentException("chatResponse must not be null");
        }
        var result = chatResponse.getResult();
        if (result == null) {
            throw new IllegalArgumentException("result must not be null");
        }

        var delta = new Delta(result.getOutput().getText(), "assistant");

        var choice = new Choice(0, delta, result.getMetadata().getFinishReason());

        var metadata = chatResponse.getMetadata();
        var usage = mapUsage(metadata.getUsage());

        return ChatCompletionStreamResponse.builder().id(id).object("chat.completion.chunk")
                .created(Instant.now().getEpochSecond()).model(metadata.getModel()).choices(List.of(choice))
                .usage(usage).build();
    }

    private static Usage mapUsage(org.springframework.ai.chat.metadata.Usage aiUsage)
    {
        return new Usage(Math.toIntExact(aiUsage.getPromptTokens()), Math.toIntExact(aiUsage.getCompletionTokens()),
                Math.toIntExact(aiUsage.getTotalTokens()));
    }

    public static String generateId()
    {
        return "chatcmpl-" + UUID.randomUUID().toString().replace("-", "").substring(0, 29);
    }

    public record Choice(int index, Delta delta, @JsonProperty("finish_reason") @Nullable String finishReason) {
    }

    public record Delta(@Nullable String content, @Nullable String role) {
    }

    public record Usage(@JsonProperty("prompt_tokens") int promptTokens,
                        @JsonProperty("completion_tokens") int completionTokens,
                        @JsonProperty("total_tokens") int totalTokens) {
    }
}
