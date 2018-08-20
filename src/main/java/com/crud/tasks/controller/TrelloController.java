package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {
    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {
//          My code exercise 18.2
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
        String a = trelloBoards.stream().filter(trelloBoard -> trelloBoard.getName() != null && trelloBoard.getId() != null)
                .filter(trelloBoard -> trelloBoard.getName().contains("Kodilla"))
                .map(board -> board.getName() + " " + board.getId())
                .collect(Collectors.joining());
        System.out.println(a);

//          Code from materials module 18.3
//    List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
//        trelloBoards.forEach(trelloBoardDto -> {
//            System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());
//            System.out.println("This board contains lists: ");
//            trelloBoardDto.getLists().forEach(trelloList ->
//                System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));
//        });
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }
}
