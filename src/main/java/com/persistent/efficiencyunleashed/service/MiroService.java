package com.persistent.efficiencyunleashed.service;

import com.persistent.efficiencyunleashed.model.ActivityData;
import com.persistent.efficiencyunleashed.model.UserData;

import java.util.ArrayList;

public interface MiroService {
    ArrayList<ActivityData.Miro> getMiroMessages(String boardId);
}
