package com.zwli.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * <pre>
 * Write CSV (Comma Separated Value)
 * files.
 * 
 * This format is used my Microsoft Word and Excel.
 * Fields are separated by commas, and enclosed in
 * quotes if they contain commas or quotes.
 * Embedded quotes are doubled.
 * Embedded spaces do not normally require surrounding quotes.
 * The last field on the line is not followed by a comma.
 * Null fields are represented by two commas in a row.
 * </pre>
 * 
 * @author 李赵伟 Create: 11:08:40 AM Mar 6, 2008
 */
public class CSVWriter {

	public static void main(String args[]) {
		try {
			PrintWriter pw = new PrintWriter(new File("D:/temp/testcsv.csv"));
			CSVWriter csv = new CSVWriter(pw, 1, ',', '\"', true);
			csv.put("日期");
			csv.put("渠道编号");
			csv.put("渠道名称");
			csv.put("产品编号");
			csv.put("产品名称");
			csv.put("使用人数");
			csv.put("使用次数");
			csv.put("计费次数");
			csv.nl();
			csv.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * PrintWriter where CSV fields will be written.
	 */
	PrintWriter pw;

	/**
	 * how much extra quoting you want
	 */
	int quoteLevel;

	/**
	 * field separator character, usually ',' in North America, ';' in Europe
	 * and sometimes '\t' for tab.
	 */
	char separator;

	/**
	 * quote character, usually '\"' '\'' for SOL used to enclose fields
	 * containing a separator character.
	 */
	private char quote;

	/**
	 * true if write should trim lead/trail whitespace from fields before
	 * writing them.
	 */
	private final boolean trim;

	/**
	 * true if there has was a field previously written to this line, meaning
	 * there is a comma pending to be written.
	 */
	boolean wasPreviousField = false;
	static final boolean DEBUGGING = false;

	/**
	 * Constructor
	 * 
	 * @param pw
	 *            Writer where fields will be written.
	 * @param quoteLevel
	 *            0 = minimal quotes 1 = quotes also around fields containing
	 *            spaces 2 = quotels around all fields. whether or not they
	 *            contain commas, quotes or spaces.
	 * @param separator
	 *            field separator character, usually ',' in North America, ';'
	 *            in Europe and sometimes '\t' for tab.
	 * @param quote
	 *            char to use to enclose fields containing a separator, usually
	 *            '\"'
	 * @param trim
	 *            true if writer should trim leading/trailing whitespace (e.g.
	 *            blank, cr, Lf, tab) before writing the field.
	 */
	public CSVWriter(PrintWriter pw, int quoteLevel, char separator,
			char quote, boolean trim) {
		if (pw instanceof PrintWriter) {
			this.pw = pw;
		} else {
			this.pw = new PrintWriter(pw);
		}
		if (this.pw == null) {
			throw new IllegalArgumentException("invalid Writer");
		}
		this.quoteLevel = quoteLevel;
		this.separator = separator;
		this.quote = quote;
		this.trim = trim;
	}

	/**
	 * convenience Constructor, defaults to quotelevel 1, comma separator , trim
	 * 
	 * @param pw
	 *            Writer where fields will be written.
	 */
	public CSVWriter(PrintWriter pw) {
		this(pw, 1, ',', '\"', true);
	}

	public void put(int i) {
		put(i + "");
	}

	/**
	 * Write one csv field to the file, followed by a separator unless it is the
	 * last field on the line. Lead and trailing blanks will be removed.
	 * 
	 * @param s
	 *            The string to write. Any additional quotes or embedded quotes
	 *            will be provided by put. Null means start a new line.
	 */
	public void put(String s) {
		if (pw == null) {
			throw new IllegalArgumentException(
					"attempt to use a closed CSVWriter");
		}
		if (s == null) {
			// nl();zhanghongwei modify
			s = "";
			// return;
		}

		if (wasPreviousField) {
			pw.print(separator);
		}
		if (trim) {
			s = s.trim();
		}
		if (s.indexOf(quote) >= 0) {
			/* worst case, needs surrounding quotes and internal quotes doubled */
			pw.print(quote);
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c == quote) {
					pw.print(quote);
					pw.print(quote);
				} else {
					pw.print(c);
				}
			}
			pw.print(quote);
		}
		// else if ( quoteLevel == 2 || quoteLevel == 1 && s.indexOf(' ') >= 0
		// || s.indexOf(separator) >= 0 )
		else if (quoteLevel == 2 || s.indexOf(separator) >= 0)// 空格不加双引号
		{
			/* need surrounding quotes */
			pw.print(quote);
			pw.print(s);
			pw.print(quote);
		} else {
			/* ordinary case, no surrounding quotes needed */
			pw.print(s);
		}
		/* make a note to print trailing comma later */
		wasPreviousField = true;
	}

	/**
	 * Write a new line in the CVS output file to demark the end of record.
	 */
	public void nl() {
		if (pw == null) {
			throw new IllegalArgumentException(
					"attempt to use a closed CSVSWriter");
		}
		/* don't bother to write last pending comma on the line */
		pw.print("\r\n"); /*
						 * windows conventions since this is a windows format
						 * file
						 */
		wasPreviousField = false;
	}

	/**
	 * Close the PrintWriter.
	 */
	public void close() {
		if (pw != null) {
			pw.close();
			pw = null;
		}
	}
}
