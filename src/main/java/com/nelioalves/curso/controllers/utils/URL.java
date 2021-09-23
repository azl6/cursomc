package com.nelioalves.curso.controllers.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class URL{

    public static List<Integer> decodeIntList(String s){
        String vet[] = s.split(",");
        List<Integer> list = new ArrayList<>();
        for(String x: vet){
            list.add(Integer.parseInt(x));
        }
        return list;

        //VERSAO LAMBDA
        //Arrays.asList(s.split",")).stream().map(x -> Integer.parseInt(x)).collect(Collectiors.toList());
    }

    public static String decodeParam(String s) {
        try{
            return URLDecoder.decode(s, "UTF-8");
        }catch(UnsupportedEncodingException e){
            return "";
        }


    }
}
