package server.home.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by default on 06/04/17.
 */
public class BoardService {

    private AnnouncementService announcementService;

    public BoardService(AnnouncementService announcementService){
        this.announcementService= announcementService;
    }

    public List<String> getIdBoards(){
        List<String> keys = new ArrayList<>();
        for (String key: announcementService.getBoards().keySet() ) {
            keys.add(key);
        }
        return keys;
    }
}
