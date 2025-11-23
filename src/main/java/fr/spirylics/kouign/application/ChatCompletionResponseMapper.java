package fr.spirylics.kouign.application;

import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Component
public class ChatCompletionResponseMapper {

    public ChatCompletionResponse map(ChatClientResponse clientResponse) {
        var chatResponse = clientResponse.chatResponse();
        var result = chatResponse.getResult();

        var message = new ChatCompletionResponse.Message(
                "assistant",
                result.getOutput().getText(),
                null,
                List.of()
        );

        var choice = new ChatCompletionResponse.Choice(
                0,
                message,
                null,
                result.getMetadata().getFinishReason()
        );

        var metadata = chatResponse.getMetadata();
        var usage = mapUsage(metadata.getUsage());

        return new ChatCompletionResponse(
                generateId(),
                "chat.completion",
                Instant.now().getEpochSecond(),
                metadata.getModel(),
                List.of(choice),
                usage,
                null,
                null
        );
    }

    private ChatCompletionResponse.Usage mapUsage(Usage aiUsage) {
        if (aiUsage == null) {
            return new ChatCompletionResponse.Usage(
                    0, 0, 0, null, null
            );
        }

        var promptTokensDetails = new ChatCompletionResponse.PromptTokensDetails(0, 0);
        var completionTokensDetails = new ChatCompletionResponse.CompletionTokensDetails(0, 0, 0, 0);

        return new ChatCompletionResponse.Usage(
                Math.toIntExact(aiUsage.getPromptTokens()),
                Math.toIntExact(aiUsage.getCompletionTokens()),
                Math.toIntExact(aiUsage.getTotalTokens()),
                promptTokensDetails,
                completionTokensDetails
        );
    }

    private String generateId() {
        return "chatcmpl-" + UUID.randomUUID().toString().replace("-", "").substring(0, 29);
    }
}
