package com.sprata.minipjtbe.service;

import com.sprata.minipjtbe.dto.CommentRequestDto;
import com.sprata.minipjtbe.dto.CommentResponseDto;
import com.sprata.minipjtbe.dto.UserInfoDto;
import com.sprata.minipjtbe.model.Comment;
import com.sprata.minipjtbe.model.User;
import com.sprata.minipjtbe.repository.CommentRepository;
import com.sprata.minipjtbe.repository.UserRepository;
import com.sprata.minipjtbe.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final Validator validator;

    //댓글 조회
    public List<CommentResponseDto> getComment(Long boardId) {
        List<Comment> commentList = commentRepository.findAllByBoardId(boardId);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for(Comment comment : commentList){
            User user= userRepository.findById(comment.getUserId()).orElseThrow(
                    ()->new IllegalArgumentException("유저 없음")
            );
            UserInfoDto userInfoDto = new UserInfoDto();
            userInfoDto.setNickname(user.getNickname());
            userInfoDto.setUsername(user.getUsername());
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment, userInfoDto);
//            List<Recomment> recomments = recommentRepository.findAllByCommentId(comment.getId());
//            commentResponseDto.setRecommentList(recomments);
            commentResponseDtoList.add(commentResponseDto);
        }
//        Iterator<CommentResponseDto> iter = commentResponseDtoList.iterator();
//        while(iter.hasNext()){
//            if(iter.next().getParentId()!=0){
//                iter.remove();
//            }
//        }
//        for(int i =0; i)
        return commentResponseDtoList;
//    }
    }

    //댓글 등록
    @Transactional
    public String registComment(CommentRequestDto commentRequestDto){

        //댓글 조건 1 : 빈 공백
        validator.emptyComment(commentRequestDto);
        //댓글 조건 2 : 로그인 안 됐을 시
//        if(commentRequestDto.getUserId() == null) {
//            throw new IllegalArgumentException("로그인이 필요합니다")
//        }
        //xss필터 써야하는지?
        Comment comment = new Comment(commentRequestDto);
        commentRepository.save(comment);
        return "댓글이 등록되었습니다.";

    }


//    //대댓글 등록
//    @Transactional
//    public void registRecomment(RecommentRequestDto recommentRequestDto, Long commentId){
//
//        //댓글 조건 1 : 빈 공백
//        if(recommentRequestDto.getComment() == null) {
//            throw new IllegalArgumentException("댓글을 입력하세요");
//        }
//        //댓글 조건 2 : 로그인 안 됐을 시
////        if(commentRequestDto.getUserId() == null) {
////            throw new IllegalArgumentException("로그인이 필요합니다")
////        }
//        //xss필터 써야하는지?
//        Recomment recomment = new Recomment(recommentRequestDto);
////        Comment comment = new Comment(commentRequestDto);
//        recomment.setCommentId(commentId);
//        recommentRepository.save(recomment);
//    }

    //댓글 수정
    @Transactional
    public String update(Long commentid, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentid).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않습니다.")
        );
        validator.sameComment(commentRequestDto, comment);
        comment.update(commentRequestDto);
        return "댓글이 수정되었습니다.";
    }

    //댓글 삭제
    @Transactional
    public String deleteComment(Long commentid){
        commentRepository.deleteById(commentid);
        return "댓글이 삭제되었습니다.";
    }

}
