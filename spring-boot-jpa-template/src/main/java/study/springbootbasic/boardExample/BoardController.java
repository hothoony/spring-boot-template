package study.springbootbasic.boardExample;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.springbootbasic.global.error.exception.BeanValidationException;
import study.springbootbasic.global.error.exception.ResourceNotFoundException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public ResponseEntity<List<Board>> readAll() {
        List<Board> boards = boardService.findAll();
        if (boards.isEmpty()) {
//            return ResponseEntity.notFound().build();
            throw new ResourceNotFoundException();
        }
        return ResponseEntity.ok(boards);
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<Board> readOne(@PathVariable @NotNull Long id) {
        Optional<Board> optionalBoard = boardService.findOne(id);
        if (optionalBoard.isEmpty()) {
//            return ResponseEntity.notFound().build();
            throw new ResourceNotFoundException();
        }
        return ResponseEntity.ok(optionalBoard.get());
    }

    @PostMapping("/boards")
    public ResponseEntity create(@RequestBody @Valid BoardUpdateRequestDto boardUpdateRequestDto, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new BeanValidationException(errors);
        }

        Board board = Board.builder().build();
        BeanUtils.copyProperties(boardUpdateRequestDto, board);

        Long id = boardService.save(board);
        URI uri = linkTo(methodOn(BoardController.class).create(boardUpdateRequestDto, errors))
                .slash(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/boards/{id}")
    public ResponseEntity<Board> update(@PathVariable @NotNull Long id, @RequestBody @Valid BoardUpdateRequestDto boardUpdateRequestDto) {
        Optional<Board> optionalBoard = boardService.findOne(id);
        if (optionalBoard.isEmpty()) {
//            return ResponseEntity.notFound().build();
            throw new ResourceNotFoundException();
        }

        Board board = Board.builder().build();
        BeanUtils.copyProperties(boardUpdateRequestDto, board);

        boardService.update(id, board);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Board> delete(@PathVariable @NotNull Long id) {
        Optional<Board> optionalBoard = boardService.findOne(id);
        if (optionalBoard.isEmpty()) {
//            return ResponseEntity.notFound().build();
            throw new ResourceNotFoundException();
        }

        boardService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class BoardUpdateRequestDto {
        private Long id;
        @NotBlank
        private String title;
        private String content;
    }
}
