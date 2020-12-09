package study.springbootbasic.boardExample;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import study.springbootbasic.global.error.exception.ResourceNotFoundException;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Validated
public class BoardService {

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Long save(Board board) {
        boardRepository.save(board);
        return board.getId();
    }

    @Transactional
    public void update(@NotNull Long id, Board board) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        Board findBoard = optionalBoard.get();
        modelMapper.map(board, findBoard);
    }

    public Optional<Board> findOne(@NotNull Long id) {
        Optional<Board> optBoard = boardRepository.findById(id);
        return optBoard;
    }

    public List<Board> findAll() {
        List<Board> boards = boardRepository.findAll();
        return boards;
    }

    @Transactional
    public void delete(@NotNull Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        boardRepository.deleteById(id);
    }
}
