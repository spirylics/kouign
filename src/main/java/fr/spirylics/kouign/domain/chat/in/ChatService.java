package fr.spirylics.kouign.domain.chat.in;

import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.prompt.Prompt;

import java.util.function.Consumer;

public interface ChatService {
    ChatClientResponse completions(final Prompt prompt);
    void stream(final Prompt prompt, Consumer<ChatClientResponse> onNext, Runnable onComplete);
}
