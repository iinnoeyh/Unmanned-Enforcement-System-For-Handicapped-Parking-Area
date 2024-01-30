package com.example.systemmanage.dto;

import com.example.systemmanage.entity.Penalty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor  // 매개변수가 아예 없는 기본 생성자 자동 생성
@Getter // 각 필드 값을 조회할 수 있는 getter 메서드 자동 생성
@ToString   // 모든 필드를 출력할 수 있는 toString 메서드 자동 생성
public class PenaltyDto {
    private Long id;    // 페널티 id
    private Long manageId;  // 페널티 부모 id
    private String body;    // 본문

    public static PenaltyDto createPenaltyDto(Penalty penalty) {
        return new PenaltyDto(
                penalty.getId(),    // 페널티 엔티티의 id
                penalty.getManage().getId(),    // 페널티 엔티티가 속한 부모 게시글 id
                penalty.getBody()   // 페널티 엔티티의 body
        );
    }
}
