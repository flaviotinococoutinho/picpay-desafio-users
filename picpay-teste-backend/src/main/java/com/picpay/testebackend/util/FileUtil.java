package com.picpay.testebackend.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class FileUtil {
	public static File unZip(File file, boolean rmFileAfterSuccess) throws IOException {
		FileOutputStream fileOutputStream = null;
		GZIPInputStream gZipInputStream = new GZIPInputStream(new FileInputStream(file));
		try {
			File outFile = new File(file.getParent(), file.getName().replaceAll("\\.gz$", ""));
			fileOutputStream = new FileOutputStream(outFile);
			byte[] buf = new byte[100000];
			int size;
			while ((size = gZipInputStream.read(buf)) > 0) {
				fileOutputStream.write(buf, 0, size);
			}
			fileOutputStream.close();
			return outFile;
		} finally {
			if (gZipInputStream != null) {
				gZipInputStream.close();
			}
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
			if (rmFileAfterSuccess) {
				file.delete();
			}
		}
	}
}