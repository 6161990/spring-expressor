package moviebuddy.data;

import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import moviebuddy.ApplicationException;

public abstract class AbstractFileSystemMovieReader {

	private Logger log = LoggerFactory.getLogger(getClass());
	private String metadata;

	public String getMetadata() {
		return metadata;
	}

	@Value("${movie.metadata}")
	public void setMetadata(String metadata) {
		this.metadata = Objects.requireNonNull(metadata, "matadata is required value");
	}

	@PostConstruct
	public void afterPropertiesSet() throws Exception { // 빈이 초기화될 때 올바른 값인지 검증하게됨. 
		URL metadataUrl = ClassLoader.getSystemResource(getMetadata());
		if(Objects.isNull(metadataUrl)) {
			throw new FileNotFoundException(metadata);
		}
		
		// 읽어들일 수 있는 파일인지 검증
		if(Files.isReadable(Path.of(metadataUrl.toURI())) == false) {
			throw new ApplicationException(String.format("cannot read to metadata. [%s]", metadata));
		}
	}

	@PreDestroy
	public void destroy() throws Exception { //CsvMovieReader가 정상적으로 소멸되었음을 로그로 남겨보기 
		log.info("destroy Bean"); // 현재 테스트(CsvMovieReaderTest)를 스프링 컨테이너를 통해 실행하고 있지 않아서 로그로 직접적확인이 어렵다. 
	}

}