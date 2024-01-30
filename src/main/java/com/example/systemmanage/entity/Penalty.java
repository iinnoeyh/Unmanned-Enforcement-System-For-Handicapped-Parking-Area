package com.example.systemmanage.entity;

import com.example.systemmanage.dto.PenaltyDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor  // 매개변수가 아예 없는 기본 생성자 자동 생성
@ToString   // 모든 필드를 출력할 수 있는 toString 메서드 자동 생성
@Entity // 해당 클래스가 엔티티임을 선언, 클래스 필드를 바탕으로 DB에 테이블 생성
@Getter // 각 필드 값을 조회할 수 있는 getter 메서드 자동 생성
public class Penalty extends BaseTimeDetail{
    @Id // 대표키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 자동으로 1씩 증가
    private Long id;    // 대표키
    @ManyToOne(cascade = CascadeType.REMOVE)  // Penalty 엔티티와 Manage 엔티티를 다대일 관계로 설정
    @JoinColumn(name = "manage_id") // 외래키 생성, Manage 엔티티의 기본키(id)와 매핑
    private Manage manage;  // 해당 페널티의 당사자
    @Column
    private String body;    // 본문


    public static Penalty createPemalty(PenaltyDto dto, Manage manage) {
        // 예외 발생
        if (dto.getId() != null)
            throw new IllegalArgumentException("페널티 생성 실패! 페널티의 id가 없어야 합니다.");
        if (dto.getManageId() != manage.getId())
            throw new IllegalArgumentException("페널티 생성 실패! 유저의 id가 잘못됐습니다.");
        // 엔티티 생성 및 반환
        return new Penalty(
                dto.getId(),    // 페널티 아이디
                manage, // 부모 유저 데이터
                dto.getBody()   // 페널티 본문
        );
    }

    public void patch(PenaltyDto dto) {
        // 예외 발생
        if (this.id != dto.getId())
            throw new IllegalArgumentException("페널티 수정 실패! 잘못된 id가 입력됐습니다");
        // 객체 갱신
        if (dto.getBody() != null)  // 수정할 본문 데이터가 있다면
            this.body = dto.getBody();  // 내용 반영
    }
}
