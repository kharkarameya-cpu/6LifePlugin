package org.succeptivitydev.xlifesmp.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for parsing and applying colors to text.
 * Supports hex colors (#RRGGBB), RGB colors (rgb(r,g,b)), and named colors.
 */
public class ColorParser {

    // Pattern for hex colors: #RRGGBB or #RGB
    private static final Pattern HEX_PATTERN = Pattern.compile("#([0-9A-Fa-f]{6}|[0-9A-Fa-f]{3})");

    // Pattern for RGB colors: rgb(r,g,b) where r,g,b are 0-255
    private static final Pattern RGB_PATTERN = Pattern.compile("rgb\\s*\\(\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})\\s*\\)");

    /**
     * Parses a color string and returns a TextColor.
     * Supports:
     * - Hex: #FF0000 or #F00
     * - RGB: rgb(255,0,0)
     * - Named: red, green, blue, yellow, etc. (standard Minecraft colors)
     *
     * @param colorStr the color string to parse
     * @return TextColor or null if invalid
     */
    public static TextColor parseColor(String colorStr) {
        if (colorStr == null || colorStr.trim().isEmpty()) {
            return null;
        }

        colorStr = colorStr.trim().toLowerCase();

        // Try hex color
        Matcher hexMatcher = HEX_PATTERN.matcher(colorStr);
        if (hexMatcher.find()) {
            String hexValue = hexMatcher.group(1);
            if (hexValue.length() == 3) {
                // Expand short form #RGB to #RRGGBB
                hexValue = String.valueOf(hexValue.charAt(0)) + hexValue.charAt(0) +
                          hexValue.charAt(1) + hexValue.charAt(1) +
                          hexValue.charAt(2) + hexValue.charAt(2);
            }
            try {
                int rgb = Integer.parseInt(hexValue, 16);
                return TextColor.color(rgb);
            } catch (NumberFormatException e) {
                return null;
            }
        }

        // Try RGB color
        Matcher rgbMatcher = RGB_PATTERN.matcher(colorStr);
        if (rgbMatcher.find()) {
            try {
                int r = Integer.parseInt(rgbMatcher.group(1));
                int g = Integer.parseInt(rgbMatcher.group(2));
                int b = Integer.parseInt(rgbMatcher.group(3));

                if (isValidRGB(r, g, b)) {
                    int rgb = (r << 16) | (g << 8) | b;
                    return TextColor.color(rgb);
                }
            } catch (NumberFormatException e) {
                return null;
            }
        }

        // Try named color
        return parseNamedColor(colorStr);
    }

    /**
     * Parse named Minecraft colors
     */
    private static TextColor parseNamedColor(String colorName) {
        return switch (colorName.toLowerCase()) {
            case "black" -> TextColor.color(0x000000);
            case "dark_blue" -> TextColor.color(0x0000AA);
            case "dark_green" -> TextColor.color(0x00AA00);
            case "dark_aqua" -> TextColor.color(0x00AAAA);
            case "dark_red" -> TextColor.color(0xAA0000);
            case "dark_purple" -> TextColor.color(0xAA00AA);
            case "gold", "orange" -> TextColor.color(0xFFAA00);
            case "gray", "grey" -> TextColor.color(0xAAAAAA);
            case "dark_gray", "dark_grey" -> TextColor.color(0x555555);
            case "blue" -> TextColor.color(0x5555FF);
            case "green" -> TextColor.color(0x55FF55);
            case "aqua", "cyan" -> TextColor.color(0x55FFFF);
            case "red" -> TextColor.color(0xFF5555);
            case "light_purple", "magenta" -> TextColor.color(0xFF55FF);
            case "yellow" -> TextColor.color(0xFFFF55);
            case "white" -> TextColor.color(0xFFFFFF);
            default -> null;
        };
    }

    /**
     * Validates if RGB values are in the valid range (0-255)
     */
    private static boolean isValidRGB(int r, int g, int b) {
        return r >= 0 && r <= 255 && g >= 0 && g <= 255 && b >= 0 && b <= 255;
    }

    /**
     * Apply color to a component string.
     * Replaces color tags like <#FF0000>text</> with colored text.
     * Example: "<#FF0000>Red text</> <rgb(0,255,0)>Green text</>"
     *
     * @param text the text with color tags
     * @return a formatted Component
     */
    public static Component applyColors(String text) {
        if (text == null || text.isEmpty()) {
            return Component.empty();
        }

        // Pattern to find color tags: <color>...text...</>
        Pattern colorTagPattern = Pattern.compile("<([^>]+)>(.+?)</>", Pattern.DOTALL);
        Matcher matcher = colorTagPattern.matcher(text);

        Component result = Component.empty();
        int lastEnd = 0;

        while (matcher.find()) {
            // Add text before the tag
            if (matcher.start() > lastEnd) {
                result = result.append(Component.text(text.substring(lastEnd, matcher.start())));
            }

            String colorStr = matcher.group(1);
            String content = matcher.group(2);
            TextColor color = parseColor(colorStr);

            if (color != null) {
                result = result.append(Component.text(content).color(color));
            } else {
                // If color parsing fails, just add the text as-is
                result = result.append(Component.text(content));
            }

            lastEnd = matcher.end();
        }

        // Add remaining text
        if (lastEnd < text.length()) {
            result = result.append(Component.text(text.substring(lastEnd)));
        }

        return result;
    }
}

