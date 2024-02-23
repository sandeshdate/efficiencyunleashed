package com.persistent.efficiencyunleashed.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MiroGetBoardItemsResponse {

    private List<BoardsData> data;
    private String cursor;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardsData {
        private String id;
        private String type;
        private String modifiedAt;
        private BoardItemData data;

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class BoardItemData {
            private String content;
        }
    }
}
