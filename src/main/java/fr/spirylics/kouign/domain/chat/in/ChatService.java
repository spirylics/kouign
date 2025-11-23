package fr.spirylics.kouign.domain.chat.in;

import org.springframework.ai.chat.client.ChatClient.CallResponseSpec;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.prompt.Prompt;

public interface ChatService {
    ChatClientResponse completions(final Prompt prompt);
}
