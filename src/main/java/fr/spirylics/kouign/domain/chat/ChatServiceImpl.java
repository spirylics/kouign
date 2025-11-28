package fr.spirylics.kouign.domain.chat;

import fr.spirylics.kouign.domain.chat.in.ChatService;
import fr.spirylics.kouign.domain.chat.out.LlmChatClient;
import java.util.function.Consumer;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.prompt.Prompt;

public record ChatServiceImpl(LlmChatClient llmChatClient) implements ChatService {
    @Override
    public ChatClientResponse completions(Prompt prompt)
    {
        return llmChatClient().call(prompt).chatClientResponse();
    }

    @Override
    public void stream(Prompt prompt, Consumer<ChatClientResponse> onNext, Runnable onComplete)
    {
        llmChatClient().stream(prompt).chatClientResponse().doOnNext(onNext).doOnComplete(onComplete).subscribe();
    }
}
