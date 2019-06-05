package ru.gazis.baseapplication.worker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.mathworks.toolbox.javabuilder.MWArray;
import com.mathworks.toolbox.javabuilder.MWCharArray;
import com.mathworks.toolbox.javabuilder.MWException;

import CalcMPCLib.*;

public class Computation {

	private ClassMPC mpc = null;
	private String dataPath = null;
	
	public Computation(String dataPath) {
		this.dataPath = dataPath;
		try {
			mpc = new ClassMPC();
		} catch (MWException e) {
			e.printStackTrace();
		}
	}

	public void dispose() {
		mpc.dispose();
	}

	public String executeModel(String xmlString, boolean silentMode) throws Exception {
		MWCharArray xml = null;
		MWCharArray mat = null;
		Object result[] = null;
		String outXml = "";

		try {
			xml = new MWCharArray(xmlString);
			mat = new MWCharArray(dataPath);

			if (silentMode) {
				// suppress matlab waste output to stdout
				PrintStream stdout = System.out;
				ByteArrayOutputStream fakeStream = new ByteArrayOutputStream();
				System.setOut(new PrintStream(fakeStream));
				result = mpc.CalcMPCLib(1, xml, mat);
				System.setOut(stdout);
			} else {
				result = mpc.CalcMPCLib(1, xml, mat);
			}

			outXml = result[0].toString();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			MWArray.disposeArray(xml);
			MWArray.disposeArray(mat);
			MWArray.disposeArray(result);
		}

		return outXml.replace("\n", "");
	}
}