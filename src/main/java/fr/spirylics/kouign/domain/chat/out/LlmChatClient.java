package fr.spirylics.kouign.domain.chat.out;

import com.openai.core.http.StreamResponse;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionChunk;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

public interface LlmChatClient {
    ChatCompletion completions(ChatCompletionCreateParams params);

    StreamResponse<ChatCompletionChunk> completionsStream(ChatCompletionCreateParams params);
}
