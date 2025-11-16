package fr.spirylics.kouign.application;

import fr.spirylics.kouign.domain.chat.in.ChatService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/{version}/chat/completions")
@RequiredArgsConstructor
public class ChatController {

    @Getter
    final ChatService chatService;

    @PostMapping(version = "1.0")
    public void completions() {
        throw new UnsupportedOperationException();
    }
}
