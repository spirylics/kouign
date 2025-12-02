package fr.spirylics.kouign.infrastructure;

import fr.spirylics.kouign.domain.chat.out.LlmChatClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClient.CallResponseSpec;
import org.springframework.ai.chat.client.ChatClient.StreamResponseSpec;
import org.springframework.ai.chat.prompt.Prompt;

public record OpenAiLlmChatClient(ChatClient chatClient) implements LlmChatClient {
    @Override
    public CallResponseSpec call(final Prompt prompt)
    {
        return chatClient().prompt(prompt).call();
    }

    @Override
    public StreamResponseSpec stream(final Prompt prompt)
    {
        return chatClient().prompt(prompt).stream();
    }
}
