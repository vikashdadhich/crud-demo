package com.justin.cruddemo.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public class ResponseUtil {

    static public ResponseEntity<?> created(String location, int id, UriComponentsBuilder ucBuilder){
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path(location).buildAndExpand(id).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
}
