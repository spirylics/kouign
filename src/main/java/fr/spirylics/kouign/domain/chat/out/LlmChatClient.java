package fr.spirylics.kouign.domain.chat.out;

import com.openai.core.http.StreamResponse;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionChunk;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;

public interface LlmChatClient {
    ChatCompletion call(ChatCompletionCreateParams params);

    StreamResponse<ChatCompletionChunk> stream(ChatCompletionCreateParams params);
}
