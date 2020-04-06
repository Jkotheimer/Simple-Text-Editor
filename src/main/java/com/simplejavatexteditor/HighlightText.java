package com.simplejavatexteditor;

import javax.swing.text.*;
import javax.swing.*;
import java.awt.*;
import java.util.regex.*;

public class HighlightText extends DefaultHighlighter.DefaultHighlightPainter{

    private Color color;

    public HighlightText(Color color) {
        super(color);
	this.color = color;
    }

    public void highlightSyntax(JTextPane textComp, String[] pattern) {
        removeHighlights(textComp);
        try {
            StyledDocument doc = textComp.getStyledDocument();
	    SimpleAttributeSet attrs = new SimpleAttributeSet();
	    StyleConstants.setForeground(attrs, color);

            String text = doc.getText(0, doc.getLength());
            for (int i = 0; i < pattern.length; i++) {
                int pos = 0;
                String patternStr = "\\b" + pattern[i] + "\\b";
                Matcher matcher = Pattern.compile(patternStr).matcher(text);
                while(pos < text.length() && matcher.find(pos)) {
		    pos = matcher.start();
		    System.out.println("FOUND " + pattern[i] + " AT " + pos);
		    doc.setCharacterAttributes(pos, pattern[i].length(), attrs, false);
		    pos += pattern[i].length();
                }
            }
	    System.out.println();
        } catch (BadLocationException e) {}

    }

    public void removeHighlights(JTextComponent textComp) {

        Highlighter hilite = textComp.getHighlighter();
        Highlighter.Highlight[] hilites = hilite.getHighlights();

        for (int i = 0; i < hilites.length; i++) {
            if (hilites[i].getPainter() instanceof HighlightText) {
                hilite.removeHighlight(hilites[i]);
            }
        }
    }
}
