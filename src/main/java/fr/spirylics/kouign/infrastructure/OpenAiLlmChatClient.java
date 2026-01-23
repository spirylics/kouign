package fr.spirylics.kouign.infrastructure;

import com.openai.client.OpenAIClient;
import com.openai.core.http.StreamResponse;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionChunk;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import fr.spirylics.kouign.domain.chat.out.LlmChatClient;

public record OpenAiLlmChatClient(OpenAIClient chatClient) implements LlmChatClient {
    @Override
    public ChatCompletion call(final ChatCompletionCreateParams params)
    {
        return chatClient.chat().completions().create(params);
    }

    @Override
    public StreamResponse<ChatCompletionChunk> stream(final ChatCompletionCreateParams params)
    {
        return chatClient.chat().completions().createStreaming(params);
    }
}
