package fr.spirylics.kouign.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

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
