package moviebuddy.data;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import moviebuddy.ApplicationException;

public abstract class AbstractMetadataResourceMovieReader implements ResourceLoaderAware{

	private Logger log = LoggerFactory.getLogger(getClass());
	private String metadata;
	private ResourceLoader resourceLoader;
	
	public String getMetadata() {
		return metadata;
	}

	@Value("${movie.metadata}")
	public void setMetadata(String metadata) {
		this.metadata = Objects.requireNonNull(metadata, "matadata is required value");
	}

	public URL getMetadataUrl() { // 자바 URL 은 한계가 있다. 서블릿 컨텍스트 경로나 클라우드 스토리지 서비스에 있는 자원은 접근할 수 없다.-> 스프링의 도움을 받아야한다. 
		String location = getMetadata();
		if(location.startsWith("file:")) {
			// file URL 처리 
		} else if(location.startsWith("http:")) {
			// http URL 처리 
		}
		return ClassLoader.getSystemResource(location);
	}
	
	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
		
	}
	
	public Resource getMetadataResource() {
		return resourceLoader.getResource(getMetadata());
	}
	
	@PostConstruct
	public void afterPropertiesSet() throws Exception { // 빈이 초기화될 때 올바른 값인지 검증하게됨. 
		
		Resource resource = getMetadataResource();
		if(resource.exists() == false) {
			throw new FileNotFoundException(metadata);
		}
		
		if(resource.isReadable() == false) {
			throw new ApplicationException(String.format("cannot read to metadata. [%s]", metadata));
		}
		
		log.info(resource + "is ready.");
		
		/**		
  		URL metadataUrl = getMetadataUrl(); // ClassLoader.getSystemResource() 클래스 패스 상의 자원만 처리할 수 있었다.
		if(Objects.isNull(metadataUrl)) {
			throw new FileNotFoundException(metadata);
		}
		
		// 읽어들일 수 있는 파일인지 검증
		if(Files.isReadable(Path.of(metadataUrl.toURI())) == false) {
			throw new ApplicationException(String.format("cannot read to metadata. [%s]", metadata));
		} */
	}


	@PreDestroy
	public void destroy() throws Exception { //CsvMovieReader가 정상적으로 소멸되었음을 로그로 남겨보기 
		log.info("destroy Bean"); // 현재 테스트(CsvMovieReaderTest)를 스프링 컨테이너를 통해 실행하고 있지 않아서 로그로 직접적확인이 어렵다. 
	}

}