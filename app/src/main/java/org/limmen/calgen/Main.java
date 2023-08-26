package org.limmen.calgen;

import java.nio.file.Paths;

import org.limmen.calgen.domain.helper.ConfigLoader;
import org.limmen.calgen.generator.ImageGenerator;

public class Main {

  public static void main(String[] args) throws Exception {
    new ImageGenerator(ConfigLoader.parse(Paths.get("config","settings.json")));
  }
}
