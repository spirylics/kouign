package fr.spirylics.kouign.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.client.ChatClientResponse;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Builder
public record ChatCompletionResponse(
        String id,
        String object,
        long created,
        String model,
        List<Choice> choices,
        Usage usage,
        @JsonProperty("service_tier") @Nullable String serviceTier,
        @JsonProperty("system_fingerprint") @Nullable String systemFingerprint
) {

    public static ChatCompletionResponse from(ChatClientResponse clientResponse) {
        var chatResponse = clientResponse.chatResponse();
        var result = chatResponse.getResult();

        var message = new Message(
                "assistant",
                result.getOutput().getText(),
                null,
                List.of()
        );

        var choice = new Choice(
                0,
                message,
                null,
                result.getMetadata().getFinishReason()
        );

        var metadata = chatResponse.getMetadata();
        var usage = mapUsage(metadata.getUsage());

        return ChatCompletionResponse.builder()
                .id(generateId())
                .object("chat.completion")
                .created(Instant.now().getEpochSecond())
                .model(metadata.getModel())
                .choices(List.of(choice))
                .usage(usage)
                .serviceTier(null)
                .systemFingerprint(null)
                .build();
    }

    private static Usage mapUsage(org.springframework.ai.chat.metadata.Usage aiUsage) {
        if (aiUsage == null) {
            return new Usage(0, 0, 0, null, null);
        }

        var promptTokensDetails = new PromptTokensDetails(0, 0);
        var completionTokensDetails = new CompletionTokensDetails(0, 0, 0, 0);

        return new Usage(
                Math.toIntExact(aiUsage.getPromptTokens()),
                Math.toIntExact(aiUsage.getCompletionTokens()),
                Math.toIntExact(aiUsage.getTotalTokens()),
                promptTokensDetails,
                completionTokensDetails
        );
    }

    private static String generateId() {
        return "chatcmpl-" + UUID.randomUUID().toString().replace("-", "").substring(0, 29);
    }
    public record Choice(
            int index,
            Message message,
            @Nullable Object logprobs,
            @JsonProperty("finish_reason") String finishReason
    ) {
    }

    public record Message(
            String role,
            String content,
            @Nullable String refusal,
            @Nullable List<Object> annotations
    ) {
    }

    public record Usage(
            @JsonProperty("prompt_tokens") int promptTokens,
            @JsonProperty("completion_tokens") int completionTokens,
            @JsonProperty("total_tokens") int totalTokens,
            @JsonProperty("prompt_tokens_details") @Nullable PromptTokensDetails promptTokensDetails,
            @JsonProperty("completion_tokens_details") @Nullable CompletionTokensDetails completionTokensDetails
    ) {
    }

    public record PromptTokensDetails(
            @JsonProperty("cached_tokens") int cachedTokens,
            @JsonProperty("audio_tokens") int audioTokens
    ) {
    }

    public record CompletionTokensDetails(
            @JsonProperty("reasoning_tokens") int reasoningTokens,
            @JsonProperty("audio_tokens") int audioTokens,
            @JsonProperty("accepted_prediction_tokens") int acceptedPredictionTokens,
            @JsonProperty("rejected_prediction_tokens") int rejectedPredictionTokens
    ) {
    }
}
