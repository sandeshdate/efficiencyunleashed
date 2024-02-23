package com.persistent.efficiencyunleashed.service;

import com.persistent.efficiencyunleashed.model.ActivityData;
import com.persistent.efficiencyunleashed.model.UserData;

import java.util.List;

public interface UserDataService {
    List<ActivityData> getMasterActivity();

    UserData getUserActivity();
}
