package com.example.demo.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class Login {
        private String username;
        private String password;
    }

    @Data
    public static class Join {
        private String username;
        private String password;
        private String email;
        private String zipcode;
        private String roadAddress;
        private String detailAddress;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .zipcode(zipcode)
                    .roadAddress(roadAddress)
                    .detailAddress(detailAddress)
                    .build();
        }
    }
}
