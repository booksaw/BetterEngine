package com.booksaw.betterEngine.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class InlineLoggerFormatter extends Formatter {

	@Override
	public String format(LogRecord logRecord) {
		return String.format("[%s][%s - %s] %s\n", calcDate(logRecord.getMillis()), logRecord.getSourceClassName(),
				logRecord.getLevel().toString(), logRecord.getMessage());
	}

	private String calcDate(long millisecs) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date resultdate = new Date(millisecs);
		return dateFormat.format(resultdate);
	}

}
