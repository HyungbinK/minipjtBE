package com.sprata.minipjtbe.utils;

import com.sprata.minipjtbe.dto.BoardResponseDto;
import com.sprata.minipjtbe.dto.CommentRequestDto;
import com.sprata.minipjtbe.dto.IdCheckRequestDto;
import com.sprata.minipjtbe.dto.SignupRequestDto;
import com.sprata.minipjtbe.model.Comment;
import com.sprata.minipjtbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class Validator {
    private final UserRepository userRepository;

    @Autowired
    public Validator(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public void signupValidate(SignupRequestDto signupRequestDto) throws IllegalArgumentException {
        if (userRepository.findByUsername(signupRequestDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("중복된 아이디가 존재합니다.");
        }

        if (userRepository.findByNickname(signupRequestDto.getNickname()).isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임이 존재합니다.");
        }

        if(!signupRequestDto.getPassword().equals(signupRequestDto.getPasswordCheck())){
            throw new IllegalArgumentException("비밀번호 확인이 일치하지 않습니다.");
        }

        if(!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", signupRequestDto.getUsername())){
            throw new IllegalArgumentException("이메일 형식의 ID를 입력 해주세요.");
        }

        if(signupRequestDto.getPassword().length()<4){
            throw new IllegalArgumentException("비밀번호는 최소 4자 이상이여야 합니다.");
        }

        if(signupRequestDto.getPassword().contains(signupRequestDto.getUsername())){
            throw new IllegalArgumentException("ID가 포함되지 않은 비밀번호를 사용해주세요.");
        }
    }


    public void idCheck(IdCheckRequestDto idCheckRequestDto) throws IllegalArgumentException {
        if (userRepository.findByUsername(idCheckRequestDto.getUserEmail()).isPresent()) {
            throw new IllegalArgumentException("중복된 아이디가 존재합니다.");
        }
    }

    public Page<BoardResponseDto> overPages(List<BoardResponseDto> boardsList, int start, int end, Pageable pageable, int page) {
        Page<BoardResponseDto> pages = new PageImpl<>(boardsList.subList(start, end), pageable, boardsList.size());
        if(page > pages.getTotalPages()){
            throw new IllegalArgumentException("요청할 수 없는 페이지 입니다.");
        }
        return pages;
    }

    public void emptyComment(CommentRequestDto commentRequestDto) {
        if(commentRequestDto.getComment() == null) {
            throw new IllegalArgumentException("댓글을 입력하세요");
        }
    }
    public void alreadyDelete(boolean favorite, String s) {
        if(favorite){
            throw new IllegalArgumentException(s);
        }
    }


    public void sameContent(boolean board, String s) {
        if(board){
            throw new IllegalArgumentException(s);
        }
    }

    public void sameComment(CommentRequestDto commentRequestDto, Comment comment) {
        if(comment.getComment().equals(commentRequestDto.getComment())){
            throw new IllegalArgumentException("수정된 내용이 없습니다.");
        }
    }



}