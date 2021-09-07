package com.example.exchangeratesminiservice.web.restclients.giphy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiphyGif {
   private Data data;

    @lombok.Data
    @AllArgsConstructor
    static class Data{
       private String type;
       private String id;
       private String url;
    }
}
