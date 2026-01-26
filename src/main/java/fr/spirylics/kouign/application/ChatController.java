package fr.spirylics.kouign.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.spirylics.kouign.domain.chat.in.ChatService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/{version}/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @PostMapping(path = "/completions", version = "1.0")
    public Object completions(@RequestBody final ChatCompletionRequest request)
    {
        if (Boolean.TRUE.equals(request.stream())) {
            return streamCompletions(request);
        }
        var clientResponse = chatService.completions(request.toParams());
        return ChatCompletionResponse.from(clientResponse);
    }

    private SseEmitter streamCompletions(ChatCompletionRequest request)
    {
        var emitter = new SseEmitter();
        var streamId = ChatCompletionStreamResponse.generateId();

        chatService.completions(request.toParams(), clientResponse -> {
            try {
                var streamResponse = ChatCompletionStreamResponse.from(clientResponse, streamId);
                var json = objectMapper.writeValueAsString(streamResponse);
                emitter.send(SseEmitter.event().data(json, MediaType.APPLICATION_JSON).name("message"));
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        });

        try {
            emitter.send(SseEmitter.event().data("[DONE]").name("message"));
            emitter.complete();
        } catch (IOException e) {
            emitter.completeWithError(e);
        }

        return emitter;
    }
}
