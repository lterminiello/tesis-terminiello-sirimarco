package server.home.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import server.home.service.BoardService;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping(value = "/idBoards", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<String>> getIdBoards() {
        List keys = boardService.getIdBoards();
        return new ResponseEntity<List<String>>(keys, HttpStatus.ACCEPTED);
    }
}


