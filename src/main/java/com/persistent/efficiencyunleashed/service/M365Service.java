package com.persistent.efficiencyunleashed.service;

import com.persistent.efficiencyunleashed.model.ActivityData;

import java.util.ArrayList;

public interface M365Service {
    ArrayList<ActivityData.M365> getM365Mails();
}
