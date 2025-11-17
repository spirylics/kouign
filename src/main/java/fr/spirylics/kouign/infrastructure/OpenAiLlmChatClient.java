package fr.spirylics.kouign.infrastructure;

import fr.spirylics.kouign.domain.chat.out.LlmChatClient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClient.CallResponseSpec;
import org.springframework.ai.chat.prompt.Prompt;

@NullMarked
@RequiredArgsConstructor
public class OpenAiLlmChatClient implements LlmChatClient {
    @Getter
    @Nullable
    final ChatClient chatClient;

    @Override
    public CallResponseSpec call(final Prompt prompt) {
        throw new UnsupportedOperationException("OpenAI LLM Chat Client is not implemented yet");
    }
}
