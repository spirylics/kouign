package fr.spirylics.kouign.domain.chat.in;

import org.springframework.ai.chat.client.ChatClient.CallResponseSpec;
import org.springframework.ai.chat.prompt.Prompt;

public interface ChatService {
    CallResponseSpec completions(final Prompt prompt);
}
