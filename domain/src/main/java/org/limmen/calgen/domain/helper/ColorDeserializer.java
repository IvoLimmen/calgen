package org.limmen.calgen.domain.helper;

import java.awt.Color;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class ColorDeserializer extends JsonDeserializer<Color> {

    @Override
    public Color deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        var color = p.getText();
        if (color.startsWith("#")) {
          color = color.substring(1);
        }
        return hex2Rgb(color);
    }

    public Color hex2Rgb(String colorStr) {
      return new Color(
              Integer.valueOf(colorStr.substring(0, 2), 16),
              Integer.valueOf(colorStr.substring(2, 4), 16),
              Integer.valueOf(colorStr.substring(4, 6), 16));
    }
}