package fr.spirylics.kouign.application;

import fr.spirylics.kouign.domain.chat.in.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/{version}/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping(path = "/completions", version = "1.0")
    public Object completions(@RequestBody final Prompt prompt) {
        return chatService.completions(prompt);
    }

//    private SseEmitter completionsStream(final ChatRequest request) {
//        final var emitter = new SseEmitter();
//        final var objectMapper = new ObjectMapper();
//
//        chatService.completionsStream(
//            request,
//            chunk -> {
//                try {
//                    final var json = objectMapper.writeValueAsString(chunk);
//                    emitter.send(SseEmitter.event()
//                        .data("data: " + json + "\n\n"));
//                } catch (Exception e) {
//                    emitter.completeWithError(e);
//                }
//            },
//            () -> {
//                try {
//                    emitter.send(SseEmitter.event()
//                        .data("data: [DONE]\n\n"));
//                    emitter.complete();
//                } catch (Exception e) {
//                    emitter.completeWithError(e);
//                }
//            }
//        );
//
//        return emitter;
//    }
}
