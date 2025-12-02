package fr.spirylics.kouign.domain.chat;

import fr.spirylics.kouign.domain.chat.in.ChatService;
import fr.spirylics.kouign.domain.chat.out.LlmChatClient;
import fr.spirylics.kouign.domain.model.Model;
import fr.spirylics.kouign.domain.model.in.ModelService;
import java.util.function.Consumer;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;

public record ChatServiceImpl(LlmChatClient llmChatClient, ModelService modelService) implements ChatService {
    @Override
    public ChatClientResponse completions(final Prompt prompt)
    {
        return llmChatClient().call(enhance(prompt)).chatClientResponse();
    }

    @Override
    public void stream(final Prompt prompt, final Consumer<ChatClientResponse> onNext, final Runnable onComplete)
    {
        llmChatClient().stream(enhance(prompt)).chatClientResponse().doOnNext(onNext).doOnComplete(onComplete)
                .subscribe();
    }

    Prompt enhance(final Prompt prompt)
    {
        final var promptBuilder = prompt.mutate();
        final ChatOptions opts;
        final String model;
        final var optsBuilder = OpenAiChatOptions.builder();
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
