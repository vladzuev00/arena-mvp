package com.besmart.arena.util;

import lombok.experimental.UtilityClass;
import net.htmlparser.jericho.Renderer;
import net.htmlparser.jericho.Segment;
import net.htmlparser.jericho.Source;

@UtilityClass
public final class HtmlUtil {

    public static String render(String html) {
        Source source = new Source(html);
        Segment segment = new Segment(source, 0, source.length());
        return new Renderer(segment)
                .setIncludeHyperlinkURLs(true)
                .toString();
    }
}
