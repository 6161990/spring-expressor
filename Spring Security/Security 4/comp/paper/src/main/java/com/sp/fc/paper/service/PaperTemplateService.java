package com.sp.fc.paper.service;

import com.sp.fc.paper.domain.PaperTemplate;
import com.sp.fc.paper.domain.Problem;
import com.sp.fc.paper.repository.PaperTemplateRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Transactional
@Service
@RequiredArgsConstructor
public class PaperTemplateService {

    private final PaperTemplateRepository paperTemplateRepository;
    private final ProblemService problemService;

    public PaperTemplate save(PaperTemplate paperTemplate){
        if(paperTemplate.getPaperTemplateId() == null){
            paperTemplate.setCreated(LocalDateTime.now());
        }
        paperTemplate.setUpdated(LocalDateTime.now());
        return paperTemplateRepository.save(paperTemplate);
    }

    public Problem addProblem(long paperTemplateId, Problem problem){  /* 문제 추가 */
        problem.setPaperTemplateId(paperTemplateId);  //Problem의 시험 템플릿 아이디 set
        return findById(paperTemplateId).map(paperTemplate-> {
            if (paperTemplate.getProblemList() == null) { //문제가 하나도 없다면
                paperTemplate.setProblemList(new ArrayList<>()); //문제 리스트 인스턴스 생성
            }
            problem.setCreated(LocalDateTime.now()); // 문제 생성 시간 set
            paperTemplate.getProblemList().add(problem); // 문제 리스트에 문제 추가
            IntStream.rangeClosed(1, paperTemplate.getProblemList().size()).forEach(i -> {
                paperTemplate.getProblemList().get(i-1).setIndexNum(i);  // 문제 num 베이스가 1이므로 리스트 배열에서 i-1 인덱스 가져온 뒤 indexNum setting
                });
                paperTemplate.setTotal(paperTemplate.getProblemList().size()); //paperTemplate 에 전체 문제 갯수 set
                Problem saved = problemService.save(problem); //문제 저장
                save(paperTemplate); //템플릿 저장
                return saved;
        }).orElseThrow(()-> new IllegalArgumentException(paperTemplateId+" 아이디 시험지가 없습니다."));
    }

    public Optional<PaperTemplate> findById(long paperTemplateId){
        return paperTemplateRepository.findById(paperTemplateId);
    }

    public PaperTemplate removeProblem(long paperTemplateId, long problemId){
        return findById(paperTemplateId).map(paperTemplate -> {
           if(paperTemplate.getProblemList() == null){
               return paperTemplate;
           }
           Optional<Problem> problem = paperTemplate.getProblemList().stream().filter(p -> p.getProblemId().equals(problemId)).findFirst(); //해당 문제를 id로 가져와서
           if(problem.isPresent()){
               paperTemplate.setProblemList(
                       paperTemplate.getProblemList().stream().filter(p -> !p.getProblemId().equals(problemId))
                                .collect(Collectors.toList())  //리스트에서 문제 제거
               );
               problemService.delete(problem.get());
               IntStream.rangeClosed(1, paperTemplate.getProblemList().size()).forEach(i->{ //템플릿의 list넘버링 다시하기
                   paperTemplate.getProblemList().get(i-1).setIndexNum(i);
               });
           }
            paperTemplate.setTotal(paperTemplate.getProblemList().size());
            return save(paperTemplate);
        }).orElseThrow(()-> new IllegalArgumentException(paperTemplateId+" 아이디 시험지가 없습니다."));
    }

    public void update(long problemId, String content, String answer){
        problemService.updateProblem(problemId, content, answer); //update는 paperTemplate에 영향을 주지 않음
    }

    @Transactional(readOnly = true)
    public Optional<PaperTemplate> findProblemTemplate(Long paperTemplateId){
        return paperTemplateRepository.findById(paperTemplateId).map(pt->{
            if(pt.getProblemList().size() != pt.getTotal()){  //total이 달라지는 경우는 없겠지만,
                pt.setTotal(pt.getProblemList().size()); //만약을을 비해 문제 list 갯수로 setting
            }
            return pt; //그렇게되면 lazy한 문제 리스트가 채워져서 내려져가게되는 효과도 있음
        });
    }

    @Transactional(readOnly = true)
    public Map<Integer, String> getPaperAnswerSheet(Long paperTemplateId){
        Optional<PaperTemplate> template = findById(paperTemplateId);
        if(!template.isPresent()) return new HashMap<>();
        return template.get().getProblemList().stream().collect(Collectors.toMap(Problem::getIndexNum, Problem::getAnswer));
    }

    @Transactional(readOnly = true)
    public List<PaperTemplate> findByTeacherId(Long userId) {
        return paperTemplateRepository.findAllByUserIdOrderByCreatedDesc(userId);
    }

    @Transactional(readOnly = true)
    public Page<PaperTemplate> findByTeacherId(Long userId, int pageNum, int size) {
        return paperTemplateRepository.findAllByUserIdOrderByCreatedDesc(userId, PageRequest.of(pageNum-1, size));
    }

    @Transactional(readOnly = true)
    public Object countByUserId(Long userId) {
        return paperTemplateRepository.countByuserId(userId);
    }

    public void updatePublishedCount(long paperTemplateId, long publishedCount) {
        paperTemplateRepository.findById(paperTemplateId).ifPresent(paperTemplate -> {
            paperTemplate.setPublishedCount(publishedCount);
            paperTemplateRepository.save(paperTemplate);
        });
    }

    public void updateCompleteCount(Long paperTemplateId) {
        paperTemplateRepository.findById(paperTemplateId).ifPresent(paperTemplate -> {
            paperTemplate.setCompleteCount(paperTemplate.getCompleteCount()+1);
            paperTemplateRepository.save(paperTemplate);
        });
    }

}
