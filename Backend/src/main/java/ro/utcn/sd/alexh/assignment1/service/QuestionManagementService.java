package ro.utcn.sd.alexh.assignment1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.alexh.assignment1.dto.QuestionDTO;
import ro.utcn.sd.alexh.assignment1.entity.Question;
import ro.utcn.sd.alexh.assignment1.entity.QuestionVote;
import ro.utcn.sd.alexh.assignment1.entity.Tag;
import ro.utcn.sd.alexh.assignment1.event.QuestionCreatedEvent;
import ro.utcn.sd.alexh.assignment1.exception.QuestionNotFoundException;
import ro.utcn.sd.alexh.assignment1.exception.SelfVoteException;
import ro.utcn.sd.alexh.assignment1.persistence.api.RepositoryFactory;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionManagementService {

    private final RepositoryFactory repositoryFactory;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public List<QuestionDTO> listQuestions() {
        List<Question> questionList = repositoryFactory.createQuestionRepository().findAll();

        // Order them by creationDatTime
        questionList.sort(Comparator.comparing(Question::getCreationDateTime).reversed());

        return questionList.stream()
                .map(QuestionDTO::ofEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public QuestionDTO addQuestion(QuestionDTO questionDTO) {
        Question question = QuestionDTO.ofDTO(questionDTO);
        QuestionDTO output = QuestionDTO.ofEntity(repositoryFactory.createQuestionRepository().save(question));
        eventPublisher.publishEvent(new QuestionCreatedEvent(output));
        return output;
    }

    @Transactional
    public QuestionDTO updateQuestion(Question question) {
        return QuestionDTO.ofEntity(repositoryFactory.createQuestionRepository().save(question));
    }

    @Transactional
    public List<QuestionDTO> listQuestionsByTag(String tagName) {
        List<Question> allQuestions = listQuestionsInside();
        List<Question> filteredQuestions = new LinkedList<>();

        for (Question question : allQuestions) {
            for (Tag iteratingTag : question.getTags()) {
                if (iteratingTag.getName().equals(tagName)) {
                    filteredQuestions.add(question);
                    break;
                }
            }
        }

        return filteredQuestions.stream()
                .map(QuestionDTO::ofEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<QuestionDTO> listQuestionsByText(String text) {
        List<Question> allQuestions = listQuestionsInside();
        List<Question> filteredQuestions = new LinkedList<>();

        for (Question question : allQuestions) {
            if (question.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredQuestions.add(question);
            }
        }

        return filteredQuestions.stream()
                .map(QuestionDTO::ofEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public QuestionDTO findQuestionById(Integer id) {
        return QuestionDTO.ofEntity(repositoryFactory.createQuestionRepository().findById(id).orElseThrow(QuestionNotFoundException::new));
    }

    @Transactional
    public void addVote(QuestionVote questionVote) {

        if (questionVote.getUserId().equals(findQuestionByIdInside(questionVote.getQuestionId()).getUserId())) {
            throw new SelfVoteException();
        }

        Question question = findQuestionByIdInside(questionVote.getQuestionId());
        question.setScore(question.getScore() + questionVote.getVote());
        updateQuestion(question);
    }

    @Transactional
    public void removeVote(QuestionVote questionVote) {
        Question question = findQuestionByIdInside(questionVote.getQuestionId());
        question.setScore(question.getScore() - questionVote.getVote());
        updateQuestion(question);
    }

    @Transactional
    public void deleteQuestion(Integer questionId) {
        Question question = findQuestionByIdInside(questionId);
        repositoryFactory.createQuestionRepository().remove(question);
    }

    @Transactional
    public void deleteAll() {
        listQuestions().forEach(questionDTO -> deleteQuestion(questionDTO.getQuestionId()));
    }


    // Methods below are only used within this class

    private List<Question> listQuestionsInside() {
        List<Question> questionList = repositoryFactory.createQuestionRepository().findAll();

        // Order them by creationDatTime
        questionList.sort(Comparator.comparing(Question::getCreationDateTime).reversed());

        return questionList;
    }

    private Question findQuestionByIdInside(Integer id) {
        return repositoryFactory.createQuestionRepository().findById(id).orElseThrow(QuestionNotFoundException::new);
    }

}
