package kim.board.service;


import kim.board.model.PostEntity;
import kim.board.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private final PostRepository postRepository;

    private int COUNTING = 0;

    public List<PostEntity> findAll(){
        return this.postRepository.findAll();
    }

    public PostEntity add(PostEntity request){
        request.setCreatedAt(LocalDate.now());
        return this.postRepository.save(request);
    }

    public PostEntity searchById(long postId) {
        return this.postRepository.findById(postId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public PostEntity countPlus(Long postId){
        PostEntity findPost = searchById(postId);
        findPost.setViewCount(++COUNTING);
        return this.postRepository.save(findPost);
    }


    public PostEntity updateById(Long postId, PostEntity request){
        PostEntity findPost = searchById(postId);
        if(request.getTitle() != null){
            findPost.setTitle(request.getTitle());
        }
        if(request.getContent() != null){
            findPost.setContent(request.getContent());
        }
        if(request.getWriter() != null){
            findPost.setWriter(request.getWriter());
        }

        return this.postRepository.save(findPost);
    }

    public void deleteById(Long id){
        this.postRepository.deleteById(id);
    }

}
