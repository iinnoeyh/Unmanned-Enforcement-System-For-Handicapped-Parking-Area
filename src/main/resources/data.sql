INSERT INTO article(article_writer, article_title, article_content,created_date) VALUES('컴소1', '문의드립니다', 'content_test1', NOW());
INSERT INTO article(article_writer, article_title, article_content,created_date) VALUES('컴소2', '문의드립니다', 'content_test2', NOW());
INSERT INTO article(article_writer, article_title, article_content,created_date) VALUES('컴소3', '문의드립니다', 'content_test3', NOW());
INSERT INTO article(article_writer, article_title, article_content,created_date) VALUES('컴소4', '문의드립니다', 'content_test3', NOW());

INSERT INTO notice(notice_writer, notice_title, notice_content,created_date) VALUES('관리자','공지드립니다', 'content_test1', NOW());
INSERT INTO notice(notice_writer, notice_title, notice_content,created_date) VALUES('관리자','공지드립니다', 'content_test2', NOW());
INSERT INTO notice(notice_writer, notice_title, notice_content,created_date) VALUES('관리자','공지드립니다', 'content_test3', NOW());
INSERT INTO notice(notice_writer, notice_title, notice_content,created_date) VALUES('관리자','공지드립니다', 'content_test3', NOW());

INSERT INTO customer(name, id, password, phone_number, car_number, front_resident_num, back_resident_num, address) VALUES('홍길동', 'test@test.com', '1234', '01011111111', '123가 4567', '990101', '1111111', '충남 아산시 순천향로22 순천향대학교');
INSERT INTO customer(name, id, password, phone_number, car_number, front_resident_num, back_resident_num, address) VALUES('김철수', 'test2@test.com', '1234', '01066662222', '36나 5658', '980421', '2111111', '충남 아산시 순천향로22 순천향대학교');
INSERT INTO customer(name, id, password, phone_number, car_number, front_resident_num, back_resident_num, address) VALUES('아무개', 'test2@test.com', '1234', '01044445555', '32가 1212', '000515', '4511111', '충남 아산시 순천향로22 순천향대학교');

INSERT INTO manage(name, phone_number, car, address, front_resident_num, back_resident_num, disabled) VALUES('홍길동', '01011111111', '123가 4567', '충남 아산시 순천향로22 순천향대학교', '990101', '1111111', false);
--INSERT INTO manage(name, phone_number, car, address, front_resident_num, back_resident_num, disabled) VALUES('김철수', '01066662222', '123가 4568', '충남 아산시 순천향로22 순천향대학교', '980421', '2111111', true);
--INSERT INTO manage(name, phone_number, car_number, address, front_resident_num, back_resident_num, disabled) VALUES('아무개', '01044445555', '32가 1212', '충남 아산시 순천향로22 순천향대학교', '000515', '4511111', false);

-- 페널티 추가
INSERT INTO penalty(manage_id, body, created_date) VALUES(1, '5분 이상, 불법 주차가 확인되었습니다.', NOW());
INSERT INTO penalty(manage_id, body, created_date) VALUES(1, '5분 이상, 불법 주차가 확인되었습니다.', NOW());
--INSERT INTO penalty(manage_id, body) VALUES(2, '5분 이상, 불법 주차가 확인되었습니다.');