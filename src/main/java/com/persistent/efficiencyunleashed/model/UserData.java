package com.persistent.efficiencyunleashed.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    public User user;
    public String date;
    public ArrayList<ActivityData> data;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User{
        public String id;
        public String name;
        public String email;
        public String role;
        public String department;
        public String location;
    }
}
