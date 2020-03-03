package com.github.nut077.article.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class JsonUtil {

  ObjectMapper mapper = new ObjectMapper();

  @SneakyThrows
  public String toJson(Object object) {
    return mapper.writeValueAsString(object);
  }
}
