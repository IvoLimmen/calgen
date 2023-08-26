package org.limmen.calgen.domain.helper;

import java.awt.Color;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class AwtModule extends SimpleModule {
  
  public AwtModule() {
    addDeserializer(Color.class, new ColorDeserializer());
  }
}
