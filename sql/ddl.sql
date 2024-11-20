drop table if exists member CASCADE;
create table member
--(
--    member_id bigint generated by default as identity,
--    member_type boolean,
--    member_name varchar(255),
--    member_password varchar(255),
--    member_email varchar(255),
--    member_created_at date
--    primary key (member_id)
--);

-- << mysql용 sql >>

-- DB 생성, 권한 설정
CREATE DATABASE space_reservation;

CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON space_reservation.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

CREATE TABLE member
(
    member_id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    member_type BOOLEAN,
    member_name VARCHAR(255),
    member_password VARCHAR(255),
    member_email VARCHAR(255),
    member_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

);

CREATE TABLE room -- space는 예약어로 사용될 때가 있기 때문에 room으로 하는 것이 안전함
(
    room_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    room_area VARCHAR(30),
    room_type TINYINT, -- byte로 매핑
    room_name VARCHAR(255),
    room_capacity TINYINT DEFAULT 1,
    room_operating_start TIMESTAMP,　-- java.time.LocalDateTime로 매핑
    room_operating_end TIMESTAMP, -- java.time.LocalDateTime로 매핑
    room_status BOOLEAN
);

CREATE TABLE reservation
(
    reservation_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    room_id BIGINT,
    member_id BIGINT,
    reservation_date DATETIME,
    reservation_start TIMESTAMP,
    reservation_end TIMESTAMP
    reservation_used_start TIMESTAMP,
    reservation_used_end TIMESTAMP
    FOREIGN KEY (room_id) REFERENCES room(room_id),
    FOREIGN KEY (member_id) REFERENCES member(member_id)
);

CREATE TABLE review
(
    review_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    reservation_id BIGINT,
    review_created_at DATETIME,
    review_article TEXT,
    FOREIGN KEY (reservation_id) REFERENCES reservation(reservation_id)
);
