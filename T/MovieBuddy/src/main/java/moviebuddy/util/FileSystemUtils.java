package moviebuddy.util;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.spi.FileSystemProvider;
import java.util.Collections;

import moviebuddy.ApplicationException;

public class FileSystemUtils {

	/*
	 * JAR(Java Archive, 자바 아카이브)로 패키징된 애플리케이션에서 파일시스템 접근시 필요에 따라 파일시스템을 초기화한다.
	 */
	public static URI checkFileSystem(URI uri) {
		if("jar".equalsIgnoreCase(uri.getScheme())){
		    for (FileSystemProvider provider : FileSystemProvider.installedProviders()) {
		        if (provider.getScheme().equalsIgnoreCase("jar")) {
		            try {
		                provider.getFileSystem(uri);
		            } catch (FileSystemNotFoundException ignore) {
		                // in this case we need to initialize it first
		                try {
							provider.newFileSystem(uri, Collections.emptyMap());
						} catch (IOException error) {
							throw new ApplicationException("failed to initialize file system.", error);
						}
		            }
		        }
		    }
		}
		return uri;
	}
	
}
