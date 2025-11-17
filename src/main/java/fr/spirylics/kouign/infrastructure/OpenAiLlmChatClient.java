package fr.spirylics.kouign.infrastructure;

import fr.spirylics.kouign.domain.chat.out.LlmChatClient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClient.CallResponseSpec;
import org.springframework.ai.chat.prompt.Prompt;

@NullMarked
@RequiredArgsConstructor
public class OpenAiLlmChatClient implements LlmChatClient {
    @Getter
    final ChatClient chatClient;

    @Override
    public CallResponseSpec call(final Prompt prompt) {
        return getChatClient().prompt(prompt).call();
    }
}
