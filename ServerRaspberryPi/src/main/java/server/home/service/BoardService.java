package server.home.service;

import java.util.ArrayList;
import java.util.List;

public class BoardService {

    private AnnouncementService announcementService;

    public BoardService(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    public List<String> getIdBoards() {
        List<String> keys = new ArrayList<>();
        for (String key : announcementService.getBoards().keySet()) {
            keys.add(key);
        }
        return keys;
    }
}
