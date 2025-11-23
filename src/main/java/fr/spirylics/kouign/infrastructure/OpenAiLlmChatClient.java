package fr.spirylics.kouign.infrastructure;

import fr.spirylics.kouign.domain.chat.out.LlmChatClient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClient.CallResponseSpec;
import org.springframework.ai.chat.client.ChatClient.StreamResponseSpec;
import org.springframework.ai.chat.prompt.Prompt;

@RequiredArgsConstructor
public class OpenAiLlmChatClient implements LlmChatClient {
    @Getter
    final ChatClient chatClient;

    @Override
    public CallResponseSpec call(final Prompt prompt) {
        return getChatClient().prompt(prompt).call();
    }

    @Override
    public StreamResponseSpec stream(final Prompt prompt) {
        return getChatClient().prompt(prompt).stream();
    }
}
