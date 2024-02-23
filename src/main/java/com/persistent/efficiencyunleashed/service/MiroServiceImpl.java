package com.persistent.efficiencyunleashed.service;

import com.persistent.efficiencyunleashed.dao.UserDAO;
import com.persistent.efficiencyunleashed.model.ActivityData;
import com.persistent.efficiencyunleashed.model.MiroGetBoardItemsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;

@Service
@Slf4j
public class MiroServiceImpl implements MiroService {

    private UserDAO userDAO;

    public MiroServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public ArrayList<ActivityData.Miro> getMiroMessages(String boardId) {
        ArrayList<ActivityData.Miro> miros = new ArrayList<>();
        MiroGetBoardItemsResponse miroGetBoardItemsResponse = userDAO.callMiroBoardItems(boardId);
        if (!ObjectUtils.isEmpty(miroGetBoardItemsResponse.getData())) {
            miroGetBoardItemsResponse.getData().forEach(stickyNote -> {
                if (!ObjectUtils.isEmpty(stickyNote.getData().getContent())) {
                    ActivityData.Miro miro = new ActivityData.Miro();
                    miro.setActivity(stickyNote.getData().getContent());
                    miro.setTimestamp(stickyNote.getModifiedAt());
                    miros.add(miro);
                }
            });
        }
        return miros;
    }
}
