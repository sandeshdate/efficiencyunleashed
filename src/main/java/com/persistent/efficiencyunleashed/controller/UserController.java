package com.persistent.efficiencyunleashed.controller;

import com.persistent.efficiencyunleashed.model.ActivityData;
import com.persistent.efficiencyunleashed.model.UserData;
import com.persistent.efficiencyunleashed.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserDataService masterActivity;

    @RequestMapping("/api/masterActivity")
    public ResponseEntity<List<ActivityData>> masterActivity() {
        List<ActivityData> activityDataList = masterActivity.getMasterActivity();

        return ResponseEntity.ok(activityDataList);
    }

    @RequestMapping("/api/userActivity")
    public ResponseEntity<UserData> userActivity() {
        UserData userData = masterActivity.getUserActivity();

        return ResponseEntity.ok(userData);
    }

}
