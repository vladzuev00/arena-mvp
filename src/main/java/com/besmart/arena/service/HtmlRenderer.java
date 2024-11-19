package com.besmart.arena.service;

import net.htmlparser.jericho.Renderer;
import net.htmlparser.jericho.Segment;
import net.htmlparser.jericho.Source;
import org.springframework.stereotype.Component;

@Component
public final class HtmlRenderer {

    public String render(String html) {
        Source source = new Source(html);
        Segment segment = new Segment(source, 0, source.length());
        return new Renderer(segment)
                .setIncludeHyperlinkURLs(true)
                .toString();
    }
}
