package fr.spirylics.kouign.domain.chat;

import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionChunk;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import fr.spirylics.kouign.domain.chat.in.ChatService;
import fr.spirylics.kouign.domain.chat.out.LlmChatClient;
import fr.spirylics.kouign.domain.model.Model;
import fr.spirylics.kouign.domain.model.in.ModelService;
import java.util.function.Consumer;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openaisdk.OpenAiSdkChatOptions;

public record ChatServiceImpl(LlmChatClient llmChatClient, ModelService modelService) implements ChatService {
    @Override
    public ChatCompletion completions(final ChatCompletionCreateParams request)
    {
        return llmChatClient().completions(request);
    }

    @Override
    public void completions(final ChatCompletionCreateParams params, final Consumer<ChatCompletionChunk> read)
    {
        try (var response = llmChatClient().completionsStream(enhance(params))){
            response.stream().forEach(read);
        }
    }

    ChatCompletionCreateParams enhance(final ChatCompletionCreateParams params)
    {
        final var promptBuilder = prompt.mutate();
        final ChatOptions opts;
        final String model;
        final var optsBuilder = OpenAiSdkChatOptions.builder();
        if ((opts = prompt.getOptions()) != null) {
            if ((model = opts.getModel()) != null) {
                modelService.findById(model).findFirst().map(Model::baseModel).ifPresent(optsBuilder::model);
            }
            optsBuilder.frequencyPenalty(opts.getFrequencyPenalty()).maxTokens(opts.getMaxTokens())
                    .topP(opts.getTopP());
        }

        return promptBuilder.chatOptions(optsBuilder.build()).build();
    }
}
