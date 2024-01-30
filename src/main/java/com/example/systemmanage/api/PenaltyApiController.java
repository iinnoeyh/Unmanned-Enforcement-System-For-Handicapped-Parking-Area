package com.example.systemmanage.api;

import com.example.systemmanage.dto.PenaltyDto;
import com.example.systemmanage.repository.PenaltyRepository;
import com.example.systemmanage.service.PenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PenaltyApiController {
    @Autowired
    private PenaltyService penaltyService;
    // 1. 페널티 조회
    @GetMapping("/api/manage/{manageId}/penalties")
    public ResponseEntity<List<PenaltyDto>> penalties(@PathVariable Long manageId){
        // 서비스에 위임
        List<PenaltyDto> dtos = penaltyService.penalties(manageId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    // 2. 페널티 생성
    @PostMapping("/api/manage/{manageId}/penalties")
    public ResponseEntity<PenaltyDto> create(@PathVariable Long manageId, @RequestBody PenaltyDto dto){
        // 서비스에 위임
        PenaltyDto createdDto = penaltyService.create(manageId, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
    // 3. 페널티 수정
    @PatchMapping("/api/penalties/{id}")
    public ResponseEntity<PenaltyDto> update(@PathVariable Long id, @RequestBody PenaltyDto dto){
        // 서비스에 위임
        PenaltyDto updatedDto = penaltyService.update(id, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }
    // 4. 페널티 삭제
//    @DeleteMapping("/api/penalties/{id}")
//    public ResponseEntity<PenaltyDto> delete(@PathVariable Long id){
//        // 서비스에 위임
//        PenaltyDto deletedDto = penaltyService.delete(id);
//        // 결과 응답
//        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
//    }
    @DeleteMapping("/api/penalties/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try {
            // 서비스에 위임
            PenaltyDto deletedDto = penaltyService.delete(id);

            // 삭제된 페널티에 대한 정보를 포함한 응답
            return ResponseEntity.status(HttpStatus.OK).body("페널티가 성공적으로 삭제되었습니다. ID: " + id);
        } catch (Exception e) {
            // 예외 발생 시 예외에 대한 응답
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("페널티 삭제 중 오류가 발생했습니다.");
        }
    }

}
