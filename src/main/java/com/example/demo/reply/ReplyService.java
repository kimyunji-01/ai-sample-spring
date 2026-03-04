package com.example.demo.reply;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.board.Board;
import com.example.demo.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    @Transactional(readOnly = true)
    public ReplyResponse.DetailDTO findById(Integer id) {
        Reply reply = replyRepository.findById(id).orElseThrow(() -> new RuntimeException("Reply not found"));
        return new ReplyResponse.DetailDTO(reply);
    }

    @Transactional
    public ReplyResponse.DetailDTO save(ReplyRequest.SaveDTO saveDTO) {
        // Here we'd normally fetch User and Board from their repositories
        // For simplicity and since repositories are not injected yet, I'll use placeholders or just focus on the structure.
        // In a real scenario, this service would also need UserRepository and BoardRepository.
        
        User user = User.builder().id(saveDTO.getUserId()).build();
        Board board = Board.builder().id(saveDTO.getBoardId()).build();

        Reply reply = Reply.builder()
                .comment(saveDTO.getComment())
                .user(user)
                .board(board)
                .build();
        
        replyRepository.save(reply);
        return new ReplyResponse.DetailDTO(reply);
    }
}
