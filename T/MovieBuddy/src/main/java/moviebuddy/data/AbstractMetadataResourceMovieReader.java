package moviebuddy.data;

import moviebuddy.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileNotFoundException;

import java.util.Objects;

public abstract class AbstractMetadataResourceMovieReader implements ResourceLoaderAware {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private String metadata;
    private ResourceLoader resourceLoader;

    public String getMetadata() {
        return metadata;
    }

    @Value("${movie.metadata}")
    public void setMetadata(String metadata) {
        this.metadata = Objects.requireNonNull(metadata, "metadata is required value");
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Resource getMetadataSource(){
        return resourceLoader.getResource(getMetadata());
    }

    @PostConstruct // 특정 규약과 환경에 종석되지 않도록 객체를 생성하기 위해서는 자바 표준 애노테이션을 쓰는 것이 (초기화,소멸 시) 좋다.
    public void afterPropertiesSet() throws Exception {

        Resource resource = getMetadataSource();

        if(!resource.exists()) {
            throw new FileNotFoundException(metadata);
        }

        if(!resource.isReadable()){
            throw new ApplicationException(String.format("cannot read to metadata. [%s]", metadata));
        }

        log.info(resource + "is ready.");
    }

    @PreDestroy
    public void destroy() throws Exception {
        log.info("Destroy Bean");
    }
}
