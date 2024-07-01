package com.seohee.board.entity;

import com.seohee.board.dto.BoardRequestDto;
import com.seohee.board.dto.UpdateBoardRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Board {
    //- `제목`, `작성자명`, `비밀번호`, `작성 내용`, `작성일`을 저장할 수 있습니다.
    //- 저장된 게시글의 정보를 반환 받아 확인할 수 있습니다.
    //    - 반환 받은 게시글의 정보에 `비밀번호`는 제외 되어있습니다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Board(String title, String username, String password, String content) {
        this.title = title;
        this.username = username;
        this.password = password;
        this.content = content;
    }

    public Board(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.username = boardRequestDto.getUsername();
        this.password = boardRequestDto.getPassword();
        this.content = boardRequestDto.getContent();
    }

    public void update(UpdateBoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.content = requestDto.getContent();
    }
}
