package com.sprata.minipjtbe.service;

import com.sprata.minipjtbe.dto.BoardRequestDto;
import com.sprata.minipjtbe.dto.BoardResponseDto;
import com.sprata.minipjtbe.dto.UserInfoDto;
import com.sprata.minipjtbe.model.Board;
import com.sprata.minipjtbe.model.Favorite;
import com.sprata.minipjtbe.model.User;
import com.sprata.minipjtbe.repository.*;
import com.sprata.minipjtbe.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final Validator validator;
    private final CommentRepository commentRepository;
    private final ImageRepository imageRepository;

    public String registBoard(BoardRequestDto boardRequestDto, MultipartFile file){
        validator.sameContent(boardRequestDto.getContent() == null, "내용을 입력하세요");
        Board board = new Board(boardRequestDto);
        boardRepository.save(board);
        Long postId = boardRepository.findBoardByContent(boardRequestDto.getContent()).getId();

        return "등록 성공하였습니다.";
    }

    //모든 게시글 보기
    public Page<BoardResponseDto> showAllBoard(int page, Long userId){
        List<Board> boardList= boardRepository.findAll();
        Pageable pageable = getPageable(page);
        List<BoardResponseDto> boardsList = new ArrayList<>();
        forboardList(boardList, boardsList,userId);
        final int start =(int)pageable.getOffset();
        final int end = Math.min((start + 16),boardList.size());
        return validator.overPages(boardsList, start, end, pageable, page);
    }

    //게시글 업데이트
    public String updateBoard(Long id, BoardRequestDto boardRequestDto){
        validator.sameContent(boardRequestDto.getContent() == null, "내용을 입력하세요");

        Board board = boardRepository.findBoardById(id);
        validator.sameContent(board.getContent().equals(boardRequestDto.getContent()), "수정된 내용이 없습니다.");
        board.update(boardRequestDto);
        boardRepository.save(board);
        return "수정 완료하였습니다";
    }

    //게시글 삭제
    public String deleteBoard(Long id){
        validator.sameContent(boardRepository.countAllById(id) == 0, "이미 없는 게시물입니다");
        boardRepository.deleteById(id);
        favoriteRepository.deleteAllByBoardId(id);
        commentRepository.deleteAllByBoardId(id);
        return "삭제 완료하였습니다";
    }


    //내가 작성한 보드 보기
    public Page<BoardResponseDto> showMyBoard(Long userId, int page){
        Pageable pageable = getPageable(page);
        List<Board> boardList= boardRepository.findAllByUserId(userId);
        List<BoardResponseDto> boardsList = new ArrayList<>();
        forboardList(boardList, boardsList,userId);
        final int start =(int)pageable.getOffset();
        final int end = Math.min((start + 16),boardsList.size());
        return validator.overPages(boardsList, start, end, pageable, page);
    }

    //내가 좋아요한 게시글 조회
    public Page<BoardResponseDto> showFavoriteBoard(Long userId, int page){
        Pageable pageable = getPageable(page);
        List<Favorite> favoriteList =favoriteRepository.findAllByUserId(userId);
        List<BoardResponseDto> boardsList = new ArrayList<>();
        for(Favorite favorite : favoriteList){
            Board board = boardRepository.findBoardById(favorite.getBoardId());
            int like = favoriteRepository.countAllByBoardId(board.getId());
            User user = userRepository.findById(board.getUserId()).orElseThrow(
                    ()-> new IllegalArgumentException("없는 유저입니다")
            );
            UserInfoDto userInfoDto= new UserInfoDto(user.getUsername(), user.getNickname());
            boolean mylike= favoriteRepository.findByBoardIdAndUserId(board.getId(), userId).isPresent();
            String url = imageRepository.findByBoardId(board.getId()).getFileUrl();
            Long imageId = imageRepository.findByBoardId(board.getId()).getId();
            BoardResponseDto boardResponseDto = new BoardResponseDto(board, like, userInfoDto,mylike,url,imageId);
            boardsList.add(boardResponseDto);
        }
        final int start =(int)pageable.getOffset();
        final int end = Math.min((start + 16),boardsList.size());
        return validator.overPages(boardsList, start, end, pageable, page);
    }

    public String updateViews(Long boardId){
        boardRepository.updateViews(boardId);
        return "성공";
    }



    //게시글 자세히 보기
    public BoardResponseDto showBoardDetail(Long boardid, Long userId){
        Board board = boardRepository.findBoardById(boardid);
        int like = favoriteRepository.countAllByBoardId(board.getId());
        User user = userRepository.findById(board.getUserId()).orElseThrow(
                ()-> new IllegalArgumentException("없는 유저입니다")
        );
        UserInfoDto userInfoDto= new UserInfoDto(user.getUsername(), user.getNickname());
        boolean mylike= favoriteRepository.findByBoardIdAndUserId(board.getId(), userId).isPresent();
        String url = imageRepository.findByBoardId(boardid).getFileUrl();
        Long imageId = imageRepository.findByBoardId(board.getId()).getId();
        return new BoardResponseDto(board,like,userInfoDto,mylike,url,imageId);
    }

    //보드리스트 만들기
    private void forboardList(List<Board> boardList, List<BoardResponseDto> boardsList, Long userId) {
        for (Board board : boardList) {
            int like = favoriteRepository.countAllByBoardId(board.getId());
            User user = userRepository.findById(board.getUserId()).orElseThrow(
                    ()-> new IllegalArgumentException("없는 유저입니다")
            );
            UserInfoDto userInfoDto= new UserInfoDto(user.getUsername(), user.getNickname());
            boolean mylike= favoriteRepository.findByBoardIdAndUserId(board.getId(), userId).isPresent();
            String url = imageRepository.findByBoardId(board.getId()).getFileUrl();
            Long imageId = imageRepository.findByBoardId(board.getId()).getId();
            BoardResponseDto boardResponseDto = new BoardResponseDto(board, like, userInfoDto,mylike,url,imageId);
            boardsList.add(boardResponseDto);
        }
    }

    //page만들기
    private Pageable getPageable(int page) {
        page = page -1;
        Sort.Direction direction = Sort.Direction.ASC ;
        Sort sort = Sort.by(direction, "createAt");
        return PageRequest.of(page, 16,sort);
    }

}
