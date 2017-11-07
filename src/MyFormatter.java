import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter {

	public String format(LogRecord arg0) {
		StringBuffer s = new StringBuffer(1000);
		Date d = new Date(arg0.getMillis());
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM, Locale.FRANCE);
		s.append(df.format(d)+"\t"+formatMessage(arg0)+"\n");
		return s.toString();
	}

}
