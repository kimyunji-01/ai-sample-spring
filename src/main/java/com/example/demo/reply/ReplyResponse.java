package com.example.demo.reply;

import lombok.Data;

public class ReplyResponse {

    @Data
    public static class DetailDTO {
        private Integer id;
        private String comment;
        private Integer boardId;
        private Integer userId;

        public DetailDTO(Reply reply) {
            this.id = reply.getId();
            this.comment = reply.getComment();
            this.boardId = reply.getBoard().getId();
            this.userId = reply.getUser().getId();
        }
    }
}
