package com.hujie.service;

import com.hujie.entity.Guru;

import java.util.HashMap;
import java.util.Map;

public interface GuruService {

    Map<String,Object> showGuru(Integer page,Integer rows);

    HashMap<String,Object> updateGuru(Guru guru);


}
