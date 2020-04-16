package com.simplejavatexteditor;

import javax.swing.text.*;
import java.awt.*;

public class HighlightText extends DefaultHighlighter.DefaultHighlightPainter{

	//Simple Constructor for HighlightText Object
    public HighlightText(Color color) {
        super(color);
    }

    //Method that highlights a word by passing in a text component, that
    //allows you to view the text, and the word you want to look for.
    public void highLight(JTextComponent textComp, String[] pattern) {
        removeHighlights(textComp);

        try {
            Highlighter hilite = textComp.getHighlighter();
            Document doc = textComp.getDocument();
            String text = doc.getText(0, doc.getLength());
            for (int i = 0; i < pattern.length; i++) {
                int pos = 0;

                while ((pos = text.indexOf(pattern[i], pos)) >= 0) {
                    hilite.addHighlight(pos, pos + pattern[i].length(), this);
                    pos += pattern[i].length();
                }
            }
        } catch (BadLocationException e) {}

    }

    //Method that removes highlights from document.
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
