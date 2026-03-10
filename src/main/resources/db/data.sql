-- 유저 더미 데이터
INSERT INTO user_tb (username, password, zipcode, road_address, detail_address, created_at) VALUES ('ssar', '1234', '06043', '서울특별시 강남구 도산대로 101', '3층', NOW());
INSERT INTO user_tb (username, password, zipcode, road_address, detail_address, created_at) VALUES ('cos', '1234', '05123', '부산광역시 해운대구 우동 102', '101호', NOW());

-- 게시글 더미 데이터
INSERT INTO board_tb (title, content, user_id, created_at) VALUES ('첫 번째 게시글', '안녕하세요. ssar의 첫 번째 글입니다.', 1, NOW());
INSERT INTO board_tb (title, content, user_id, created_at) VALUES ('두 번째 게시글', '안녕하세요. ssar의 두 번째 글입니다.', 1, NOW());
INSERT INTO board_tb (title, content, user_id, created_at) VALUES ('세 번째 게시글', '안녕하세요. cos의 첫 번째 글입니다.', 2, NOW());