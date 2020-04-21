package com.simplejavatexteditor;

import javax.swing.text.*;
import javax.swing.*;
import java.awt.*;
import java.util.regex.*;

public class HighlightText extends DefaultHighlighter.DefaultHighlightPainter{

	//Simple Constructor for HighlightText Object
	private Color primary_color, secondary_color, tertiary_color;

	public HighlightText(Color color) {
		super(color);
		primary_color = color;
		secondary_color = color;
		tertiary_color = color;
	}
	public HighlightText(Color primary_color, Color secondary_color) {
		super(primary_color);
		this.primary_color = primary_color;
		this.secondary_color = secondary_color;
		this.tertiary_color = secondary_color;
	}

	public HighlightText(Color primary_color, Color secondary_color, Color tertiary_color) {
		super(primary_color);
		this.primary_color = primary_color;
		this.secondary_color = secondary_color;
		this.tertiary_color = tertiary_color;
	}

	//Method that highlights a word by passing in a text component, that
	//allows you to view the text, and the word you want to look for.
	public void highlightSyntax(JTextPane textComp, String[] pattern) {
		removeHighlights(textComp);
		try {
			StyledDocument doc = textComp.getStyledDocument();

			SimpleAttributeSet black_attr = new SimpleAttributeSet();
			StyleConstants.setForeground(black_attr, Color.BLACK);
			doc.setCharacterAttributes(0, doc.getLength(), black_attr, true);
		
			SimpleAttributeSet primary_attr = new SimpleAttributeSet();
			StyleConstants.setForeground(primary_attr, primary_color);

			String text = doc.getText(0, doc.getLength());
			
			// Find any patterns in the given syntax list and color it with the primary color
			for (int i = 0; i < pattern.length; i++) {
				int pos = 0;

				Matcher regex = Pattern.compile("\\b" + pattern[i] + "\\b").matcher(text);
				while(pos < text.length() && regex.find(pos)) {
					pos = regex.start();
					doc.setCharacterAttributes(pos, pattern[i].length(), primary_attr, false);
					pos += pattern[i].length();
				}
			}

			// Find any strings within quotes and color them with the tertiary color
			// Find any digits and color them with the secondary color
			SimpleAttributeSet sattr = new SimpleAttributeSet();
			SimpleAttributeSet tattr = new SimpleAttributeSet();
			StyleConstants.setForeground(sattr, secondary_color);
			StyleConstants.setForeground(tattr, tertiary_color);

			Matcher digit_regex = Pattern.compile("\\d+").matcher(text);
			Matcher string_regex = Pattern.compile("([\"'])(?:(?=(\\\\?))\\2.)*?\\1").matcher(text);
			int i = 0;
			boolean d = digit_regex.find();
			boolean s = string_regex.find();
			while(i < text.length()) {
				if(!d && !s) break;
				if(d) {
                    System.out.print(i + " : ");
					i = digit_regex.end();
					doc.setCharacterAttributes(digit_regex.start(), i - digit_regex.start(), sattr, false);
                    System.out.println(i + " : " + digit_regex.group());
				}
				if(s) {
					i = string_regex.end();
					doc.setCharacterAttributes(string_regex.start(), i - string_regex.start(), tattr, false);
				}
				d = digit_regex.find();
				s = string_regex.find();
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

	public void highlight(JTextComponent textComp, String[] pattern) {
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
}
