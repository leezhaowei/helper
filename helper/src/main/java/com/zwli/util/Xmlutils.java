package com.zwli.util;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Xmlutils {

	public static void outputXML(final String fn) throws Exception {
		final SAXBuilder builder = new SAXBuilder();
		final Document doc = builder.build(fn);
		Format fmt = Format.getRawFormat();
		//		fmt = Format.getCompactFormat();
		final XMLOutputter out = new XMLOutputter();
		out.setFormat(fmt);
		out.output(doc, System.out);
	}

	public static void main(final String[] args) throws Exception {
		final String fn = "d:/tmp/tmp.xml";
		Xmlutils.outputXML(fn);
	}
}
