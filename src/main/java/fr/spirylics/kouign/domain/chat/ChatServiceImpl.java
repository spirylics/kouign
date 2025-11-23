package fr.spirylics.kouign.domain.chat;

import fr.spirylics.kouign.domain.chat.in.ChatService;
import fr.spirylics.kouign.domain.chat.out.LlmChatClient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.prompt.Prompt;

@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    @Getter
    final LlmChatClient llmChatClient;

    @Override
    public ChatClientResponse completions(Prompt prompt) {
        return getLlmChatClient().call(prompt).chatClientResponse();
    }
}
