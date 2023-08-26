package org.limmen.calgen.domain.helper;

import java.io.IOException;
import java.nio.file.Path;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.limmen.calgen.domain.Settings;

public class ConfigLoader {

  private static ObjectMapper mapper = new ObjectMapper()
      .registerModule(new JavaTimeModule())
      .registerModule(new AwtModule());

  public static Settings parse(Path file) throws StreamReadException, DatabindException, IOException {
    return mapper.readValue(file.toFile(), Settings.class);
  }
}