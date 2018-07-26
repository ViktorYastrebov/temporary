package track_approx_rt;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import net.sf.marineapi.nmea.io.ExceptionListener;
import net.sf.marineapi.nmea.io.SentenceReader;
import net.sf.marineapi.provider.PositionProvider;
import net.sf.marineapi.provider.event.PositionEvent;
import net.sf.marineapi.provider.event.PositionListener;


public class PositionProviderNMEA_0183 implements PositionListener, ExceptionListener {
	private PositionProvider provider;
	
	public PositionProviderNMEA_0183(String path)  {
		try {
			File f = new File(path);
			InputStream stream = new FileInputStream(f);
			SentenceReader reader = new SentenceReader(stream);
			reader.setExceptionListener(this);
			
			provider = new PositionProvider(reader);
			provider.addListener(this);
			reader.start();
		} catch ( Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void providerUpdate(PositionEvent evt) {
		System.out.println("TPV: " + evt.toString());
	}

	public void onException(Exception e) {
		e.printStackTrace();
	}
}
