package fr.spirylics.kouign.domain.chat.out;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;

public interface LlmChatClient {
    ChatClient.CallResponseSpec call(Prompt prompt);
    ChatClient.StreamResponseSpec stream(Prompt prompt);
}
