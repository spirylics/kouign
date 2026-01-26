package fr.spirylics.kouign.domain.chat.in;

import java.util.function.Consumer;

import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionChunk;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.prompt.Prompt;

public interface ChatService {
    ChatCompletion completions(final ChatCompletionCreateParams request);

    void completions(final ChatCompletionCreateParams request, Consumer<ChatCompletionChunk> read);
}
