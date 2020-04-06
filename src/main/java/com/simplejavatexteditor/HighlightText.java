package com.simplejavatexteditor;

import javax.swing.text.*;
import javax.swing.*;
import java.awt.*;

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

                while ((pos = text.indexOf(pattern[i], pos)) >= 0) {
		    doc.setCharacterAttributes(pos, pattern[i].length(), attrs, false);
		    pos += pattern[i].length();
                }
            }
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
