//package com.sprata.minipjtbe.model;
//
//import com.sprata.minipjtbe.dto.RecommentRequestDto;
//import com.sprata.minipjtbe.repository.RecommentRepository;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Getter
//@NoArgsConstructor
//@Entity
//@Setter
//public class Recomment {
//
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    private Long id;
//
//    @Column(nullable = false)
//    private Long userId;
//
//    @Column(nullable = false)
//    private Long boardId;
//
//    @Column(nullable = false)
//    private String comment;
//
//    @Column(nullable = false)
//    private Long parentId;
//
//    public Recomment(RecommentRequestDto recommentRequestDto){
//        this.userId= recommentRequestDto.getUserId();
//        this.boardId = recommentRequestDto.getBoardId();
//        this.comment =recommentRequestDto.getComment();
//    }
//
//}
